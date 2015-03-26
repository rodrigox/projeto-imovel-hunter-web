package br.com.imovelhunterweb.util;

import java.io.File;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import com.sun.org.apache.bcel.internal.classfile.Field;

public class EnderecoImagens {


	private String caminhoServidor;
	private String caminhoTemp;
	
	
	public EnderecoImagens(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
		this.caminhoServidor = scontext.getRealPath("") + "/servidor/imagens/";
		this.caminhoTemp = scontext.getRealPath("") + "/uploads/imagens/";
	}
	
	public void excluirImagensServidor(String nome){
		File f = new File(caminhoServidor + nome);
		f.delete();
	}
	
	

	public String getCaminhoServidor() {
		return caminhoServidor;
	}


	public String getCaminhoTemp() {
		return caminhoTemp;
	}

	
}
