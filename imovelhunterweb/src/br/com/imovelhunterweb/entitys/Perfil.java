package br.com.imovelhunterweb.entitys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.imovelhunterweb.enums.SituacaoImovel;
import br.com.imovelhunterweb.enums.TipoImovel;
import br.com.imovelhunterweb.util.ObjetoJSON;

@Entity
public class Perfil extends ObjetoJSON<Perfil> implements Serializable {
	
	
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -5462524156925002842L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idPerfil;

	@ManyToOne
    private Usuario usuario;
    
    @Enumerated(EnumType.STRING)
    private SituacaoImovel situacaoImovel;

    @Column
    private double valor;

    @Enumerated(EnumType.STRING)
    private TipoImovel tipo;

    @Column
    private String uf;
    
    @Column
    private String cidade;

    @Column
    private String bairro;

    @Column
    private Integer qtQuartos;


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

   

    public long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public void setQtQuartos(Integer qtQuartos) {
		this.qtQuartos = qtQuartos;
	}

	
    public SituacaoImovel getSituacaoImovel() {
		return situacaoImovel;
	}

	public void setSituacaoImovel(SituacaoImovel situacaoImovel) {
		this.situacaoImovel = situacaoImovel;
	}

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public TipoImovel getTipo() {
        return tipo;
    }

    public void setTipo(TipoImovel tipo) {
        this.tipo = tipo;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Integer getQtQuartos() {
        return qtQuartos;
    }

    public void setQtQuartos(int qtQuartos) {
        this.qtQuartos = qtQuartos;
    }
}
