package application.model;

import java.util.ArrayList;
import java.util.List;

public class Grupo {
	
	private static int cod = 0;
	private int codGrupo;
	private List<Integer> selecoes = new ArrayList<Integer>();
	private Partida partidas[] = new Partida[6];
	private int pontuacoes[] = new int[4];
	
	public Grupo(List<Integer> selecoes) {
		this.selecoes = selecoes;
		this.codGrupo = cod;
		cod++;
		for(int i=0; i<6; i++) {
			Partida p = new Partida();
			this.partidas[i] = p;
		}
	}

	public int getCodGrupo() {
		return codGrupo;
	}

	public List<Integer> getSelecoes() {
		return selecoes;
	}

	public void setSelecoes(List<Integer> selecoes) {
		this.selecoes = selecoes;
	}

	public Partida[] getPartidas() {
		return partidas;
	}
}
