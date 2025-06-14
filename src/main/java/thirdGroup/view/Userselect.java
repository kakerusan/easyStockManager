package thirdGroup.view;

import java.awt.Font;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import thirdGroup.dao.LoginUseImp;

public class Userselect extends IndexAdmin{
	//声明对象
	int id;
	private JTable table;
	
	//构造函数
	public Userselect(String name) {
		super(name);
		init();
	}
	
	public void init() {
		//初始化字体
		Font t = new Font("楷体",Font.BOLD, 38);
		final Font f = new Font("楷体",Font.BOLD, 28);
		//初始化对象
		JLabel title = new JLabel("用户信息");
		title.setFont(t);
		title.setBounds(200, 60, 400, 160);
		//初始化Vector集合
		Vector v = new Vector();
		//为集合添加字段
		v.add("编号");
		v.add("用户名");
		v.add("用户类型");
		LoginUseImp l = new LoginUseImp();
		//初始化表格
		table = new JTable(LoginUseImp.vec,v);
		table.setFont(f);

		//设置较高行高
		table.setRowHeight(50);
		JScrollPane jp = new JScrollPane(table);
		// 创建一个默认的单元格渲染器并设置居中对齐
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);

		// 为表格的所有列设置相同的渲染器，以实现居中显示
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}
		jp.setBounds(50, 200, 500, 400);
		try {
			l.Select("select * from users");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		index.add(title);
		index.add(jp);
	}
}
