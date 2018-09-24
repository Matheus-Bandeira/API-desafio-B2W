package com.desafio.api.b2w.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.desafio.api.b2w.model.Planeta;
import com.desafio.api.b2w.repository.PlanetaRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Service
public class PlanetaService {

	@Autowired
	PlanetaRepository planetaRepository;

	private static final String SWAPI_URL = "https://swapi.co/api/planets/?search=";

	public Planeta criarPlaneta(Planeta planeta) {
		Integer qtd = this.obterQtdAparicoes(planeta.getNome());

		if (qtd == null) {
			return null;
		}

		planeta.setQuantidadeAparicoes(qtd);
		return planetaRepository.save(planeta);
	}

	public List<Planeta> listarTodos() {
		return planetaRepository.findAll();
	}

	public Planeta buscarpeloId(String id) {
		Planeta planetaSalvo = planetaRepository.findOne(id);
		if (planetaSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return planetaSalvo;
	}

	public Planeta atualizar(String id, Planeta planeta) {
		Planeta planteSalvo = planetaRepository.findOne(id);
		if (planteSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(planeta, planteSalvo, "id");
		return planetaRepository.save(planteSalvo);
	}

	public List<Planeta> listarPlanetaPorNome(String nome) {
		return planetaRepository.findByNomeLikeIgnoreCase(nome);
	}

	private Integer obterQtdAparicoes(String name) {
		StringBuilder completeUrl = new StringBuilder();
		completeUrl.append(SWAPI_URL).append(name);

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

		Object object;
		try {
			object = restTemplate.exchange(completeUrl.toString(), HttpMethod.GET,
					new HttpEntity<String>("parameters", headers), Object.class);
		} catch (Exception e) {
			return null;
		}

		Gson gson = new Gson();
		JsonArray results = gson.fromJson(gson.toJson(object), JsonObject.class).getAsJsonObject("body")
				.getAsJsonArray("results");

		JsonElement correctResult = null;

		for (JsonElement e : results) {
			if (e.getAsJsonObject().get("name").getAsString().equalsIgnoreCase(name)) {
				correctResult = e;
			}
		}

		if (correctResult == null) {
			return 0;
		} else {
			return correctResult.getAsJsonObject().getAsJsonArray("films").size();
		}
	}

}
