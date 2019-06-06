package zzti.servlet.web;


import zzti.dao.AuctionDAO;
import zzti.dao.TemporaryCartDAO;
import zzti.entity.AuctionDO;
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

/**
 * Created by guoli on 17/7/10.
 */
@WebServlet(name="AddCartServlet",urlPatterns = {"/web/AddCartServlet"})
public class AddCartServlet extends HttpServlet {
    private TemporaryCartDAO temporaryCartDAO = (TemporaryCartDAO) DAOFactory.getDAO(DAOFactory.TEMPORARY_CART_DAO_CLASS_NAME);
    private AuctionDAO auctionDAO = (AuctionDAO) DAOFactory.getDAO(DAOFactory.AUCTION_DAO_CLASS_NAME);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String basePath = request.getContextPath()+ PathConstence.W_SERVLET_BASE;
        String aucId = request.getParameter("id");
        if (aucId==null||"".equals(aucId)){
            response.sendRedirect(basePath+"/getAllAuctionServlet");
        }
        try {
            AuctionDO auctionDO = auctionDAO.getAuction(aucId);
            this.temporaryCartDAO.addToCart(((UserDO)request.getSession().getAttribute("user")).getUsername(),auctionDO);
            response.sendRedirect(basePath+"/CartListServlet");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(basePath+"/getAllAuctionServlet");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
