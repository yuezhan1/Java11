package zzti.servlet.web;


import zzti.dao.PersonalDAO;
import zzti.entity.PersonalInfoDO;
import zzti.entity.UserDO;
import zzti.util.DAOFactory;
import zzti.util.PathConstence;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="PersonalInfoProcess",urlPatterns = {"/web/PersonalInfoProcess"})
public class PersonalInfoProcess extends HttpServlet {
	PersonalDAO personalDAO = (PersonalDAO) DAOFactory.getDAO(DAOFactory.PERSONAL_DAO_CLASS_NAME);;
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		UserDO u= null;
		if(request.getSession().getAttribute("user")!=null){
			u = (UserDO)request.getSession().getAttribute("user");
			String targetPath = PathConstence.W_SERVLET_BASE+"/GetPsersonalServlet";
			
			String requestType = request.getParameter("requestType");
			if(requestType!=null&&"next".equals(requestType)){
				targetPath = PathConstence.JSP_WEB_BASE+"/personal/personalPage2.jsp";
				PersonalInfoDO p = new PersonalInfoDO();
				p.setAge(Integer.parseInt(request.getParameter("age")));
				p.setGender(request.getParameter("gender"));
				p.setAddress(request.getParameter("address"));
				p.setTel(request.getParameter("tel"));
				p.setEmail(request.getParameter("email"));
				//System.out.println(p);
				request.getSession().setAttribute("personal", p);
			}else if(requestType!=null&&"save".equals(requestType)){
				PersonalInfoDO p = (PersonalInfoDO)request.getSession().getAttribute("personal");
				p.setHighestEducation(request.getParameter("highestEducation"));
				p.setGraduateSchool(request.getParameter("graduateSchool"));
				p.setMajor("major");
				try {
					personalDAO.setPersonalInfo(u.getUsername(), p);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			response.sendRedirect(request.getContextPath() + targetPath);
		}else{
			response.sendRedirect(request.getContextPath() + PathConstence.JSP_WEB_BASE+"/login.jsp");
		}
		



	}

}
