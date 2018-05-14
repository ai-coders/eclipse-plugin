package net.aicoder.epi.base.model;

public enum StateFlagEnum {
    // Possible state transitions ( OLD STATE - action - NEW STATE )
    // load - ORIGINAL
    // insert - INSERTED
    // ORIGINAL - update - UPDATED
    // ORIGINAL - delete - DELETED
    // UPDATED - delete - DELETED
    // INSERTED - delete
    // UPDATED - save - ORIGINAL
    // INSERTED - save - ORIGINAL
    // INSERTED - revert
    // UPDATED - revert - ORIGINAL
    // DELETED - revert - ORIGINAL
	
	// 记录状态
	ORIGINAL(0), // 原始状态
	INSERTED(10), // 新增(插入)
	UPDATED(20), // 修改
	DELETED(30), // 删除
	//CHANGED(90), // 变更，当前记录没有变化，但是子记录或记录结构有变化
	;
	private final int flag;

	private StateFlagEnum(int flag) {
		this.flag = flag;
	}
	
    public static StateFlagEnum forInt(int flag) {
        for (StateFlagEnum type : values()) {
            if (type.flag == flag) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid ActionFlag: " + flag);
    }
	
	public int getStateFlag() {
		return flag;
	}
}
