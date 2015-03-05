package br.com.imovelhunterweb.util.crud.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BuscadorDeDiretorio {
	
	/*public static void main(String[] args){
		BuscadorDeDiretorio busc = new BuscadorDeDiretorio();
		
		try {
			List<String> arquivos = busc.listarArquivos("C:\\Users\\BOTTOMUP 05\\faculdadedazuera\\imovelhunterweb\\src\\br\\com\\imovelhunterweb\\entitys");
		
			System.out.println(arquivos);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}*/
	
	
	/**
	 * Lista todos os arquivos de extensão .java de um diretório
	 * @param caminho
	 * @return
	 * @throws Exception
	 */
	public List<String> listarArquivos(String caminho) throws Exception{
		List<String> arquivosEncontrados = new ArrayList<String>();
		
		File arquivo = new File(caminho);
		
		if(arquivo.isDirectory()){
			
			
			
			File[] arquivos = arquivo.listFiles();
			
			for(File f : arquivos){
				
				if(f.isFile()){
					
					String nomeDoArquivo = f.getName();
					
					if(nomeDoArquivo.contains(".java")){
						
						int tamOffset = ".java".length();
						
						String nomeSemAExtensao = nomeDoArquivo.substring(0,nomeDoArquivo.length() - tamOffset);
						
						arquivosEncontrados.add(nomeSemAExtensao);						
						
					}
					
				}
				
			}			
			
			
			
		}else{
			throw new Exception("O caminho especificado não é um diretório");
		}	
		
		
		return arquivosEncontrados;
	}

}
