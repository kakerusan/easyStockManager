package thirdGroup.dao;

import java.sql.*;
import java.util.Vector;

import thirdGroup.bean.Goods;
import thirdGroup.util.DB;

public class GoodsmanagementImp implements Goodsmanagement{
			public static Vector vec = new Vector();
			//获取数据库连接
			Connection conn = DB.getConnection();
			//查询方法
			@Override
            public void Query(String sql) throws SQLException {
				// TODO Auto-generated method stub
				//加载SQL语句
				PreparedStatement pra = conn.prepareStatement(sql);
				//放入结果集
				ResultSet rs = pra.executeQuery();
				vec.removeAllElements();
				while(rs.next()) {
					Vector v = new Vector();
					v.add(rs.getInt("id"));
					v.add(rs.getString("goodsname"));
					v.add(rs.getString("goodsstyle"));
					v.add(rs.getInt("goodsnumber"));
					v.add(rs.getInt("storageID"));
					v.add(rs.getDate("dateOfManufacture"));
					v.add(rs.getString("supplier"));
					vec.add(v);
				}
			}
			
			public boolean Query1(Goods goods, String sql) throws SQLException {
				// TODO Auto-generated method stub
				//加载SQL语句
				PreparedStatement pra = conn.prepareStatement(sql);
				//放入结果集
				ResultSet rs = pra.executeQuery();
				//遍历结果集
				return false;
			}
			
			@Override
            public void Add(Goods goods, String sql) throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement pra = conn.prepareStatement(sql);
				pra.setString(1, goods.getGoodsname());
				pra.setString(2, goods.getGoodsstyle());
				pra.setInt(3, goods.getGoodsnumber());
				pra.setString(4, goods.getStorageID());
				pra.setDate(5, new Date(goods.getDateOfManufacture().getTime()));
				pra.setString(6,goods.getSupplier());
				pra.executeUpdate();
				pra.close();
			}

			@Override
            public void Delete(String sql) throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement pra = conn.prepareStatement(sql);
				pra.executeUpdate();
				pra.close();
			}

			@Override
            public void Update(Goods goods, String sql) throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement pra = conn.prepareStatement(sql);
				pra.setString(1, goods.getGoodsname());
				pra.setString(2, goods.getGoodsstyle());
				pra.setInt(3, goods.getGoodsnumber());
				pra.setString(4, goods.getStorageID());
				pra.setDate(5, new Date(goods.getDateOfManufacture().getTime()));
				pra.setString(6,goods.getSupplier());
				pra.executeUpdate();
				pra.close();
			}
}
