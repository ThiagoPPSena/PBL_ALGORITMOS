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
	
	public int[] getPontuacoes() {
		return pontuacoes;
	}

	public void updatePontos() {
		for (int i = 0; i < 6; i++) {
			if (this.partidas[i].getGolsTime1() > this.partidas[i].getGolsTime2()) {
				for (int j = 0; j < 4; j++) {
					if (this.selecoes.get(j) == this.partidas[i].getCodTime1()) {
						this.pontuacoes[j] += 3;
						break;
					}
				}
			}else if (this.partidas[i].getGolsTime1() < this.partidas[i].getGolsTime2()) {
				for (int j = 0; j < 4; j++) {
					if (this.selecoes.get(j) == this.partidas[i].getCodTime2()) {
						this.pontuacoes[j] += 3;
						break;
					}
				}
			}else {
				for (int j = 0; j < 4; j++) {
					if (this.selecoes.get(j) == this.partidas[i].getCodTime1() || this.selecoes.get(j) == this.partidas[i].getCodTime2()) {
						this.pontuacoes[j] += 1;
					}
				}
			}
		}
	}
}
