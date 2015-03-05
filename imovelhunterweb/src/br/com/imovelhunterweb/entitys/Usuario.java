package br.com.imovelhunterweb.entitys;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Usuario implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4633881873007954301L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idUsuario;
	
	@OneToOne
	private Anunciante anunciante;
	
	@Column
	private String chaveGCM;
	
	@Column
	private String serialDispositivo;

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Anunciante getAnunciante() {
		return anunciante;
	}

	public void setAnunciante(Anunciante anunciante) {
		this.anunciante = anunciante;
	}	

	public String getChaveGCM() {
		return chaveGCM;
	}

	public void setChaveGCM(String chaveGCM) {
		this.chaveGCM = chaveGCM;
	}

	public String getSerialDispositivo() {
		return serialDispositivo;
	}

	public void setSerialDispositivo(String serialDispositivo) {
		this.serialDispositivo = serialDispositivo;
	}

	
}
