package application.model;

import java.util.*;

public class JogadorDAO implements InterfaceJogador{

	private Map<Integer, Jogador> ColecaoJog = new HashMap<Integer, Jogador>();
	
	public Jogador BuscarJogador(int codJog) {
		return ColecaoJog.get(codJog);
	}

	public void InserirJogador(Jogador jog) {
		ColecaoJog.put(jog.getCodJog(), jog);
	}

	public void RemoverJogador(Jogador jog) {
		ColecaoJog.remove(jog.getCodJog());
	}

	public Map<Integer, Jogador> ListaJog() {
		return ColecaoJog;
	}
}