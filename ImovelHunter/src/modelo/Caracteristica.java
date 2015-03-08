package modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Caracteristica implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7879951292243680814L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idCaracteristica;
	
	private String nome;
	
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
