package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.JogPartida;
import application.model.Partida;

class PartidaTest {

	private static Partida partida = new Partida();
	
	@BeforeEach
	public void criarJogadores() {
		//Pega os jogadores da partida e seta o código deles
		for(int i=0;i<partida.getJogadores().size(); i++) {
			partida.getJogadores().get(i).setCodJogador(i);
		}
	}
	
	@Test
	void buscarJogPartidaTest() {
		//Compara o jogador da partida com o código 2 com o retorno da busca
		JogPartida jogadorEsperado = partida.getJogadores().get(2);
		JogPartida jogadorObtido = partida.buscarJogPartida(2);
		assertEquals(jogadorEsperado, jogadorObtido);
		
		//Busca um jogador com o código inválido, então o retorno e nulo
		jogadorEsperado = null;
		jogadorObtido = partida.buscarJogPartida(22);
		assertEquals(jogadorEsperado, jogadorObtido);
	}

}
