package exceptions;

public class RegistroInexistenteException extends Exception {
	public RegistroInexistenteException(){
		super("Registro inexistente.");
	}
}
