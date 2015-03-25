package br.com.imovelhunterweb.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.imovelhunterweb.entitys.Caracteristica;
import br.com.imovelhunterweb.service.CaracteristicaService;
import br.com.imovelhunterweb.util.Navegador;
import br.com.imovelhunterweb.util.PrimeUtil;

@ManagedBean(name="caracteristicaBean")
@ViewScoped
public class CaracteristicaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2072943478976908813L;

	/**
	 * 
	 */
	private Caracteristica caracteristica;
	
	private Navegador navegador;
	private PrimeUtil primeUtil;
	
	
	@ManagedProperty("#{caracteristicaService}")
	private CaracteristicaService caracteristicaService;
	
	@PostConstruct
	public void init(){
		this.navegador = new Navegador();
		this.primeUtil = new PrimeUtil();
		this.caracteristica = new Caracteristica();
	}

	
	public void salvarCaracteristica (){
		if(this.validarCampos()){
			this.caracteristicaService.inserir(this.caracteristica);		
			
			//Nessa parte exibe a mensagem e limpa todo o resto
			this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO,"Cadastro","Caracteristica Cadastrada com Sucesso");				
		}
		this.primeUtil.update("idFormMensagem");			
		
	}
	
	
	
	private boolean validarCampos(){		
		return true;
	}	
	

	public Navegador getNavegador() {
		return navegador;
	}

	public void setNavegador(Navegador navegador) {
		this.navegador = navegador;
	}

	public void setCaracteristicaService(CaracteristicaService caracteristicaService) {
		this.caracteristicaService = caracteristicaService;
	}


	public Caracteristica getCaracteristica() {
		return caracteristica;
	}


	public void setCaracteristica(Caracteristica caracteristica) {
		this.caracteristica = caracteristica;
	}
}
