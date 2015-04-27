package br.com.imovelhunterweb.util;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="localServidor")
@ViewScoped
public class LocalServidor implements Serializable{
	
	private static final long serialVersionUID = 5685474647688012860L;
	private String local;

	@PostConstruct
	public void init(){
		this.local = localServidor();
	}
	
	public String getLocal() {
		return local;
	}
	
	public static String localServidor(){
		String sistema = System.getProperty("os.name");							
		if(sistema.substring(0,7).toUpperCase().equals("WINDOWS")){
			return "http://localhost:8080/imovelhunterweb/";
		}else{
			return "http://ec2-54-68-17-181.us-west-2.compute.amazonaws.com/imovelhunterweb/";
		}
	}
}
