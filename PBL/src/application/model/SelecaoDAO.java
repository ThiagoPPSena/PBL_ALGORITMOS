package application.model;

import java.util.*;

import execoes.LimiteSelecoesException;

public class SelecaoDAO implements InterfaceSelecao{
	
	private Map<Integer, Selecao> ColecaoSelecao = new HashMap<Integer, Selecao>();
	
	public Selecao BuscarSelecao(int codSel) {
		return this.ColecaoSelecao.get(codSel);
	}
	
	public void InserirSelecao(Selecao sel) throws LimiteSelecoesException {
		if (this.ColecaoSelecao.size() < 32) {
			this.ColecaoSelecao.put(sel.getCodSel(), sel);
		}else {
			throw new LimiteSelecoesException();
		}
	}
	
	public Selecao RemoverSelecao(int codSel) {
		return this.ColecaoSelecao.remove(codSel);
	}
	
	public List<Integer> CodJogadoresSelecao(int codSel) {
		return BuscarSelecao(codSel).getListaCodJog();
	}
	
	public Map<Integer, Selecao> ListaSelecao() {
		return this.ColecaoSelecao;
	}
}