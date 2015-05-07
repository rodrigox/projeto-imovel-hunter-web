package br.com.imovelhunterweb.entitys;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.imovelhunterweb.enums.TipoUsuario;

@Entity
public class Anunciante implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7132428884709032729L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idAnunciante;	
	
	@Column
	private String telefone;
	

	
	@Column
	private String nome;
	
	@Column
	private String sobreNome;
	
	@Column
	private String email;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataDeNascimento;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataDeVencimento;
	
	@Column
	private String creci;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataDeCriacao;
	
	@Column
	private String cpf;
	
	@Column
	private String login;
	
	@Column
	private String senha;
	
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipoUsuario;
	
	public Anunciante(){
		this.tipoUsuario = TipoUsuario.USUARIO;
	}

	public long getIdAnunciante() {
		return idAnunciante;
	}

	public void setIdAnunciante(long idAnunciante) {
		this.idAnunciante = idAnunciante;
	}	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public Date getDataDeVencimento() {
		return dataDeVencimento;
	}

	public void setDataDeVencimento(Date dataDeVencimento) {
		this.dataDeVencimento = dataDeVencimento;
	}

	public String getCreci() {
		return creci;
	}

	public void setCreci(String creci) {
		this.creci = creci;
	}

	public Date getDataDeCriacao() {
		return dataDeCriacao;
	}

	public void setDataDeCriacao(Date dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}	
	
	
	
	
}
