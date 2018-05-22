package com.desafio.b2w;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@SpringBootApplication
public class ApiDesafioB2WApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiDesafioB2WApplication.class, args);

	}

}
