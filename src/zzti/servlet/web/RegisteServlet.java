package zzti.servlet.web;


import zzti.dao.UserDAO;
import zzti.entity.ResultDO;
import zzti.entity.UserDO;
import zzti.util.DAOFactory;
import zzti.util.PathConstence;
import zzti.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by guoli on 17/7/6.
 */
@WebServlet(name="RegisteServlet",urlPatterns = {"/web/RegisteServlet"})
public class RegisteServlet extends HttpServlet {
    UserDAO userDAO = (UserDAO) DAOFactory.getDAO(DAOFactory.USER_DAO_CLASS_NAME);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("repassword");
        UserDO userDO = new UserDO();
        userDO.setUsername(username);
        userDO.setPassword(password);
        ResultDO resultDO = new ResultDO();
        resultDO.setResult(userDO);
        if(StringUtil.isEmpty(username)||StringUtil.isEmpty(password)||StringUtil.isEmpty(rePassword)){
            resultDO.setInfo("用户名或密码不能为空");
            request.setAttribute("result",resultDO);
            request.getRequestDispatcher(PathConstence.JSP_WEB_BASE+"/registe.jsp");
            return;

        }
        if (!password.equals(rePassword)){
            resultDO.setInfo("两次密码输入不一致");
            request.setAttribute("result",resultDO);
            request.getRequestDispatcher(PathConstence.JSP_WEB_BASE+"/registe.jsp");
            return;
        }
        try {
            userDAO.insertUser(userDO);
            resultDO.setInfo("注册成功");
            request.setAttribute("result",resultDO);
            request.getRequestDispatcher(PathConstence.W_SERVLET_BASE+"/getAllAuction");
        } catch (SQLException e) {
            e.printStackTrace();
            resultDO.setInfo("注册失败");
            request.setAttribute("result",resultDO);
            request.getRequestDispatcher(PathConstence.JSP_WEB_BASE+"/registe.jsp");
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
