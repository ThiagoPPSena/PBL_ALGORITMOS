package application.model;

public class Arbitro{
	private int codArbitro;
	private String nome;
	private static int cod = 0;
	
	public Arbitro(String nome) {
		this.nome = nome;
		this.codArbitro = cod;
		cod++;
	}
	
	public int getCodArbitro() {
		return this.codArbitro;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}