package zzti.servlet.view.auction;

import zzti.entity.AuctionDO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet("/AuctionListView")
public class AuctionListView extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.println("<!DOCTYPE HTML>");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>商品列表</TITLE></HEAD>");
		out.println("  <BODY>");
		out.println("当前登录的用户是：");
		
		out.println("<br><a href='IndexView'>进入首页</a>");
		out.println("|<a href='PersonalInfoView'>个人中心</a>");
		out.println("|<a href='AuctionListServlet'>商品列表</a><br/>");
		StringBuffer body = new StringBuffer();
		body.append("<table border='1'><tr>"
				+ "<td>商品编号</td>" +
				"<td>商品名称</td>" + 
				"<td>商品详情</td>" +
				"<td>商品价格</td>" +
				"<td>操作</td>" +
				"</tr>");
		Object o = req.getAttribute("auctionList");
		if(o!=null) {
			
			List<AuctionDO> list = (List<AuctionDO>)o;
			for(AuctionDO auc : list) {
				body.append("<tr>")
				.append("<td>"+auc.getId()+"</td>")
				.append("<td>"+auc.getTitle()+"</td>")
				.append("<td>"+auc.getDescription()+"</td>")
				.append("<td>"+auc.getPrice()+"</td>")
				.append("<td>"+"<a href=''>加入购物车</a>"+"</td>")
				.append("</tr>")
				;
				
			}
		}
		
		body.append("</table>");
		out.println(body.toString());
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
		
	}
}
