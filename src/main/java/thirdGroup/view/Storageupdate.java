package thirdGroup.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import thirdGroup.bean.Storage;
import thirdGroup.dao.StoragemanagementImp;

public class Storageupdate extends IndexAdmin{
	int id ;
	//声明表格
	private JTable table;
	JTextField name;
	JTextField style;
	JTextField s_id;
	
	public Storageupdate(String name) {
		super(name);
		init();
	}
	public void init() {
		Font t = new Font("楷体",Font.BOLD, 28);
		final Font f = new Font("楷体",Font.BOLD, 20);
		JLabel title = new JLabel("仓库信息");
		JLabel storagename = new JLabel("仓库名称：");
		storagename.setBounds(70, 420, 160, 60);
		storagename.setFont(f);
		name = new JTextField();
		name.setBounds(250, 420, 300, 60);
		name.setFont(f);
		JLabel storagestyle = new JLabel("仓库类型：");
		storagestyle.setBounds(70, 500, 160, 60);
		storagestyle.setFont(f);
		style = new JTextField();
		style.setBounds(250, 500, 300, 60);
		style.setFont(f);
		JLabel storageid = new JLabel("仓库编号：");
		storageid.setBounds(70, 580, 160, 60);
		storageid.setFont(f);
		s_id = new JTextField();
		s_id.setBounds(250, 580, 300, 60);
		s_id.setFont(f);
		title.setFont(t);
		title.setBounds(200, 20, 200, 60);
		final Vector c = new Vector();
		//添加数据
		c.add("编号");
		c.add("仓库名称");
		c.add("仓库类型");
		c.add("仓库编号");
		final StoragemanagementImp s = new StoragemanagementImp();
		try {
			s.Query("select * from storage");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//创建表格
		table = new JTable(StoragemanagementImp.vec,c);
		table.setFont(f);
		table.getTableHeader().setFont(f);
		//为表格添加鼠标单击事件
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 获取表格中的ID
				//获取每条记录所对应的id
				id = (int)table.getValueAt(table.getSelectedRow(), 0);
				//获取部门名称
				String sname = (String)table.getValueAt(table.getSelectedRow(), 1);
				//获取部门编号
				String sstyle =(String)table.getValueAt(table.getSelectedRow(), 2);
				//获取部门描述
				String sid=(String)table.getValueAt(table.getSelectedRow(), 3).toString();
				//将数据设置进文本框3

				name.setText(sname);
				style.setText(sstyle);
				s_id.setText(sid);
				super.mouseClicked(e);
			}
		});

		//设置较高行高
		table.setRowHeight(30);

		JScrollPane jp = new JScrollPane(table);
		// 创建一个默认的单元格渲染器并设置居中对齐
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);

		// 为表格的所有列设置相同的渲染器，以实现居中显示
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}
		//创建Jscrollpane
		final JScrollPane js = new JScrollPane(table);
		js.setBounds(50, 100, 500, 300);



		//创建按钮
		final JButton update = new JButton("修改");
		//为按钮创建监听事件
		update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//判断Id是否在列表内
				if(id == 0){
					JOptionPane.showMessageDialog(null, "修改失败请选择需要修改的记录！");
				}else{
				try {
					//给出提示
					//4个参数：1.null 2.提示内容 3.标题 4.按钮类型
					int mess = JOptionPane.showConfirmDialog(
							null,"确定修改记录？","友情提示：",
							JOptionPane.YES_NO_OPTION );
					//点击确定按钮之后的事件
					//0 == 确定 ，1 == 取消
					if(mess == 0){
						//调用删除方法
						synchronized (Storageupdate.class) {
							Storage storage = new Storage();
							storage.setStoragename(name.getText());
							storage.setStoragestyle(style.getText());
							storage.setStorageID(Integer.parseInt(s_id.getText()));
							s.Update(storage,"update storage set storagename = ?,storagestyle=?,storageID=? where id = "+ id);
							//提示删除成功
							JOptionPane.showMessageDialog(null, "修改成功");
							s.Query("select * from storage");
						}
						//原理：移除原组件，添加新组件
						//创建表格
						JTable new_table = new JTable(StoragemanagementImp.vec,c);
						new_table.setFont(f);
						new_table.getTableHeader().setFont(f);

						//设置较高行高
						new_table.setRowHeight(30);
						JScrollPane jp = new JScrollPane(new_table);
						// 创建一个默认的单元格渲染器并设置居中对齐
						DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
						renderer.setHorizontalAlignment(SwingConstants.CENTER);

						// 为表格的所有列设置相同的渲染器，以实现居中显示
						for (int i = 0; i < new_table.getColumnCount(); i++) {
							new_table.getColumnModel().getColumn(i).setCellRenderer(renderer);
						}

						//创建Jscrollpane
						JScrollPane p = new JScrollPane(new_table);
						//设置新组建的大小
						p.setBounds(200, 140, 600, 260);


						//移除旧组件
						index.remove(js);
						//添加新组件
						index.add(p);
						//重绘组键
						index.repaint();
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
				}
			}
		});
		//设置位置及大小
		update.setBounds(80, 660, 120,60);
		update.setFont(f);
		index.add(title);
		index.add(storagename);
		index.add(name);
		index.add(storagestyle);
		index.add(style);
		index.add(storageid);
		index.add(s_id);
		index.add(js);
		//添加按钮
		index.add(update);
	}
}
