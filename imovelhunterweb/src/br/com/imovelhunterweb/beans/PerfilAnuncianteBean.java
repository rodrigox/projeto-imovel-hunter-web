package br.com.imovelhunterweb.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.imovelhunterweb.entitys.Anunciante;
import br.com.imovelhunterweb.service.AnuncianteService;
import br.com.imovelhunterweb.util.EmailValidator;
import br.com.imovelhunterweb.util.Navegador;
import br.com.imovelhunterweb.util.PrimeUtil;
import br.com.imovelhunterweb.util.UtilSession;

@ManagedBean(name = "perfilAnuncianteBean")
@ViewScoped
public class PerfilAnuncianteBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 500703284436109611L;

	private Anunciante anunciante;
	
	@ManagedProperty("#{anuncianteService}")
	private AnuncianteService anuncianteService;

	private Navegador navegador;
	
	private PrimeUtil primeUtil;
	
	private SimpleDateFormat simpleDateFormat;
	
	private String dataDeNascimento;
	
	private String email;
	
	@PostConstruct
	public void init(){
		this.navegador = new Navegador();
		this.primeUtil = new PrimeUtil();
		this.anunciante = (Anunciante)UtilSession.getHttpSessionObject("anuncianteLogado");
		if(this.anunciante == null){
			this.navegador.redirecionarPara("login.xhtml");
			return;
		}
		
		this.simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		this.dataDeNascimento = this.simpleDateFormat.format(this.anunciante.getDataDeNascimento());
		
		this.email = this.anunciante.getEmail();

	}


	
	public Anunciante getAnunciante() {
		return anunciante;
	}



	public void setAnunciante(Anunciante anunciante) {
		this.anunciante = anunciante;
	}


	public void setAnuncianteService(AnuncianteService anuncianteService) {
		this.anuncianteService = anuncianteService;
	}


	public String getDataDeNascimento() {
		return dataDeNascimento;
	}



	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}

}
