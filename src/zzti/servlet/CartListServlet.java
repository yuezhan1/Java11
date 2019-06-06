package zzti.servlet;


import zzti.dao.TemporaryCartDAO;
import zzti.entity.CartDO;
import zzti.entity.UserDO;
import zzti.util.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by guoli on 17/7/6.
 */
@WebServlet(name="CartListServletDemo",urlPatterns = {"/servlet/CartListServlet"})
public class CartListServlet extends HttpServlet {
    private TemporaryCartDAO temporaryCartDAO = (TemporaryCartDAO) DAOFactory.getDAO(DAOFactory.TEMPORARY_CART_DAO_CLASS_NAME);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDO user = (UserDO)session.getAttribute("user");
        List<CartDO> list = this.temporaryCartDAO.getAllFromCart(user.getUsername());
        request.setAttribute("cartList",list);
        request.getRequestDispatcher(session.getAttribute("sessionType")+"/shoppingCart/myShoppingCart.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
