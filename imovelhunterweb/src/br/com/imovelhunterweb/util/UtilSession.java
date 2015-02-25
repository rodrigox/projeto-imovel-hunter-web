package br.com.imovelhunterweb.util;


import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UtilSession {

	public static HttpSession getHttpSession() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		HttpSession s = (HttpSession) externalContext.getSession(true);				
		
		
		return s;
	}

	public static void setHttpSessionObject(String nomeSessao, Object o) {
		getHttpSession().setAttribute(nomeSessao, o);		
	}

	public static Object getHttpSessionObject(String nomeSessao) {
		return getHttpSession().getAttribute(nomeSessao);
	}
	
	public static HttpServletRequest getRequest(){
		return (HttpServletRequest) FacesContext
		.getCurrentInstance().getExternalContext().getRequest();
	}

//	public static void genericShowMessage(String mensagem) {
//		FacesMessage message = new FacesMessage(mensagem);
//		message.setSeverity(FacesMessage.SEVERITY_ERROR);
//		throw new ValidatorException(message);
//	}
}
