package br.com.imovelhunterweb.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.catalina.startup.Catalina;

import br.com.imovelhunterweb.entitys.Anunciante;
import br.com.imovelhunterweb.entitys.Caracteristica;
import br.com.imovelhunterweb.entitys.Imovel;
import br.com.imovelhunterweb.service.ImovelService;
import br.com.imovelhunterweb.util.Navegador;
import br.com.imovelhunterweb.util.PrimeUtil;
import br.com.imovelhunterweb.util.UtilSession;

@ManagedBean(name = "listarImovelBean")
@ViewScoped
public class ListarImovelBean implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2072699815347138448L;
	private List<Imovel> meusImoveis;
	private Navegador navegador;	
	private PrimeUtil primeUtil;
	private Anunciante anunciante;

	@ManagedProperty("#{imovelService}")
	private ImovelService imovelService;
	
	@PostConstruct
	public void init(){
		this.meusImoveis = new ArrayList<Imovel>();
		this.navegador = new Navegador();
		this.primeUtil = new PrimeUtil();
		
		this.anunciante = (Anunciante)UtilSession.getHttpSessionObject("anuncianteLogado");
		if(this.anunciante == null){
			this.navegador.redirecionarPara("login.xhtml");
			return;
		}
		
		this.meusImoveis = this.imovelService.listarImoveisDoAnunciante(anunciante);
		// Início - Por favor, não tirar essa rotina bizarra
		for(Imovel i : this.meusImoveis){
			for(Caracteristica c : i.getCaracteristicas()){
				// Única forma de ajeitar o bug escroto de Session.
			}
		}
		// Fim - Por favor, não tirar essa rotina bizarra
	}

	public List<Imovel> getMeusImoveis() {
		return meusImoveis;
	}

	public void setMeusImoveis(List<Imovel> meusImoveis) {
		this.meusImoveis = meusImoveis;
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
	
	
	public void imovelSelecionado(Imovel imovel){	
		UtilSession.setHttpSessionObject("imovelSelecionado",imovel);	
		if(!imovel.equals(null)){
			this.navegador.redirecionarPara("detalheImovel.xhtml");
	
		}
	}
	
	public void excluirImovel(Imovel imovel){	
		if(!imovel.equals(null)){
			this.imovelService.remover(imovel);
			this.meusImoveis.remove(imovel);
			primeUtil.update("formConteudo");
		}
	}	
	public void editarImovel(Imovel imovel){	
		UtilSession.setHttpSessionObject("imovelSelecionado",imovel);	
		if(!imovel.equals(null)){
			this.navegador.redirecionarPara("editarImovel.xhtml");
		}
	}		
}
