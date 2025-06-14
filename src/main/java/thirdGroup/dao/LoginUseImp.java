package thirdGroup.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import thirdGroup.util.DB;
import thirdGroup.bean.User;

public class LoginUseImp implements LoginUse{
		public static Vector vec = new Vector();
		//获取数据库连接
		Connection conn = DB.getConnection();
		//查询方法
		@Override
        public boolean Query(User user, String sql) throws SQLException {
			//加载SQL语句
			PreparedStatement pra = conn.prepareStatement(sql);
			pra.setString(1, user.getUsername());
			pra.setString(2, user.getUserpwd());
			//放入结果集
			ResultSet rs = pra.executeQuery();
			//遍历结果集
			while(rs.next()){
				
				//获取数据库列字段
				String name = rs.getString("username");
				String pass = rs.getString("userpwd");
				//判断用户名和密码是否符合数据库数据
				if(name.equals(user.getUsername()) && pass.equals(user.getUserpwd())) {
					//符合数据返回 true
					return true;
				}else{
					//不符合数据返回false
					return false;
				}
			}
			return false;
		}
		
		public boolean Query1(User user, String sql) throws SQLException {
			// TODO Auto-generated method stub
			//加载SQL语句
			PreparedStatement pra = conn.prepareStatement(sql);
			//放入结果集
			ResultSet rs = pra.executeQuery();
			//遍历结果集
			while(rs.next()){
				
				//获取数据库列字段
				String name = rs.getString("username");
				String pass = rs.getString("userpwd");
				String flag = rs.getString("flag");
				//判断用户名和密码是否符合数据库数据
				if(name.equals(user.getUsername()) ) {
					//符合数据返回 true
					user.setUsername(name);
					user.setUserpwd(pass);
					user.setFlag(flag);
					return true;
				}else{
					//不符合数据返回false
					return false;
				}
			}
			return false;
		}
		
		@Override
        public void Add(User user, String sql) throws SQLException {
			// TODO Auto-generated method stub
			PreparedStatement pra = conn.prepareStatement(sql);
			pra.setString(1, user.getUsername());
			pra.setString(2, user.getUserpwd());
			pra.setString(3, user.getFlag());
			pra.executeUpdate();
			pra.close();
		}

		@Override
        public void Delete(User user, String sql) throws SQLException {
			// TODO Auto-generated method stub
			PreparedStatement pra = conn.prepareStatement(sql);
			pra.executeUpdate();
			pra.close();
		}

		@Override
        public void Update(User user, String sql) throws SQLException {
			// TODO Auto-generated method stub
			PreparedStatement pra = conn.prepareStatement(sql);
			pra.executeUpdate();
			pra.close();
		}

		public void Select(String sql) throws SQLException {
			PreparedStatement pra = conn.prepareStatement(sql);
			ResultSet rs = pra.executeQuery();
			vec.removeAllElements();
			while(rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt("id"));
				v.add(rs.getString("username"));
				if(rs.getString("flag").equals("1")) {
					v.add("普通用户");
				}else {
					v.add("管理员");
				}
				vec.add(v);
			}
		}
}
