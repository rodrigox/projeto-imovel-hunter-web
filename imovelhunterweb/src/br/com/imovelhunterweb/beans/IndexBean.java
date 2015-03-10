package br.com.imovelhunterweb.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "indexBean")
@ViewScoped
public class IndexBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7312096374909595297L;
	private String bemVindo;

	
	@PostConstruct
	public void init(){
		
		this.bemVindo = "Bem vindo";
		
	}
	
	public String getBemVindo() {
		return bemVindo;
	}

	public void setBemVindo(String bemVindo) {
		this.bemVindo = bemVindo;
	}


}
