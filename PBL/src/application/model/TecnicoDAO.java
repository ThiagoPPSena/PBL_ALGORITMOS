package application.model;

import java.util.*;

public class TecnicoDAO{
	
	private Map<Integer, Tecnico> ColecaoTecnicos = new HashMap<Integer, Tecnico>();
	
	public void InserirTecnico(Tecnico Tec) {
		this.ColecaoTecnicos.put(Tec.getCodTec(), Tec);
	}
	
	public void RemoverTecnico(int codTecnico) {
		this.ColecaoTecnicos.remove(codTecnico);
	}
	
	public Map<Integer, Tecnico> ListaTecnico(){
		return this.ColecaoTecnicos;
	}

}