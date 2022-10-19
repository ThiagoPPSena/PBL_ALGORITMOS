package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.model.TecnicoDAO;
import application.model.Tecnico;

import java.util.HashMap;
import java.util.Map;

class TecnicoDAOTeste {

	static Tecnico tecnico = new Tecnico("Joao");
	static Tecnico tecnico1 = new Tecnico("Maria");
	static Tecnico tecnico2 = new Tecnico("Enzo");
	static Tecnico tecnico3 = new Tecnico("Tite");
	
	@Test
	void testInserirTecnico() {
		TecnicoDAO tecDAO = new TecnicoDAO();
		int tamanhoEsperado, tamanhoObtido;
		
		//Sem inserir objeto Tecnico na coleção, o tamanho tem que ser 0
		tamanhoEsperado = 0;
		tamanhoObtido = tecDAO.ListaTecnico().size();
		assertEquals(tamanhoEsperado, tamanhoObtido);
		//Inserindo 1 objeto (Logo, o tamanho da coleção desses objetos deve ser igual a 1).
		tecDAO.InserirTecnico(tecnico);
		tamanhoObtido = tecDAO.ListaTecnico().size();
		tamanhoEsperado = 1;
		assertEquals(tamanhoEsperado, tamanhoObtido);
		//Inserindo o mesmo objeto na coleção, o tamanho permanece o mesmo: 1
		tecDAO.InserirTecnico(tecnico);
		tamanhoObtido = tecDAO.ListaTecnico().size();
		assertEquals(tamanhoEsperado, tamanhoObtido);
		//Inserindo mais 3 objeto (Logo, o tamanho da coleção desses objetos deve ser igual a 4).
		tecDAO.InserirTecnico(tecnico1);
		tecDAO.InserirTecnico(tecnico2);
		tecDAO.InserirTecnico(tecnico3);
		tamanhoObtido = tecDAO.ListaTecnico().size();
		tamanhoEsperado = 4;
		assertEquals(tamanhoEsperado, tamanhoObtido);
		
	}
	
	@Test
	void testRemoverTecnico() {
		
		Tecnico tecnicoEsperado, tecnicoObtido;
		TecnicoDAO tecDAO = new TecnicoDAO();
		//Remover técnico sem estar na coleção, retorno é nulo
		tecnicoEsperado = null;
		tecnicoObtido = tecDAO.RemoverTecnico(tecnico.getCodTec());
		assertEquals(tecnicoEsperado, tecnicoObtido);
		//Remover técnico que esteja na coleção, retorna o técnico removido
		tecDAO.InserirTecnico(tecnico);
		tecnicoEsperado = tecnico;
		tecnicoObtido = tecDAO.RemoverTecnico(tecnico.getCodTec());
		assertEquals(tecnicoEsperado, tecnicoObtido);
		//Checar se após a remoção, o técnico foi realmente removido
		tecnicoEsperado = null;
		tecnicoObtido = tecDAO.ListaTecnico().get(tecnico.getCodTec());
		assertEquals(tecnicoEsperado, tecnicoObtido);
		
	}
	
	@Test
	public void testListaTecnico() {
		TecnicoDAO tecDAO = new TecnicoDAO();
		
		//Chamando a função sem nenhum objeto na lista.
		//Fazendo com que o map que deve ser retornado seja um map vazio.
		Map<Integer, Tecnico> ListaObtida = tecDAO.ListaTecnico();
		Map<Integer, Tecnico> ListaEsperada = new HashMap<Integer, Tecnico>();
		assertEquals(ListaEsperada, ListaObtida);
		
		//Adicionando os tecnicos 0 e 2.
		//Fazendo com que o map que deve ser retornado seja {0=tecnico, 2=tecnico2}
		tecDAO.InserirTecnico(tecnico);
		tecDAO.InserirTecnico(tecnico2);
		ListaObtida = tecDAO.ListaTecnico();
		ListaEsperada.put(tecnico.getCodTec(), tecnico);
		ListaEsperada.put(tecnico2.getCodTec(), tecnico2);
		assertEquals(ListaEsperada, ListaObtida);
		
		//Adicionando os jogadores 1 e 3.
		//Fazendo com que o map que deve ser retornado seja {0=tecnico1, 2=tecnico2, 1=tecnico1, 3=tecnico3}
		tecDAO.InserirTecnico(tecnico1);
		tecDAO.InserirTecnico(tecnico3);
		ListaObtida = tecDAO.ListaTecnico();
		ListaEsperada.put(tecnico1.getCodTec(), tecnico1);
		ListaEsperada.put(tecnico3.getCodTec(), tecnico3);
		assertEquals(ListaEsperada, ListaObtida);
	}

}
