package br.com.imovelhunterweb.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.com.imovelhunterweb.entitys.Anunciante;
import br.com.imovelhunterweb.entitys.Caracteristica;
import br.com.imovelhunterweb.entitys.Imagem;
import br.com.imovelhunterweb.entitys.Imovel;
import br.com.imovelhunterweb.enums.SituacaoImovel;
import br.com.imovelhunterweb.enums.TipoImovel;
import br.com.imovelhunterweb.service.AnuncianteService;
import br.com.imovelhunterweb.service.CaracteristicaService;
import br.com.imovelhunterweb.service.ImagemService;
import br.com.imovelhunterweb.service.ImovelService;
import br.com.imovelhunterweb.service.PontoGeograficoService;
import br.com.imovelhunterweb.util.ConsultaCEP;
import br.com.imovelhunterweb.util.EnderecoImagens;
import br.com.imovelhunterweb.util.Navegador;
import br.com.imovelhunterweb.util.PrimeUtil;
import br.com.imovelhunterweb.util.UtilSession;

@ManagedBean(name = "editarImovelBean")
@ViewScoped
public class EditarImovelBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5953013392455530029L;
	private Imovel imovel;
	private Anunciante anunciante;
	private List<Imagem> imovelImagens;
	private Navegador navegador;
	private PrimeUtil primeUtil;
	private String selectedItem;
	private Map<Long, Boolean> checked = new HashMap<Long, Boolean>();
	private Caracteristica caracteristica;
	private List<Caracteristica> allCacaracteristicas;
	private boolean skip;
	private int valorImovel;
	private EnderecoImagens enderecoImagens;
	private String cep;
	
	private MapModel draggableModel;

	private Marker marker;
	
	private int zoom;



	@ManagedProperty("#{imovelService}")
	private ImovelService imovelService;

	@ManagedProperty("#{pontoGeograficoService}")
	private PontoGeograficoService pontoGeograficoService;

	@ManagedProperty("#{imagemService}")
	private ImagemService imagemService;

	@ManagedProperty("#{anuncianteService}")
	private AnuncianteService anuncianteService;

	@ManagedProperty("#{caracteristicaService}")
	private CaracteristicaService caracteristicaService;

	@PostConstruct
	public void init() {
		this.navegador = new Navegador();
		this.anunciante = (Anunciante) UtilSession
				.getHttpSessionObject("anuncianteLogado");
		if (this.anunciante == null) {
			this.navegador.redirecionarPara("login.xhtml");
			return;
		}
		this.draggableModel = new DefaultMapModel();
		this.enderecoImagens = new EnderecoImagens();
		System.out.println(getEnderecoImagens().getCaminhoServidor());
		this.navegador = new Navegador();
		this.primeUtil = new PrimeUtil();
		this.imovelImagens = new ArrayList<Imagem>();
		this.imovel = (Imovel) UtilSession.getHttpSessionObject("imovelSelecionado");
		this.cep = this.imovel.getCep();
//		this.valorImovel = String.valueOf(this.imovel.getPreco());
		this.valorImovel = (int)this.imovel.getPreco();
		this.allCacaracteristicas = this.caracteristicaService.listarTodos();
		this.zoom = 17;
		deletarTemp(new File(enderecoImagens.getCaminhoTemp()));
		carregarCaracteristicaSelecionadas();
		carregarImagensSelecionadas();
	}
	
	public int getZoom(){
		return this.zoom;
	}
	
	public void getZoom(int zoom){
		this.zoom = zoom;
	}

	public List<Imagem> getImovelImagens() {
		return imovelImagens;
	}

	public void setImovelImagens(List<Imagem> imovelImagens) {
		this.imovelImagens = imovelImagens;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Anunciante getAnunciante() {
		return anunciante;
	}

	public void setAnunciante(Anunciante anunciante) {
		this.anunciante = anunciante;
	}
	
	public MapModel getDraggableModel() {
		return draggableModel;
	}	

	public ImovelService getImovelService() {
		return imovelService;
	}

	public void setImovelService(ImovelService imovelService) {
		this.imovelService = imovelService;
	}

	public AnuncianteService getAnuncianteService() {
		return anuncianteService;
	}

	public void setAnuncianteService(AnuncianteService anuncianteService) {
		this.anuncianteService = anuncianteService;
	}

	public CaracteristicaService getCaracteristicaService() {
		return caracteristicaService;
	}

	public void setCaracteristicaService(
			CaracteristicaService caracteristicaService) {
		this.caracteristicaService = caracteristicaService;
	}

	public PontoGeograficoService getPontoGeograficoService() {
		return pontoGeograficoService;
	}

	public void setPontoGeograficoService(
			PontoGeograficoService pontoGeograficoService) {
		this.pontoGeograficoService = pontoGeograficoService;
	}

	public ImagemService getImagemService() {
		return imagemService;
	}

	public void setImagemService(ImagemService imagemService) {
		this.imagemService = imagemService;
	}

	public Navegador getNavegador() {
		return navegador;
	}

	public void setNavegador(Navegador navegador) {
		this.navegador = navegador;
	}

	public PrimeUtil getPrimeUtil() {
		return primeUtil;
	}

	public void setPrimeUtil(PrimeUtil primeUtil) {
		this.primeUtil = primeUtil;
	}

	public String getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}

	public Map<Long, Boolean> getChecked() {
		return checked;
	}

	public void setChecked(Map<Long, Boolean> checked) {
		this.checked = checked;
	}

	public Caracteristica getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(Caracteristica caracteristica) {
		this.caracteristica = caracteristica;
	}

	public List<Caracteristica> getAllCacaracteristicas() {
		return allCacaracteristicas;
	}

	public void setAllCacaracteristicas(
			List<Caracteristica> allCacaracteristicas) {
		this.allCacaracteristicas = allCacaracteristicas;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public String getCep() {
		return cep;
	}

	public EnderecoImagens getEnderecoImagens() {
		return enderecoImagens;
	}	
	
	public void setCep(String cep) {
		this.cep = cep;
		this.imovel.setCep(cep);
		ConsultaCEP endereco = new ConsultaCEP(cep);
		this.imovel.setEstado(endereco.getEstado());
		this.imovel.setLogradouro(endereco.getLogradouro());
		this.imovel.setCidade(endereco.getCidade());
		this.imovel.setBairro(endereco.getBairro());
	}

	public int getValorImovel() {
		return valorImovel;
	}

	public void setValorImovel(int valorImovel) {
		this.valorImovel = valorImovel;
	}

	// MÉTODOS PARA EXECUÇÃO:

	public void imagensNoTemp() {

		File pastaImagens = new File(enderecoImagens.getCaminhoTemp());
		if (!pastaImagens.exists())
			pastaImagens.mkdirs();
		File[] arquivos = pastaImagens.listFiles();

		for (File arquivo : arquivos) {
			if (arquivo.isFile()) {

				String ext = arquivo.getName()
						.substring(arquivo.getName().lastIndexOf("."))
						.toLowerCase();
				if (ext.equals(".jpg") || ext.equals(".jpeg")
						|| ext.equals(".bmp") || ext.equals(".gif")
						|| ext.equals(".png")) {
					boolean registro = false;
					for (Imagem im : this.imovelImagens) {
						if (im.getCaminhoImagem().equals(arquivo.getName())) {
							registro = true;
							break;
						}
					}
					if (!registro) {
						Imagem img = new Imagem(null, null, arquivo.getName(),
								null);
						this.imovelImagens.add(img);
					}
				}
			}
		}
	}
	
	public void carregarCaracteristicaSelecionadas(){
		this.checked.clear();
		for(Caracteristica allCaract : this.allCacaracteristicas){
			boolean marcado = false;
			for(Caracteristica cadCaract : this.imovel.getCaracteristicas()){
				if(allCaract.getIdCaracteristica() == cadCaract.getIdCaracteristica()){
					marcado = true;
					break;
				}
			}
			this.checked.put(allCaract.getIdCaracteristica(), marcado);
		}
	}
	public void carregarImagensSelecionadas(){
		this.imovelImagens = this.imovel.getImagens();
	}

	public void upload(FileUploadEvent event) {
		Imagem img = new Imagem();
		try {
			byte[] foto = event.getFile().getContents();
			
			String nomeArquivo = event.getFile().getFileName();
			
			img = this.imagemService.inserir(new Imagem(nomeArquivo, this.imovel));
			img.setCaminhoImagem(img.getIdImagem()+"_"+img.getCaminhoImagem());
			this.imovelImagens.add(this.imagemService.atualizar(img));
			File f = new File(enderecoImagens.getCaminhoServidor() + img.getIdImagem()+"_"+nomeArquivo);
			if (!f.getParentFile().exists())
				f.getParentFile().mkdirs();

			if (!f.exists())
				f.createNewFile();

			FileOutputStream fos = new FileOutputStream(f);

			fos.write(foto);
			fos.flush();
			fos.close();

			
		} catch (IOException e) {
			this.imagemService.remover(img);
			e.printStackTrace();
		}
	}

	public void removerImagem(Imagem imagem) {
		for (Imagem im : this.imovelImagens) {
			if (im.getIdImagem() == (imagem.getIdImagem())) {
				this.imovelImagens.remove(im);
//				this.imagemService.removerPorId(imagem);
				this.imovel.getImagens().remove(im);
				this.imovelService.atualizar(this.imovel);
				im = this.imagemService.buscarPorId(im.getIdImagem());
				this.imagemService.remover(im);
				break;
			}
		}
		File f = new File(enderecoImagens.getCaminhoServidor() + imagem.getCaminhoImagem());
		f.delete();
		this.primeUtil.update("idFormWizard");
	}

	public void cadastrarImovel() {
		this.imovel.setCaracteristicas(new ArrayList<Caracteristica>());
		for (Caracteristica caract : this.allCacaracteristicas) {
			if (checked.get(caract.getIdCaracteristica())) {
				this.imovel.getCaracteristicas().add(caract);

			}
		}
		///checked.clear();

//		LocalizacaoUtil localizacao = new LocalizacaoUtil();
//		try {
////			PontoGeografico pg = pontoGeograficoService.inserir(localizacao
////					.getPontoGeografico(this.imovel.getLogradouro() + ", "
////							+ this.imovel.getNumeroDoImovel() + ", "
////							+ this.imovel.getBairro() + ", "
////							+ this.imovel.getCidade()));
////
////			this.imovel.setPontoGeografico(pg);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		this.imovel.setAnunciante(this.anunciante);
		this.imovel.setImagens(this.imovelImagens);

		Imovel im = this.imovelService.atualizar(this.imovel);
		if (im != null) {
			for (Imagem img : this.imovelImagens) {
				this.imagemService.atualizar(img);
			}

			this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO, "Cadastro",
					"Imóvel alterado com sucesso.");			
			this.navegador.redirecionarPara("listarImovel.xhtml");
			this.primeUtil.update("idFormMensagem");

		} else {
			this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO, "Erro",
					"Não foi possível alterar o imóvel.");
			this.primeUtil.update("idFormMensagem");
		}
	}

	public void enviarImagemAoServidor(String nomeImagemServidor,
			String nomeImagemTemp) {
		File destino = new File(enderecoImagens.getCaminhoServidor()
				+ nomeImagemServidor);
		File origem = new File(enderecoImagens.getCaminhoTemp()
				+ nomeImagemTemp);
		if (!destino.getParentFile().exists())
			destino.getParentFile().mkdirs();
		if (!destino.exists())
			try {
				destino.createNewFile();
				FileOutputStream fos = new FileOutputStream(destino);

				fos.close();
				copiaImagem(origem, destino);

			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public static void copiaImagem(File origem, File destino)
			throws IOException {
		if (destino.exists())
			destino.delete();

		FileChannel origemChannel = null;
		FileChannel destinoChannel = null;

		try {
			origemChannel = new FileInputStream(origem).getChannel();
			destinoChannel = new FileOutputStream(destino).getChannel();
			origemChannel.transferTo(0, origemChannel.size(), destinoChannel);

		} finally {
			if (origemChannel != null && origemChannel.isOpen())
				origemChannel.close();
			if (destinoChannel != null && destinoChannel.isOpen())
				destinoChannel.close();
		}
	}
	
	public boolean deletarTemp(File diretorio) {
		if (diretorio.isDirectory()) {
			String[] registros = diretorio.list();
			for (int i = 0; i < registros.length; i++) {
				boolean deletado = deletarTemp(new File(diretorio, registros[i]));
				if (!deletado) {
					return false;
				}
			}
		}

		return diretorio.delete();
	}

	public String onFlowProcess(FlowEvent event) {
		if (event.getOldStep().equals("imovelEndereco")) {
			if (!validarCamposGerais()) {
				return event.getOldStep();
			}
			carregarPontoGeografico();
		}
		return event.getNewStep();
	}
	
	public void onMarkerDrag(MarkerDragEvent event) {
        this.marker = event.getMarker();  
        System.out.println(this.zoom);
//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + marker.getLatlng().getLat() + ", Lng:" + marker.getLatlng().getLng()));
    }   

    public void onPointSelect(PointSelectEvent event) {
        LatLng latlng = event.getLatLng();
        this.marker = new Marker(latlng);
        draggableModel.getMarkers().clear();
        draggableModel.addOverlay(this.marker);
        this.imovel.getPontoGeografico().setLatitude(latlng.getLat());
        this.imovel.getPontoGeografico().setLongitude(latlng.getLng()); 
    }
 
	private void carregarPontoGeografico(){
//		LocalizacaoUtil localizacao = new LocalizacaoUtil();

//		try {
//			this.pontoGeografico = localizacao
//					.getPontoGeografico(this.imovel.getLogradouro() + ","
//								      + this.imovel.getNumeroDoImovel() + ","
//								      + this.imovel.getBairro() + ","
//								      + this.imovel.getCidade() + ","
//			 					      + this.imovel.getEstado());
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
        //Criando as coordenadas a serem inseridas no mapa.
        LatLng coordenada = new LatLng(this.imovel.getPontoGeografico().getLatitude(), this.imovel.getPontoGeografico().getLongitude());
          
        //Draggable (Criando o modelo de coordenadas).
        draggableModel.addOverlay(new Marker(coordenada, this.imovel.getLogradouro()));
        // Como existe apenas uma coordenada, estou pegando apenas a primeira posição.
        Marker premarker = this.draggableModel.getMarkers().get(0);  
        premarker.setClickable(true);
        premarker.setDraggable(true);
	}

	public SituacaoImovel[] getSituacao() {
		return SituacaoImovel.values();
	}

	public TipoImovel[] getTipoImovel() {
		return TipoImovel.values();
	}

	public boolean validarCamposGerais() {
		if (this.imovel.getNomeDoProprietario() == null
				|| this.imovel.getNomeDoProprietario().trim().equals("")) {
			this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO,
					"Campo inválido",
					"O nome do proprietário do imóvel deve ser informado.");
			this.primeUtil.update("idFormMensagem");
			return false;
		}
		if (this.imovel.getCep() == null
				|| this.imovel.getCep().trim().equals("")) {
			this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO,
					"Campo inválido", "O CEP do imóvel deve ser informado.");
			this.primeUtil.update("idFormMensagem");
			return false;
		}
		if (this.imovel.getLogradouro() == null
				|| this.imovel.getLogradouro().trim().equals("")) {
			this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO,
					"Campo inválido",
					"O Logradouro do imóvel deve ser informado.");
			this.primeUtil.update("idFormMensagem");
			return false;
		}
		if (this.imovel.getBairro() == null
				|| this.imovel.getBairro().trim().equals("")) {
			this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO,
					"Campo inválido", "O Bairro do imóvel deve ser informado.");
			this.primeUtil.update("idFormMensagem");
			return false;
		}
		if (this.imovel.getCidade() == null
				|| this.imovel.getCidade().trim().equals("")) {
			this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO,
					"Campo inválido", "A Cidade do imóvel deve ser informado.");
			this.primeUtil.update("idFormMensagem");
			return false;
		}
		if (this.imovel.getEstado() == null
				|| this.imovel.getEstado().trim().equals("")) {
			this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO,
					"Campo inválido", "O Estado do imóvel deve ser informado.");
			this.primeUtil.update("idFormMensagem");
			return false;
		}
		if (this.imovel.getNumeroDoImovel() == null
				|| this.imovel.getNumeroDoImovel().trim().equals("")) {
			this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO,
					"Campo inválido", "O Número do imóvel deve ser informado.");
			this.primeUtil.update("idFormMensagem");
			return false;
		}

//		if (this.valorImovel == null || valorImovel.trim().equals("")) {
		if (this.valorImovel == 0) {

			this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO,
					"Campo inválido", "O valor do imóvel deve ser informado.");
			this.primeUtil.update("idFormMensagem");
			return false;

		} else {
			try {
//				String valor = this.valorImovel.replaceAll("\\.","").replace(",", ".");
								
				this.imovel.setPreco(valorImovel);
				
//				
//				if (this.imovel.getPreco() > 9000000){
//					this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO,
//							"Campo inválido", "O valor do imóvel não deve ultrapassar 9.000.000 reais.");
//					this.primeUtil.update("idFormMensagem");
//					return false;					
//				}
				
				
				
			} catch (NumberFormatException e) {
				this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO,
						"Campo inválido",
						"O valor do imóvel deve ser informado corretamente.");
				this.primeUtil.update("idFormMensagem");
				return false;
			}
		}
		return true;
	}
}
