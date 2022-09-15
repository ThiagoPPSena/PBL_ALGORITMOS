package application.model;

public class Jogador {
	private int codJog;
	private String nome;
	private String posicao;
	private int[] cartoes = new int[2];
	private int numGols;
	
	public Jogador(int codJog, String nome, String posicao, int[] cartoes, int numGols) {
		this.codJog = codJog;
		this.nome = nome;
		this.posicao = posicao;
		this.cartoes = cartoes;
		this.numGols = numGols;
	}
	
	public int getCodJog() {
		return codJog;
	}
	
	public void setCodJog(int codJog) {
		this.codJog = codJog;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPosicao() {
		return posicao;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	public int[] getCartoes() {
		return cartoes;
	}

	public void setCartoes(int[] cartoes) {
		this.cartoes = cartoes;
	}

	public int getNumGols() {
		return numGols;
	}

	public void setNumGols(int numGols) {
		this.numGols = numGols;
	}
	
	@Override
	public String toString() {
		return this.nome + " (" + this.posicao + ")\n" + "Cartões amarelos: " + this.cartoes[0] + "\nCartões vermelhos: " + this.cartoes[1] + "\nSaldo de Gols: " + this.numGols + "\n";
	}
}