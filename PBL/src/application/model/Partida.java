package application.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Partida {
	
	private static int cod = 0;
	private int codPartida; 
	private int codArbitro;
	private Calendar data = Calendar.getInstance();
	private String horario;
	private String local;
	private int codTime1;
	private int codTime2;
	private int golsTime1;
	private int golsTime2;
	private List<JogPartida> jogadores = new ArrayList<JogPartida>();
	
	public Partida() {
		this.codPartida = cod;
		cod++;
		this.golsTime1 = 0;
		this.golsTime2 = 0;
		for(int i = 0; i < 22; i++) {
			JogPartida jogador = new JogPartida();
			jogadores.add(jogador);
		}
	}

	public int getCodArbitro() {
		return codArbitro;
	}

	public void setCodArbitro(int codArbitro) {
		this.codArbitro = codArbitro;
	}

	public List<JogPartida> getJogadores() {
		return jogadores;
	}

	public void setJogadores(List<JogPartida> jogadores) {
		this.jogadores = jogadores;
	}

	public int getCodPartida() {
		return codPartida;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(int ano, int mes, int dia) {
		this.data.set(ano, mes, dia);
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public int getCodTime1() {
		return codTime1;
	}

	public void setCodTime1(int codTime1) {
		this.codTime1 = codTime1;
	}

	public int getCodTime2() {
		return codTime2;
	}

	public void setCodTime2(int codTime2) {
		this.codTime2 = codTime2;
	}

	public int getGolsTime1() {
		return golsTime1;
	}

	public void setGolsTime1(int golsTime1) {
		this.golsTime1 = golsTime1;
	}

	public int getGolsTime2() {
		return golsTime2;
	}

	public void setGolsTime2(int golsTime2) {
		this.golsTime2 = golsTime2;
	}
	
	public JogPartida buscarJogPartida(int cod) {
		for(JogPartida jogador:jogadores) {
			if(jogador.getCodJogador() == cod)
				return jogador;
		}
		return null;
	}
	
}