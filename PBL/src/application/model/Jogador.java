package application.model;

public class Jogador {
	//Atributos
	private int codJog;
	private static int cod = 0;
	private String nome;
	private String posicao;
	private int[] cartoes = {0, 0};
	private int numGols = 0;
	
	//Construtor
	public Jogador(String nome, String posicao) {
		this.codJog = cod;
		cod++;
		this.nome = nome;
		this.posicao = posicao;
	}
	
	//Setters e getters
	public int getCodJog() {
		return this.codJog;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPosicao() {
		return this.posicao;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	public int[] getCartoes() {
		return this.cartoes;
	}

	public void setCartoes(int[] cartoes) {
		this.cartoes = cartoes;
	}

	public int getNumGols() {
		return this.numGols;
	}

	public void setNumGols(int numGols) {
		this.numGols = numGols;
	}
	
	public void addCartoes(int cartoesAma, int cartoesVer) {
		this.cartoes[0] += cartoesAma;
		this.cartoes[1] += cartoesVer;
	}
	
	public void addGols(int numGols) {
		this.numGols += numGols;
	}
	
	@Override
	public String toString() {
		return this.nome + " (" + this.posicao + ")\n" + "Cartões amarelos: " + this.cartoes[0] + "\nCartões vermelhos: " + this.cartoes[1] + "\nSaldo de Gols: " + this.numGols + "\n";
	}
}