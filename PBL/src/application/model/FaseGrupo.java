package application.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import execoes.MinimoArbitrosException;
import execoes.QuantidadeSelecoesIncompletaException;

public class FaseGrupo {

	private Grupo grupos[] = new Grupo[8];
	private List<Integer> selecoes = new ArrayList<Integer>();
	private boolean faseIniciada = false;
	private boolean faseEncerrada = false;

	public FaseGrupo() {
		
	}
	
	public boolean isFaseIniciada() {
		return faseIniciada;
	}
	
	public boolean isFaseEncerrada() {
		return faseEncerrada;
	}

	public void setFaseEncerrada(boolean faseEncerrada) {
		this.faseEncerrada = faseEncerrada;
	}

	public void updateSelecoes(Map<Integer, Selecao> selecoes) {
		this.selecoes = new ArrayList<Integer>();
		for (int key: selecoes.keySet()) {
			this.selecoes.add(key);
		}
	}
	
	public void iniciarFase(ArbitroDAO arbitroDAO) throws QuantidadeSelecoesIncompletaException, MinimoArbitrosException {
		
		if(arbitroDAO.ListaArbitro().size()>0) {
			if (this.selecoes.size() == 32) {
				
				this.faseIniciada = true;
				this.grupos[0] = new Grupo(this.selecoes.subList(0, 4));
				this.grupos[1] = new Grupo(this.selecoes.subList(4, 8));
				this.grupos[2] = new Grupo(this.selecoes.subList(8, 12));
				this.grupos[3] = new Grupo(this.selecoes.subList(12, 16));
				this.grupos[4] = new Grupo(this.selecoes.subList(16, 20));
				this.grupos[5] = new Grupo(this.selecoes.subList(20, 24));
				this.grupos[6] = new Grupo(this.selecoes.subList(24, 28));
				this.grupos[7] = new Grupo(this.selecoes.subList(28, 32));
			}else {
				throw new QuantidadeSelecoesIncompletaException();
			}
		}else {
			throw new MinimoArbitrosException();
		}
	}
	
	public Grupo[] getGrupos() {
		return this.grupos;
	}
	
	public Grupo buscarGrupo(int index) {
		return this.grupos[index];
	}
	
	public void updateJogadores(JogadorDAO jogadorDAO) {
		Grupo[] grupos = getGrupos();
		Partida[] partidas;
		for (int i = 0; i < 8; i++) {
			partidas = grupos[i].getPartidas();
			for (int j = 0; j < 6; j++) {
				for (JogPartida jogPartida: partidas[j].getJogadores()) {
					jogadorDAO.BuscarJogador(jogPartida.getCodJogador()).addCartoes(jogPartida.getCartoesAma(), jogPartida.getCartoesVer());
					jogadorDAO.BuscarJogador(jogPartida.getCodJogador()).addGols(jogPartida.getGols());
				}
			}
		}
	}
	
	public List<Integer> encerrarFase() {
		//Pegar as duas seleções com maior pontuação de cada grupo.
		List<Integer> selecoesPassaram = new ArrayList<Integer>();
		int pontos1, pontos2, index1, index2;

		for (int i = 0; i < 8; i++) {
			pontos1 = -1;
			pontos2 = -1;
			index1 = 0;
			index2 = 0;
			this.grupos[i].updatePontos();

			//Pegando a primeira:
			for (int j = 0; j < 4; j++) {
				if(this.grupos[i].getPontuacoes()[j] > pontos1) {
					pontos1 = this.grupos[i].getPontuacoes()[j];
					index1 = j;
				}
			}
			selecoesPassaram.add(this.grupos[i].getSelecoes().get(index1));

			//Pegando a segunda:
			for (int j = 0; j < 4; j++) {
				if(this.grupos[i].getPontuacoes()[j] >= pontos2 && j != index1) {
					pontos2 = this.grupos[i].getPontuacoes()[j];
					index2 = j;
				}
			}
			selecoesPassaram.add(this.grupos[i].getSelecoes().get(index2));
		}
		this.faseEncerrada = true;
		return selecoesPassaram;
	}
}
