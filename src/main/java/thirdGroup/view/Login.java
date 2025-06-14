package thirdGroup.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import thirdGroup.bean.User;
import thirdGroup.dao.LoginUseImp;
/**
 * 		登陆界面
 *	1、创建登陆界面，初始化上面的对象
 *	2、美化登陆界面，设置各个对象的大小、位置、字体
 *	3、给按钮设置监听事件
 *
 */

public class Login {
	    //初始化字体
		Font d = new Font("楷体", Font.BOLD, 44);
		Font f = new Font("楷体", Font.BOLD, 30);
		// 初始化对象
		JFrame logingui = new JFrame("用户登录界面");
		JLabel userlogin = new JLabel("仓库管理系统用户登录");
		JLabel username = new JLabel("用户名：");
		JLabel password = new JLabel("密 码：");
		JLabel usertyle = new JLabel("用户类型");
		JTextField name = new JTextField();
		JTextField pwd = new JPasswordField();
		JComboBox box = new JComboBox(new String[]{"管理员","普通用户"} );
		JButton login = new JButton("登录");
		//给User类初始化对象user
		User user = new User();
	public void LoginGui() {
		// 设置对象
/*
		logingui.setBounds(450, 200, 550, 350);
		//设置退出
		logingui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//取消框架格式
		logingui.setLayout(null);

		*/
/*//*
/设置登录图形界面的背景图片
		((JComponent) logingui.getContentPane()).setOpaque(false); //将框架强转为容器
		ImageIcon img = new ImageIcon("Images//登录背景.png"); //传入背景图片路径
		JLabel background = new JLabel(img);//将图片放进标签里
		logingui.getLayeredPane().add(background, (Integer.MIN_VALUE));//将标签放进容器里
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());//设置标签的大小*//*


		//设置位置、大小和字体
		userlogin.setBounds(160, 30, 250, 30);
		userlogin.setFont(d);
		username.setBounds(110, 80, 100, 30);
		username.setFont(f);
		password.setBounds(110, 120, 100, 30);
		password.setFont(f);
		usertyle.setBounds(110, 160, 100, 30);
		usertyle.setFont(f);
		name.setBounds(200, 80, 180, 30);
		name.setFont(f);
		pwd.setBounds(200, 120, 180, 30);
		box.setBounds(200, 160, 100, 30);
		box.setFont(f);
		login.setBounds(200, 200, 80, 30);
		login.setFont(f);
*/





		logingui.setBounds(900, 400, 1100, 700);
		//设置退出
		logingui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//取消框架格式
		logingui.setLayout(null);


		//设置位置、大小和字体
		userlogin.setBounds(320, 60, 500, 60);
		userlogin.setFont(d);
		username.setBounds(220, 160, 200, 60);
		username.setFont(f);
		password.setBounds(220, 240, 200, 60);
		password.setFont(f);
		usertyle.setBounds(220, 320, 200, 60);
		usertyle.setFont(f);
		name.setBounds(400, 160, 320, 60);
		name.setFont(f);
		pwd.setBounds(400, 240, 360, 60);
		box.setBounds(400, 320, 200, 60);
		box.setFont(f);
		login.setBounds(400, 400, 160, 60);
		login.setFont(f);









		// 添加对象
		logingui.add(userlogin);
		logingui.add(username);
		logingui.add(password);
		logingui.add(usertyle);
		logingui.add(name);
		logingui.add(pwd);
		logingui.add(box);
		logingui.add(login);
		// 窗体可视化
		logingui.setLocationRelativeTo(null);
		logingui.setVisible(true);

		//给下拉框设置选择监听事件
		box.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//判断选择选项是否和下拉框数据一致
				if(box.getSelectedItem().equals("管理员")){
					//设置标志量的值
					user.setFlag("2");
				}else{
					user.setFlag("1");
				}
			}
		});
		//给登录按钮设置监听事件
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					//提取文本框里的用户名和密码
					String name_text = name.getText();
					String pwd_text = pwd.getText();
					//将得到的值存入user对象里面
					user.setUsername(name_text);
					user.setUserpwd(pwd_text);
					//给登陆接口实现类初始化对象
					LoginUseImp l = new LoginUseImp();
					//获取标志量
					String state = user.getFlag();
					//判断标志量，设置文本框的默认值为管理员
					if(state != "1" && state != "2") {
						state = "2";
					}
					//判断文本框值是不是管理员
					if(state == "2") {
						try {
							//执行sql语句，进行数据库添加
							boolean flag = l.Query(user, "select * from users where username=? and userpwd=? and flag="+state);
							if(flag) {
								//文本提示框
								JOptionPane.showMessageDialog(null, "登陆成功");
								//界面转换，隐藏原来界面
								logingui.setVisible(false);
								//构造新的界面
								new IndexAdmin(name_text);
							} else {
								//文本提示框
								JOptionPane.showMessageDialog(null, "登陆失败，请检查用户名和密码");
								//设置用户名框和密码框的值为空
								name.setText("");
								pwd.setText("");
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
				//判断是不是普通用户
				} else if(state == "1") {
					try {
						//执行sql语句
						boolean flag = l.Query(user, "select * from users where username=? and userpwd=? and flag="+state);
						if(flag) {
							JOptionPane.showMessageDialog(null, "登陆成功");
							logingui.setVisible(false);	
							new Index(name_text);
						} else {
							JOptionPane.showMessageDialog(null, "登陆失败，请检查用户名和密码");
							name.setText("");
							pwd.setText("");
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}
			}
		});
	}
	//整个程序执行的入口
	public static void main(String[] args) {
		Login l = new Login();
		l.LoginGui();
	}
}
