package application.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import execoes.QuantidadeSelecoesIncompletaException;

public class FaseGrupo {

	private Grupo grupos[] = new Grupo[8];
	private List<Integer> selecoes = new ArrayList<Integer>();
	private boolean faseIniciada = false;

	public FaseGrupo() {
		
	}
	
	public void updateSelecoes(Map<Integer, Selecao> selecoes) {
		for (int key: selecoes.keySet()) {
			this.selecoes.add(key);
		}
	}
	
	public void iniciarFase() throws QuantidadeSelecoesIncompletaException {
		
		if (this.selecoes.size() == 32) {
		
			this.faseIniciada = true;
			this.grupos[0] = new Grupo(this.selecoes.subList(0, 3));
			this.grupos[1] = new Grupo(this.selecoes.subList(4, 7));
			this.grupos[2] = new Grupo(this.selecoes.subList(8, 11));
			this.grupos[3] = new Grupo(this.selecoes.subList(12, 15));
			this.grupos[4] = new Grupo(this.selecoes.subList(16, 19));
			this.grupos[5] = new Grupo(this.selecoes.subList(20, 23));
			this.grupos[6] = new Grupo(this.selecoes.subList(24, 27));
			this.grupos[7] = new Grupo(this.selecoes.subList(28, 31));
		}else {
			throw new QuantidadeSelecoesIncompletaException();
		}
	}
	
	public int[] encerrarFase() {
		//Retornar seleções que passaram.
		return null;
	}

	public boolean isFaseIniciada() {
		return faseIniciada;
	}
}
