package com.desafio.api.b2w.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.desafio.api.b2w.model.Planeta;
import com.desafio.api.b2w.repository.PlanetaRepository;

@Service
public class PlanetaService {

	@Autowired
	PlanetaRepository planetaRepository;
	
	private Planeta buscarpeloId(String id) {
		Planeta planetaSalvo = planetaRepository.findOne(id);
		if (planetaSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return planetaSalvo;
	}
	
	public Planeta atualizar(String id, Planeta planeta) {
		Planeta planteSalvo = planetaRepository.findOne(id);
		if(planteSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(planeta, planteSalvo, "id");
		return planetaRepository.save(planteSalvo);
	}
	

}
