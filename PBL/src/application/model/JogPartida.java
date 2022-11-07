package application.model;

public class JogPartida {
	
	//Atributos
	private int codJogador;
	private int gols;
	private int cartoesAma;
	private int cartoesVer;
	
	//Construtor 
	public JogPartida() {
		this.codJogador = 0;
		this.gols = 0;
		this.cartoesAma = 0;
		this.cartoesVer = 0;
	}

	//Setters e getters
	public int getCodJogador() {
		return codJogador;
	}

	public void setCodJogador(int codJogador) {
		this.codJogador = codJogador;
	}

	public int getGols() {
		return gols;
	}

	public void setGols(int gols) {
		this.gols = gols;
	}

	public int getCartoesAma() {
		return cartoesAma;
	}

	public void setCartoesAma(int cartoesAma) {
		this.cartoesAma = cartoesAma;
	}
	
	public int getCartoesVer() {
		return cartoesVer;
	}

	public void setCartoesVer(int cartoesVer) {
		this.cartoesVer = cartoesVer;
	}
	

}
