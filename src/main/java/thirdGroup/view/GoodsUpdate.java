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

import thirdGroup.bean.Goods;
import thirdGroup.dao.GoodsmanagementImp;
import thirdGroup.dao.StoragemanagementImp;

public class GoodsUpdate extends IndexAdmin{
	int id ;
	//声明表格
	private JTable table;
	JTextField name;
	JTextField style;
	JTextField number;
	JTextField dateManu;
	JTextField Manufactor;
	JComboBox s_id;

	
	public GoodsUpdate(String name) {
		super(name);
		init();
	}
	public void init() {
		Font t = new Font("楷体",Font.BOLD, 24);
		final Font f = new Font("楷体",Font.BOLD, 15);
		JLabel title = new JLabel("商品信息");
		JLabel goodsname = new JLabel("商品名称：");
		goodsname.setBounds(160, 180, 80, 30);
		name = new JTextField();
		name.setBounds(240, 180, 150, 30);
		JLabel goodsstyle = new JLabel("商品类型：");
		goodsstyle.setBounds(160, 230, 80, 30);
		style = new JTextField();
		style.setBounds(240, 230, 150, 30);
		JLabel goodsnumber = new JLabel("商品数量：");
		goodsnumber.setBounds(160, 280, 80, 30);
		number = new JTextField();
		number.setBounds(240, 280, 150, 30);
		JLabel dateOfManufacture=new JLabel("生产日期");
		dateOfManufacture.setBounds(160,330,80,30);
		dateManu=new JTextField();
		dateManu.setBounds(240,330,150,30);
		JLabel sample = new JLabel("日期格式：“年-月-日” 示例： 2024-01-01");
		sample.setBounds(160,380,380,30);//这个后俩参数差不多多大了
		sample.setFont(f);
		JLabel supplier=new JLabel("制造商");
		supplier.setBounds(160,430,80,30);
		Manufactor=new JTextField();
		Manufactor.setBounds(240,430,150,30);
		JLabel storageid = new JLabel("仓库编号：");
		storageid.setBounds(160, 480, 80, 30);
		StoragemanagementImp s = new StoragemanagementImp();
		try {
			s.Query1("select storageID from storage");
			s_id = new JComboBox(StoragemanagementImp.vr);
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
		s_id.setBounds(240, 480, 150, 30);
		title.setFont(t);
		title.setBounds(230, 10, 100, 30);
		final Vector c = new Vector();
		//添加数据
		c.add("编号");
		c.add("商品名称");
		c.add("商品类型");
		c.add("商品数量");
		c.add("仓库编号");
		c.add("生产日期");
		c.add("制造商");
		final GoodsmanagementImp g = new GoodsmanagementImp();
		try {
			g.Query("select * from goods");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		//创建表格
		table = new JTable(GoodsmanagementImp.vec,c);
		table.setFont(f);
		table.getTableHeader().setFont(f);
		//为表格添加鼠标单击事件
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 获取表格中的ID
				//获取每条记录所对应的id
				id = (int)table.getValueAt(table.getSelectedRow(), 0);
				String gname = (String)table.getValueAt(table.getSelectedRow(), 1);
				String gstyle = (String)table.getValueAt(table.getSelectedRow(), 2);
				String gnumber=table.getValueAt(table.getSelectedRow(), 3).toString();
				String sid=table.getValueAt(table.getSelectedRow(), 4).toString();
				String dateOfManufacture=table.getValueAt(table.getSelectedRow(),5).toString();
				String supplier=table.getValueAt(table.getSelectedRow(),6).toString();
				//将数据设置进文本框
				name.setText(gname);
				style.setText(gstyle);
				number.setText(gnumber);
				dateManu.setText(dateOfManufacture);
				Manufactor.setText(supplier);
				for(int i=0;i<s_id.getItemCount();i++){
					Integer g = (Integer)s_id.getItemAt(i);
					if(g.equals(Integer.valueOf(sid))){
						s_id.setSelectedIndex(i);
					}
				}
				super.mouseClicked(e);
			}
		});

		//创建Jscrollpane
		final JScrollPane js = new JScrollPane(table);
		js.setBounds(50, 60, 500, 100);
		//创建删除按钮
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
						Goods goods = new Goods();
						goods.setId(id);
						goods.setGoodsname(name.getText());
						goods.setGoodsstyle(style.getText());
						goods.setGoodsnumber(Integer.parseInt(number.getText()));
						goods.setStorageID(s_id.getSelectedItem().toString());
						goods.setDateOfManufacture(dateManu.getText());
						goods.setSupplier(Manufactor.getText());

						synchronized (GoodsUpdate.class) {
							g.Update(goods,"update goods set goodsname = ?,goodsstyle=?,goodsnumber=?,storageID=?,dateOfManufacture=?,supplier=? where id = "+ id);
							JOptionPane.showMessageDialog(null, "修改成功");
							name.setText("");
							style.setText("");
							number.setText("");
							dateManu.setText("");
							Manufactor.setText("");
							g.Query("select * from goods");
						}
						//原理：移除原组件，添加新组件
						//创建表格
						JTable new_table = new JTable(GoodsmanagementImp.vec,c);
						new_table.setFont(f);
						new_table.getTableHeader().setFont(f);

						//设置较高行高
						new_table.setRowHeight(30);
						JScrollPane jp = new JScrollPane(new_table);
						// 创建一个默认的单元格渲染器并设置居中对齐
						DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
						renderer.setHorizontalAlignment(SwingConstants.CENTER);

						//创建Jscrollpane
						JScrollPane p = new JScrollPane(new_table);

						//设置新组建的大小
						p.setBounds(50, 60, 500, 100);
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
		update.setBounds(250, 530, 80,30);
		index.add(title);
		index.add(goodsname);
		index.add(name);
		index.add(goodsstyle);
		index.add(style);
		index.add(goodsnumber);
		index.add(number);
		index.add(storageid);
		index.add(s_id);
		index.add(js);
		index.add(update);
		index.add(Manufactor);
		index.add(dateOfManufacture);
		index.add(supplier);
		index.add(dateManu);
		index.add(sample);
	}
}
