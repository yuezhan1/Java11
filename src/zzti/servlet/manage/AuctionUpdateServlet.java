package zzti.servlet.manage;


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

/**
 * Servlet implementation class AuctionUpdateServlet
 */
@WebServlet(name = "AuctionUpdateServlet",urlPatterns = { "/manage/AuctionUpdateServlet"})
public class AuctionUpdateServlet extends HttpServlet {
	AuctionDAO auctionDAO =  (AuctionDAO) DAOFactory.getDAO(DAOFactory.AUCTION_DAO_CLASS_NAME);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String id = req.getParameter("id");
        String title = req.getParameter("title");
        String auctionDescription = req.getParameter("auctionDescription");
        String price = req.getParameter("price");
        AuctionDO auc = new AuctionDO(title,auctionDescription,Float.parseFloat(price));
        auc.setId(id);
        try {
			auctionDAO.updateAuction(auc);
		} catch (SQLException e) {
			e.printStackTrace();
		};
        resp.sendRedirect(this.getServletContext().getContextPath()+ PathConstence.M_SERVLET_BASE+"/AuctionListServlet");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
