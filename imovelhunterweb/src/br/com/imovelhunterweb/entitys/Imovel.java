package br.com.imovelhunterweb.entitys;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.imovelhunterweb.enums.SituacaoImovel;



@Entity
public class Imovel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 369610495863986782L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idImovel;
	
	@Column
	private String numeroDoImovel;
	
	@Column
	private int numeroDeBanheiros;
	
	@Column
	private int numeroDeQuartos;
	
	@Column
	private int ambientes;
	
	@Column
	private String nomeDoProprietario;
	
	@Column
	private String descricao;
	
	@Column
	private int numeroDeSuites;
	
	@Enumerated(EnumType.STRING)
	private SituacaoImovel situacaoImovel;
	
	@Column
	private double areaTotal;
	
	@Column
	private double preco;
	
	@Column
	private String codigoDeReferencia;
	
	@Column
	private int numeroDeSalas;
	
	@Column
	private String logradouro; 
	
	@Column
	private String bairro;
	
	@Column
	private String cidade;
	
	@Column
	private String estado;
	
	@Column
	private String cep;
	
	@Column
	private String pais;
	
	@Column
	private String complemento;

	@ManyToOne
	private Anunciante anunciante;
	
	@ManyToMany
	private List<Caracteristica> caracteristicas;
	
	@OneToOne
	private PontoGeografico pontoGeografico;
	
	@OneToMany
	private List<PontoGeografico> cobertura;

	public long getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(long idImovel) {
		this.idImovel = idImovel;
	}

	public String getNumeroDoImovel() {
		return numeroDoImovel;
	}

	public void setNumeroDoImovel(String numeroDoImovel) {
		this.numeroDoImovel = numeroDoImovel;
	}

	public int getNumeroDeBanheiros() {
		return numeroDeBanheiros;
	}

	public void setNumeroDeBanheiros(int numeroDeBanheiros) {
		this.numeroDeBanheiros = numeroDeBanheiros;
	}

	public int getNumeroDeQuartos() {
		return numeroDeQuartos;
	}

	public void setNumeroDeQuartos(int numeroDeQuartos) {
		this.numeroDeQuartos = numeroDeQuartos;
	}

	public int getAmbientes() {
		return ambientes;
	}

	public void setAmbientes(int ambientes) {
		this.ambientes = ambientes;
	}

	public String getNomeDoProprietario() {
		return nomeDoProprietario;
	}

	public void setNomeDoProprietario(String nomeDoProprietario) {
		this.nomeDoProprietario = nomeDoProprietario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getNumeroDeSuites() {
		return numeroDeSuites;
	}

	public void setNumeroDeSuites(int numeroDeSuites) {
		this.numeroDeSuites = numeroDeSuites;
	}

	public SituacaoImovel getSituacaoImovel() {
		return situacaoImovel;
	}

	public void setSituacaoImovel(SituacaoImovel situacaoImovel) {
		this.situacaoImovel = situacaoImovel;
	}

	public double getAreaTotal() {
		return areaTotal;
	}

	public void setAreaTotal(double areaTotal) {
		this.areaTotal = areaTotal;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getCodigoDeReferencia() {
		return codigoDeReferencia;
	}

	public void setCodigoDeReferencia(String codigoDeReferencia) {
		this.codigoDeReferencia = codigoDeReferencia;
	}

	public int getNumeroDeSalas() {
		return numeroDeSalas;
	}

	public void setNumeroDeSalas(int numeroDeSalas) {
		this.numeroDeSalas = numeroDeSalas;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return this.cep;
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
	
	public Anunciante getAnunciante() {
		return anunciante;
	}

	public void setAnunciante(Anunciante anunciante) {
		this.anunciante = anunciante;
	}

	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(List<Caracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public PontoGeografico getPontoGeografico() {
		return pontoGeografico;
	}

	public void setPontoGeografico(PontoGeografico pontoGeografico) {
		this.pontoGeografico = pontoGeografico;
	}

	public List<PontoGeografico> getCobertura() {
		return cobertura;
	}

	public void setCobertura(List<PontoGeografico> cobertura) {
		this.cobertura = cobertura;
	}

	
}
