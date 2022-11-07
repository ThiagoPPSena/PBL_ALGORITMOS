package application.model;

public class Tecnico{
	//Atributos
	private int codTec;
	private String nome;
	private static int cod = 0;
	
	//Cpnstrutor
	public Tecnico(String nome) {
		this.codTec = cod;
		this.nome = nome;
		cod++;
	}
	
	//Setters e getters
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