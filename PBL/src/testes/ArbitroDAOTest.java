package testes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import application.model.Arbitro;
import application.model.ArbitroDAO;

class ArbitroDAOTest {

	private static Arbitro arbitro1 = new Arbitro("Wilton");
	private static Arbitro arbitro2 = new Arbitro("Neuza");
	private static Arbitro arbitro3 = new Arbitro("Sandro");
	private static Arbitro arbitro4 = new Arbitro("Anderson");
	
	@Test
	void testInserirArbitro() {
		//Inserindo apenas 1 objeto (Logo, o tamanho da coleção desses objetos deve ser igual a 1).
		ArbitroDAO arbitroDAO = new ArbitroDAO();
		arbitroDAO.InserirArbitro(arbitro1);
		int retorno = arbitroDAO.ListaArbitro().size();
		assertEquals(1, retorno);
		
		//Inserindo mais 2 objetos (Logo, o tamanho da coleção desses objetos deve ser igual a 3).
		arbitroDAO.InserirArbitro(arbitro2);
		arbitroDAO.InserirArbitro(arbitro3);
		retorno = arbitroDAO.ListaArbitro().size();
		assertEquals(3, retorno);
		
		//Inserindo mais 1 objeto (Logo, o tamanho da coleção desses objetos deve ser igual a 4).
		arbitroDAO.InserirArbitro(arbitro4);
		retorno = arbitroDAO.ListaArbitro().size();
		assertEquals(4, retorno);
	}

	@Test
	void testRemoverArbitro() {
		//Removendo objeto que não está armazenado no DAO (Logo, deve-se retornar null).
		ArbitroDAO arbitroDAO = new ArbitroDAO();
		Arbitro retorno = arbitroDAO.RemoverArbitro(arbitro1.getCodArbitro());
		assertEquals(null, retorno);
		
		//Removendo objeto que está armazenado no DAO (Logo, deve-se retornar o objeto).
		arbitroDAO.InserirArbitro(arbitro1);
		retorno = arbitroDAO.RemoverArbitro(arbitro1.getCodArbitro());
		assertEquals(arbitro1, retorno);
		
		//Verificando se o objeto ainda está armazenado no DAO (Como não está, a busca deve retornar null).
		retorno = arbitroDAO.ListaArbitro().get(arbitro1.getCodArbitro());
		assertEquals(null, retorno);
	}

	@Test
	void testListaArbitro() {
		ArbitroDAO arbitroDAO = new ArbitroDAO();
		
		//Chamando a função sem nenhum objeto na lista.
		//Fazendo com que o map que deve ser retornado seja um map vazio.
		Map<Integer, Arbitro> retorno = arbitroDAO.ListaArbitro();
		Map<Integer, Arbitro> esperado = new HashMap<Integer, Arbitro>();
		assertEquals(esperado, retorno);
		
		//Adicionando os arbitros 1 e 4.
		//Fazendo com que o map que deve ser retornado seja {0=arbitro1, 3=arbitro4}
		arbitroDAO.InserirArbitro(arbitro1);
		arbitroDAO.InserirArbitro(arbitro4);
		retorno = arbitroDAO.ListaArbitro();
		esperado.put(arbitro1.getCodArbitro(), arbitro1);
		esperado.put(arbitro4.getCodArbitro(), arbitro4);
		assertEquals(esperado, retorno);
		
		//Adicionando os arbitros 2 e 3.
		//Fazendo com que o map que deve ser retornado seja {0=arbitro1, 3=arbitro4, 1=arbitro2, 2=arbitro3}
		arbitroDAO.InserirArbitro(arbitro2);
		arbitroDAO.InserirArbitro(arbitro3);
		retorno = arbitroDAO.ListaArbitro();
		esperado.put(arbitro2.getCodArbitro(), arbitro2);
		esperado.put(arbitro3.getCodArbitro(), arbitro3);
		assertEquals(esperado, retorno);
	}

}
