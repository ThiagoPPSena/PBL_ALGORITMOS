package application.model;

import java.util.*;

public interface InterfaceJogador {
	
	/**
     * Retorna um objeto do tipo Jogador.
     *
     * @param codJog código do Jogador
     * @return retorna um objeto do tipo Jogador
     */
	public Jogador BuscarJogador(int codJog);
	
	/**
     * Insere um objeto do tipo Jogador em um Map específico.
     *
     * @param jog objeto do tipo Jogador
     */
	public void InserirJogador(Jogador jog);
	
	/**
     * Remove um objeto do tipo Jogador de um Map específico, e retorna-o.
     *
     * @param codJog código do Jogador
     * @return objeto do tipo Jogador
     */
	public Jogador RemoverJogador(int codJog);
	
	/**
     * Retorna um Map de objetos do tipo Jogador.
     *
     * @return retorna um Map de objetos do tipo Jogador
     */
	public Map<Integer, Jogador> ListaJog();
}