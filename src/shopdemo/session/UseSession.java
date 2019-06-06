package shopdemo.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UseSession
 */
@WebServlet("/UseSession")
public class UseSession extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		String mydata = "test.....";
		session.setAttribute("data", mydata);
		
		Object o  = session.getAttribute("data");
		
		response.getWriter().println("当前会话的sessionid是：" + session.getId());
		
		if(session.isNew()) {
			System.out.println("当前session是新建的");
		}else {
			System.out.println("这个session不是新的");
		}
		///response.ec
		System.out.println(response.encodeURL("1.html"));
		response.getWriter().println("<a href='1.html?JSESSIONID=" +session.getId()+"'" );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
