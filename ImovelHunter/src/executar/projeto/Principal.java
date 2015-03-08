package executar.projeto;

import fachada.Fachada;
import fachada.IFachada;

public class Principal {
	
	private static IFachada fachada = Fachada.obterInstancia(); 
	
	public static void main (String [] args){
		fachada.buscarAnuncinates();
	}

}
