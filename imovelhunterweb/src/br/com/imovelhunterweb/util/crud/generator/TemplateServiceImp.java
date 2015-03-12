package br.com.imovelhunterweb.util.crud.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class TemplateServiceImp {
	
private String template;
	
	private String pacoteDoService;
	
	private String imports;
	
	private String nomeDaClasse;
	
	private String nomeDaClasseMin;
	
	
	public TemplateServiceImp() throws IOException{		
		
		FileReader fr = new FileReader(new File("service.temp"));
		
		BufferedReader br = new BufferedReader(fr);
		
		String linha = null;
		
		StringBuffer stb = new StringBuffer();
		
		while((linha = br.readLine()) != null){
			stb.append(linha);
			stb.append("\r\n");
		}
		
		this.template = stb.toString();
		
		stb = null;
		
		br.close();
		
		fr.close();
		
	}
	
	public void resetar() throws IOException{
		
		FileReader fr = new FileReader(new File("service.temp"));
		
		BufferedReader br = new BufferedReader(fr);
		
		String linha = null;
		
		StringBuffer stb = new StringBuffer();
		
		while((linha = br.readLine()) != null){
			stb.append(linha);
			stb.append("\r\n");
		}
		
		this.template = stb.toString();
		
		stb = null;
		
		br.close();
		
		fr.close();
		
	}
	
	
	
	public void setPacoteDoDAO(String pacoteDoService) {
		this.pacoteDoService = pacoteDoService;
	}

	public void setNomeDaClasse(String nomeDaClasse) {
		this.nomeDaClasse = nomeDaClasse;
		this.nomeDaClasseMin = this.nomeDaClasse.substring(0,1).toLowerCase()+this.nomeDaClasse.substring(1);		
	}
	
	public void addImport(String importe){
		this.imports += importe+";\r\n";
	}

	private void replaceKey(String chave,String valor){
		this.template = this.template.replace(chave,valor);
	}
	
	/**
	 * Retorna a string que será escrita na classe
	 * @return
	 */
	public String getTemplate() {
		
		if(this.pacoteDoService != null){
			this.replaceKey("#{pacoteDoService}",this.pacoteDoService);
		}
		if(this.nomeDaClasse != null){
			this.replaceKey("#{nomeDaClasse}",this.nomeDaClasse);
			this.replaceKey("#{nomeDaClasseMin}",this.nomeDaClasseMin);
		}
		if(this.imports != null){
			this.replaceKey("#{imports}",this.imports);
		}
		
		return template;
	}	
	

}
