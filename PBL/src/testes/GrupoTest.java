package testes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.Grupo;

class GrupoTest {
	
	private static List<Integer> selecoes = new ArrayList<Integer>();
	
	@BeforeEach
	public void adcionarSelecoes() {
		//Adiciona 4 códigos de seleções
		selecoes.add(0);
		selecoes.add(1);
		selecoes.add(2);
		selecoes.add(3);
	}

	@Test
	void buscarPartidaTest() {
		
		Grupo grupo = new Grupo(selecoes);
		
		//Faz uma busca pelo código existente 0, e tem o mesmo retorno do index esperado
		int indexEsperado = 0;    
		int indexObtido = grupo.buscarPartida(0);
		assertEquals(indexEsperado, indexObtido);
		
		//Faz uma busca por um código inválido 6 e tem como retorno -1 por não ter encontrado a partida
		indexEsperado = -1;    
		indexObtido = grupo.buscarPartida(6);
		assertEquals(indexEsperado, indexObtido);
	}

}
