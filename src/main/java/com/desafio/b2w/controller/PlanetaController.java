package com.desafio.b2w.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.b2w.model.Planeta;
import com.desafio.b2w.repository.PlanetaRepository;

@RestController
@RequestMapping("/planetas")
public class PlanetaController {

	@Autowired
	PlanetaRepository planetaRepository;

	@GetMapping
	public List<Planeta> listPlaneta() {
		return this.planetaRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<Planeta> savePlaneta(@Valid @RequestBody Planeta planeta) {
		//return this.planetaRepository.save(planeta);
		Planeta planetaSalvo = planetaRepository.save(planeta);
		return ResponseEntity.status(HttpStatus.CREATED).body(planetaSalvo);
	}

	@RequestMapping(value = "/planeta/{id}", method = RequestMethod.GET)
	public Planeta findById(@PathVariable String id) {
		return this.planetaRepository.findOne(id);
	}


	@RequestMapping(value = "/planeta/{nome}/nome", method = RequestMethod.GET)
	public List<Planeta> findByNome(@PathVariable String nome) {
		return this.planetaRepository.findByNomeLikeIgnoreCase(nome);
	}
}
