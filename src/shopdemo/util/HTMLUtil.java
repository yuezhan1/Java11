package shopdemo.util;
/**
 * HTML代码生成工具
 * @author admin
 *
 */
public class HTMLUtil {
	/**
	 * 生成HTML结构文档
	 * @param title
	 * @param body
	 * @return
	 */
	public static String generateHtml(String title,String body) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<html><head><title>")
		.append(title)
		.append("</title></head>")
		.append("<body>")
		.append(body)
		.append("</body></html>");
		return sb.toString();
	}
	/**
	 * 生成一个表单输入控件
	 * @param name
	 * @param type
	 * @return
	 */
	public static String gennerateInput(String name,String type) {
		
		return "";
	}

}
