package br.com.imovelhunterweb.beans;

import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.sun.faces.facelets.PrivateApiFaceletCacheAdapter;

import br.com.imovelhunterweb.entitys.Anunciante;
import br.com.imovelhunterweb.entitys.Caracteristica;
import br.com.imovelhunterweb.entitys.Imagem;
import br.com.imovelhunterweb.entitys.Imovel;
import br.com.imovelhunterweb.enums.SituacaoImovel;
import br.com.imovelhunterweb.enums.TipoImovel;
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
	private int ambientes;
	private String nomedoProprietario;
	private String descricao;
	private int numerodeQuartos;
	private int numeroDeBanheiros;
	private int numeroDeSuites;
	private SituacaoImovel situacao;
	private double preco;
	private int numeroDeSalas;
	private String estado;
	private String cep;
	private String pais;
	private String complemento;
	private String nomeAnunciante;
	private TipoImovel tipoDeImovel;
	private List<Caracteristica>caracteristicas;
	private String logradouro;
	
	
	
	public Anunciante anunciante;

	List<String> imagens;
	
	private Navegador navegador;

	private PrimeUtil primeUtil;


	
	
	
	@PostConstruct
	public void init() {
		
		this.imovel= imovelService.listarTodos().get(2);
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

			//this.imagens.add("C:/tomcat/webapps/imagens/"+mg.getIdImagem()+"_"+mg.getCaminhoImagem());
			this.imagens.add(mg.getIdImagem()+"_"+mg.getCaminhoImagem());
			
			
		}
		
		this.cidade = this.imovel.getCidade();
		
		this.areaTotal = this.imovel.getAreaTotal();
		this.numeroDoImovel = this.imovel.getNumeroDoImovel();
		this.bairro = this.imovel.getBairro();
		this.ambientes = this.imovel.getAmbientes();
		this.nomedoProprietario = this.imovel.getNomeDoProprietario();
		this.descricao = this.imovel.getDescricao();
		this.numerodeQuartos = this.imovel.getNumeroDeQuartos();
		this.numeroDeBanheiros = this.imovel.getNumeroDeBanheiros();
		this.numeroDeSuites = this.imovel.getNumeroDeSuites();
		this.situacao =  this.imovel.getSituacaoImovel();
		this.preco = this.imovel.getPreco();
		this.numeroDeSalas = this.imovel.getNumeroDeSalas();
		this.estado = this.imovel.getEstado();
		this.cep = this.imovel.getCep();
		this.pais = this.imovel.getPais();
		this.complemento = this.imovel.getComplemento();
		this.nomeAnunciante = this.imovel.getAnunciante().getNome();
		this.tipoDeImovel = this.imovel.getTipoImovel();
		this.logradouro = this.imovel.getLogradouro();
		
	}

	
	
	
	
	
	
	
	
	public String getLogradouro() {
		return logradouro;
	}









	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}









	public int getAmbientes() {
		return ambientes;
	}







	public void setAmbientes(int ambientes) {
		this.ambientes = ambientes;
	}







	public String getNomedoProprietario() {
		return nomedoProprietario;
	}







	public void setNomedoProprietario(String nomedoProprietario) {
		this.nomedoProprietario = nomedoProprietario;
	}







	public String getDescricao() {
		return descricao;
	}







	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}







	public int getNumerodeQuartos() {
		return numerodeQuartos;
	}







	public void setNumerodeQuartos(int numerodeQuartos) {
		this.numerodeQuartos = numerodeQuartos;
	}







	public int getNumeroDeBanheiros() {
		return numeroDeBanheiros;
	}







	public void setNumeroDeBanheiros(int numeroDeBanheiros) {
		this.numeroDeBanheiros = numeroDeBanheiros;
	}







	public int getNumeroDeSuites() {
		return numeroDeSuites;
	}







	public void setNumeroDeSuites(int numeroDeSuites) {
		this.numeroDeSuites = numeroDeSuites;
	}







	public SituacaoImovel getSituacao() {
		return situacao;
	}







	public void setSituacao(SituacaoImovel situacao) {
		this.situacao = situacao;
	}







	public double getPreco() {
		return preco;
	}







	public void setPreco(double preco) {
		this.preco = preco;
	}







	public int getNumeroDeSalas() {
		return numeroDeSalas;
	}







	public void setNumeroDeSalas(int numeroDeSalas) {
		this.numeroDeSalas = numeroDeSalas;
	}







	public String getEstado() {
		return estado;
	}







	public void setEstado(String estado) {
		this.estado = estado;
	}







	public String getCep() {
		return cep;
	}







	public void setCep(String cep) {
		this.cep = cep;
	}







	public String getPais() {
		return pais;
	}







	public void setPais(String pais) {
		this.pais = pais;
	}







	public String getComplemento() {
		return complemento;
	}







	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}







	public String getNomeAnunciante() {
		return nomeAnunciante;
	}







	public void setNomeAnunciante(String nomeAnunciante) {
		this.nomeAnunciante = nomeAnunciante;
	}







	public TipoImovel getTipoDeImovel() {
		return tipoDeImovel;
	}







	public void setTipoDeImovel(TipoImovel tipoDeImovel) {
		this.tipoDeImovel = tipoDeImovel;
	}







	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}







	public void setCaracteristicas(List<Caracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}







	public Anunciante getAnunciante() {
		return anunciante;
	}







	public void setAnunciante(Anunciante anunciante) {
		this.anunciante = anunciante;
	}







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

	
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	
	
	

}
