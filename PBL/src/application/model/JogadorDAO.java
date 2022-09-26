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

	public Jogador RemoverJogador(int codJog) {
		return this.ColecaoJog.remove(codJog);
	}

	public Map<Integer, Jogador> ListaJog() {
		return this.ColecaoJog;
	}
}