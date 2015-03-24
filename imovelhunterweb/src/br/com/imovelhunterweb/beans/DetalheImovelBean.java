package br.com.imovelhunterweb.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.sun.faces.facelets.PrivateApiFaceletCacheAdapter;

import br.com.imovelhunterweb.entitys.Anunciante;
import br.com.imovelhunterweb.entitys.Imagem;
import br.com.imovelhunterweb.entitys.Imovel;
import br.com.imovelhunterweb.service.ImovelService;
import br.com.imovelhunterweb.util.Navegador;
import br.com.imovelhunterweb.util.PrimeUtil;
import br.com.imovelhunterweb.util.UtilSession;

@ManagedBean(name = "detalheImovelBean")
@ViewScoped
public class DetalheImovelBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7757743129870117596L;

	@ManagedProperty("#{imovelService}")
	public ImovelService imovelService;
	private Imovel imovel;
	private String cidade;
	private double areaTotal;
	private String bairro;
	private String numeroDoImovel;
	
	public Anunciante anunciante;

	List<String> imagens;
	
	

	public List<String> getImagens() {
		return imagens;
	}

	public void setImagens(List<String> imagens) {
		this.imagens = imagens;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Imovel getImovel() {
		return imovel;
	}
	
	

	public double getAreaTotal() {
		return areaTotal;
	}

	public void setAreaTotal(double areaTotal) {
		this.areaTotal = areaTotal;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getNumeroDoImovel() {
		return numeroDoImovel;
	}

	public void setNumeroDoImovel(String numeroDoImovel) {
		this.numeroDoImovel = numeroDoImovel;
	}

	public ImovelService getImovelService() {
		return imovelService;
	}

	public void setImovelService(ImovelService imovelService) {
		this.imovelService = imovelService;
	}

	private Navegador navegador;

	private PrimeUtil primeUtil;

	@PostConstruct
	public void init() {
		
		this.imovel= imovelService.listarTodos().get(1);
		imagens = new ArrayList<String>();
		this.navegador = new Navegador();
		this.primeUtil = new PrimeUtil();
		/*
		this.anunciante = (Anunciante) UtilSession
				.getHttpSessionObject("anuncianteLogado");
		if (this.anunciante == null) {
			this.navegador.redirecionarPara("login.xhtml");
			return;

		}

		this.imovel = (Imovel) UtilSession
				.getHttpSessionObject("imovelSelecionado");
		if (this.imovel == null) {
			this.navegador.redirecionarPara("login.xhtml");
			return;

		}
*/
		List<Imagem> imgs = this.imovel.getImagems();
		for (Imagem mg : imgs) {

			this.imagens.add("C:/tomcat/webapps/imagens/"+mg.getIdImagem()+"_"+mg.getCaminhoImagem());
			
			
			
		}
		
		this.cidade = this.imovel.getCidade();
		
		this.areaTotal = this.imovel.getAreaTotal();
		this.numeroDoImovel = this.imovel.getNumeroDoImovel();
		
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	

}
