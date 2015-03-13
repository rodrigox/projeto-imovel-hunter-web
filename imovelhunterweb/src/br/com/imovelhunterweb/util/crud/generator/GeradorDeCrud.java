package br.com.imovelhunterweb.util.crud.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GeradorDeCrud {
	
	private File arquivo;
	
	private FileWriter fw;
	
	private BufferedWriter bw;	
	
	private String caminhoDasEntidades;
	
	private String caminhoQueSeraCriadoOsCruds;
	
	private String pacoteDasEntidades;
	
	private String pacoteDosDaos;
	
	private String pacoteDaImplementacaoDosDaos;
	
	private String pacoteDoService;
	
	private String pacoteDoServiceImplementacao;
	
	private BuscadorDeDiretorio buscadorDeDiretorio;
	
	private List<String> classesEncontradas;
	
	/**
	 * Prepara o gerador de crud
	 * @param caminhoDasEntidades - Local onde seus arquivos .java do projeto se encontram
	 * @param caminhoQueSeraCriadoOsCruds - Local onde o gerador irá jogar os arquivos criados
	 * @param pacoteDasEntidades - nome do pacote do seu projeto onde se encontram as entidades, ex: br.com.meuprojeto.entitys
	 * @param pacoteDosDaos - nome do pacote do seu projeto onde se encontram as interfaces do dao, ex: br.com.meuprojeto.dao
	 * @param pacoteDaImplementacaoDosDaos - nome do pacote do seu projeto onde se encontram as implementações das interfaces do dao ex: br.com.meuprojeto.dao.imp
	 * @param pacoteDoService - nome do pacote do seu projeto onde se encontra as interfaces da camada de negócio(service) do seu projeto, ex: br.com.meuprojeto.service
	 * @param pacoteDoServiceImplementacao - nome do pacote onde irá ficar a implementação das interfaces da camada de negócio(service) do seu projeto, ex: br.com.meuprojeto.service.imp
	 * @throws Exception 
	 */
	public GeradorDeCrud(String caminhoDasEntidades,String caminhoQueSeraCriadoOsCruds,String pacoteDasEntidades,String pacoteDosDaos
			,String pacoteDaImplementacaoDosDaos,String pacoteDoService,String pacoteDoServiceImplementacao) throws Exception{
		
		this.caminhoDasEntidades = caminhoDasEntidades;
		this.caminhoQueSeraCriadoOsCruds = caminhoQueSeraCriadoOsCruds;
		this.pacoteDasEntidades = pacoteDasEntidades;
		this.pacoteDosDaos = pacoteDosDaos;
		this.pacoteDaImplementacaoDosDaos = pacoteDaImplementacaoDosDaos;
		this.pacoteDoService = pacoteDoService;
		this.pacoteDoServiceImplementacao = pacoteDoServiceImplementacao;
		
		this.buscadorDeDiretorio = new BuscadorDeDiretorio();	
		
		this.classesEncontradas = this.buscadorDeDiretorio.listarArquivos(this.caminhoDasEntidades);
		
	}
	
	/**
	 * Esse método cria todos os cruds do pacote de entidade
	 * @throws IOException 
	 */
	public void criarTodosOsCruds() throws IOException{
		
		for(String classe : this.classesEncontradas){
			
			TemplateDAO tempDAO = new TemplateDAO();
			TemplateDAOImp tempDAOImp = new TemplateDAOImp();			
			TemplateService tempService = new TemplateService();
			TemplateServiceImp tempoServiceImp = new TemplateServiceImp();
			
			
			
			
			
			
			tempDAO = null;
			tempDAOImp = null;			
			tempService = null;
			tempoServiceImp = null;
			
		}
		
	}
	

}
