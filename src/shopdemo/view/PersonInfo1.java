package shopdemo.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopdemo.util.HTMLUtil;

/**
 * Servlet implementation class PersonInfo1
 */
@WebServlet("/view/p1")
public class PersonInfo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonInfo1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String htmlTitle = "个人主页-1";
		String controllerPath = getServletContext().getContextPath() + "/PersonProcess";
		StringBuilder sb = new StringBuilder();
		sb.append("<form name='p1' method='post' action='").append(controllerPath ).append("'>")
		.append("<br/>")
		.append("学校名称：<input type='text' name='schoolName'/><br/>")
		.append("专业方向：<select name='professional' ")
		.append("<option value='1'>金融信息化</option>")
		.append("<oprion value='2'>大数据分析</option>")
		.append("")
		.append("</form>");
		PrintWriter pw = response.getWriter();
		pw.println(HTMLUtil.generateHtml(htmlTitle, sb.toString()));
		pw.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
