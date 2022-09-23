package application.model;

import java.util.*;

public class JogadorDAO implements InterfaceJogador{

	private Map<Integer, Jogador> ColecaoJog = new HashMap<Integer, Jogador>();
	
	public Jogador BuscarJogador(int codJog) {
		return this.ColecaoJog.get(codJog);
	}

	public void InserirJogador(Jogador jog) {
		this.ColecaoJog.put(jog.getCodJog(), jog);
	}

	public void RemoverJogador(int codJog) {
		this.ColecaoJog.remove(codJog);
	}

	public Map<Integer, Jogador> ListaJog() {
		return this.ColecaoJog;
	}
}