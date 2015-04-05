package br.com.imovelhunterweb.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.imovelhunterweb.service.AnuncianteService;
import br.com.imovelhunterweb.util.EmailValidator;
import br.com.imovelhunterweb.util.PrimeUtil;

@ManagedBean(name = "faleConoscoBean")
@ViewScoped
public class FaleConoscoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2465262372102338614L;
	private String nome;
	private String email;
	private String telefoneFixo;
	private String telefoneCelular;
	private String mensagem;
	private String assunto;

	private PrimeUtil primeUtil;

	@ManagedProperty("#{anuncianteService}")
	private AnuncianteService anuncianteService;

	@PostConstruct
	public void init() {
		this.primeUtil = new PrimeUtil();
	}

	public void enviarMensagem() {
		if (this.validarCampos()) {

			String mensagemEnvio = "email: " + this.email + "\r\nTelefone: "
					+ this.telefoneCelular + "\r\nTelefone fixo: "
					+ this.telefoneFixo + "\r\nMensagem: \r\n";
			mensagemEnvio += this.mensagem;

			Object[] resposta = this.anuncianteService.enviarMensagem(
					this.assunto, mensagemEnvio);
			// EmailValidator validadorDeEmail = new EmailValidator();
			// validadorDeEmail.isEmailValid("");

			boolean resultado = (Boolean) resposta[0];
			String mensagem = (String) resposta[1];

			if (resultado) {
				this.mensagem = "";
				this.email = "";
				this.telefoneCelular = "";
				this.assunto = "";
				this.nome = "";

				// Nessa parte exibe a mensagem e limpa todo o resto
				this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO, "Enviado",
						mensagem);
			} else {
				// Nessa parte exibe a mensagem de erro
				this.primeUtil.mensagem(FacesMessage.SEVERITY_ERROR, "Erro",
						mensagem);
			}
			this.primeUtil.update("idFormFaleConosco");
			this.primeUtil.update("idFormMensagem");
		}
	}

	private boolean validarCampos() {
		if (this.nome == null || this.nome.trim().equals("")) {
			this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO,
					"Campo inválido", "Informe o nome para identificação.");
			this.primeUtil.update("idFormMensagem");
			return false;
		}

		EmailValidator validator = new EmailValidator();
		if (!validator.isEmailValid(this.email)) {
			this.primeUtil
					.mensagem(FacesMessage.SEVERITY_INFO, "Campo inválido",
							"Informe o E-mail para que possamos entrar em contato com você.");
			this.primeUtil.update("idFormMensagem");
			return false;
		}

		if (this.mensagem == null || this.mensagem.trim().equals("")) {
			this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO,
					"Campo inválido",
					"Informe a mensagem para que possamos lhe ajudar.");
			this.primeUtil.update("idFormMensagem");
			return false;
		}

		if (this.assunto == null || this.assunto.trim().equals("")) {
			this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO,
					"Campo inválido", "Informe o assunto da menssagem.");
			this.primeUtil.update("idFormMensagem");
			return false;
		}
		return true;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefoneFixo() {
		return telefoneFixo;
	}

	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public void setAnuncianteService(AnuncianteService anuncianteService) {
		this.anuncianteService = anuncianteService;
	}
}
