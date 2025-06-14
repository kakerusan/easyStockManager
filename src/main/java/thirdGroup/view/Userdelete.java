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

public class Userdelete extends IndexAdmin {
	//声明部分对象
	JLabel name;
	JLabel pwd;
	JLabel style;
	JTextField dname;
	//为User类初始化对象
	User user = new User();
	
	//构造函数
	public Userdelete(String name) {
		super(name);
		init();
	}
	
	public void init() {
		//初始化字体
		Font d = new Font("楷体", Font.BOLD, 32);
		Font f = new Font("楷体", Font.BOLD, 24);
		//初始化对象
		JLabel userdelete = new JLabel("用户删除");
		JLabel deletename = new JLabel("输入用户名：");
		dname = new JTextField();
		JLabel usertitle = new JLabel("删除的用户信息");
		JLabel username = new JLabel("用户名：");
		name = new JLabel();
		JLabel userpwd = new JLabel("密  码：");
		pwd = new JLabel();
		JLabel userstyle = new JLabel("类  型：");
		style = new JLabel();
		JButton submit = new JButton("确定");
		JButton delete = new JButton("删除");
		//设置对象
		userdelete.setBounds(200, 40, 200, 40);
		userdelete.setFont(d);
		deletename.setBounds(50, 140, 240, 40);
		deletename.setFont(f);
		dname.setBounds(190, 140, 250, 40);
		dname.setFont(new Font("楷体", Font.BOLD,24));
		usertitle.setBounds(100, 240, 400, 40);
		usertitle.setFont(d);
		username.setBounds(100, 350, 160, 60);
		username.setFont(f);
		name.setBounds(300, 350, 300, 60);
		name.setFont(f);
		userpwd.setBounds(100, 400, 160, 60);
		userpwd.setFont(f);
		pwd.setBounds(300, 400, 300,60 );
		pwd.setFont(f);
		userstyle.setBounds(100, 450, 160, 60);
		userstyle.setFont(f);
		style.setBounds(300, 450, 300, 60);
		style.setFont(f);
		submit.setBounds(440, 140, 130, 40);
		submit.setFont(f);
		delete.setBounds(220, 550, 130, 60);
		delete.setFont(f);
		//添加对象
		index.add(userdelete);
		index.add(deletename);
		index.add(dname);
		index.add(submit);
		index.add(usertitle);
		index.add(username);
		index.add(name);
		index.add(userpwd);
		index.add(pwd);
		index.add(style);
		index.add(userstyle);
		index.add(delete);
		
		//为确定按钮添加监听事件
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//从文本框中取得用户名设置进user对象中
				user.setUsername(dname.getText());
				LoginUseImp l = new LoginUseImp();
				boolean b;
				try {
					//执行sql语句
					b = l.Query1(user, "select * from users where username= '"+dname.getText()+"'");
					if(b){
						//将数据库中的值设置进文本框
						name.setText(user.getUsername());
						pwd.setText(user.getUserpwd());
						style.setText(user.getFlag());
					} else {
						//未查找到提示框
						JOptionPane.showMessageDialog(null,"未查到该用户");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		//为删除按钮添加监听事件
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//将选中的名字添加进文本框
				synchronized (Userdelete.class) {
					user.setUsername(dname.getText());
					LoginUseImp l = new LoginUseImp();
					try {
						//执行删除的sql语句
						l.Delete(user, "delete from users where username='"+dname.getText()+"'");
						JOptionPane.showMessageDialog(null, "删除成功");
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
				}
			}
		});
	}
}