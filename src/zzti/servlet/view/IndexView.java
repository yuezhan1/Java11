package zzti.servlet.view;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/IndexView")
public class IndexView extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML>");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>Web应用首页</TITLE></HEAD>");
		out.println("  <BODY>");
		out.println("当前登录的用户是：");
		out.println(request.getSession().getAttribute("user"));
		out.println("<br><a href='IndexView'>进入首页</a>");
		out.println("|<a href='PersonalInfoView'>个人中心</a>");
		out.println("|<a href='AuctionListServlet'>商品列表</a><br/>");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
