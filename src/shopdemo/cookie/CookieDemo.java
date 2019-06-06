package shopdemo.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class CookieDemo
 */
@WebServlet(
		urlPatterns = { "/servlet/cookiedemo" }, 
		initParams = { 
				@WebInitParam(name = "param1", value = "value1")
		})
public class CookieDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieDemo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		//假设username通过登录表单获取的，7天免登录
		Cookie cookie1 = new Cookie("username","zztitest");
		cookie1.setMaxAge(7*24*60*60);
		//将登录信息写到浏览器中
		response.addCookie(cookie1);
		PrintWriter pw = response.getWriter();
		pw.println("cookie写入成功");
		pw.flush();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
