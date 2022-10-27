package execoes;

public class LimiteSelecoesException extends Exception {
	
	public LimiteSelecoesException() {
		super("Número de Seleções ultrapassou o limite.");
	}
}
