package web;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("all")
public class BaseServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		request.setCharacterEncoding("utf-8");
		try {
			String methodName = request.getParameter("method");
			Class clazz = this.getClass();
			Method method = clazz.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class );
			method.invoke(this, request,response);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
