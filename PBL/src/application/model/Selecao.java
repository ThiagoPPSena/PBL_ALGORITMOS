package application.model;
import java.util.*;

public class Selecao{
	private String nome;
	private List<Integer> listaCodJog = new ArrayList<Integer>();
	private int codTec;
	
	public Selecao(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Integer> getListaCodJog() {
		return listaCodJog;
	}
	
	public void setListaCodJog(List<Integer> listaCodJog) {
		this.listaCodJog = listaCodJog;
	}

	public int getCodTec() {
		return codTec;
	}

	public void setCodTec(int codTec) {
		this.codTec = codTec;
	}
}