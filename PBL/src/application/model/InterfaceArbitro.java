package application.model;

import java.util.*;

public interface InterfaceArbitro {
	
	/**
     * Insere um objeto do tipo Arbitro em um Map específico.
     *
     * @param Arb objeto do tipo Arbitro
     */
	public void InserirArbitro(Arbitro Arb);
	
	/**
     * Remove um objeto do tipo Arbitro de um Map específico, e retorna-o.
     *
     * @param codArbitro código do Arbitro
     * @return objeto do tipo Arbitro
     */
	public Arbitro RemoverArbitro(int codArbitro);
	
	/**
     * Retorna um Map de objetos do tipo Arbitro.
     *
     * @return retorna um Map de objetos do tipo Arbitro
     */
	public Map<Integer, Arbitro> ListaArbitro();
} 