package com.mobicare.backendsilviobassijunior.api.client;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class BlackListClient {

	
	
	private static final String BASE_URL = "https://5e74cb4b9dff120016353b04.mockapi.io/api/v1";
	
	private static final String RESOURCE_PATH_AND_FILTER_PARAM = "/blacklist?filter=";
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	public List<BlackListClientModelResume> findByCpf(String cpf){
		
		URI resourceUri = URI.create(BASE_URL + RESOURCE_PATH_AND_FILTER_PARAM + cpf);
		
		BlackListClientModelResume[] blackListClientModelResume = 
				restTemplate.getForObject(resourceUri, BlackListClientModelResume[].class);
		
		return Arrays.asList(blackListClientModelResume);
		
	}

}
