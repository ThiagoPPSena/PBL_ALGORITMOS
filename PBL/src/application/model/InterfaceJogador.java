package application.model;

import java.util.*;

public interface InterfaceJogador {
	public Jogador BuscarJogador(int codJog);
	public void InserirJogador(Jogador jog);
	public Jogador RemoverJogador(int codJog);
	public Map<Integer, Jogador> ListaJog();
}