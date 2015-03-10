package br.com.imovelhunterweb.beans;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.imovelhunterweb.dao.imp.AnuncianteDAOImp;
import br.com.imovelhunterweb.entitys.Anunciante;
import br.com.imovelhunterweb.service.AnuncianteService;
import br.com.imovelhunterweb.service.imp.AnuncianteServiceImp;


@ManagedBean(name ="anuncianteBean")
@ViewScoped
public class AnuncianteBean {
	
	
	private Anunciante anunciante;
	private String confirmarSenha;
	
	@PostConstruct
	public void init(){		
		this.anunciante = new Anunciante();	
		this.confirmarSenha = "";
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
		System.out.println("eeeeeeeeeeerrrrrrrrrrrrooooooooooooooooooooooo");
		try{
		AnuncianteService serv = new AnuncianteServiceImp();
		System.out.println(this.anunciante.getNome());
		System.out.println(this.anunciante.getDataDeNascimento().toString());
		
		this.anunciante.setDataDeNascimento(new Date());
		serv.inserir(this.anunciante);
		}catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println("eeeeeeeeeeerrrrrrrrrrrrooooooooooooooooooooooo");

		}
		System.out.println("ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");

	} 
	


}
