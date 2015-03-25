package br.com.imovelhunterweb.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.imovelhunterweb.entitys.Anunciante;
import br.com.imovelhunterweb.entitys.Caracteristica;
import br.com.imovelhunterweb.entitys.GrupoCaracteristica;
import br.com.imovelhunterweb.service.CaracteristicaService;
import br.com.imovelhunterweb.service.GrupoCarecteristicaService;
import br.com.imovelhunterweb.util.Navegador;
import br.com.imovelhunterweb.util.PrimeUtil;
import br.com.imovelhunterweb.util.UtilSession;

@ManagedBean(name = "grupoCaracteristicaBean")
@ViewScoped
public class GrupoCaracteristicaBean {

	
	@ManagedProperty("#{grupoCaracteristicaService}")
	private GrupoCarecteristicaService grupoCaracteristicaService;
	
	@ManagedProperty("#{caracteristicaService}")
	private CaracteristicaService caracteristicaService; 
	
	private List<Caracteristica> listaCaracteristica;
	
	private List<Caracteristica> listaCaracteristicasSelecionadas;
	
	private List<GrupoCaracteristica> listaGrupoCaracteristica;
	
	private GrupoCaracteristica grupoCaracteristica;
	
	private GrupoCaracteristica grupoCaracteristicaSelecionado;
	
	private List<Caracteristica> caracteristicasDoGrupoSelecionado;
	
	private Map<Long, Boolean> mapaCaracteristica;
	
	private Boolean renderSelectedGrupo;
	
	private PrimeUtil primeUtil;
	
	private Navegador navegador;
	
	private Anunciante anunciante;
	
	@PostConstruct
	public void init(){
		
		this.navegador = new Navegador();
		this.anunciante = (Anunciante)UtilSession.getHttpSessionObject("anuncianteLogado");
		if(this.anunciante == null){
			this.navegador.redirecionarPara("login.xhtml");
			return;
		}
		
		this.primeUtil = new PrimeUtil();
		
		this.navegador = new Navegador();
		
		this.grupoCaracteristica = new GrupoCaracteristica();
		
		this.mapaCaracteristica = new HashMap<Long, Boolean>();
		
		this.listaCaracteristicasSelecionadas = new ArrayList<Caracteristica>();
		
		this.listaCaracteristica = this.caracteristicaService.listarTodos();
		
		this.listaGrupoCaracteristica = this.grupoCaracteristicaService.listarTodos();
		
		this.renderSelectedGrupo = false;
		
	}
	
	public void salvar(){
		
		this.grupoCaracteristica = this.grupoCaracteristicaSelecionado != null ? this.grupoCaracteristicaSelecionado : this.grupoCaracteristica;
		
		if(this.validarCampos()){
			this.setarCaracteristicasSelecionadas();
			this.grupoCaracteristica.setCaracteristicas(this.listaCaracteristicasSelecionadas);
			
			//Atualiza
			if(this.renderSelectedGrupo){
				this.grupoCaracteristicaService.atualizar(this.grupoCaracteristica);
			}
			//Salva
			else{
				this.grupoCaracteristicaService.inserir(this.grupoCaracteristica);
				this.listaGrupoCaracteristica.add(this.grupoCaracteristica);
				this.grupoCaracteristica = new GrupoCaracteristica();
				this.mapaCaracteristica.clear();
			}		
			
		}
		
	}
	
	public void remover(){
		this.grupoCaracteristica = this.grupoCaracteristicaSelecionado != null ? this.grupoCaracteristicaSelecionado : this.grupoCaracteristica;
	
	}	
	
	
	public void novo(){
		this.mapaCaracteristica.clear();
		this.grupoCaracteristica = new GrupoCaracteristica();
		this.renderSelectedGrupo = false;
	}
	
	
	private boolean validarCampos(){
		return true;
	}
		
	
	public void onRowSelect(SelectEvent event){		
		this.grupoCaracteristica = this.grupoCaracteristicaSelecionado != null ? this.grupoCaracteristicaSelecionado : this.grupoCaracteristica;
		this.setarCaracteristicasDoGrupoSelecionado();
		this.renderSelectedGrupo = true;
	}
	
	public void onRowUnselect(UnselectEvent event){	
		this.renderSelectedGrupo = false;
	}

	
	
	/**
	 * Pega as características que o usuário selecinou e joga em uma lista global privado do escopo da classe
	 */
	private void setarCaracteristicasSelecionadas(){
		this.listaCaracteristicasSelecionadas.clear();
		
		for(Caracteristica carac : this.listaCaracteristica){
			if(this.mapaCaracteristica.get(carac.getIdCaracteristica())){
				this.listaCaracteristicasSelecionadas.add(carac);
			}
		}
		
	}
	
	/**
	 * Seta as características do grupo selecionado nos checkbox's
	 */
	private void setarCaracteristicasDoGrupoSelecionado(){
		this.caracteristicasDoGrupoSelecionado = this.grupoCaracteristica.getCaracteristicas();
		
		if(this.caracteristicasDoGrupoSelecionado != null){
			for(Caracteristica carac : this.caracteristicasDoGrupoSelecionado){			
				this.mapaCaracteristica.put(carac.getIdCaracteristica(),true);
			}
		}
		
	}
	
	
	public void setGrupoCaracteristicaService(
			GrupoCarecteristicaService grupoCaracteristicaService) {
		this.grupoCaracteristicaService = grupoCaracteristicaService;
	}

	public void setCaracteristicaService(CaracteristicaService caracteristicaService) {
		this.caracteristicaService = caracteristicaService;
	}

	public List<Caracteristica> getListaCaracteristica() {
		return listaCaracteristica;
	}

	public void setListaCaracteristica(List<Caracteristica> listaCaracteristica) {
		this.listaCaracteristica = listaCaracteristica;
	}

	public List<GrupoCaracteristica> getListaGrupoCaracteristica() {
		return listaGrupoCaracteristica;
	}

	public void setListaGrupoCaracteristica(
			List<GrupoCaracteristica> listaGrupoCaracteristica) {
		this.listaGrupoCaracteristica = listaGrupoCaracteristica;
	}

	public GrupoCaracteristica getGrupoCaracteristica() {
		return grupoCaracteristica;
	}

	public void setGrupoCaracteristica(GrupoCaracteristica grupoCaracteristica) {
		this.grupoCaracteristica = grupoCaracteristica;
	}

	public GrupoCaracteristica getGrupoCaracteristicaSelecionado() {
		return grupoCaracteristicaSelecionado;
	}

	public void setGrupoCaracteristicaSelecionado(
			GrupoCaracteristica grupoCaracteristicaSelecionado) {
		this.grupoCaracteristicaSelecionado = grupoCaracteristicaSelecionado;
	}

	public Map<Long, Boolean> getMapaCaracteristica() {
		return mapaCaracteristica;
	}

	public void setMapaCaracteristica(Map<Long, Boolean> mapaCaracteristica) {
		this.mapaCaracteristica = mapaCaracteristica;
	}

	public Boolean getRenderSelectedGrupo() {
		return renderSelectedGrupo;
	}
	
}
