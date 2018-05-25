package com.desafio.api.b2w.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.desafio.api.b2w.facade.PlanetaFacade;

import java.util.Arrays;

@RestController
@RequestMapping("/swapi")
public class SwapiController {

    @Autowired
    private PlanetaFacade planetaFacade;

    @GetMapping("/{nomePlaneta}")
	public ResponseEntity<Object> buscarPlanetas(@PathVariable("nomePlaneta") String nomePlaneta){
	    return ResponseEntity.ok(planetaFacade.findPlanetByName(nomePlaneta));
    }
    
}
