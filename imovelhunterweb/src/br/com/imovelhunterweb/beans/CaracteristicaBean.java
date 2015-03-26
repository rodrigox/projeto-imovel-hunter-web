package br.com.imovelhunterweb.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.imovelhunterweb.entitys.Anunciante;
import br.com.imovelhunterweb.entitys.Caracteristica;
import br.com.imovelhunterweb.enums.TipoUsuario;
import br.com.imovelhunterweb.service.CaracteristicaService;
import br.com.imovelhunterweb.util.Navegador;
import br.com.imovelhunterweb.util.PrimeUtil;
import br.com.imovelhunterweb.util.UtilSession;

@ManagedBean(name="caracteristicaBean")
@ViewScoped
public class CaracteristicaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2072943478976908813L;

	/**
	 * 
	 */
	private Caracteristica caracteristica;
	
	private Caracteristica caracteristicaSelecionada;
	
	private Navegador navegador;
	private PrimeUtil primeUtil;
	
	private Anunciante anunciante;
	
	@ManagedProperty("#{caracteristicaService}")
	private CaracteristicaService caracteristicaService;
	
	private List<Caracteristica> listaCaracteristica;
	
	private List<Caracteristica> listaCaracteristicaFiltered;
	
	private Boolean renderButtonDelete;
	
	private int index;
	
	@PostConstruct
	public void init(){
		this.navegador = new Navegador();
		this.primeUtil = new PrimeUtil();
		this.caracteristica = new Caracteristica();
		
		this.navegador = new Navegador();
		this.anunciante = (Anunciante)UtilSession.getHttpSessionObject("anuncianteLogado");
		if(this.anunciante == null){
			this.navegador.redirecionarPara("index.xhtml");
			return;
		}else if(this.anunciante.getTipoUsuario().equals(TipoUsuario.USUARIO)){
			this.navegador.redirecionarPara("index.xhtml");
			return;
		}
		this.renderButtonDelete = false;
		this.listaCaracteristica = this.caracteristicaService.listarTodos();
		
	}
	
	public void remover(){
		this.caracteristica = this.caracteristicaSelecionada != null ? this.caracteristicaSelecionada : this.caracteristica;
		if(this.caracteristicaService.remover(this.caracteristica)){			
			this.listaCaracteristica.remove(this.caracteristica);
			this.caracteristica = new Caracteristica();
			this.renderButtonDelete = false;			
			this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO,"Remover","Caracteristica removida com Sucesso");
			this.primeUtil.update("idFormMensagem");	
			this.primeUtil.update("idFormCaracteristicas");				
		}else{
			this.primeUtil.mensagem(FacesMessage.SEVERITY_ERROR,"Remover","Não foi possível remover a característica");
			this.primeUtil.update("idFormMensagem");	
			this.primeUtil.update("idFormCaracteristicas");
		}
		
	}

	public void onRowSelect(SelectEvent event){		
		this.caracteristica = this.caracteristicaSelecionada != null ? this.caracteristicaSelecionada : this.caracteristica;
		this.renderButtonDelete = true;
	}
	
	public void onRowUnselect(UnselectEvent event){	
		this.renderButtonDelete = false;
	}
	
	public void novo(){
		this.caracteristica = new Caracteristica();
		this.renderButtonDelete = false;
		this.primeUtil.update("idFormMensagem");	
		this.primeUtil.update("idFormCaracteristicas");
	}
	
	
	public void salvarCaracteristica (){
		this.caracteristica = this.caracteristicaSelecionada != null ? this.caracteristicaSelecionada : this.caracteristica;
		if(this.validarCampos()){
			
			if(this.renderButtonDelete){
				this.caracteristicaService.atualizar(this.caracteristica);				
				this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO,"Atualização","Caracteristica atulizada com Sucesso");
			}
			else{
				this.caracteristica = this.caracteristicaService.inserir(this.caracteristica);		
				
				
				this.listaCaracteristica.add(this.caracteristica);
				//Nessa parte exibe a mensagem e limpa todo o resto
				this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO,"Cadastro","Caracteristica Cadastrada com Sucesso");				
				this.caracteristica = new Caracteristica();
			}
		}
		
		this.primeUtil.update("idFormMensagem");	
		this.primeUtil.update("idFormCaracteristicas");		
	}
	
	
	
	private boolean validarCampos(){		
		if(this.caracteristica.getNome().length() == 0){
			this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO,"Campo inválido","O nome da característica deve ser informado");
			return false;
		}
		if(this.caracteristica.getDescricao().length() == 0){
			this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO,"Campo inválido","A característica deve ter uma descrição");
			return false;
		}
		
		
		return true;
	}	
	

	public Navegador getNavegador() {
		return navegador;
	}

	public void setNavegador(Navegador navegador) {
		this.navegador = navegador;
	}

	public void setCaracteristicaService(CaracteristicaService caracteristicaService) {
		this.caracteristicaService = caracteristicaService;
	}


	public Caracteristica getCaracteristica() {
		return caracteristica;
	}


	public void setCaracteristica(Caracteristica caracteristica) {
		this.caracteristica = caracteristica;
	}


	public List<Caracteristica> getListaCaracteristica() {
		return listaCaracteristica;
	}


	public List<Caracteristica> getListaCaracteristicaFiltered() {
		return listaCaracteristicaFiltered;
	}


	public Boolean getRenderButtonDelete() {
		return renderButtonDelete;
	}

	public Caracteristica getCaracteristicaSelecionada() {
		return caracteristicaSelecionada;
	}

	public void setCaracteristicaSelecionada(
			Caracteristica caracteristicaSelecionada) {
		this.caracteristicaSelecionada = caracteristicaSelecionada;
	}
}
