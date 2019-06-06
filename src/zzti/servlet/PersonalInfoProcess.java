package zzti.servlet;


import zzti.dao.PersonalDAO;
import zzti.dao.impl.constance.PersonalDAOImpl;
import zzti.entity.PersonalInfoDO;
import zzti.entity.UserDO;
import zzti.util.PathConstence;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="PersonalInfoProcessDemo",urlPatterns = {"/servlet/PersonalInfoProcess"})
public class PersonalInfoProcess extends HttpServlet {
	PersonalDAO personalDAO = new PersonalDAOImpl();
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = PathConstence.getBasePath(request.getParameter("type"));
		UserDO u= null;
		if(request.getSession().getAttribute("user")!=null){
			u = (UserDO)request.getSession().getAttribute("user");
			String targetPath = PathConstence.M_SERVLET_BASE+"/GetPersonalServlet";
			PersonalInfoDO p = new PersonalInfoDO();
			p.setAge(Integer.parseInt(request.getParameter("age")));
			p.setGender(request.getParameter("gender"));
			p.setAddress(request.getParameter("address"));
			p.setTel(request.getParameter("tel"));
			p.setEmail(request.getParameter("email"));
			p.setHighestEducation(request.getParameter("highestEducation"));
			p.setGraduateSchool(request.getParameter("graduateSchool"));
			p.setMajor("major");
			try {
				personalDAO.setPersonalInfo(u.getUsername(), p);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			 * 第二种写法，将两个页面的信息都提交给这个业务处理类中，利用session完成数据存储 
			 */
//			String requestType = request.getParameter("requestType");
//			if(requestType!=null&&"next".equals(requestType)){
//				targetPath = "personalPage2";
//				PersonalInfo p = new PersonalInfo();
//				p.setAge(Integer.parseInt(request.getParameter("age")));
//				p.setGender(request.getParameter("gender"));
//				p.setAddress(request.getParameter("address"));
//				p.setTel(request.getParameter("tel"));
//				p.setEmail(request.getParameter("email"));
//				//System.out.println(p);
//				request.getSession().setAttribute("personal", p);
//			}else if(requestType!=null&&"save".equals(requestType)){
//				PersonalInfo p = (PersonalInfo)request.getSession().getAttribute("personal");
//				p.setHighestEducation(request.getParameter("highestEducation"));
//				p.setGraduateSchool(request.getParameter("graduateSchool"));
//				p.setMajor("major");
//				personalDAO.setPersonalInfo(u.getUsername(), p);
//				//System.out.println(p);
//			}
			response.sendRedirect(request.getContextPath() + "/" +targetPath);
		}else{
			response.sendRedirect(request.getContextPath() +request.getSession().getAttribute("sessionType")+"/login.jsp");
		}
		



	}

}
