package com.backbase.assignment;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class AppConfig {
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public Map<Long, List<Integer>> idToList(){
		return new HashMap<>();
	}

	@Bean
	public Map<String, Long> listStringToId(){
		return new HashMap<>();
	}
}
