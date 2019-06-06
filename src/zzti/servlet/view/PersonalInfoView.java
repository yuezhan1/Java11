package zzti.servlet.view;

import zzti.dao.PersonalDAO;
import zzti.dao.impl.constance.PersonalDAOImpl;
import zzti.entity.PersonalInfoDO;
import zzti.entity.UserDO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


/**
 * 展现个人信息
 * @author guoli
 *
 */
@WebServlet("/PersonalInfoView")
public class PersonalInfoView extends HttpServlet {
	PersonalDAO personalDAO = new PersonalDAOImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML>");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>Web应用首页</TITLE></HEAD>");
		out.println("  <BODY>");
		out.println("<br><a href='IndexView'>进入首页</a>");
		out.println("|<a href='PersonalInfoView'>个人中心</a>");
		out.println("|<a href='AuctionListServlet'>商品列表</a><br/>");
		out.println("<br><a href='PersonalPage1'>完善个人信息</a>");
		String body="还没有完善个人信息 ";
		UserDO user = (UserDO)request.getSession().getAttribute("user");
		PersonalInfoDO p = null;
		try {
			p = personalDAO.getPersonalInfo(user.getUsername());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//(PersonalInfo)personalObject;
		
		if(p!=null){
			body="<table><tr><td>年龄：</td><td>" + p.getAge()+
					"</td></tr><tr><td>性别：</td><td>" + p.getGender()+
					"</td></tr><tr><td>家庭住址：</td><td>" +p.getAddress()+
					"</td></tr><tr><td>联系方式：</td><td>" +p.getTel()+
					"</td></tr><tr><td>email：</td><td>" +p.getEmail()+
					"</td></tr><tr><td>毕业院校：</td><td>" +p.getGraduateSchool()+
					"</td></tr><tr><td>最高学历：</td><td>" +p.getHighestEducation()+
					"</td></tr><tr><td>专业方向：</td><td>" +p.getMajor()+
					"</td></tr></table>";
		}
		out.println(body);
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
}
