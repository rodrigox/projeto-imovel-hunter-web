package br.com.imovelhunterweb.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import br.com.imovelhunterweb.entitys.Mensagem;
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
	
	
	/*public static void main(String[] args){
		try{
		
			String mensagemJson = "{ \"data\": { \"mensagem\":{\"idImovel\":9,\"numeroDoImovel\":\"105\",\"numeroDeBanheiros\":1,\"numeroDeQuartos\":2,\"ambientes\":1,\"nomeDoProprietario\":\"was\",\"numeroDeSuites\":1,\"situacaoImovel\":\"LOCACAO\",\"areaTotal\":30.0,\"preco\":500.0,\"numeroDeSalas\":1,\"logradouro\":\"Alcedo Marrocos\",\"bairro\":\"Afogados\",\"cidade\":\"Recife\",\"estado\":\"PE\",\"cep\":\"50750-060\",\"complemento\":\"Casa\",\"tipoImovel\":\"CASA\",\"anunciante\":{\"idAnunciante\":2,\"telefone\":\"8134285172\",\"nome\":\"washington\",\"sobreNome\":\"luiz\",\"email\":\"wasluizoliveira@gmail.com\",\"dataDeNascimento\":\"15/03/1989 00:00:00\",\"dataDeVencimento\":\"06/07/2015 12:26:05\",\"creci\":\"192319239\",\"dataDeCriacao\":\"06/06/2015 12:26:05\",\"cpf\":\"08363598445\",\"login\":\"admin\",\"senha\":\"21232f297a57a5a743894a0e4a801fc3\",\"tipoUsuario\":\"USUARIO\"},\"caracteristicas\":[],\"pontoGeografico\":{\"idPontoGeografico\":9,\"longitude\":-34.9064404,\"latitude\":-8.0755248}}}, \"registration_ids\": [ \"APA91bFU2p-fmuD4uToDRH9XVDVoX_XX4xu3kgmaIf3OCE-TyBKRSkm642lKxIYI-WNdu7-cotMP0-RpJrzoRsjAkWEUwLnWTrWieyj2fDlJzknRoYZlRyhLjoZbfjNNvfpeE6muBJQJDjzQYRFteSwm_9txeXXF3tqagd4fNJHvW8A3nLzwwt4\" ] }";
			
			System.out.println(mensagemJson);
			
			byte[] dados = mensagemJson.getBytes("UTF-8");
			
			URL url = new URL("https://android.googleapis.com/gcm/send");
	
			HttpsURLConnection conexao = (HttpsURLConnection) url.openConnection();
			
			conexao.addRequestProperty("Authorization", "key="
					+ "AIzaSyBoLQVrQ25cibPkORmvKc1Luua6JsX4u84");
			conexao.addRequestProperty("Content-Type", "application/json");
			conexao.setRequestMethod("POST");
			conexao.setDoOutput(true);
			conexao.setUseCaches(false);
			conexao.connect();
	
			OutputStream os = conexao.getOutputStream();
			os.write(dados);
			os.close();
			
			
			int responseCode = conexao.getResponseCode();
			
			if(true){
				if (responseCode == HttpURLConnection.HTTP_OK) {				
					System.out.println("OK");
				} else {						
					System.out.println("FALSE");
				}		
			}		
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}*/
	
	
	public void enviarObjetoJsonViaGcm(ObjetoJSON<?> objetoJson,StringBuffer tokens,PrintWriter printer,Boolean feedBack) throws IOException{
		
		
		String stringTokens = tokens.toString();
		
		String mensagemJson = "{ \"data\": { \"mensagem\":"
				+ objetoJson.toString() + "}, "
				+ "\"registration_ids\": [ "+stringTokens+" ] }";	
		
		System.out.println(mensagemJson);
		
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
		
		int responseCode = conexao.getResponseCode();
		
		
		if(feedBack){
			if (responseCode == HttpURLConnection.HTTP_OK || stringTokens.length() == 0) {				
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
	
	
	/**
	 * Monta os tokens de acordo com a quantidade de usuários
	 * @param usuarios
	 * @return
	 */
	public StringBuffer montarTokens(Usuario... usuarios){
		StringBuffer stb = new StringBuffer();
		
		if(usuarios != null){
			
			int cont = 0;
			int tam = usuarios.length;
			
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
