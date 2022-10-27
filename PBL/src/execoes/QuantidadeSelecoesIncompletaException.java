package execoes;

public class QuantidadeSelecoesIncompletaException extends Exception {

	public QuantidadeSelecoesIncompletaException() {
		super("Quantidade de Seleções incompleta para iniciar a fase de grupos.");
	}
}
