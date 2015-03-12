package br.com.imovelhunterweb.util.crud.generator;


public class TemplateService {
	
	private String template;
	
	private String pacoteDoService;
	
	private String imports;
	
	private String nomeDaClasse;
	
	private String nomeDaClasseMin;
	
	
	public TemplateService(){
		
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
