package application.model;

import java.util.*;

public interface InterfaceArbitro {
	public void InserirArbitro(Arbitro Arb);
	public void RemoverArbitro(int codArbitro);
	public void AtualizarArbitro(int codArbitro, String nome);
	public Map<Integer, Arbitro> ListaArbitro();
}