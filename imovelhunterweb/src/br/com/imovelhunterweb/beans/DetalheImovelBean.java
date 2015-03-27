package br.com.imovelhunterweb.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.imovelhunterweb.entitys.Anunciante;
import br.com.imovelhunterweb.entitys.Imovel;
import br.com.imovelhunterweb.util.Navegador;
import br.com.imovelhunterweb.util.UtilSession;

@ManagedBean(name = "detalheImovelBean")
@ViewScoped
public class DetalheImovelBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7757743129870117596L;

	
	private Navegador navegador;
	private Imovel imovel;
	private Anunciante anunciante;
	
	
	@PostConstruct
	public void init() {
		this.navegador = new Navegador();	

		this.anunciante = (Anunciante) UtilSession
				.getHttpSessionObject("anuncianteLogado");
		
		if (this.anunciante == null) {
			this.navegador.redirecionarPara("login.xhtml");
			return;
		}
		
		this.imovel = (Imovel) UtilSession.getHttpSessionObject("imovelSelecionado");		
		
		
	}
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

	
}