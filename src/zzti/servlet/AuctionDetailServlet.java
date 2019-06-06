package zzti.servlet;


import zzti.dao.AuctionDAO;
import zzti.entity.AuctionDO;
import zzti.util.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class AuctionDetailServlet
 */
@WebServlet(name = "AuctionDetailDemo",urlPatterns = {"/servlet/AuctionDetailServlet"})
public class AuctionDetailServlet extends HttpServlet {
	 AuctionDAO auctionDAO =  (AuctionDAO) DAOFactory.getDAO(DAOFactory.AUCTION_DAO_CLASS_NAME);
	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        req.setCharacterEncoding("utf-8");
	        resp.setContentType("text/html;charset=utf-8");
	        String id = req.getParameter("id");
	       
	        AuctionDO auctionDO = null;
			try {
				auctionDO = auctionDAO.getAuction(id);
				req.setAttribute("auctionDO", auctionDO);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	       req.getRequestDispatcher( req.getSession().getAttribute("sessionType")+"/auction/auctionDetail.jsp").forward(req, resp);
	     //   resp.sendRedirect(this.getServletContext().getContextPath()+"/auctionJSTL/auctionDetail.jsp");

	    }

	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        doGet(req, resp);
	    }

}
