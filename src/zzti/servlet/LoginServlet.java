package zzti.servlet;


import zzti.dao.UserDAO;
import zzti.entity.UserDO;
import zzti.util.DAOFactory;
import zzti.util.PathConstence;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Date;

@WebServlet(name="LoginServletDemo",urlPatterns = {"/servlet/LoginServlet"})
public class LoginServlet extends HttpServlet {

	UserDAO userDAO = (UserDAO) DAOFactory.getDAO(DAOFactory.USER_DAO_CLASS_NAME);
	public String checkLogin(UserDO user) throws SQLException {
		String errorInfo=null;
		if(user.getUsername()==null||"".equals(user.getUsername().trim())
				||user.getPassword()==null||"".equals(user.getPassword().trim())){
			errorInfo = "用户名或者密码不能为空";
		}else if(userDAO.findUser(user.getUsername(), user.getPassword())==null){
			errorInfo = "用户名或者密码不正确";

		}

		return errorInfo;
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserDO user = new UserDO(username,password,new Date());

		String targetPath = request.getContextPath()+"/";
		String error = "";
		try {
			error = checkLogin(user);
			targetPath = request.getContextPath();
			if(error==null){
				//在登录模块中需要在session、中设置两个数值：1、当前的登录用户，2、当前登录用户的登录端，前台还是后台
				request.getSession().setAttribute("user", user);
				request.getSession().setAttribute("sessionType", PathConstence.getRequestPath(request));
				targetPath += PathConstence.M_SERVLET_BASE + "/AuctionListServlet";

			}else{

				targetPath += PathConstence.getRequestPath(request)+ "/login.jsp?error="+ URLEncoder.encode(error,"UTF-8");

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.sendRedirect(targetPath);

	}

}
