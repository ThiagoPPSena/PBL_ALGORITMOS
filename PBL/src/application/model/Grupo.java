package application.model;

import java.util.ArrayList;
import java.util.List;

public class Grupo {
	//Atributos
	private static int cod = 0;
	private int codGrupo;
	private List<Integer> selecoes = new ArrayList<Integer>();
	private Partida partidas[] = new Partida[6];
	private int pontuacoes[] = {0, 0, 0, 0};
	
	//Construtor
	public Grupo(List<Integer> selecoes) {
		this.selecoes = selecoes;
		this.codGrupo = cod;
		cod++;
		for(int i=0; i<6; i++) { //Cria as 6 partidas do grupo
			Partida p = new Partida();
			this.partidas[i] = p;
		}
		//Seta os times de cada partida
		this.partidas[0].setCodTime1(selecoes.get(0));this.partidas[0].setCodTime2(selecoes.get(1));
		this.partidas[1].setCodTime1(selecoes.get(0));this.partidas[1].setCodTime2(selecoes.get(2));
		this.partidas[2].setCodTime1(selecoes.get(0));this.partidas[2].setCodTime2(selecoes.get(3));
		this.partidas[3].setCodTime1(selecoes.get(1));this.partidas[3].setCodTime2(selecoes.get(2));
		this.partidas[4].setCodTime1(selecoes.get(1));this.partidas[4].setCodTime2(selecoes.get(3));
		this.partidas[5].setCodTime1(selecoes.get(2));this.partidas[5].setCodTime2(selecoes.get(3));
		
	}
	
	//Setters e getters
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
	
	//Método que a partir do código da partida, retorna o index dela em partidas
	public int buscarPartida(int cod) {
		for(int i = 0; i < 6; i++) {
			if(partidas[i].getCodPartida() == cod)
				return i;
		}
		return -1;
	}
	
	//Método que atualiza os pontos das seleções; ganhou: +3 pontos; empatou: +1 ponto; perdeu: +0 pontos
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

