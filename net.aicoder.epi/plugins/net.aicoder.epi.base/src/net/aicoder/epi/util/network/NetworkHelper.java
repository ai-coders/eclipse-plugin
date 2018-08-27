package net.aicoder.epi.util.network;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import net.aicoder.tcom.tools.util.JsonUtil;

public class NetworkHelper extends BaseNetwork {

	public static <T> T postForObject(String url,Object request,Class<T> responseType,Object... uriVariables) {
		return restTemplate.postForObject(url, request, responseType, uriVariables);
	}
	
	public static <T> T postForObject(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables) {
		return restTemplate.postForObject(url, request, responseType, uriVariables);
	}
	
	@SuppressWarnings("finally")
	public static <T> T postForObject(String url, Map<String, Object> requestParams, Class<T> responseType) {
		HttpHeaders headers = new HttpHeaders();	
        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        headers.add("RichClient", "1");
        headers.add("Cache-Control", "no-cache");
        if(Lessee.getPresentSession() != null) headers.add("Cookie", "SESSION="+Lessee.getPresentSession());
        String body = JsonUtil.serialize(requestParams);
        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        T postForObject = null;
        try {
        	postForObject = restTemplate.postForObject(url, requestEntity, responseType);        	
        }catch(Exception e) {
        	System.out.println(e.getMessage());
        }finally {
			return postForObject;
		}
//		return postForObject;
	}
	
//	public static <T> T postForObjectLogin(String url, String body, Class<T> responseType) {
//		HttpHeaders headers = new HttpHeaders();		
//        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
//        headers.setContentType(type);
//        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
//        headers.add("RichClient", "1");
//        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
//		return restTemplate.postForObject(url, requestEntity, responseType);
//	}
	
	
//	public static void main(String[] args) {
//		MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
//		Map<String, String> value = new HashMap<>();
//		value.put("username", "admin");
//		value.put("password", "123456");
//		value.put("adfd", "adfaf");
//		System.out.println(JSON.toJSON(value));
//	}
}
