package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import enums.TipoContato;


@Entity
public class Contato implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6237409989577921090L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idContato;
	
	@ManyToOne
	private Anunciante anunciante;
	
	@ManyToOne
	private Imovel imovel;
	
	@Column
	private String numero;
	
	@Enumerated(EnumType.STRING)
	private TipoContato tipoContato;

	public long getIdContato() {
		return idContato;
	}

	public void setIdContato(long idContato) {
		this.idContato = idContato;
	}

	public Anunciante getAnunciante() {
		return anunciante;
	}

	public void setAnunciante(Anunciante anunciante) {
		this.anunciante = anunciante;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public TipoContato getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(TipoContato tipoContato) {
		this.tipoContato = tipoContato;
	}
	
	
	
	
}
