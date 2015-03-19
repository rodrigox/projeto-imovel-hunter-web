package br.com.imovelhunterweb.entitys;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Imagem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3305441889753631178L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idImagem;
	
	private String nome;
	
	private String descricao;
	
	private String caminhoImagem;
	
	@ManyToOne
	private Imovel imovel;



	public Imagem(String nome, String descricao, String caminhoImagem,
			Imovel imovel) {
		this.nome = nome;
		this.descricao = descricao;
		this.caminhoImagem = caminhoImagem;
		this.imovel = imovel;
	}

	public long getIdImagem() {
		return idImagem;
	}

	public void setIdImagem(long idImagem) {
		this.idImagem = idImagem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCaminhoImagem() {
		return caminhoImagem;
	}

	public void setCaminhoImagem(String caminhoImagem) {
		this.caminhoImagem = caminhoImagem;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	
	
	
}
