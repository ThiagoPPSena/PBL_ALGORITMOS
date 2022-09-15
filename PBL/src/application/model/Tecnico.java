package application.model;

public class Tecnico{
	private int codTec;
	private String nome;
	
	public Tecnico(int codTec, String nome) {
		this.codTec = codTec;
		this.nome = nome;
	}
	
	public int getCodTec() {
		return codTec;
	}
	
	public void setCodTec(int codTec) {
		this.codTec = codTec;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}