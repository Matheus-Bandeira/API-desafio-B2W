package com.desafio.api.b2w.repository;

import com.desafio.api.b2w.model.Planeta;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class PlanetaRepositoryTests {

	@Autowired
	PlanetaRepository planetaRepository;

	private String id1 = "1";
	private String nome1 = "Nome 1";
	private String clima1 = "Clima 1";
	private String terreno1 = "Terreno 1";
	private int qtd1 = 1;
	private Planeta planeta1 = new Planeta(id1, nome1, clima1, terreno1, qtd1);

	private String id2 = "2";
	private String nome2 = "Nome 2";
	private String clima2 = "Clima 2";
	private String terreno2 = "Terreno 2";
	private int qtd2 = 2;
	private Planeta planeta2 = new Planeta(id2, nome2, clima2, terreno2, qtd2);

	@Test
	public void econtrarPorNome() {
		planetaRepository.save(planeta1);
		planetaRepository.save(planeta2);

		List<Planeta> listaPlaneta = planetaRepository.findByNomeLikeIgnoreCase(nome1);
		assertThat(listaPlaneta).hasSize(1).extracting("nome").contains(nome1);
	}

	@Test
	public void encontrarPorNomeMultiplosResultados() {
		planetaRepository.save(planeta1);
		planetaRepository.save(planeta2);

		List<Planeta> listaPlaneta = planetaRepository.findByNomeLikeIgnoreCase("nome");
		assertEquals(listaPlaneta.size(), 2);
	}
}
