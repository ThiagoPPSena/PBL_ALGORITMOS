package application.model;

import java.util.*;

import execoes.LimiteSelecoesException;

public interface InterfaceSelecao {
	
	/**
     * Retorna um objeto do tipo Selecao.
     *
     * @param codSel código da Selecao
     * @return retorna um objeto do tipo Selecao
     */
	public Selecao BuscarSelecao(int codSel);
	
	/**
     * Insere um objeto do tipo Selecao em uma lista específica.
     *
     * @param Sel objeto do tipo Selecao
     */
	public void InserirSelecao(Selecao Sel) throws LimiteSelecoesException;
	
	/**
     * Remove um objeto do tipo Selecao de uma lista específica, e retorna-o.
     *
     * @param codSel código da Selecao
     * @return objeto do tipo Selecao
     */
	public Selecao RemoverSelecao(int codSel);
	
	/**
     * Retorna uma lista de inteiros que são os códigos dos jogadores de uma determinada Seleção.
     *
     * @param codSel código da Selecao
     * @return retorna uma lista de inteiros que são os códigos dos jogadores de uma determinada Seleção.
     */
	public List<Integer> CodJogadoresSelecao(int codSel);
	
	/**
     * Retorna um Map de objetos do tipo Selecao.
     *
     * @return retorna um Map de objetos do tipo Selecao
     */
	public Map<Integer, Selecao> ListaSelecao();
}