package br.com.imovelhunterweb.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "indexBean")
@ViewScoped
public class IndexBean {
	
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
