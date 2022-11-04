package execoes;

public class MinimoArbitrosException extends Exception{
	
	public MinimoArbitrosException() {
		super("Mínimo de árbitros não atingido!!! Cadastre pelo pelo menos 1 no sistema para iniciar a fase de grupos.");
	}
	
}
