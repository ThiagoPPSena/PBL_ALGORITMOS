package application.model;

public class JogPartida {
	
	private int codJogador;
	private int gols;
	private int cartoes[] = new int[2];
	
	public JogPartida(int codJogador, int gols, int cartoes[]) {
		this.codJogador = codJogador;
		this.gols = gols;
		this.cartoes = cartoes;
	}

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

	public int[] getCartoes() {
		return cartoes;
	}

	public void setCartoes(int[] cartoes) {
		this.cartoes = cartoes;
	}
}
