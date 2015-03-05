package br.com.imovelhunterweb.entitys;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Bloqueio implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7816181546141658762L;

	@Id
	@GeneratedValue
	private long idBloqueio;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataBloqueio;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataVencimento;
	
	@ManyToOne
	private Usuario usuarioBloqueador;
	
	@ManyToOne
	private Usuario usuarioBloqueado;

	public long getIdBloqueio() {
		return idBloqueio;
	}

	public void setIdBloqueio(long idBloqueio) {
		this.idBloqueio = idBloqueio;
	}

	public Date getDataBloqueio() {
		return dataBloqueio;
	}

	public void setDataBloqueio(Date dataBloqueio) {
		this.dataBloqueio = dataBloqueio;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Usuario getUsuarioBloqueador() {
		return usuarioBloqueador;
	}

	public void setUsuarioBloqueador(Usuario usuarioBloqueador) {
		this.usuarioBloqueador = usuarioBloqueador;
	}

	public Usuario getUsuarioBloqueado() {
		return usuarioBloqueado;
	}

	public void setUsuarioBloqueado(Usuario usuarioBloqueado) {
		this.usuarioBloqueado = usuarioBloqueado;
	}
	
	
	
	
	
}
