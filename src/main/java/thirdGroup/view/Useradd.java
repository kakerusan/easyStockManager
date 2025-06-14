package thirdGroup.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import thirdGroup.bean.User;
import thirdGroup.dao.LoginUseImp;

public class Useradd extends IndexAdmin{
	//声明部分对象
	JTextField name;
	JTextField pwd;
	JTextField style;
	//为User类初始化对象user
	User user = new User();
	
	//构造函数
	public Useradd(String name) {
		super(name);
		init();
	}
	
	
	public void init() {
		//初始化字体
		Font d = new Font("楷体", Font.BOLD, 48);
		Font f = new Font("楷体", Font.BOLD, 36);
		//初始化对象
		JLabel usertitle = new JLabel("添加用户");
		JLabel username = new JLabel("用户名：");
		name = new JTextField();
		JLabel userpwd = new JLabel("密  码：");
		pwd = new JTextField();
		JLabel userstyle = new JLabel("类  型：");
		style = new JTextField();
		JButton submit = new JButton("提交");
		JButton reset = new JButton("重置");
		//设置对象的位置、大小和字体
		usertitle.setBounds(200, 50, 200, 80);
		usertitle.setFont(d);
		username.setBounds(70, 160, 160, 60);
		username.setFont(f);
		name.setBounds(230, 160, 300, 60);
		name.setFont(f);
		userpwd.setBounds(70, 280, 160, 60);
		userpwd.setFont(f);
		pwd.setBounds(230, 280, 300, 60);
		pwd.setFont(f);
		userstyle.setBounds(70, 400, 160, 60);
		userstyle.setFont(f);
		style.setBounds(230, 400, 300, 60);
		style.setFont(f);
		submit.setBounds(70, 520, 160, 60);
		submit.setFont(f);
		reset.setBounds(350, 520, 160, 60);
		reset.setFont(f);
		//将对象添加到对象中
		index.add(usertitle);
		index.add(username);
		index.add(name);
		index.add(userpwd);
		index.add(pwd);
		index.add(style);
		index.add(userstyle);
		index.add(submit);
		index.add(reset);
		
		//为重置按钮设置监听事件
		reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//清空用户名框、密码框、用户类型
				name.setText("");
				pwd.setText("");
				style.setText("");
			}
		});
		
		//未提交按钮设置监听事件
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//判断文本框是否为空
				if(name.getText().equals("") || pwd.getText().equals("") || style.getText().equals("")) {
					//为空，弹出提示框
					JOptionPane.showMessageDialog(null,"请输入内容");
				} else {
					//不为空
					//从文本框中提取数据并存放到user对象中
					synchronized (Useradd.class) {
						user.setUsername(name.getText());
						user.setUserpwd(pwd.getText());
						user.setFlag(style.getText());
						//为登陆接口实现类初始化对象
						LoginUseImp l = new LoginUseImp();
						try {
							//执行sql语句
							l.Add(user, "insert into users(username,userpwd,flag) values(?,?,?)");
							//清空文本框
							name.setText("");
							pwd.setText("");
							style.setText("");
							//添加成功提示框
							JOptionPane.showMessageDialog(null, "添加成功");
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
	}
}
