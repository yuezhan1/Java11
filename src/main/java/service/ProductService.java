package service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import dao.ProductDao;
import domain.Category;
import domain.Order;
import domain.OrderItem;
import domain.PageBean;
import domain.Product;
import utils.DataSourceUtils;

public class ProductService {

	//查找最热门的商品
	public List<Product> findTop() {
		ProductDao productDao = new ProductDao();
		List<Product> list = null;
		try {
			list = productDao.getTopProduct();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	//获取分类后的商品列表
	public List<Product> getProductList(String cid) {
		ProductDao productDao = new ProductDao();
		List<Product> list = null;
		try {
			list = productDao.getProductList(cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	//根据商品的id寻找到这个商品
	public Product findProductByPid(String pid) {
		ProductDao productDao = new ProductDao();
		Product product = null;
		try {
			product = productDao.findProductByPid(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}

	//用
	public PageBean<Product> findProductListByPage(String cid, int currentPage, int currentCount) {
		ProductDao dao = new ProductDao();

		// 封装pageBean
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setCurrentPage(currentPage);
		pageBean.setCurrentCount(currentCount);
		int totalCount = 0;

		try {
			totalCount = dao.getCount(cid);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		pageBean.setTotalCount(totalCount);

		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);

		int index = (currentPage - 1) * currentCount;

		List<Product> productList = null;
		try {
			productList = dao.findProductListByPage(cid, index, currentCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		pageBean.setList(productList);
		return pageBean;
	}

	//提交订单
	public void submitOrder(Order order) {
		ProductDao dao = new ProductDao();

		try {
			// 1、开启事务
			DataSourceUtils.startTransaction();
			// 2、调用dao存储order表数据的方法
			dao.addOrders(order);
			// 3、调用dao存储orderitem表数据的方法
			dao.addOrderItem(order);

		} catch (SQLException e) {
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				DataSourceUtils.commitAndRelease();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void updateOrderAdrr(Order order) {
		ProductDao dao = new ProductDao();
		try {
			dao.updateOrderAdrr(order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateOrderState(String r6_Order) {
		ProductDao dao = new ProductDao();
		try {
			dao.updateOrderState(r6_Order);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//获得指定用户的订单集合
	public List<Order> findAllOrders(String uid) {
		ProductDao dao = new ProductDao();
		List<Order> orderList=null;
		try {
			orderList = dao.findAllOrders(uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderList;
	}

	//根据订单id获取订单下的所有订单项
	public List<Map<String, Object>> findAllOrderItemsByOid(String oid) {
		ProductDao dao = new ProductDao();
		List<Map<String, Object>> mapList=null;
		try {
			mapList = dao.findAllOrderItemByOid(oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapList;
	}
}
