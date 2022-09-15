package application.model;

import java.util.*;

public class ArbitroDAO{
	Map<Integer, Arbitro> ColecaoArbitro = new HashMap<Integer, Arbitro>();
	
	public void InserirArbitro(Arbitro Arb) {
		ColecaoArbitro.put(Arb.getCodArbitro(), Arb);
	}
	
	public void RemoverArbitro(int codArbitro) {
		ColecaoArbitro.remove(codArbitro);
	}
	
	public void AtualizarArbitro(int codArbitro, String nome) {
		ColecaoArbitro.get(codArbitro).setNome(nome);
	}
	
	public Map<Integer, Arbitro> ListaArbitro() {
		return ColecaoArbitro;
	}
}