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


/**
 * Servlet implementation class GetPsersonalServlet
 */
@WebServlet(name="GetPsersonalServlet",urlPatterns = {"/web/GetPsersonalServlet"})
public class GetPsersonalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PersonalDAO personalDAO = (PersonalDAO) DAOFactory.getDAO(DAOFactory.PERSONAL_DAO_CLASS_NAME);
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		UserDO u= null;
		if(request.getSession().getAttribute("user")!=null){
			u = (UserDO)request.getSession().getAttribute("user");
			try {
				PersonalInfoDO personal = personalDAO.getPersonalInfo(u.getUsername());
				request.setAttribute("personalInfo", personal);
			} catch (Exception e) {
				// TODO: handle exception
			}
			request.getRequestDispatcher( PathConstence.JSP_WEB_BASE+"/personal/personalInfo.jsp").forward(request, response);
		}else{
			response.sendRedirect(request.getContextPath() + PathConstence.JSP_WEB_BASE+"/login.jsp");
		}
	}

}
