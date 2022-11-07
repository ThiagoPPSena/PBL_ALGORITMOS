package testes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.model.Arbitro;
import application.model.ArbitroDAO;
import application.model.FaseGrupo;
import application.model.Selecao;
import execoes.MinimoArbitrosException;
import execoes.QuantidadeSelecoesIncompletaException;

class FaseDeGrupoTest {
	
	private static ArbitroDAO arbitroDAO = new ArbitroDAO();
	private static Map<Integer, Selecao> selecoes = new HashMap<Integer, Selecao>();
	private static FaseGrupo faseGrupo = new FaseGrupo();
	
	@Test
	void iniciarFaseTest() throws QuantidadeSelecoesIncompletaException, MinimoArbitrosException {
		
		//Tenta iniciar a fase de grupos sem árbitro
		try {
			faseGrupo.iniciarFase(arbitroDAO);
		}catch (MinimoArbitrosException e){
			assertEquals("Mínimo de árbitros não atingido!!! Cadastre pelo pelo menos 1 no sistema para iniciar a fase de grupos.", e.getMessage());
		}
		
		//Cria um árbitro
		Arbitro arbitro = new Arbitro("Daronco");
		arbitroDAO.InserirArbitro(arbitro);
		
		//Tenta iniciar a fase agora sem ter as 32 seleções
		try {
			faseGrupo.iniciarFase(arbitroDAO);
		}catch (QuantidadeSelecoesIncompletaException e){
			assertEquals("Quantidade de Seleções incompleta para iniciar a fase de grupos.", e.getMessage());
		}
		//Cria as 32 seleções
		for(int i=0;i<32;i++) {
			selecoes.put(i, null);
		}
		//Tenta iniciar a fase e consegue
		faseGrupo.updateSelecoes(selecoes);
		faseGrupo.iniciarFase(arbitroDAO);
		assertEquals(true, faseGrupo.isFaseIniciada());
		
	}

}
