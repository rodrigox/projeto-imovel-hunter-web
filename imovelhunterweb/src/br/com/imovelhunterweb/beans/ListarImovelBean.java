package br.com.imovelhunterweb.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.imovelhunterweb.entitys.Anunciante;
import br.com.imovelhunterweb.entitys.Imovel;
import br.com.imovelhunterweb.service.ImovelService;
import br.com.imovelhunterweb.util.Navegador;
import br.com.imovelhunterweb.util.UtilSession;

import javax.faces.bean.ManagedProperty;

@ManagedBean(name = "listarImovelBean")
@ViewScoped
public class ListarImovelBean implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2072699815347138448L;

	
	private List<Imovel> meusimoveis;


	private Navegador navegador;
	
	private Anunciante anunciante;
	
	
	@ManagedProperty("#{imovelService}")
	private ImovelService imovelService;
	
	@PostConstruct
	public void init(){
		this.meusimoveis = new ArrayList<Imovel>();
		this.navegador = new Navegador();
		this.anunciante = (Anunciante)UtilSession.getHttpSessionObject("anuncianteLogado");
		if(this.anunciante == null){
			this.navegador.redirecionarPara("login.xhtml");
			return;
		}
		this.anunciante.setIdAnunciante(2);
		this.meusimoveis = this.imovelService.listarImoveisDoAnunciante(anunciante);
	}

	public List<Imovel> getMeusimoveis() {
		return meusimoveis;
	}

	public void setMeusimoveis(List<Imovel> meusimoveis) {
		this.meusimoveis = meusimoveis;
	}

	public Anunciante getAnunciante() {
		return anunciante;
	}

	public void setAnunciante(Anunciante anunciante) {
		this.anunciante = anunciante;
	}

	public void setImovelService(ImovelService imovelService) {
		this.imovelService = imovelService;
	}
	
}
