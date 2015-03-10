package br.com.imovelhunterweb.beans;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.imovelhunterweb.entitys.Anunciante;
import br.com.imovelhunterweb.service.AnuncianteService;
import br.com.imovelhunterweb.util.Navegador;
import br.com.imovelhunterweb.util.PrimeUtil;
import br.com.imovelhunterweb.util.UtilSession;

@ManagedBean("editarAnuncianteBean")
public class EditarAnuncianteBean implements Serializable{
	
	private Anunciante anunciante;
	
	@ManagedProperty("#{anuncianteService}")
	private AnuncianteService anuncianteService;

	private Navegador navegador;
	
	private PrimeUtil primeUtil;
	
	@PostConstruct
	public void init(){
		this.navegador = new Navegador();
		this.primeUtil = new PrimeUtil();
		this.anunciante = (Anunciante)UtilSession.getHttpSessionObject("anuncianteLogado");
		if(this.anunciante == null){
			this.navegador.redirecionarPara("telaDeLogin.xhtml");
		}
	}


	
	public Anunciante getAnunciante() {
		return anunciante;
	}



	public void setAnunciante(Anunciante anunciante) {
		this.anunciante = anunciante;
	}



	public void atualizarAnunciante(){
		this.anuncianteService.atualizar(anunciante);
		this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO,"Atualização","Anunciante atualizado com sucesso");
		this.primeUtil.update("idFormMensagem");
	}
	
	public void  remover(){
		if(this.anuncianteService.remover(this.anunciante)){
			
			FacesContext contexto = FacesContext.getCurrentInstance();
			
			contexto.getExternalContext().getSessionMap().remove("anuncianteLogado");
			  FacesContext facesContext = FacesContext.getCurrentInstance();
			  HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
			  session.invalidate();
			
			this.navegador.redirecionarPara("telaDeLogin.xhtml");
		}else{
			this.primeUtil.mensagem(FacesMessage.SEVERITY_ERROR,"Erro","Erro ao tentar remover o perfil");
			this.primeUtil.update("idFormMensagem");
		}		
	}
	

	public void setAnuncianteService(AnuncianteService anuncianteService) {
		this.anuncianteService = anuncianteService;
	}

}
