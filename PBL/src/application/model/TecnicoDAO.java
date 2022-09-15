package application.model;

import java.util.*;

public class TecnicoDAO{
	
	Map<Integer, Tecnico> ColecaoTecnicos = new HashMap<Integer, Tecnico>();
	
	public void InserirTecnico(Tecnico Tec) {
		ColecaoTecnicos.put(Tec.getCodTec(), Tec);
	}
	
	public void RemoverTecnico(int codTecnico) {
		ColecaoTecnicos.remove(codTecnico);
	}
	
	public Map<Integer, Tecnico> ListaTecnico(){
		return ColecaoTecnicos;
	}

}