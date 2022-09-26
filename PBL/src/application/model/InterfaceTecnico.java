package application.model;
import java.util.*;

public interface InterfaceTecnico {
	
	/**
     * Insere um objeto do tipo Tecnico em um Map específico.
     *
     * @param Tec objeto do tipo Tecnico
     */
	public void InserirTecnico(Tecnico Tec);
	
	/**
     * Remove um objeto do tipo Tecnico de um Map específico, e retorna-o.
     *
     * @param codTecnico código do Tecnico
     * @return objeto do tipo Tecnico
     */
	public Tecnico RemoverTecnico(int codTecnico);
	
	/**
     * Retorna um Map de objetos do tipo Tecnico.
     *
     * @return retorna um Map de objetos do tipo Tecnico
     */
	public Map<Integer, Tecnico> ListaTecnico();
}
