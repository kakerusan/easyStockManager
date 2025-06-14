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

public class Userupdate extends Index{
	
	JLabel uname;
	JTextField pwd;
	JTextField pass;
	User user = new User();
	
	public Userupdate(String name) {
		super(name);
		init(name);
	}
	
	public void init(String name) {
		user.setUsername(name);
		Font d = new Font("楷体", Font.BOLD, 32);
		Font f = new Font("楷体", Font.BOLD, 24);
		JLabel usertitle = new JLabel("修改密码");
		JLabel username = new JLabel("用  户  名：");
		uname = new JLabel();
		JLabel userpwd = new JLabel("新  密  码：");
		pwd = new JTextField();
		JLabel userpass = new JLabel("再次输入密码：");
		pass = new JTextField();
		JButton submit = new JButton("修改");
		JButton reset = new JButton("重置");
		usertitle.setBounds(200,  40,200, 40);
		usertitle.setFont(d);
		username.setBounds(100, 100, 200, 40);
		username.setFont(f);
		uname.setBounds(300, 100, 300, 40);
		uname.setFont(f);
		uname.setText(name);
		userpwd.setBounds(100, 150, 200, 60);
		userpwd.setFont(f);
		pwd.setBounds(300, 150, 200, 30);
		pwd.setFont(f);
		userpass.setBounds(100, 200, 200, 60);//再次输入密码
		userpass.setFont(f);
		pass.setBounds(300, 200, 200, 30);
		pass.setFont(f);
		submit.setBounds(100, 300, 100, 40);
		submit.setFont(f);
		reset.setBounds(300, 300, 100, 40);
		reset.setFont(f);
		index.add(usertitle);
		index.add(username);
		index.add(uname);
		index.add(userpwd);
		index.add(pwd);
		index.add(pass);
		index.add(userpass);
		index.add(submit);
		index.add(reset);
		reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				pwd.setText("");
				pass.setText("");
			}
		});
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				LoginUseImp l = new LoginUseImp();
				try {
					l.Delete(user, "update users set userpwd='"+pass.getText()+"' where username='"+user.getUsername()+"'");
					JOptionPane.showMessageDialog(null, "修改成功");
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});
	}

}
