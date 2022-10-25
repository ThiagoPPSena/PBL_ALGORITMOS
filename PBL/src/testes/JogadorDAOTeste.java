package testes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import application.model.Jogador;
import application.model.JogadorDAO;

class JogadorDAOTeste {

	static Jogador jogador = new Jogador("Joao", "ATACANTE");
	static Jogador jogador1 = new Jogador("Maria", "MEIA");
	static Jogador jogador2 = new Jogador("Enzo", "GOLEIRO");
	static Jogador jogador3 = new Jogador("Tite", "ZAGUEIRO");
	
	@Test
	public void testBuscarJogador() {
		//Buscando um objeto que está armazenado no DAO (Logo, deve-se retornar o objeto).
		JogadorDAO jogDAO = new JogadorDAO();
		jogDAO.InserirJogador(jogador1);
		Jogador retorno = jogDAO.BuscarJogador(jogador1.getCodJog());
		assertEquals(jogador1, retorno);
		
		//Buscando um objeto que não está armazenado no DAO (Logo, deve-se retornar null).
		retorno = jogDAO.BuscarJogador(jogador2.getCodJog());
		assertEquals(null, retorno);
	}
	
	@Test
	void testInserirJogador() {
		JogadorDAO jogDAO = new JogadorDAO();
		int tamanhoEsperado, tamanhoObtido;
		
		//Sem inserir objeto Jogador na coleção, o tamanho tem que ser 0
		tamanhoEsperado = 0;
		tamanhoObtido = jogDAO.ListaJog().size();
		assertEquals(tamanhoEsperado, tamanhoObtido);
		//Inserindo 1 objeto (Logo, o tamanho da coleção desses objetos deve ser igual a 1).
		jogDAO.InserirJogador(jogador);
		tamanhoObtido = jogDAO.ListaJog().size();
		tamanhoEsperado = 1;
		assertEquals(tamanhoEsperado, tamanhoObtido);
		//Inserindo o mesmo objeto na coleção, o tamanho permanece o mesmo: 1
		jogDAO.InserirJogador(jogador);
		tamanhoObtido = jogDAO.ListaJog().size();
		assertEquals(tamanhoEsperado, tamanhoObtido);
		//Inserindo mais 3 objeto (Logo, o tamanho da coleção desses objetos deve ser igual a 4).
		jogDAO.InserirJogador(jogador1);
		jogDAO.InserirJogador(jogador2);
		jogDAO.InserirJogador(jogador3);
		tamanhoObtido = jogDAO.ListaJog().size();
		tamanhoEsperado = 4;
		assertEquals(tamanhoEsperado, tamanhoObtido);
		
	}
	
	@Test
	void testRemoverJogador() {
		
		Jogador jogadorEsperado, jogadorObtido;
		JogadorDAO jogDAO = new JogadorDAO();
		//Remover jogador sem estar na coleção, retorno é nulo
		jogadorEsperado = null;
		jogadorObtido = jogDAO.RemoverJogador(jogador.getCodJog());
		assertEquals(jogadorEsperado, jogadorObtido);
		//Remover jogador que esteja na coleção, retorna o jogador removido
		jogDAO.InserirJogador(jogador);
		jogadorEsperado = jogador;
		jogadorObtido = jogDAO.RemoverJogador(jogador.getCodJog());
		assertEquals(jogadorEsperado, jogadorObtido);
		//Checar se após a remoção, o jogador foi realmente removido
		jogadorEsperado = null;
		jogadorObtido = jogDAO.ListaJog().get(jogador.getCodJog());
		assertEquals(jogadorEsperado, jogadorObtido);
		
	}
	
	@Test
	public void testListaJog() {
		
		JogadorDAO jogDAO = new JogadorDAO();
		
		//Chamando a função sem nenhum objeto na lista.
		//Fazendo com que o map que deve ser retornado seja um map vazio.
		Map<Integer, Jogador> ListaObtida = jogDAO.ListaJog();
		Map<Integer, Jogador> ListaEsperada = new HashMap<Integer, Jogador>();
		assertEquals(ListaEsperada, ListaObtida);
		
		//Adicionando os jogadors 0 e 2.
		//Fazendo com que o map que deve ser retornado seja {0=jogador, 2=jogador2}
		jogDAO.InserirJogador(jogador);
		jogDAO.InserirJogador(jogador2);
		ListaObtida = jogDAO.ListaJog();
		ListaEsperada.put(jogador.getCodJog(), jogador);
		ListaEsperada.put(jogador2.getCodJog(), jogador2);
		assertEquals(ListaEsperada, ListaObtida);
		
		//Adicionando os jogadores 1 e 3.
		//Fazendo com que o map que deve ser retornado seja {0=jogador1, 2=jogador2, 1=jogador1, 3=jogador3}
		jogDAO.InserirJogador(jogador1);
		jogDAO.InserirJogador(jogador3);
		ListaObtida = jogDAO.ListaJog();
		ListaEsperada.put(jogador1.getCodJog(), jogador1);
		ListaEsperada.put(jogador3.getCodJog(), jogador3);
		assertEquals(ListaEsperada, ListaObtida);
	}


}