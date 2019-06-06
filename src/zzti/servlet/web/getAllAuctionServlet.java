package zzti.servlet.web;


import zzti.dao.AuctionDAO;
import zzti.entity.AuctionDO;
import zzti.util.DAOFactory;
import zzti.util.PathConstence;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guoli on 17/7/6.
 */
@WebServlet(name="getAllAuctionServlet",urlPatterns = {"/web/getAllAuction"})
public class getAllAuctionServlet extends HttpServlet {
    AuctionDAO auctionDAO = (AuctionDAO) DAOFactory.getDAO(DAOFactory.AUCTION_DAO_CLASS_NAME);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<AuctionDO> list = new ArrayList<AuctionDO>();
        try {
            list = auctionDAO.getAll();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        req.setAttribute("auctionList",list);
        req.getRequestDispatcher(PathConstence.JSP_WEB_BASE+"/auction/auctionList.jsp").forward(req,resp);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
