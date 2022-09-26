package application.model;

public class Tecnico{
	private int codTec;
	private String nome;
	private static int cod = 0;
	
	public Tecnico(String nome) {
		this.codTec = cod;
		this.nome = nome;
		cod++;
	}
	
	public int getCodTec() {
		return this.codTec;
	}
	
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}