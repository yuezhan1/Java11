package zzti.servlet.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/PersonalPage1")
public class PersonalPage1 extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML>");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>Web应用首页</TITLE></HEAD>");
		out.println("  <BODY>");
		out.println("当前登录的用户是：");

		out.println("<br><a href='IndexView'>进入首页</a>");
		out.println("|<a href='PersonalInfoView'>个人中心</a>");
		out.println("|<a href='AuctionListServlet'>商品列表</a><br/>");
		
		
		String body = " <form action='PersonalPage2' method='post'>"
				+"<table>"
				+ "<tr><td>年龄：</td><td><input type='text' name='age'/></td></tr>"
				+ "<tr><td>性别：</td><td><input type='radio' name='gender' checked='checked' value='女'/>女"
				+ "<input type='radio' name='gender' value='男'/>男</td></tr>"
				+ "<tr><td>家庭住址：</td><td><input type='text' name='address'/></td></tr>"
				+ "<tr><td>联系方式：</td><td><input type='text' name='tel'/></td></tr>"
				+ "<tr><td>email：</td><td><input type='text' name='email'/></td></tr>"
				+ "<tr><td>" 
				/*+ "<input type='hidden' name='requestType' value='next'>" */
				+ "</td>"
				+ "<td><input type='submit' value='下一步'></td></tr>" 
				+"</table>"
				+ "</form>";
		out.println(body);
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
