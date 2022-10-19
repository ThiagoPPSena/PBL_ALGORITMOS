package application.model;

public class Grupo {
	
	static int cod = 0;
	int codGrupo;
	int selecoes[] = new int[4];
	Partida partidas[] = new Partida[6];
	
	public Grupo(int selecoes[]) {
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

	public int[] getSelecoes() {
		return selecoes;
	}

	public void setSelecoes(int[] selecoes) {
		this.selecoes = selecoes;
	}

	public Partida[] getPartidas() {
		return partidas;
	}
}
