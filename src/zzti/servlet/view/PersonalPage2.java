package zzti.servlet.view;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/PersonalPage2")
public class PersonalPage2 extends HttpServlet {
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
		
//		PersonalInfo p = new PersonalInfo();
//		p.setAddress(request.getParameter("address"));
//		p.setAge(Integer.parseInt(request.getParameter("age")));
//		p.setEmail(request.getParameter("email"));
//		p.setTel(request.getParameter("tel"));
//		p.setGender(request.getParameter("gender"));
		
		String body = "<form action='PersonalInfoProcess' method='post'>" 
				+ "<table>"
				+ "<tr><td>最高学历：</td>"
				+ "<td><select name='highestEducation'>"
				+ "<option value='学士'>学士</option>"
				+ "<option value='硕士'>硕士</option>"
				+ "<option value='博士'>博士</option>"
				+ "<option value='其他'>其他</option>" + "</select></td></tr>"
				+ "<tr><td>毕业院校：</td>" 
				+ "<td><select name='graduateSchool'>"
				+ "<option value='北京大学'>北京大学</option>"
				+ "<option value='清华大学'>清华大学</option>"
				+ "<option value='其他院校'>其他院校</option>" + "</select></td></tr>"
				+ "<tr><td>所学专业：</td>" 
				+ "<td><input type='text' name='major'/></td></tr>"
				+ "<tr><td>" 
				+ "<input type='hidden' name='requestType' value='save'>" 
				+ "<input type='hidden' name='age' value='"+request.getParameter("age")+"'>" 
				+ "<input type='hidden' name='gender' value='"+request.getParameter("gender")+"'>" 
				+ "<input type='hidden' name='address' value='"+request.getParameter("address")+"'>" 
				+ "<input type='hidden' name='tel' value='"+request.getParameter("tel")+"'>" 
				+ "<input type='hidden' name='email' value='"+request.getParameter("email")+"'>" 
				+ "</td>"
				+ "<td><input type='submit' value='保存'></td></tr>"
				+ "</table>"
				+ " </form>";
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
