package br.com.imovelhunterweb.beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


import br.com.imovelhunterweb.entitys.Anunciante;
import br.com.imovelhunterweb.service.AnuncianteService;
import br.com.imovelhunterweb.util.Navegador;
import br.com.imovelhunterweb.util.PrimeUtil;
import br.com.imovelhunterweb.util.UtilSession;

@ManagedBean(name = "faleConoscoBean")
@ViewScoped
public class FaleConoscoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2465262372102338614L;
	private String nome;
	private String email;
	private String telefoneFixo;
	private String telefoneCelular;
	private String mensagem;
	private String assunto;
	
	private Navegador navegador;
	private PrimeUtil primeUtil;
	
	@ManagedProperty("#{anuncianteService}")
	private AnuncianteService anuncianteService;
	
	@PostConstruct
	public void init(){
		this.navegador = new Navegador();
		this.primeUtil = new PrimeUtil();
		this.navegador = new Navegador();
	}
	
	
	public void enviarMensagem(){
		if(this.validarCampos()){	
			
			String mensagemEnvio = "email: "+this.email+"\r\nTelefone: "+this.telefoneCelular+"\r\nTelefone fixo: "+this.telefoneFixo+"\r\nMensagem: \r\n";
			mensagemEnvio += this.mensagem;
			
			Object[] resposta = this.anuncianteService.enviarMensagem(this.assunto,mensagemEnvio);
			
			boolean resultado = (Boolean)resposta[0];
			String mensagem = (String)resposta[1];
			
			if(resultado){
				this.mensagem = "";
				this.email = "";
				this.telefoneCelular = "";
				this.assunto = "";
				this.nome = "";
				
				//Nessa parte exibe a mensagem e limpa todo o resto
				this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO,"Enviado",mensagem);					
			}else{
				//Nessa parte exibe a mensagem de erro
				this.primeUtil.mensagem(FacesMessage.SEVERITY_ERROR,"Erro",mensagem);				
			}
			this.primeUtil.update("idFormFaleConosco");
			this.primeUtil.update("idFormMensagem");			
		}
	}
	
	private boolean validarCampos(){		
		return true;
	}	
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefoneFixo() {
		return telefoneFixo;
	}
	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}
	public String getTelefoneCelular() {
		return telefoneCelular;
	}
	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}


	public void setAnuncianteService(AnuncianteService anuncianteService) {
		this.anuncianteService = anuncianteService;
	}
	
	
	
	
	}
