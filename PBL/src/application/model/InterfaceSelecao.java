package application.model;

import java.util.*;

public interface InterfaceSelecao {
	
	/**
     * Retorna um objeto do tipo Selecao.
     *
     * @param nomeSelecao nome da Seleção
     * @return retorna um objeto do tipo Selecao
     */
	public Selecao BuscarSelecao(int codSel);
	
	/**
     * Insere um objeto do tipo Selecao em uma lista específica.
     *
     * @param Sel objeto do tipo Selecao
     */
	public void InserirSelecao(Selecao Sel);
	
	/**
     * Remove um objeto do tipo Selecao de uma lista específica.
     *
     * @param nomeSelecao nome da Seleção
     */
	public Selecao RemoverSelecao(int codSel);
	
	/**
     * Retorna uma lista de inteiros que são os códigos dos jogadores de uma determinada Seleção.
     *
     * @param nomeSelecao nome da Seleção
     * @return retorna uma lista de inteiros que são os códigos dos jogadores de uma determinada Seleção.
     */
	public List<Integer> CodJogadoresSelecao(int codSel);
	
	/**
     * Retorna uma lista de objetos do tipo Selecao.
     *
     * @return retorna uma lista de objetos do tipo Selecao
     */
	public Map<Integer, Selecao> ListaSelecao();
}