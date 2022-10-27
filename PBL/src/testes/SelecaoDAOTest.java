package testes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import application.model.Jogador;
import application.model.Selecao;
import application.model.SelecaoDAO;
import execoes.LimiteSelecoesException;

class SelecaoDAOTest {

	private static Selecao selecao1 = new Selecao("Brasil");
	private static Selecao selecao2 = new Selecao("Argentina");
	private static Selecao selecao3 = new Selecao("Dinamarca");
	private static Selecao selecao4 = new Selecao("França");
	
	@Test
	public void testBuscarSelecao() throws LimiteSelecoesException {
		//Buscando um objeto que está armazenado no DAO (Logo, deve-se retornar o objeto).
		SelecaoDAO selecaoDAO = new SelecaoDAO();
		selecaoDAO.InserirSelecao(selecao1);
		Selecao retorno = selecaoDAO.BuscarSelecao(selecao1.getCodSel());
		assertEquals(selecao1, retorno);
		
		//Buscando um objeto que não está armazenado no DAO (Logo, deve-se retornar null).
		retorno = selecaoDAO.BuscarSelecao(selecao2.getCodSel());
		assertEquals(null, retorno);
	}
	
	@Test
	public void testInserirSelecao() throws LimiteSelecoesException {
		//Inserindo apenas 1 objeto (Logo, o tamanho da coleção desses objetos deve ser igual a 1).
		SelecaoDAO selecaoDAO = new SelecaoDAO();
		selecaoDAO.InserirSelecao(selecao1);
		int retorno = selecaoDAO.ListaSelecao().size();
		assertEquals(1, retorno);
		
		//Inserindo mais 2 objetos (Logo, o tamanho da coleção desses objetos deve ser igual a 3).
		selecaoDAO.InserirSelecao(selecao2);
		selecaoDAO.InserirSelecao(selecao3);
		retorno = selecaoDAO.ListaSelecao().size();
		assertEquals(3, retorno);
		
		//Inserindo mais 1 objeto (Logo, o tamanho da coleção desses objetos deve ser igual a 4).
		selecaoDAO.InserirSelecao(selecao4);
		retorno = selecaoDAO.ListaSelecao().size();
		assertEquals(4, retorno);
	}

	@Test
	public void testRemoverSelecao() throws LimiteSelecoesException {
		//Removendo objeto que não está armazenado no DAO (Logo, deve-se retornar null).
		SelecaoDAO selecaoDAO = new SelecaoDAO();
		Selecao retorno = selecaoDAO.RemoverSelecao(selecao1.getCodSel());
		assertEquals(null, retorno);
		
		//Removendo objeto que está armazenado no DAO (Logo, deve-se retornar o objeto).
		selecaoDAO.InserirSelecao(selecao1);
		retorno = selecaoDAO.RemoverSelecao(selecao1.getCodSel());
		assertEquals(selecao1, retorno);
		
		//Verificando se o objeto ainda está armazenado no DAO (Como não está, a busca deve retornar null).
		retorno = selecaoDAO.BuscarSelecao(selecao1.getCodSel());
		assertEquals(null, retorno);
	}
	
	@Test
	public void testCodJogadoresSelecao() throws LimiteSelecoesException {
		SelecaoDAO selecaoDAO = new SelecaoDAO();
		selecaoDAO.InserirSelecao(selecao1);
		Jogador jogador1 = new Jogador("Alisson", "Goleiro");
		Jogador jogador2 = new Jogador("Thiago Silva", "Zagueiro");
		Jogador jogador3 = new Jogador("Casemiro", "Meia");
		Jogador jogador4 = new Jogador("Paquetá", "Meia");
		Jogador jogador5 = new Jogador("Neymar", "Atacante");
		Jogador jogador6 = new Jogador("Firmino", "Atacante");
		
		//Adicionando os jogadores 1, 3 e 6 que tem seus códigos, respectivamente: 0, 2, 5.
		//Fazendo com que a lista que deve ser retornada seja [0, 2, 5].
		selecao1.getListaCodJog().add(jogador1.getCodJog());
		selecao1.getListaCodJog().add(jogador3.getCodJog());
		selecao1.getListaCodJog().add(jogador6.getCodJog());
		List<Integer> esperado = Arrays.asList(0, 2, 5);
		List<Integer> retorno = selecaoDAO.CodJogadoresSelecao(selecao1.getCodSel());
		assertEquals(esperado, retorno);
		
		//Adicionando os jogadores 2, 4 e 5 que tem seus códigos, respectivamente: 1, 3, 4.
		//Fazendo com que a lista que deve ser retornada seja [0, 2, 5, 1, 3, 4].
		selecao1.getListaCodJog().add(jogador2.getCodJog());
		selecao1.getListaCodJog().add(jogador4.getCodJog());
		selecao1.getListaCodJog().add(jogador5.getCodJog());
		esperado = Arrays.asList(0, 2, 5, 1, 3, 4);
		retorno = selecaoDAO.CodJogadoresSelecao(selecao1.getCodSel());
		assertEquals(esperado, retorno);
	}
	
	@Test
	public void testListaSelecao() throws LimiteSelecoesException {
		SelecaoDAO selecaoDAO = new SelecaoDAO();
		
		//Chamando a função sem nenhum objeto na lista.
		//Fazendo com que o map que deve ser retornado seja um map vazio.
		Map<Integer, Selecao> retorno = selecaoDAO.ListaSelecao();
		Map<Integer, Selecao> esperado = new HashMap<Integer, Selecao>();
		assertEquals(esperado, retorno);
		
		//Adicionando os jogadores 1 e 4.
		//Fazendo com que o map que deve ser retornado seja {0=selecao1, 3=selecao4}
		selecaoDAO.InserirSelecao(selecao1);
		selecaoDAO.InserirSelecao(selecao4);
		retorno = selecaoDAO.ListaSelecao();
		esperado.put(selecao1.getCodSel(), selecao1);
		esperado.put(selecao4.getCodSel(), selecao4);
		assertEquals(esperado, retorno);
		
		//Adicionando os jogadores 2 e 3.
		//Fazendo com que o map que deve ser retornado seja {0=selecao1, 3=selecao4, 1=selecao2, 2=selecao3}
		selecaoDAO.InserirSelecao(selecao2);
		selecaoDAO.InserirSelecao(selecao3);
		retorno = selecaoDAO.ListaSelecao();
		esperado.put(selecao2.getCodSel(), selecao2);
		esperado.put(selecao3.getCodSel(), selecao3);
		assertEquals(esperado, retorno);
	}

}
