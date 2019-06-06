package shopdemo.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetCookie1
 */
@WebServlet("/servlet/getcookie1")
public class GetCookie1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCookie1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//在继续响应信息之前，需要判断一下，当前用户是否登录了
		//通过获取cookie的信息来判断当前用户是否已经登录了
		Cookie[] cookies = request.getCookies();
		PrintWriter pw = response.getWriter();
		boolean flag = false;
		for(int i=0;cookies!=null&&i<cookies.length;i++) {
			if("username".equals(cookies[i].getName())) {
				pw.append(cookies[i].getValue());
				flag=true;
			}
		}
		if(!flag) {
			response.sendRedirect("cookiedemo");
		}
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
