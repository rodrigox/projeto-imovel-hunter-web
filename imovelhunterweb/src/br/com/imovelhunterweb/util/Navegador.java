package br.com.imovelhunterweb.util;

import java.io.Serializable;

import javax.faces.context.FacesContext;

public class Navegador implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1287513429006745350L;

	/**
	 * Redireciona para página que estiver no parâmetro
	 * @param pagina
	 */
	public void redirecionarPara(String pagina){
		try{
			FacesContext.getCurrentInstance().getExternalContext().redirect(pagina);				
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
