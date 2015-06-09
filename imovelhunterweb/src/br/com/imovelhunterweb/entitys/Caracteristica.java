package br.com.imovelhunterweb.entitys;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CollectionId;

import br.com.imovelhunterweb.util.ObjetoJSON;

@Entity
public class Caracteristica extends ObjetoJSON<Caracteristica> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7879951292243680814L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idCaracteristica;
	
	@Column
	private String nome;
	
	@Column
	private String descricao;

	public long getIdCaracteristica() {
		return idCaracteristica;
	}

	public void setIdCaracteristica(long idCaracteristica) {
		this.idCaracteristica = idCaracteristica;
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
	
	
}
