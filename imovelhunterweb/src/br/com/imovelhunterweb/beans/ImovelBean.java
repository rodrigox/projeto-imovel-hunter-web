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
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;

import br.com.imovelhunterweb.entitys.Anunciante;
import br.com.imovelhunterweb.entitys.Caracteristica;
import br.com.imovelhunterweb.entitys.Imagem;
import br.com.imovelhunterweb.entitys.Imovel;
import br.com.imovelhunterweb.enums.SituacaoImovel;
import br.com.imovelhunterweb.service.AnuncianteService;
import br.com.imovelhunterweb.service.CaracteristicaService;
import br.com.imovelhunterweb.service.ImagemService;
import br.com.imovelhunterweb.service.ImovelService;
import br.com.imovelhunterweb.util.ConsultaCEP;
import br.com.imovelhunterweb.util.Navegador;
import br.com.imovelhunterweb.util.PrimeUtil;
import br.com.imovelhunterweb.util.UtilSession;

@ManagedBean(name = "imovelBean")
@ViewScoped
public class ImovelBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -500703284436109611L;

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
	private String cep;
	


	@ManagedProperty("#{imovelService}")
	private ImovelService imovelService;
	
	@ManagedProperty("#{imagemService}")
	private ImagemService imagemService;
	
	@ManagedProperty("#{anuncianteService}")
	private AnuncianteService anuncianteService;
	
	@ManagedProperty("#{caracteristicaService}")
	private CaracteristicaService caracteristicaService;
	


	@PostConstruct
	public void init(){	
		this.navegador = new Navegador();
		this.primeUtil = new PrimeUtil();
		this.imovelImagens = new ArrayList<Imagem>();
		this.imovel = new Imovel();
		this.caracteristica = new Caracteristica();
		this.allCacaracteristicas = this.caracteristicaService.listarTodos();
		deletarTemp(new File(retornaCaminho("")));

		this.anunciante = (Anunciante)UtilSession.getHttpSessionObject("anuncianteLogado");
		if(this.anunciante == null){
			this.navegador.redirecionarPara("login.xhtml");
			return;
		}
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


	public void setCaracteristicaService(CaracteristicaService caracteristicaService) {
		this.caracteristicaService = caracteristicaService;
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


	public static long getSerialversionuid() {
		return serialVersionUID;
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


	public void setAllCacaracteristicas(List<Caracteristica> allCacaracteristicas) {
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


	public void setCep(String cep) {		
		this.cep = cep;
		ConsultaCEP endereco = new ConsultaCEP(cep);
		this.imovel.setEstado(endereco.getEstado());
		this.imovel.setLogradouro(endereco.getLogradouro());
		this.imovel.setCidade(endereco.getCidade());
		this.imovel.setBairro(endereco.getBairro());	
	}

	
	
	// MÉTODOS PARA EXECUÇÃO:
	
	
	public List<Imagem> getImagens(){  
		FacesContext facesContext = FacesContext.getCurrentInstance();  
        ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();  
          
        File pastaImagens = new File(scontext.getRealPath("/uploads/imagens/"));  
        if(!pastaImagens.exists())pastaImagens.mkdirs();  
        File[] arquivos = pastaImagens.listFiles();  

        for(File arquivo : arquivos){  
            if(arquivo.isFile()){  

            	String ext=arquivo.getName().substring(arquivo.getName().lastIndexOf(".")).toLowerCase();  
                if(ext.equals(".jpg")||ext.equals(".jpeg")||ext.equals(".bmp")||ext.equals(".gif")||ext.equals(".png")){  
                	boolean registro = false;
            		for(Imagem im : this.imovelImagens){
            			if(im.getCaminhoImagem().equals(arquivo.getName())){
            				registro = true;
            				break;
            			}
            		}  
            		if(!registro){
                    	Imagem img = new Imagem(null, null, arquivo.getName(), null);
                    	this.imovelImagens.add(img);
            		}
                }  
            }  
        }  
        return imovelImagens;  
    } 
	
	
    public void upload(FileUploadEvent event) {         
        try {  
            byte[] foto = event.getFile().getContents();  
            String nomeArquivo = event.getFile().getFileName();    
            
            File f = new File(retornaCaminho(nomeArquivo));  
            if(!f.getParentFile().exists())f.getParentFile().mkdirs();  
            if(!f.exists())f.createNewFile();  
            //System.out.println(f.getAbsolutePath());  
            FileOutputStream fos = new FileOutputStream(retornaCaminho(nomeArquivo));  
            fos.write(foto);  
            fos.flush();  
            fos.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
	
	public void removerImagem(String imagem){
		for(Imagem im : this.imovelImagens){
			if(im.getCaminhoImagem().equals(imagem)){
				this.imovelImagens.remove(im);
				break;
			}
		}
		File f = new File(retornaCaminho(imagem));  
        f.delete();
	}
	
	public String retornaCaminho(String nomeImagem){
        FacesContext facesContext = FacesContext.getCurrentInstance();    
        ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();    
        return scontext.getRealPath("/uploads/imagens/"+ nomeImagem); 
	}
	

	
	public void cadastrarImovel(){
    	this.imovel.setCaracteristicas(new ArrayList<Caracteristica>());	
        for (Caracteristica caract : this.allCacaracteristicas) {
            if (checked.get(caract.getIdCaracteristica())) {
            	this.imovel.getCaracteristicas().add(caract);

            }
        }
        checked.clear(); 

        this.imovel.setAnunciante(this.anunciante);
        //this.imovel.setSituacaoImovel(SituacaoImovel.VENDA);

		Imovel im = this.imovelService.inserir(this.imovel);
		if (im != null){
		for(int i = 0; i < this.imovelImagens.size(); i++){
			this.imovelImagens.get(i).setImovel(im);
			Imagem img = this.imagemService.inserir(this.imovelImagens.get(i));
			if (img != null){
				String nomeImagemServidor = img.getIdImagem() + "_" + img.getCaminhoImagem(); 
				enviarImagemAoServidor(nomeImagemServidor, img.getCaminhoImagem());
			}
		}
		this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO,"Cadastro","Imóvel cadastrado com sucesso.");
		this.primeUtil.update("cadastroImovel.xhtml");
        this.navegador.redirecionarPara("cadastroImovel.xhtml");

		}else{
			this.primeUtil.mensagem(FacesMessage.SEVERITY_INFO,"Erro","Não fui possível cadastrar o imóvel.");
			this.primeUtil.update("cadastroImovel.xhtml");
			
		}
	}
	

	public void enviarImagemAoServidor(String nomeImagemServidor, String nomeImagemTemp){
    	String caminhoServidor = "C:/Users/Developers/Desktop/apache/apache-tomcat-7.0.59/webapps/imagens/";
        File destino = new File(caminhoServidor + nomeImagemServidor);  
        File origem = new File(retornaCaminho(nomeImagemTemp));  
        if(!destino.getParentFile().exists())destino.getParentFile().mkdirs();  
        if(!destino.exists())
			try {
				destino.createNewFile();
				copiaImagem(origem, destino);
								
			} catch (IOException e) {
				e.printStackTrace();
			}  
	}
	
	
	public static void copiaImagem(File origem, File destino) throws IOException {  
	     if (destino.exists())  
	    	 destino.delete();  
	  
	     FileChannel origemChannel = null;  
	     FileChannel destinoChannel = null;  
	  
	     try {  
	    	 origemChannel = new FileInputStream(origem).getChannel();  
	    	 destinoChannel = new FileOutputStream(destino).getChannel();  
	         origemChannel.transferTo(0, origemChannel.size(),  
	        		 destinoChannel); 
	         

	         	
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
            for (int i=0; i<registros.length; i++) { 
               boolean deletado = deletarTemp(new File(diretorio, registros[i]));
                if (!deletado) {
                    return false;
                }
            }
        }

        return diretorio.delete();
    }	
	
    public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   
            return "galeria";
        }
        else {
            return event.getNewStep();
        }
    }
    
    public SituacaoImovel[] getSituacao() {
        return SituacaoImovel.values();
      }
	
}
