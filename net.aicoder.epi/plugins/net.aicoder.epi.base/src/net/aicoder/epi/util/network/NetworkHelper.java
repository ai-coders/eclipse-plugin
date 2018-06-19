package net.aicoder.epi.util.network;

import java.util.Map;

public class NetworkHelper extends BaseNetwork {

	public static <T> T postForObject(String url,Object request,Class<T> responseType,Object... uriVariables) {
		return restTemplate.postForObject(url, request, responseType, uriVariables);
	}
	
	public static <T> T postForObject(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables) {
		return restTemplate.postForObject(url, request, responseType, uriVariables);
	}
	
	public static <T> T postForObject(String url, Object request, Class<T> responseType) {
		return restTemplate.postForObject(url, request, responseType);
	}
}
