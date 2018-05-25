package com.desafio.api.b2w;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map.Entry;
import java.util.Set;

@SpringBootApplication
public class ApiDesafioB2WApplication {

	// public static final String API_URL = "https://swapi.co/api/planets/1/";

	public static void main(String[] args) {
		SpringApplication.run(ApiDesafioB2WApplication.class, args);

	}
//
//	 @Override
//	 public void run(String... arg0) throws Exception {
//	 RestTemplate restTemplate = new RestTemplate();
//	 HttpHeaders headers = new HttpHeaders();
//	 headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
//	 headers.add("user-agent", "Chrome/54.0.2840.99");
//	 HttpEntity<?> entity = new HttpEntity<>(headers);
//	 HttpEntity<Planet> response = restTemplate.exchange(
//	 API_URL,
//	 HttpMethod.GET,
//	 entity,
//	 Planet.class);
//	 Planet planet = response.getBody();
//	 System.out.println(planet);
//	
//	 }

}