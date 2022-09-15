package application.model;

import java.util.*;

public interface InterfaceSelecao {
	
	
	/**
     * Retorna um objeto do tipo Selecao.
     *
     * @param nomeSelecao nome da Seleção
     * @return retorna um objeto do tipo Selecao
     */
	public Selecao BuscarSelecao (String nomeSelecao);
	
	/**
     * Insere um objeto do tipo Selecao em uma lista específica.
     *
     * @param Sel Objeto do tipo Selecao
     */
	public void InserirSelecao(Selecao Sel);
	
	/**
     * Remove um objeto do tipo Selecao de uma lista específica.
     *
     * @param nomeSelecao nome da Seleção
     */
	public void RemoverSelecao(String nomeSelecao);
	
	/**
     * Modifica o atributo nome de um objeto Selecao.
     *
     * @param nomeSelecao nome da Seleção
     * @param novo_nomeSelecao novo nome que deseja atribuir
     */
	public void AtualizarSelecao(String nomeSelecao, String novo_nomeSelecao);
	
	/**
     * Retorna uma lista de inteiros que são os códigos dos jogadores de uma determinada Seleção.
     *
     * @param nomeSelecao nome da Seleção
     * @return retorna uma lista de inteiros que são os códigos dos jogadores de uma determinada Seleção.
     */
	public List<Integer> CodJogadoresSelecao(String nomeSelecao);
	
	/**
     * Retorna uma lista de objetos do tipo Selecao.
     *
     * @return retorna uma lista de objetos do tipo Selecao
     */
	public List<Selecao> ListaSelecao();
}