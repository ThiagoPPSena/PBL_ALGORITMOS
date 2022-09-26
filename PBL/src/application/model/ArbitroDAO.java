package application.model;

import java.util.*;

public class ArbitroDAO implements InterfaceArbitro{
	
	private Map<Integer, Arbitro> ColecaoArbitro = new HashMap<Integer, Arbitro>();
	
	public void InserirArbitro(Arbitro Arb) {
		this.ColecaoArbitro.put(Arb.getCodArbitro(), Arb);
	}
	
	public Arbitro RemoverArbitro(int codArbitro) {
		return this.ColecaoArbitro.remove(codArbitro);
	}
	
	public Map<Integer, Arbitro> ListaArbitro() {
		return this.ColecaoArbitro;
	}
	
}