package net.aicoder.epi.util.network;

import org.apache.commons.codec.digest.DigestUtils;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 创建唯一ID类
 * 采用雪花算法ID
 */
public class MakeId {

    //开始该类生成ID的时间截，这一时刻到当前时间所经过的毫秒数，占 41 位（还有一位是符号位，永远为 0）。
    private final long startTime = 1488697858401L;

    //机器id所占的位数
    private long workerIdBits = 5L;

    //数据标识id所占的位数
    private long datacenterIdBits = 5L;

    //支持的最大机器id，结果是31,这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数（不信的话可以自己算一下，记住，计算机中存储一个数都是存储的补码，结果是负数要从补码得到原码）
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);

    //支持的最大数据标识id
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    //序列在id中占的位数
    private long sequenceBits = 12L;

    //机器id向左移12位
    private long workerIdLeftShift = sequenceBits;

    //数据标识id向左移17位
    private long datacenterIdLeftShift = workerIdBits + workerIdLeftShift;

    //时间截向左移5+5+12=22位
    private long timestampLeftShift = datacenterIdBits + datacenterIdLeftShift;

    //生成序列的掩码，这里为1111 1111 1111
    private long sequenceMask = -1 ^ (-1 << sequenceBits);

    private long workerId;

    //同一个时间截内生成的序列数，初始值是0，从0开始
    private long sequence = 0L;

    //上次生成id的时间截
    private long lastTimestamp = -1L;

    public MakeId(long workerId){
        if(workerId < 0 || workerId > maxWorkerId){
            throw new IllegalArgumentException(String.format("workerId[%d] is less than 0 or greater than maxWorkerId[%d].", workerId, maxWorkerId));
        }
        this.workerId = workerId;
    }

    public MakeId(long workerId, long datacenterId){
        if(workerId < 0 || workerId > maxWorkerId){
            throw new IllegalArgumentException(String.format("workerId[%d] is less than 0 or greater than maxWorkerId[%d].", workerId, maxWorkerId));
        }
        if(datacenterId < 0 || datacenterId > maxDatacenterId){
            throw new IllegalArgumentException(String.format("datacenterId[%d] is less than 0 or greater than maxDatacenterId[%d].", datacenterId, maxDatacenterId));
        }
        this.workerId = workerId;
    }

    private synchronized Long nextId(Class<?> clazz){
        String md5 = DigestUtils.md5Hex(clazz.getName());
        int datacenterId = Integer.parseInt(md5.substring(0, 1), 16) + Integer.parseInt(md5.substring(30, 31), 16) - 1;

        long timestamp = timeGen();
        if(timestamp < lastTimestamp){
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        //如果是同一时间生成的，则自增
        if(timestamp == lastTimestamp){
            sequence = (sequence + 1) & sequenceMask;
            if(sequence == 0){
                //生成下一个毫秒级的序列
                timestamp = tilNextMillis();
                //序列从0开始
                sequence = 0L;
            }
        }else{
            //如果发现是下一个时间单位，则自增序列回0，重新自增
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        //看本文第二部分的结构图，移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - startTime) << timestampLeftShift)
                | (datacenterId << datacenterIdLeftShift)
                | (workerId << workerIdLeftShift)
                | sequence;
    }

    protected long tilNextMillis(){
        long timestamp = timeGen();
        if(timestamp <= lastTimestamp){
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen(){
        return System.currentTimeMillis();
    }

    private static MakeId local;

    private static synchronized MakeId getLocalInstance(){
        if(local==null){
            int workId = 1;
            try {
                InetAddress ia = InetAddress.getLocalHost();
                String hostAddress = ia.getHostAddress();
                hostAddress = DigestUtils.md5Hex(hostAddress);
                workId = Integer.parseInt(hostAddress.substring(0, 1), 16) + Integer.parseInt(hostAddress.substring(30, 31), 16);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            local = new MakeId(workId);
        }
        return local;
    }
    
    public static Long newId() {
    	return getLocalInstance().nextId(MakeId.class);
    }

}  