package thirdGroup.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import thirdGroup.bean.Storage;
import thirdGroup.dao.StoragemanagementImp;

public class Storageadd extends IndexAdmin{
	
	JTextField name;
	JTextField style;
	JTextField id;
	Storage storage = new Storage();
	
	public Storageadd(String name) {
		super(name);
		init();
	}
	public void init() {
		Font d = new Font("楷体", Font.BOLD, 48);
		Font f = new Font("楷体", Font.BOLD, 36);
		JLabel storagetitle = new JLabel("仓库添加");
		JLabel storagename = new JLabel("仓库名：");
		name = new JTextField();
		JLabel storagestyle = new JLabel("类  型：");
		style = new JTextField();
		JLabel storageid = new JLabel("编  号：");
		id = new JTextField();
		JButton submit = new JButton("提交");
		JButton reset = new JButton("重置");
		storagetitle.setBounds(180, 50, 200, 70);
		storagetitle.setFont(d);
		storagename.setBounds(70, 180, 160, 50);
		storagename.setFont(f);
		name.setBounds(250, 180, 200, 50);
		name.setFont(f);
		storagestyle.setBounds(70, 300, 160, 50);
		storagestyle.setFont(f);
		style.setBounds(250, 300, 200, 50);
		style.setFont(f);
		storageid.setBounds(70, 420, 160, 50);
		storageid.setFont(f);
		id.setBounds(250, 420, 200, 50);
		id.setFont(f);
		submit.setBounds(70, 560, 160, 60);
		submit.setFont(f);
		reset.setBounds(300, 560, 160, 60);
		reset.setFont(f);
		index.add(storagetitle);
		index.add(storagename);
		index.add(name);
		index.add(storagestyle);
		index.add(style);
		index.add(storageid);
		index.add(id);
		index.add(submit);
		index.add(reset);
		reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				name.setText("");
				style.setText("");
				id.setText("");
			}
		});
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				if(name.getText().equals("") || style.getText().equals("") || id.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"请输入内容");
				} else {
					storage.setStoragename(name.getText());
					storage.setStoragestyle(style.getText());
					storage.setStorageID(Integer.parseInt(id.getText()));
					StoragemanagementImp s = new StoragemanagementImp();
					try {
						synchronized (Storageadd.class) {
							s.Add(storage, "insert into storage(storagename,storagestyle,storageID)" + "values(?,?,?)");
							name.setText("");
							style.setText("");
							id.setText("");
							JOptionPane.showMessageDialog(null, "添加成功");
						}
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
				}
			}
		});
	}
}
