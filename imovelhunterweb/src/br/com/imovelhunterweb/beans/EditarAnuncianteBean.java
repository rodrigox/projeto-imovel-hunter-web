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

@ManagedBean(name = "editarAnuncianteBean")
@ViewScoped
public class EditarAnuncianteBean implements Serializable{
	
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
	
	private String senha;
	
	private String confirmarSenha;
	
	private String dataDeNascimento;
	
	private String email;
	
	private EmailValidator validadorDeEmail;

	
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
		
		this.validadorDeEmail = new EmailValidator();

	}


	
	public Anunciante getAnunciante() {
		return anunciante;
	}



	public void setAnunciante(Anunciante anunciante) {
		this.anunciante = anunciante;
	}



	public void atualizarAnunciante(){
		try{
			if(!this.validarCampos()){
				return;
			}
			if(!this.anunciante.getEmail().equals(this.email) && this.anuncianteService.existeEmail(this.anunciante.getEmail())){
				this.primeUtil.mensagem(FacesMessage.SEVERITY_WARN,"Atenção","Esse email está sendo utilizado por outra conta");
				this.primeUtil.update("idFormMensagem");
				return;
			}
			if(this.senha.length() > 0){
				this.anunciante.setSenha(this.senha);
			}
			this.anunciante.setDataDeNascimento(this.simpleDateFormat.parse(this.dataDeNascimento));
			this.anuncianteService.atualizar(anunciante);
			this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO,"Atualização","Anunciante atualizado com sucesso");
			this.primeUtil.update("idFormMensagem");
		}
		catch(Exception ex){
			ex.printStackTrace();
			this.primeUtil.mensagem(FacesMessage.SEVERITY_ERROR,"Erro","Erro ao tentar atualizar o anunciante");
			this.primeUtil.update("idFormMensagem");
		}
	}
	
	private boolean validarCampos(){
		
		if(this.anunciante.getNome().length() == 0){
			this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO,"Campo inválido","O nome do anunciante deve ser informado");
			//Atualiza o componente que exibe a mensagem para o usuário, para que ele exiba
			this.primeUtil.update("idFormMensagem");
			return false;
		}
		if(this.anunciante.getSobreNome().length() == 0){
			this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO,"Campo inválido","O sobrenome do anunciante deve ser informado");
			//Atualiza o componente que exibe a mensagem para o usuário, para que ele exiba
			this.primeUtil.update("idFormMensagem");
			return false;
		}		
		if(this.anunciante.getCreci().length() == 0){
			this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO,"Campo inválido","O creci deve ser informado");
			//Atualiza o componente que exibe a mensagem para o usuário, para que ele exiba
			this.primeUtil.update("idFormMensagem");
			return false;
		}
		
		if(!this.anunciante.getEmail().equals(this.email) && this.email.length() == 0){
			this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO,"Campo inválido","O email do anunciante deve ser informado");
			//Atualiza o componente que exibe a mensagem para o usuário, para que ele exiba
			this.primeUtil.update("idFormMensagem");
			return false;
		}else if(!this.anunciante.getEmail().equals(this.email) && !this.validadorDeEmail.isEmailValid(this.email)){
			this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO,"Email inválido","O email do anunciante é inválido");
			//Atualiza o componente que exibe a mensagem para o usuário, para que ele exiba
			this.primeUtil.update("idFormMensagem");
			return false;
		}
		
		//Só valida a senha se tiver algo digitado nele
		if(this.senha.length() > 0){			
			if(confirmarSenha.length() == 0){
				this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO,"Campo inválido","A senha deve ser confirmada");
				//Atualiza o componente que exibe a mensagem para o usuário, para que ele exiba
				this.primeUtil.update("idFormMensagem");
				return false;
			}
			if(!this.senha.equals(this.confirmarSenha)){
				this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO,"Valores diferentes","A senha diferente de confirmar senha");
				//Atualiza o componente que exibe a mensagem para o usuário, para que ele exiba
				this.primeUtil.update("idFormMensagem");
				return false;
			}
		}
		
		
		return true;
	}
	
	public void  remover(){
		if(this.anuncianteService.remover(this.anunciante)){
			
			FacesContext contexto = FacesContext.getCurrentInstance();
			
			contexto.getExternalContext().getSessionMap().remove("anuncianteLogado");
			  FacesContext facesContext = FacesContext.getCurrentInstance();
			  HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
			  session.invalidate();
			
			this.navegador.redirecionarPara("login.xhtml");
		}else{
			this.primeUtil.mensagem(FacesMessage.SEVERITY_ERROR,"Erro","Erro ao tentar remover o perfil");
			this.primeUtil.update("idFormMensagem");
		}		
	}
	

	public void setAnuncianteService(AnuncianteService anuncianteService) {
		this.anuncianteService = anuncianteService;
	}



	public String getConfirmarSenha() {
		return confirmarSenha;
	}



	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
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



	public String getSenha() {
		return senha;
	}



	public void setSenha(String senha) {
		this.senha = senha;
	}

}
