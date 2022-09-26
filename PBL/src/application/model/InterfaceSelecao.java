package application.model;

import java.util.*;

public interface InterfaceSelecao {
	public Selecao BuscarSelecao(int codSel);
	public void InserirSelecao(Selecao Sel);
	public Selecao RemoverSelecao(int codSel);
	public List<Integer> CodJogadoresSelecao(int codSel);
	public Map<Integer, Selecao> ListaSelecao();
}