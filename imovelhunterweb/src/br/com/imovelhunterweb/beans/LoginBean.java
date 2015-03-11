package br.com.imovelhunterweb.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.imovelhunterweb.entitys.Anunciante;
import br.com.imovelhunterweb.service.AnuncianteService;
import br.com.imovelhunterweb.util.Navegador;
import br.com.imovelhunterweb.util.PrimeUtil;
import br.com.imovelhunterweb.util.UtilSession;

@ManagedBean(name = "loginBean")
@ViewScoped
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2465262372102338614L;
	private String login;
	private String senha;
	private String email;
	
	private PrimeUtil primeUtil;
	
	private Navegador navegador;
	
	@ManagedProperty("#{anuncianteService}")
	private AnuncianteService anuncianteService;
	
	private Anunciante anuncianteLogado;
	
	@PostConstruct
	public void init(){		
		
		this.primeUtil = new PrimeUtil();
		this.navegador = new Navegador();
	}
	
	public void logar(){
		if(this.anuncianteLogado == null){
			Anunciante anunciante = this.anuncianteService.buscarPorLoginESenha(this.login,this.senha);
			
			if(anunciante != null){
				this.anuncianteLogado = anunciante;
				UtilSession.setHttpSessionObject("anuncianteLogado",anunciante);			
				this.navegador.redirecionarPara("editarAnunciante.xhtml");
			}else{
				this.primeUtil.mensagem(FacesMessage.SEVERITY_WARN,"Inv·lido","Login ou senha inv·lido");
				this.primeUtil.update("idFormMensagem");
			}
		}else{
			this.navegador.redirecionarPara("editarAnunciante.xhtml");
		}
	}
	
	public String deslogar(){
		FacesContext contexto = FacesContext.getCurrentInstance();
		 try {
			  contexto.getExternalContext().getSessionMap().remove("anuncianteLogado");
			  FacesContext facesContext = FacesContext.getCurrentInstance();
			  HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
			  session.invalidate();
			  
			  this.login = "";
			  this.senha = "";
			  this.email = "";			 
			  this.anuncianteLogado = null;
			  
		} catch (Exception e) {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Erro ao encerar a sess√£o",""));
		}
		  return "telaDeLogin.xhtml";
	}
	
	public void recuperarSenha(){					
		String status = this.anuncianteService.recuperarSenha(this.email);
		String texto = status.substring(1);				
		
		
		if(status.substring(0,1).equals("I")){
			this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO,"",texto);
			this.primeUtil.update(":idFormRecuperarSenha");						
		}
		else if(status.substring(0,1).equals("E")){
			this.primeUtil.mensagem(FacesMessage.SEVERITY_ERROR,"",texto);
			this.primeUtil.update(":idFormRecuperarSenha");
		}
		else if(status.substring(0,1).equals("A")){
			this.primeUtil.mensagem(FacesMessage.SEVERITY_WARN,"",texto);
			this.primeUtil.update(":idFormRecuperarSenha");
		}			
		//Atualiza o componente que exibe a mensagem para o usu·rio, para que ele exiba
		this.primeUtil.update("idFormMensagem");
	}
	
	public void irParaRecuperar(){
		this.navegador.redirecionarPara("recuperasenha.xhtml");
	}
	
	public void cadastrar(){
		this.navegador.redirecionarPara("cadastroAnunciante.xhtml");
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAnuncianteService(AnuncianteService anuncianteService) {
		this.anuncianteService = anuncianteService;
	}
	
}
