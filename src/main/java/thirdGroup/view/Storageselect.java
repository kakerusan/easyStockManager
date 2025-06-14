package thirdGroup.view;

import java.awt.Font;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import thirdGroup.dao.StoragemanagementImp;

public class Storageselect extends Index{

	int id;
	private JTable table;
	
	public Storageselect(String name) {
		super(name);
		init();
	}
	@SuppressWarnings("unchecked")
	public void init() {
		Font t = new Font("楷体",Font.BOLD, 36);
		final Font f = new Font("楷体",Font.BOLD, 18);
		JLabel title = new JLabel("仓库信息");
		title.setFont(t);
		title.setBounds(250, 40, 500, 50);
		Vector v = new Vector();
		v.add("编号");
		v.add("仓库名称");
		v.add("仓库类型");
		v.add("仓库编号");
		StoragemanagementImp s = new StoragemanagementImp();
		table = new JTable(StoragemanagementImp.vec,v);
		table.setFont(f);
		table.getTableHeader().setFont(f);
		JScrollPane jp = new JScrollPane(table);
		jp.setBounds(50, 100, 500, 600);
		table.setRowHeight(50);
		// 创建一个默认的单元格渲染器并设置居中对齐
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);

		// 为表格的所有列设置相同的渲染器，以实现居中显示
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}
		try {
			s.Query("select * from storage");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		index.add(title);
		index.add(jp);
	}
}
