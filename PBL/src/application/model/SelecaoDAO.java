package application.model;

import java.util.*;

public class SelecaoDAO implements InterfaceSelecao{
	
	private List<Selecao> ColecaoSelecao = new ArrayList<Selecao>();
	
	public Selecao BuscarSelecao (String nomeSelecao) {
		for (int i = 0; i < ColecaoSelecao.size(); i++) {
			if (nomeSelecao == ColecaoSelecao.get(i).getNome()) {
				return ColecaoSelecao.get(i);
			}
		}
		return null;
	}
	
	public void InserirSelecao(Selecao Sel) {
		this.ColecaoSelecao.add(Sel);
	}
	
	public void RemoverSelecao(String nomeSelecao) {
		Selecao sel = BuscarSelecao(nomeSelecao);
		
		if (sel != null) {
			this.ColecaoSelecao.remove(sel);
		}
	}
	
	public void AtualizarSelecao(String nomeSelecao, String novo_nomeSelecao) {
		BuscarSelecao(nomeSelecao).setNome(novo_nomeSelecao);
	}
	
	public List<Integer> CodJogadoresSelecao(String nomeSelecao) {
		return BuscarSelecao(nomeSelecao).getListaCodJog();
	}
	
	public List<Selecao> ListaSelecao(){
		return this.ColecaoSelecao;
	}
}