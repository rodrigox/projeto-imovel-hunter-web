package br.com.imovelhunterweb.util;

import javax.faces.context.FacesContext;

public class Navegador {
	
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
