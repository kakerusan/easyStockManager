package thirdGroup.view;

import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JLabel;

import thirdGroup.bean.User;
import thirdGroup.dao.LoginUseImp;

public class Usernews extends Index{
	//声明对象
	JLabel uname;
	JLabel pwd;
	JLabel style;
	//为User类初始化user对象
	User user = new User();
	
	//构造函数
	public Usernews(String name) {
		super(name);
		init();
		//将用户名设置进user对象
		user.setUsername(name);
		LoginUseImp l = new LoginUseImp();
		try {
			//执行按姓名查找的语句
			l.Query1(user, "select * from users where username= '"+name+"'");
			uname.setText(user.getUsername());
			pwd.setText(user.getUserpwd());
			//通过标志量判断普通用户和管理员
			if(user.getFlag().equals("1")) {
				style.setText("普通用户");
			} else {
				style.setText("管理员");
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public void init() {
		//初始化字体
		Font d = new Font("楷体", Font.BOLD, 36);
		Font f = new Font("楷体", Font.BOLD, 28);
		//初始化对象
		JLabel usertitle = new JLabel("个人信息");
		JLabel username = new JLabel("用户名：");
		uname = new JLabel();
		JLabel userpwd = new JLabel("密  码：");
		pwd = new JLabel();
		JLabel userstyle = new JLabel("类  型：");
		style = new JLabel();
		//设置对象大小、位置和字体
		usertitle.setBounds(200, 50, 400, 80);
		usertitle.setFont(d);
		username.setBounds(70, 160, 150, 60);
		username.setFont(f);
		uname.setBounds(230, 160, 200, 60);
		uname.setFont(f);
		userpwd.setBounds(70, 260, 150, 60);
		userpwd.setFont(f);
		pwd.setBounds(230, 260, 200, 60);
		pwd.setFont(f);
		userstyle.setBounds(70, 360, 150, 60);
		userstyle.setFont(f);
		style.setBounds(230, 360, 200, 60);
		style.setFont(f);
		//将对象添加进框架
		index.add(usertitle);
		index.add(username);
		index.add(uname);
		index.add(userpwd);
		index.add(pwd);
		index.add(style);
		index.add(userstyle);
	}
}
