package com.desafio.b2w.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.desafio.b2w.model.Planeta;

public interface PlanetaRepository extends MongoRepository<Planeta, String> {

	public List<Planeta> findByNomeLikeIgnoreCase(String nome);
}
