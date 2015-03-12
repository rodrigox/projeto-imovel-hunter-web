package br.com.imovelhunterweb.util.crud.generator;

public class TemplateDAOImp {
	
	private String template;
	
	private String nomeDoPacoteDAOImp;
	
	private String imports;
	
	private String nomeDaClasse;
	
	
	public TemplateDAOImp(){
		
		this.template = "#{pacoteDoDAOImp}\r\n"
				+ "\r\n"
				+ "import java.io.Serializable;"
				+ "#{imports}"
				+ "\r\n"
				+ "\r\n"
				+ "public class #{nomeDaClasse}DAOImp extends GenericDAOImp<#{nomeDaClasse},Serializable> implements #{nomeDaClasse}DAO,Serializable{\r\n"
				+ "\r\n"
				+ "}";	
		
		
		
	}
	
	public void resetar(){
		this.template = "#{pacoteDoDAOImp}\r\n"
				+ "\r\n"
				+ "import java.io.Serializable;"
				+ "#{imports}"
				+ "\r\n"
				+ "\r\n"
				+ "public class #{nomeDaClasse}DAOImp extends GenericDAOImp<#{nomeDaClasse},Serializable> implements #{nomeDaClasse}DAO,Serializable{\r\n"
				+ "\r\n"
				+ "}";	
	}
	
	
	
	public void setNomeDoPacoteDAOImp(String nomeDoPacoteDAOImp) {
		this.nomeDoPacoteDAOImp = nomeDoPacoteDAOImp;
	}

	public void setNomeDaClasse(String nomeDaClasse) {
		this.nomeDaClasse = nomeDaClasse;
	}
	
	public void addImport(String importe){
		this.imports += importe+";\r\n";
	}

	public void replaceKey(String chave,String valor){
		this.template = this.template.replace(chave,valor);
	}
	
	/**
	 * Retorna a string que será escrita na classe
	 * @return
	 */
	public String getTemplate() {
		
		if(this.nomeDoPacoteDAOImp != null){
			this.replaceKey("#{pacoteDoDAOImp}",this.nomeDoPacoteDAOImp);
		}
		if(this.nomeDaClasse != null){
			this.replaceKey("#{nomeDaClasse}",this.nomeDaClasse);
		}
		if(this.imports != null){
			this.replaceKey("#{imports}",this.imports);
		}
		
		return template;
	}	

}
