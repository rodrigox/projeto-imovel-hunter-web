package br.com.imovelhunterweb.util.crud.generator;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.com.imovelhunterweb.dao.PontoGeograficoDAO;
import br.com.imovelhunterweb.entitys.PontoGeografico;
import br.com.imovelhunterweb.service.PontoGeograficoService;

public class TemplateServiceImp {
	
private String template;
	
	private String pacoteDoService;
	
	private String imports;
	
	private String nomeDaClasse;
	
	private String nomeDaClasseMin;
	
	
	public TemplateServiceImp(){
		
		this.template = "#{pacoteDoService}\r\n"
				+ "\r\n"
				+ "import java.util.List;\r\n"
				+ "import java.io.Serializable;\r\n"
				+ "import javax.annotation.Resource;\r\n"
				+ "import org.springframework.stereotype.Service;\r\n"
				+ "import org.springframework.test.annotation.Rollback;\r\n"
				+ "import org.springframework.transaction.annotation.Transactional;\r\n"
				+ "import com.sun.xml.internal.bind.v2.model.core.ID;\r\n"
				+ "\r\n"
				+ "#{imports}\r\n"
				+ "\r\n"
				+ "@Service(\"#{nomeDaClasseMin}Service\")\r\n"
				+ "public class #{nomeDaClasse}ServiceImp implements #{nomeDaClasse}Service,Serializable {\r\n"
				+ "\r\n"
				+ "@Resource(name = \"#{nomeDaClasseMin}DAO\")\r\n"
				+ "private #{nomeDaClasse}DAO #{nomeDaClasseMin}DAO;\r\n"
				+ "\r\n"
				+ "@Override\r\n"
				+ "@Transactional\r\n"
				+ "@Rollback\r\n"
				+ "public #{nomeDaClasse} inserir(#{nomeDaClasse} #{nomeDaClasseMin}) {\r\n"
				+ "return this.#{nomeDaClasseMin}DAO.insert(#{nomeDaClasseMin});\r\n"
				+ "}\r\n"
				+ ""
				+ "";
				
		
	}
	
	public void resetar(){
		this.template = "#{pacoteDoService}\r\n"
				+ "\r\n"
				+ "import java.util.List;\r\n"
				+ "\r\n"
				+ "#{imports}\r\n"
				+ "\r\n"
				+ "public interface #{nomeDaClasse}Service {\r\n"
				+ "\r\n"
				+ "#{nomeDaClasse} inserir(#{nomeDaClasse} #{nomeDaClasseMin});\r\n"
				+ "\r\n"
				+ "#{nomeDaClasse} atualizar(#{nomeDaClasse} #{nomeDaClasseMin});\r\n"
				+ "\r\n"
				+ "Boolean remover(#{nomeDaClasse} #{nomeDaClasseMin});\r\n"
				+ "\r\n"
				+ "Boolean removerPorId(#{nomeDaClasse} #{nomeDaClasseMin});\r\n"
				+ "\r\n"
				+ "#{nomeDaClasse} buscarPorId(ID id);\r\n"
				+ "\r\n"
				+ "List<#{nomeDaClasse}> listarTodos();\r\n"
				+ "\r\n"
				+ "List<#{nomeDaClasse}> listarTodos(int index,int quantidade);\r\n"
				+ "\r\n"
				+ "}";
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
		this.template = this.template.replaceAll(chave,valor);
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
