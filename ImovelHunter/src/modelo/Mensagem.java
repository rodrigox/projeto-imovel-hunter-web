package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
public class Mensagem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6581716970465655451L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idMensagem;
	
	@Column
	private String mensagem;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEnvio;	
	
	@ManyToOne
	private Usuario usuarioRemetente;
	
	@ManyToMany
	private List<Usuario> usuariosDestino;
	
	

	public long getIdMensagem() {
		return idMensagem;
	}

	public void setIdMensagem(long idMensagem) {
		this.idMensagem = idMensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public Usuario getUsuarioRemetente() {
		return usuarioRemetente;
	}

	public void setUsuarioRemetente(Usuario usuarioRemetente) {
		this.usuarioRemetente = usuarioRemetente;
	}

	public List<Usuario> getUsuariosDestino() {
		return usuariosDestino;
	}

	public void setUsuariosDestino(List<Usuario> usuariosDestino) {
		this.usuariosDestino = usuariosDestino;
	}
	
	
	
	
}
