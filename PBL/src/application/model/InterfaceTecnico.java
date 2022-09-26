package application.model;
import java.util.*;

public interface InterfaceTecnico {
	public void InserirTecnico(Tecnico Tec);
	public Tecnico RemoverTecnico(int codTecnico);
	public Map<Integer, Tecnico> ListaTecnico();
}
