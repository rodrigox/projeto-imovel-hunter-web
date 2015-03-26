package br.com.imovelhunterweb.entitys;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class GrupoCaracteristica implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1867080893607777384L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idGrupoCaracteristica;
	
	@Column
	private String nome;
	
	@Column
	private String descricao;	
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Caracteristica> caracteristicas;

	public long getIdGrupoCaracteristica() {
		return idGrupoCaracteristica;
	}

	public void setIdGrupoCaracteristica(long idGrupoCaracteristica) {
		this.idGrupoCaracteristica = idGrupoCaracteristica;
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

	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(List<Caracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	
	
	
}
