package application.model;

import java.util.*;

public class SelecaoDAO implements InterfaceSelecao{
	
	private Map<Integer, Selecao> ColecaoSelecao = new HashMap<Integer, Selecao>();
	
	public Selecao BuscarSelecao(int codSel) {
		return this.ColecaoSelecao.get(codSel);
	}
	
	public void InserirSelecao(Selecao sel) {
		this.ColecaoSelecao.put(sel.getCodSel(), sel);
	}
	
	public Selecao RemoverSelecao(int codSel) {
		return this.ColecaoSelecao.remove(codSel);
	}
	
	public List<Integer> CodJogadoresSelecao(int codSel) {
		return BuscarSelecao(codSel).getListaCodJog();
	}
	
	public Map<Integer, Selecao> ListaSelecao(){
		return this.ColecaoSelecao;
	}
}