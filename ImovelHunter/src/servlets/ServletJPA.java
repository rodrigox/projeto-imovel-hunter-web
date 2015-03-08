package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.JPAUtil;

public class ServletJPA extends HttpServlet{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4728191173670765987L;

	public ServletJPA(){
		JPAUtil.comecarJPA();
	}
	
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
	}	
	
}
