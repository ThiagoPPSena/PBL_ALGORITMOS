package application.model;
import java.util.*;

public interface InterfaceTecnico {
	public void InserirTecnico(Tecnico Tec);
	public void RemoverTecnico(int codTecnico);
	public Map<Integer, Tecnico> ListaTecnico();
}
