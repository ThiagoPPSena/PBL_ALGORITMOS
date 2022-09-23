package application.model;

import java.util.*;

public class ArbitroDAO{
	
	private Map<Integer, Arbitro> ColecaoArbitro = new HashMap<Integer, Arbitro>();
	
	public void InserirArbitro(Arbitro Arb) {
		this.ColecaoArbitro.put(Arb.getCodArbitro(), Arb);
	}
	
	public void RemoverArbitro(int codArbitro) {
		this.ColecaoArbitro.remove(codArbitro);
	}
	
	public Map<Integer, Arbitro> ListaArbitro() {
		return this.ColecaoArbitro;
	}
	
}