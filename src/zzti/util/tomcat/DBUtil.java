package zzti.util.tomcat;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	private static String username = "root";
	private static String password = "password";
	private static String url = "jdbc:mysql://localhost:3306/shop?useUnicode=true;characterEncoding=utf8;serverTimezone=UTC";
	private static String driverName = "com.mysql.jdbc.Driver";

private static DataSource ds;//定义一个连接池对象
	
    public static DataSource getDataSource() {
		return ds;
	}


    static{
        try{
            Context initCtx = new InitialContext();
            Context envCtx = (Context)initCtx.lookup("java:comp/env");
            ds = (DataSource) envCtx.lookup("shop");    
            //根据<Resource>元素的name属性值到JNDI容器中检索连接池对象 
        }catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    //从池中获取一个连接
    public static Connection getConnection() throws SQLException{
        return ds.getConnection();
    }
    
    public static void closeAll(ResultSet rs,Statement stmt,Connection conn){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        if(conn!=null){
            try {
                conn.close();//关闭
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
