package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import domain.Category;
import domain.Order;
import domain.OrderItem;
import domain.Product;
import utils.DataSourceUtils;

public class ProductDao {
	public List<Product> getTopProduct() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product limit ?,?";
		List<Product> list = runner.query(sql, new BeanListHandler<Product>(Product.class), 0, 3);
		return list;
	}

	public List<Product> getProductList(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where cid=?";
		List<Product> list = runner.query(sql, new BeanListHandler<Product>(Product.class), cid);

		return list;
	}

	public Product findProductByPid(String pid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where product_id=?";
		Product product = runner.query(sql, new BeanHandler<Product>(Product.class), pid);

		return product;
	}

	// 根据cid获得商品总条数
	public int getCount(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from product where cid = ?";
		Long count = (Long) runner.query(sql, new ScalarHandler(), cid);
		return count.intValue();
	}

	// 根据类别查询商品
	public List<Product> findProductListByPage(String cid, int index, int currentCount) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where cid = ? limit ?,?";
		List<Product> list = runner.query(sql, new BeanListHandler<Product>(Product.class), cid, index, currentCount);
		return list;
	}

	//向orders表插入数据
	/*`order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单号',
  `order_productId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品id',
  `order_userId` varchar(255)*/
		public void addOrders(Order order) throws SQLException {
			QueryRunner runner = new QueryRunner();
			String sql = "insert into `order` values(?,?,?,?,?,?,?,?)";
			Connection conn = DataSourceUtils.getConnection();
			runner.update(conn,sql, order.getOid(),order.getOrdertime(),order.getTotal(),order.getState(),
					order.getAddr(),order.getName(),order.getTel(),order.getUser().getUser_id());
		}

		//向orderitem表插入数据
		public void addOrderItem(Order order) throws SQLException {
			QueryRunner runner = new QueryRunner();
			String sql = "insert into orderitem values(?,?,?,?,?)";
			Connection conn = DataSourceUtils.getConnection();
			List<OrderItem> orderItems = order.getOrderItems();
			for(OrderItem item : orderItems){
				runner.update(conn,sql,item.getItemid(),item.getCount(),item.getSubtotal(),item.getProduct().getProduct_id(),item.getOid().getOid());
			}
			
			
		}

		public void updateOrderAdrr(Order order) throws SQLException {
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			String sql = "update `order` set addr=?,name=?,tel=? where oid=?";
			runner.update(sql, order.getAddr(),order.getName(),order.getTel(),order.getOid());
		}

		public void updateOrderState(String r6_Order) throws SQLException {
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			String sql = "update `order` set state=? where oid=?";
			runner.update(sql, 1,r6_Order);
		}

		public List<Order> findAllOrders(String uid) throws SQLException {
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			String sql = "select * from `order` where userId=?";
			return runner.query(sql, new BeanListHandler<Order>(Order.class), uid);
		}

		public List<Map<String, Object>> findAllOrderItemByOid(String oid) throws SQLException {
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			String sql = "select i.count,i.subtotal,p.product_name,p.product_price,p.product_image from orderitem i,product p where i.pid=p.product_id and i.oid=?";
			//多表查询时，结果集不止一个，用MapListHandler封装
			List<Map<String, Object>> mapList=runner.query(sql, new MapListHandler(),oid);
			return mapList;
		}

}
