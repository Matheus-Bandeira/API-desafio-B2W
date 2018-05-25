package com.desafio.api.b2w.facade;

import com.desafio.api.b2w.model.Planeta;
import com.desafio.api.b2w.repository.PlanetaRepository;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

@Service
public class PlanetaFacade {

	RestTemplate restTemplate = new RestTemplate();
	HttpHeaders headers = new HttpHeaders();
	List<Planeta> planetasBanco = new ArrayList<>();
	
	@Autowired
	PlanetaRepository planetaRepository;

	private static final String URL_API_SWAPI_FILM = "https://swapi.co/api/planets/";

	@PostConstruct
	private void init() {
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
	}

	public Object findPlanetByName(String name) {
		Object objectReturned = restTemplate.exchange(URL_API_SWAPI_FILM + "?search=" + name, HttpMethod.GET,
				new HttpEntity<String>("parameters", headers), Object.class);

		System.out.println("Número de filmes que o planeta participou: " + qtsFilms(objectReturned));
		planetasBanco = planetaRepository.findAll();
		for(Planeta planeta: planetasBanco) {
			if(planeta.getNome().equals(name)) {
				planeta.setQuantidadeAparicoes(qtsFilms(objectReturned));
			}
			
			System.out.println("Nome do planeta: " + planeta.getNome() + " Numero de aparições: " + planeta.getQuantidadeAparicoes());
		}
		
		return objectReturned;
	}

	public Integer qtsFilms(Object object) {
		Gson gson = new Gson();
		JsonElement element = gson.fromJson(gson.toJson(object), JsonObject.class).getAsJsonObject("body")
				.getAsJsonArray("results").get(0);
		return ((JsonObject) element).getAsJsonArray("films").size();
	}

	public Object findPlanetById(String id) {
		Object objectReturned = restTemplate.exchange(URL_API_SWAPI_FILM + id + "/", HttpMethod.GET,
				new HttpEntity<String>("parameters", headers), Object.class);

		return objectReturned;
	}
}
