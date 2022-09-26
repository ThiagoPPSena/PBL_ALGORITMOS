package application.model;

import java.util.*;

public interface InterfaceArbitro {
	public void InserirArbitro(Arbitro Arb);
	public Arbitro RemoverArbitro(int codArbitro);
	public Map<Integer, Arbitro> ListaArbitro();
} 