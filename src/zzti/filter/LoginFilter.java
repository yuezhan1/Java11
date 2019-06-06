package zzti.filter;


import zzti.util.PathConstence;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {
	private String[] pathList;
    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// place your code here
		HttpServletRequest hreq  = (HttpServletRequest)request;
		HttpServletResponse hresp = (HttpServletResponse)response;
		String requestPath = hreq.getServletPath();
		HttpSession session = hreq.getSession();
		System.out.println(requestPath);
		if(containsPath(requestPath)){
			chain.doFilter(request, response);
		}else if(session.getAttribute("user")==null){
			if (requestPath.indexOf("web")>-1){
				hresp.sendRedirect(hreq.getContextPath()+ PathConstence.JSP_WEB_BASE+"/login.jsp");
			}else{
				hresp.sendRedirect(hreq.getContextPath()+PathConstence.JSP_MANAGE_BASE+"/login.jsp");
			}
		}else{
			chain.doFilter(request, response);
		}

		// pass the request along the filter chain

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		//获取要放行的路径列表
		String values = fConfig.getInitParameter("paths");
	    pathList = values.split(",");
	}
	public  boolean containsPath(String path){
		for(String p:pathList){
			if(p.equals(path)){
				return true;
			}
		}
		return false;
	}

}
