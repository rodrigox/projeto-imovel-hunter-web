package br.com.imovelhunterweb.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;



public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		synchronized (this) {
			HttpSession s = event.getSession();

			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();

			String ipAddress = request.getHeader("X-FORWARDED-FOR");
			request.getRemoteUser();
			if (ipAddress == null) {
				ipAddress = request.getRemoteAddr();
				System.out.println("Id da sessão: " + s.getId());
				System.out.println("Ip: " + ipAddress);
				System.out.println("Porta: " + request.getServerPort());
				System.out.println("Protocolo: " + request.getProtocol());
				System.out.println("Data: "
						+ DataUtil.obterTimestampAtual().toString());
				// pega a url digitada no lado do cliente
				// System.out.println(request.getRequestURL());
			}
			
//			String nome = request.getParameter("login");
//			String senha = request.getParameter("senha");
//
//			if (nome != null && nome.length() > 0 && senha != null
//					&& senha.length() > 0) {
//				s.setAttribute("usuarioSessao", nome);
//				s.setAttribute("senhaSessao", senha);
////				request.setAttribute("usuarioSessao", nome);
////				request.setAttribute("senhaSessao", senha);
//			}

		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		synchronized (this) {

		}
	}

}
