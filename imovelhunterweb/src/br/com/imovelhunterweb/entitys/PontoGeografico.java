package br.com.imovelhunterweb.entitys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PontoGeografico implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2078039141469619425L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idPontoGeografico;
	
	@Column
	private double longitude;
	
	@Column
	private double latitude;

	public long getIdPontoGeografico() {
		return idPontoGeografico;
	}

	public void setIdPontoGeografico(long idPontoGeografico) {
		this.idPontoGeografico = idPontoGeografico;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
}
