package br.com.imovelhunterweb.util;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

public class EnderecoImagens {


	private String caminhoServidor;
	private String caminhoTemp;
	
	
	public EnderecoImagens(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
		this.caminhoServidor = scontext.getRealPath("") + "/servidor/imagens/";
		this.caminhoTemp = scontext.getRealPath("") + "/uploads/imagens/";
	}
	
	

	public String getCaminhoServidor() {
		return caminhoServidor;
	}


	public String getCaminhoTemp() {
		return caminhoTemp;
	}

	
}
