package com.desafio.api.b2w.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.api.b2w.model.Planeta;
import com.desafio.api.b2w.repository.PlanetaRepository;
import com.desafio.api.b2w.service.PlanetaService;
import com.desafio.api.b2w.event.RecursoCriadoEvent;

@RestController
@RequestMapping("/planetas")
public class PlanetaController {

	@Autowired
	PlanetaRepository planetaRepository;

	@Autowired
	PlanetaService planetaService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Planeta> listPlaneta() {
		return planetaService.listarTodos();
	}

	@PostMapping
	public ResponseEntity<Planeta> savePlaneta(@Valid @RequestBody Planeta planeta, HttpServletResponse response) {

		Planeta planetaSalvo = planetaService.criarPlaneta(planeta);
		if (planetaSalvo != null) {
			publisher.publishEvent(new RecursoCriadoEvent(this, response, planeta.getId()));
			return ResponseEntity.status(HttpStatus.CREATED).body(planetaSalvo);
		} else {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Planeta> findById(@PathVariable String id) {
		Planeta planeta = planetaService.buscarpeloId(id);
		return planeta != null ? ResponseEntity.ok(planeta) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleta(@PathVariable String id) {
		planetaRepository.delete(id);
	}

	@RequestMapping(value = "/{nome}/nome", method = RequestMethod.GET)
	public ResponseEntity<List<Planeta>> findByNome(@PathVariable String nome) {
		List<Planeta> listaPlaneta = planetaService.listarPlanetaPorNome(nome);

		if (listaPlaneta != null) {
			return new ResponseEntity<>(listaPlaneta, HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
