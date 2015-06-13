package br.com.imovelhunterweb.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

public class ConsultaCEP {

	private String estado;
	private String cidade;
	private String bairro;
	private String tipoLogradouro;
	private String logradouro;
	private int resultado;

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getResultado() {
		return resultado;
	}

	public void setResultado(int resultado) {
		this.resultado = resultado;
	}

	@SuppressWarnings("rawtypes")
	public ConsultaCEP(String cep) {
		inicializaCampos();	
		try {
			URL url = new URL(
					"http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep
							+ "&formato=xml");

			Document document = getDocumento(url);

			Element root = document.getRootElement();

			for (Iterator i = root.elementIterator(); i.hasNext();) {
				Element element = (Element) i.next();

				if (element.getQualifiedName().equals("uf"))
					setEstado(element.getText());

				if (element.getQualifiedName().equals("cidade"))
					setCidade(element.getText());

				if (element.getQualifiedName().equals("bairro"))
					setBairro(element.getText());

				if (element.getQualifiedName().equals("tipo_logradouro"))
					setTipoLogradouro(element.getText());

				if (element.getQualifiedName().equals("logradouro"))
					setLogradouro(element.getText());

				if (element.getQualifiedName().equals("resultado"))
					setResultado(Integer.parseInt(element.getText()));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Document getDocumento(URL url) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(url);

		return document;
	}
	
	public void inicializaCampos(){
		this.estado = "";
		this.cidade = "";
		this.bairro = "";
		this.tipoLogradouro = "";
		this.logradouro = "";
		this.resultado = 0;
	}
		
	
	// Utilizando Json dos Correios.
	
	/*
	public static void retornarEndereco(){
		try{
		  URL url = new URL("http://cep.correiocontrol.com.br/54450010.json");  

		  BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));  
		  String str;              

		  if ((str = in.readLine()) != null) 
		  {  
		    JSONObject obj = new JSONObject(str);
		    System.out.println(obj.getString("bairro") +
		    obj.getString("localidade") +
		    obj.getString("logradouro") +
		    obj.getString("uf"));
		  }     

		  in.close();  
		} catch (MalformedURLException e) {  
			  e.printStackTrace();

			} catch (IOException e) {  
			  e.printStackTrace();

			} catch (JSONException e) {
			  e.printStackTrace();
			}
		  
	}
	
	*/
	
}	
	




	

