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
import br.com.imovelhunterweb.entitys.Imovel;
import br.com.imovelhunterweb.service.AnuncianteService;
import br.com.imovelhunterweb.service.ImovelService;
import br.com.imovelhunterweb.util.EmailValidator;
import br.com.imovelhunterweb.util.Navegador;
import br.com.imovelhunterweb.util.PrimeUtil;
import br.com.imovelhunterweb.util.UtilSession;

@ManagedBean(name = "imovelBean")
@ViewScoped
public class ImovelBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -500703284436109611L;

	private Imovel imovel;
	private Anunciante anunciante;
	
	@ManagedProperty("#{imovelveService}")
	private ImovelService imovelService;
	
	@ManagedProperty("#{anuncianteveService}")
	private AnuncianteService anuncianteService;
	
	public Imovel getImovel() {
		return imovel;
	}


	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}


	public Anunciante getAnunciante() {
		return anunciante;
	}


	public void setAnunciante(Anunciante anunciante) {
		this.anunciante = anunciante;
	}


	public ImovelService getImovelService() {
		return imovelService;
	}


	public void setImovelService(ImovelService imovelService) {
		this.imovelService = imovelService;
	}


	public AnuncianteService getAnuncianteService() {
		return anuncianteService;
	}


	public void setAnuncianteService(AnuncianteService anuncianteService) {
		this.anuncianteService = anuncianteService;
	}


	public Navegador getNavegador() {
		return navegador;
	}


	public void setNavegador(Navegador navegador) {
		this.navegador = navegador;
	}


	public PrimeUtil getPrimeUtil() {
		return primeUtil;
	}


	public void setPrimeUtil(PrimeUtil primeUtil) {
		this.primeUtil = primeUtil;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	private Navegador navegador;
	
	private PrimeUtil primeUtil;
	

	@PostConstruct
	public void init(){
		this.navegador = new Navegador();
		this.primeUtil = new PrimeUtil();
		this.anunciante = (Anunciante)UtilSession.getHttpSessionObject("anuncianteLogado");
		if(this.anunciante == null){
			this.navegador.redirecionarPara("login.xhtml");
			return;
		}
	}


	
	
}
