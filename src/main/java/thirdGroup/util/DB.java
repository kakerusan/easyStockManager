package thirdGroup.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
	//数据库地址
	private String Driver_name =
			"jdbc:mysql://localhost:3306/stock_manager";
	//数据库用户名
	private String USER = "root";
	//数据库密码
	private String PASS = "20041118aa";
	//数据库连接
	public static Connection con;
	//构造方法
	public DB(){
		try {
			con = 
			DriverManager.getConnection(
			Driver_name, USER, PASS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//获取连接
	public static Connection getConnection(){
		if(con == null){
			new DB();
		}
		return con;
	}
}
