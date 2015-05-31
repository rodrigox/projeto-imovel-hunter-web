package br.com.imovelhunterweb.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import br.com.imovelhunterweb.entitys.Usuario;



public class GcmUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6988146053941890260L;
	
	/**
	 * Chave que faz com que a api do gcm identifique que o servidor que está enviando é o autorizado
	 */
	private String chaveServidorGCM = "AIzaSyBoLQVrQ25cibPkORmvKc1Luua6JsX4u84";
	
	
	/**
	 * Chave do projeto na api criada no google api console
	 */
	private String identificacaoDoProjeto = "929616541019";
	
	
	
	public void enviarObjetoJsonViaGcm(ObjetoJSON<?> objetoJson,StringBuffer tokens,PrintWriter printer,Boolean feedBack) throws IOException{
		
		
		String stringTokens = tokens.toString();
		
		String mensagemJson = "{ \"data\": { \"mensagem\":"
				+ objetoJson.toString() + "}, "
				+ "\"registration_ids\": [ "+stringTokens+" ] }";	
		
		byte[] dados = mensagemJson.getBytes("UTF-8");
		
		URL url = new URL("https://android.googleapis.com/gcm/send");

		HttpsURLConnection conexao = (HttpsURLConnection) url.openConnection();
		
		conexao.addRequestProperty("Authorization", "key="
				+ chaveServidorGCM);
		conexao.addRequestProperty("Content-Type", "application/json");
		conexao.setRequestMethod("POST");
		conexao.setDoOutput(true);
		conexao.setUseCaches(false);
		conexao.connect();

		OutputStream os = conexao.getOutputStream();
		os.write(dados);
		os.close();
		
		
		
		
		if(feedBack){
			if (conexao.getResponseCode() == HttpURLConnection.HTTP_OK || stringTokens.length() == 0) {				
				printer.print("OK_MENSAGEM_ENVIADA");
			} else {						
				printer.print("ERRO;_ENVIO:" + conexao.getResponseCode() + " - "
						+ conexao.getResponseMessage());
			}		
		}		
		
	}
	
	
	
	/**
	 * Monta os tokens de acordo com a quantidade de usuários
	 * @param usuarios
	 * @return
	 */
	public StringBuffer montarTokens(List<Usuario> usuarios){
		StringBuffer stb = new StringBuffer();
		
		if(usuarios != null){
			
			int cont = 0;
			int tam = usuarios.size();
			
			Boolean fim = false;
			
			for(Usuario u : usuarios){
				
				if(cont + 1 == tam){
					fim = true;
				}
				
				if(!fim){
					stb.append("\"");
					stb.append(u.getChaveGCM());
					stb.append("\",");
				}else{
					stb.append("\"");
					stb.append(u.getChaveGCM());
					stb.append("\"");
				}
				
				
				cont++;
			}
			
		}		
		
		
		return stb;
	}
	
	
	
	
	public String listaParaJson(List<? extends ObjetoJSON<?>> lista) {
		StringBuffer stb = new StringBuffer();
		
		stb.append("[");
		
		int tam = 0;
		if(lista != null && (tam = lista.size()) > 0){
			
			int cont = 0;
			Boolean fim = false;
			
			for(Object o : lista){
				
				if(cont + 1 == tam){
					fim = true;
				}
				
				
				if(fim){
		
					stb.append(o.toString());
					
				}else{
					
					stb.append(o.toString());
					stb.append(",");
					
				}
				
				cont++;
			}
			
			
		}
		
		stb.append("]");
		
		
		return stb.toString();
	}
}
