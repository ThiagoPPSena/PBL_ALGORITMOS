package application.model;
import java.util.*;

public class Selecao{
	//Atributos
	private static int cod = 0;
	private int codSel;
	private String nome;
	private List<Integer> listaCodJog = new ArrayList<Integer>();
	private int codTec;
	
	//Construtor
	public Selecao(String nome) {
		this.nome = nome;
		this.codSel = cod;
		cod++;
	}
	
	//Setters e getters
	public int getCodSel() {
		return this.codSel;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Integer> getListaCodJog() {
		return this.listaCodJog;
	}
	
	public void setListaCodJog(List<Integer> listaCodJog) {
		this.listaCodJog = listaCodJog;
	}

	public int getCodTec() {
		return this.codTec;
	}

	public void setCodTec(int codTec) {
		this.codTec = codTec;
	}
}