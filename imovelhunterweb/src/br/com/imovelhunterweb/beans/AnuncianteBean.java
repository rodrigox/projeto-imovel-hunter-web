package br.com.imovelhunterweb.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.imovelhunterweb.entitys.Anunciante;
import br.com.imovelhunterweb.service.AnuncianteService;
import br.com.imovelhunterweb.util.PrimeUtil;


@ManagedBean(name ="anuncianteBean")
@ViewScoped
public class AnuncianteBean implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2842024280524632375L;
	private Anunciante anunciante;
	private String confirmarSenha;
	
	/**
	 * Este atributo é o que irá utilizar a persistencia do anunciante, O spring cuida da parte de fazer a injeção de dependência desse atributo
	 * por sessão do escopo criado de cada usuário, para que funcione deve ser colocado o set deste atributo, para que o 
	 * spring consiga injetar o service corretamente
	 */
	@ManagedProperty("#{anuncianteService}")
	private AnuncianteService anuncianteService;
	
	/**
	 * Esse atributo serve para você mandar mensagem para tela do usuário,
	 * atualizar componentes da tela, e mandar executar algum javascript de algum componente da tela também
	 */
	private PrimeUtil primeUtil;
	
	
	@PostConstruct
	public void init(){		
		this.anunciante = new Anunciante();	
		this.confirmarSenha = "";
		
		this.primeUtil = new PrimeUtil();
	}
	
	
	public Anunciante getAnunciante() {
		return anunciante;
	}

	public void setAnunciante(Anunciante anunciante) {
		this.anunciante = anunciante;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}
	
	
	public void cadastrarAnunciante(ActionEvent actionEvent){
		try{
			this.anunciante = this.anuncianteService.inserir(this.anunciante);
			//Valida se o anunciante foi inserido
			if(this.anunciante.getIdAnunciante() != 0 ){
				//Exibe uma mensagem para o usuário
				this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO,"Cadastro","Anunciante cadastrado com sucesso");
				//Instancia um novo anunciante para que seja cadastrado um novo
				this.anunciante = new Anunciante();
				//Limpa a caixinha do confirmar senha
				this.confirmarSenha = "";
				//Exibe uma mensagem para o usuário
				this.primeUtil.update("cadastroAnunciantes");
			}else{
				//Exibe uma mensagem para o usuário
				this.primeUtil.mensagem(FacesMessage.SEVERITY_ERROR,"Cadastro","Erro ao cadastrar o anunciante");
			}
		}catch(Exception e){
			e.printStackTrace();
			//Exibe uma mensagem para o usuário
			this.primeUtil.mensagem(FacesMessage.SEVERITY_ERROR,"Erro","Erro ao cadastrar o anunciante");
		}
		//Atualiza o componente que exibe a mensagem para o usuário, para que ele exiba
		this.primeUtil.update("idFormMensagem");
	}


	public void setAnuncianteService(AnuncianteService anuncianteService) {
		this.anuncianteService = anuncianteService;
	} 
	


}
