package br.com.imovelhunterweb.util.crud.generator;


public class TemplateDAO {
	
	private String template;
	
	private String pacoteDoDAO;
	
	private String imports;
	
	private String nomeDaClasse;
	
	
	public TemplateDAO(){
		
		this.template = "#{pacoteDoDAO}\r\n"
				+ "\r\n"
				+ "import java.io.Serializable;"
				+ "#{imports}"
				+ "\r\n"
				+ "\r\n"
				+ "public interface #{nomeDaClasse}DAO extends GenericDAO<#{nomeDaClasse},Serializable> {\r\n"
				+ "\r\n"
				+ "}";	
		
		
		
	}
	
	public void resetar(){
		this.template = "#{pacoteDoDAO}\r\n"
				+ "\r\n"
				+ "import java.io.Serializable;"
				+ "#{imports}"
				+ "\r\n"
				+ "\r\n"
				+ "public interface #{nomeDaClasse}DAO extends GenericDAO<#{nomeDaClasse},Serializable> {\r\n"
				+ "\r\n"
				+ "}";
	}
	
	
	
	public void setPacoteDoDAO(String pacoteDoDAO) {
		this.pacoteDoDAO = pacoteDoDAO;
	}

	public void setNomeDaClasse(String nomeDaClasse) {
		this.nomeDaClasse = nomeDaClasse;
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
		
		if(this.pacoteDoDAO != null){
			this.replaceKey("#{pacoteDoDAO}",this.pacoteDoDAO);
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
