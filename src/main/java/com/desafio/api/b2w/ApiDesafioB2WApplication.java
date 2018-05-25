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

	public static void main(String[] args) {
		SpringApplication.run(ApiDesafioB2WApplication.class, args);

	}

}