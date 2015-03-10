package br.com.imovelhunterweb.util;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class PrimeUtil implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8578105621216478300L;

	public void execute(String componente){
		org.primefaces.context.RequestContext.getCurrentInstance().execute(componente);
	}
	
	public void update(String componente){
		org.primefaces.context.RequestContext.getCurrentInstance().update(componente);
	}
	
	public void mensagem(Severity tipoMensagem,String sumario,String mensagem){		
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(tipoMensagem,sumario,mensagem));
	}

}
