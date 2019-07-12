package web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;

import domain.Cart;
import domain.CartItem;
import domain.Category;
import domain.Order;
import domain.OrderItem;
import domain.PageBean;
import domain.Product;
import domain.User;
import redis.clients.jedis.Jedis;
import service.CategoryService;
import service.ProductService;
import utils.CommonsUtils;
import utils.JedisPoolUtils;

public class ProductServlet extends BaseServlet {

	// 首页
	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		CategoryService categoryService=new CategoryService();
		ProductService productService = new ProductService();
		List<Product> topProduct = productService.findTop();
//	    List<Category> allCategory=categoryService.findAll();
//	    request.setAttribute("allCategory", allCategory);
		request.setAttribute("topProduct", topProduct);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	// 显示商品列表
	public void productList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获得类别id
		String cid = request.getParameter("cid");
		// 获得当前页
		String currentPageStr = request.getParameter("currentPage");
		if (currentPageStr == null) {
			currentPageStr = "1";
		}
		int currentPage = Integer.parseInt(currentPageStr);
		// 每页显示4条数据
		int currentCount = 4;
		ProductService service = new ProductService();
		PageBean<Product> pageBean = service.findProductListByPage(cid, currentPage, currentCount);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("cid", cid);
		request.getRequestDispatcher("/productList.jsp").forward(request, response);

	}

	// 显示商品的类别功能
	public void categoryList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CategoryService service = new CategoryService();

		// 先从缓存中查询categoryList 如果有直接使用 没有在从数据库中查询 存到缓存中
		// 1、获得jedis对象 连接redis数据库
		Jedis jedis = JedisPoolUtils.getJedis();
		String categoryListJson = jedis.get("categoryListJson");
		// 2、判断categoryListJson是否为空
		if (categoryListJson == null) {
			System.out.println("缓存没有数据 查询数据库");
			// 准备分类数据
			List<Category> categoryList = service.findAll();
			Gson gson = new Gson();
			categoryListJson = gson.toJson(categoryList);
			jedis.set("categoryListJson", categoryListJson);
		}

		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(categoryListJson);
	}

	// 获得商品详细信息
	public void productInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String product_id = request.getParameter("product_id");
		String cid = request.getParameter("cid");
		String currentPage = request.getParameter("currentPage");
		ProductService service = new ProductService();
		Product product = service.findProductByPid(product_id);
		request.setAttribute("product", product);
		request.setAttribute("cid", cid);
		request.setAttribute("currentPage", currentPage);
		request.getRequestDispatcher("/product_info.jsp").forward(request, response);
	}

	// 加入购物车
	public void addProductToCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		ProductService service = new ProductService();

		// 获得要放到购物车的商品的pid
		String pid = request.getParameter("pid");
		// 获得该商品的购买数量
		int buyNum = Integer.parseInt(request.getParameter("buyNum"));
		// 获得product对象
		Product product = service.findProductByPid(pid);
		// 计算小计
		double subtotal = product.getProduct_price() * buyNum;
		// 封装CartItem
		CartItem item = new CartItem();
		item.setProduct(product);
		item.setBuyNum(buyNum);
		item.setSubtotal(subtotal);

		// 获得购物车---判断是否在session中已经存在购物车
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
		}

		// 将购物项放到车中---key是pid
		// 先判断购物车中是否已将包含此购物项了 ----- 判断key是否已经存在
		// 如果购物车中已经存在该商品----将现在买的数量与原有的数量进行相加操作
		Map<String, CartItem> cartItems = cart.getCartItems();

		double newsubtotal = 0.0;

		if (cartItems.containsKey(pid)) {
			// 取出原有商品的数量
			CartItem cartItem = cartItems.get(pid);
			int oldBuyNum = cartItem.getBuyNum();
			oldBuyNum += buyNum;
			cartItem.setBuyNum(oldBuyNum);
			cart.setCartItems(cartItems);
			// 修改小计
			// 原来该商品的小计
			double oldsubtotal = cartItem.getSubtotal();
			// 新买的商品的小计
			newsubtotal = buyNum * product.getProduct_price();
			cartItem.setSubtotal(oldsubtotal + newsubtotal);

		} else {
			// 如果车中没有该商品
			cart.getCartItems().put(product.getProduct_id(), item);
			newsubtotal = buyNum * product.getProduct_price();
		}

		// 计算总计
		double total = cart.getTotal() + newsubtotal;
		cart.setTotal(total);

		// 将车再次访问session
		session.setAttribute("cart", cart);
		System.out.println("把购物车放入到session");
		// 直接跳转到购物车页面
		response.sendRedirect(request.getContextPath() + "/cart.jsp");
	}

	// 从购物车中删除单个商品
	public void delProFromCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 获得要删除的item的pid
		String pid = request.getParameter("pid");
		// 删除session中的购物车中的购物项集合中的item
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart != null) {
			Map<String, CartItem> cartItems = cart.getCartItems();
			// 重新设置购物车总价
			cart.setTotal(cart.getTotal() - cartItems.get(pid).getSubtotal());
			// 删除购物项
			cartItems.remove(pid);
			cart.setCartItems(cartItems);

		}
		session.setAttribute("cart", cart);

		// 跳转回购物车页面

		response.sendRedirect(request.getContextPath() + "/cart.jsp");

	}

	// 提交订单
	public void submitOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		// 判断用户是否已经登录 未登录下面代码不执行
		User user = (User) session.getAttribute("loginUser");
		if (user == null) {
			// 没有登录
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}

		// 目的：封装好一个Order对象 传递给service层
		Order order = new Order();

		// 1、private String oid;//该订单的订单号
		String oid = CommonsUtils.getUUID();
		order.setOid(oid);

		// 2、private Date ordertime;//下单时间
		order.setOrdertime(new Date());

		// 3、private double total;//该订单的总金额
		// 获得session中的购物车
		Cart cart = (Cart) session.getAttribute("cart");
		double total = cart.getTotal();
		order.setTotal(total);

		// 4、private int state;//订单支付状态 1代表已付款 0代表未付款
		order.setState(0);

		// 5、private String address;//收货地址
		order.setAddr(null);

		// 6、private String name;//收货人
		order.setName(null);

		// 7、private String telephone;//收货人电话
		order.setTel(null);

		// 8、private User user;//该订单属于哪个用户
		order.setUser(user);

		// 9、该订单中有多少订单项List<OrderItem> orderItems = new ArrayList<OrderItem>();
		// 获得购物车中的购物项的集合map
		Map<String, CartItem> cartItems = cart.getCartItems();
		for (Map.Entry<String, CartItem> entry : cartItems.entrySet()) {
			// 取出每一个购物项
			CartItem cartItem = entry.getValue();
			// 创建新的订单项
			OrderItem orderItem = new OrderItem();
			// 1)private String itemid;//订单项的id
			orderItem.setItemid(CommonsUtils.getUUID());
			// 2)private int count;//订单项内商品的购买数量
			orderItem.setCount(cartItem.getBuyNum());
			// 3)private double subtotal;//订单项小计
			orderItem.setSubtotal(cartItem.getSubtotal());
			// 4)private Product product;//订单项内部的商品
			orderItem.setProduct(cartItem.getProduct());
			// 5)private Order order;//该订单项属于哪个订单
			orderItem.setOid(order);

			// 将该订单项添加到订单的订单项集合中
			order.getOrderItems().add(orderItem);
		}

		// order对象封装完毕
		// 传递数据到service层
		ProductService service = new ProductService();
		service.submitOrder(order);

		session.setAttribute("order", order);

		// 页面跳转
		response.sendRedirect(request.getContextPath() + "/order_info.jsp");

	}

	// 确认订单---更新收获人信息
	public void confirmOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1、更新收货人信息
		Map<String, String[]> properties = request.getParameterMap();
		Order order = new Order();
		try {
			BeanUtils.populate(order, properties);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		ProductService service = new ProductService();
		service.updateOrderAdrr(order);
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	// 获得当前用户的订单集合
	public void myOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		// 判断用户是否已经登录 未登录下面代码不执行
		User user = (User) session.getAttribute("loginUser");
		if (user == null) {
			// 没有登录
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}
		// 查询当前用户的所有订单集合(单表查询order)
		ProductService service = new ProductService();
		// ！查询得到的orders对象是不完整的，缺少List<orderItem>
		List<Order> orderList = service.findAllOrders(user.getUser_id());
		// 循环订单集合，填充订单项集合信息
		for (Order order : orderList) {
			// 获得每一个订单的order_id
			String oid = order.getOid();
			// 获取该订单下的所有订单项 ！查询得到的订单项是不完整的，缺少Product对象
			// mapList中封装的是多个订单项和该订单项中的商品信息
			List<Map<String, Object>> mapList = service.findAllOrderItemsByOid(oid);
			// 把mapList转换成List<Order> orderList
			for (Map<String, Object> map : mapList) {
				OrderItem item = new OrderItem();
				Product product = new Product();
				try {
					// 从map中取出count,subtotal封装到orderItem中
					BeanUtils.populate(item, map);
					// 从map中取出product_name,procuct_image,product_price封装到Product中
					BeanUtils.populate(product, map);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 将Product封装到orderItem中
				item.setProduct(product);
				// 将orderItem封装到orderList中
				order.getOrderItems().add(item);
			}
		}
		// orderList封装完毕，存放到session域中，跳转到order_info.jsp页面循环显示
		request.setAttribute("orderList", orderList);
		request.getRequestDispatcher("/order_list.jsp").forward(request, response);

	}
}
