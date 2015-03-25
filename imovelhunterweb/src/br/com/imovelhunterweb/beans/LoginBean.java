package br.com.imovelhunterweb.beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.imovelhunterweb.entitys.Anunciante;
import br.com.imovelhunterweb.enums.TipoUsuario;
import br.com.imovelhunterweb.service.AnuncianteService;
import br.com.imovelhunterweb.util.Navegador;
import br.com.imovelhunterweb.util.PrimeUtil;
import br.com.imovelhunterweb.util.UtilSession;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2465262372102338614L;
	private String login;
	private String senha;
	private String email;
	
	private int anoAgora;
	
	private PrimeUtil primeUtil;
	
	private Navegador navegador;
	
	@ManagedProperty("#{anuncianteService}")
	private AnuncianteService anuncianteService;
	
	private Anunciante anuncianteLogado;
	
	private String logado;
	
	private String cadastroImovel;
	
	
	
	@PostConstruct
	public void init(){		
		
		this.anuncianteService.instalarSuperUsuario();
		
		this.primeUtil = new PrimeUtil();
		this.navegador = new Navegador();
		
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(new Date());
		
		this.anoAgora = calendario.get(Calendar.YEAR);
		
		this.anuncianteLogado = (Anunciante)UtilSession.getHttpSessionObject("anuncianteLogado");
		if(this.anuncianteLogado != null){
			this.logado = "<li><a onclick=\"rc();\" href=\"\"  >Deslogar</a></li>";
			this.cadastroImovel = "<li><a href=\"meuperfil.xhtml\">Meu perfil</a></li>";
		}else{
			this.logado = "<li><a href=\"login.xhtml\"  >Logar</a></li>";
			this.cadastroImovel = "";
		}
	}
	
	public void logar(){
		
	    Object login = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("login");
	    Object senha = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("senha");
	    
	    this.login = login != null ? (String)login : this.login;
	    
	    this.senha = senha != null ? (String)senha : this.senha;
	    
		if(this.anuncianteLogado == null){
			Anunciante anunciante = null;
			
			try{
				anunciante = this.anuncianteService.buscarPorLoginESenha(this.login,this.senha);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			
			if(anunciante != null){
				this.anuncianteLogado = anunciante;
				UtilSession.setHttpSessionObject("anuncianteLogado",anunciante);			
				this.navegador.redirecionarPara("index.xhtml");
			}else{
				this.primeUtil.mensagem(FacesMessage.SEVERITY_WARN,"Inválido","Login ou senha inválido");
				this.primeUtil.update("idFormMensagem");
			}
		}else{
			this.navegador.redirecionarPara("index.xhtml");
		}
		
		if(this.anuncianteLogado != null){
			this.logado = "<li><a onclick=\"rc();\" href=\"\"  >Deslogar</a></li>";
			this.cadastroImovel = "<li><a href=\"meuperfil.xhtml\">Meu perfil</a></li>";
		}else{
			this.logado = "<li><a href=\"login.xhtml\"  >Logar</a></li>";
			this.cadastroImovel = "";
		}
	}
	
	public String deslogar(){
		this.navegador.redirecionarPara("index.xhtml");
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
			  
			  if(this.anuncianteLogado != null){
					this.logado = "<li><a onclick=\"rc();\" href=\"\"  >Deslogar</a></li>";
					this.cadastroImovel = "<li><a href=\"meuperfil.xhtml\">Meu perfil</a></li>";
				}else{
					this.logado = "<li><a href=\"login.xhtml\"  >Logar</a></li>";
					this.cadastroImovel = "";
				}
			  
			  
		} catch (Exception e) {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Erro ao encerar a sessÃ£o",""));
		}
		return "index.xhtml"; 		 
	}
	
	public String getUsuarioLogado(){
		if(this.anuncianteLogado != null){
			return "";
		}else{
			this.navegador.redirecionarPara("index.xhtml");
			return "";
		}
	}
	
	public String getMinhaPermissao(){
		
		//Se de erro aqui, verifique se no banco o campo tipoUsuario está preenchido, se ele estiver nulo, vai bugar aqui
		if(this.anuncianteLogado != null && this.anuncianteLogado.getTipoUsuario().equals(TipoUsuario.USUARIO)){
			return "<li><a href=\"imoveis.xhtml\" >Imoveis</a></li> \r\n <li><a href=\"editarAnunciante.xhtml\">Editar meu perfil</a></li> \r\n <li><a href=\"cadastroGrupoCaracteristica.xhtml\">Cadastro grupo de características</a></li>";
		}else if(this.anuncianteLogado != null){
			return "<li><a href=\"cadastroCaracteristica.xhtml\" >Cadastro característica</a></li> \r\n <li><a href=\"cadastroGrupoCaracteristica.xhtml\">Cadastro grupo de características</a></li>";
		}
		
		return "";
	}
	
	
	
	public void recuperarSenha(){		
	    Object email = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("email");

	    this.email = email != null ? (String)email : this.email;
	    
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
		//Atualiza o componente que exibe a mensagem para o usuário, para que ele exiba
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

	public int getAnoAgora() {
		return anoAgora;
	}

	public String getLogado() {
		return logado;
	}

	public String getCadastroImovel() {
		return cadastroImovel;
	}
	
}
