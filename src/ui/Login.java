package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

//import jxl.common.Logger;

import javax.swing.event.InternalFrameAdapter;

import org.apache.log4j.PropertyConfigurator;

import ClassType.DialogThread;
import ClassType.User;
import Controller.LoginController;


public class Login {

	//组件
	public JInternalFrame frame;
	private JTextField nameField;
	private JPasswordField passwordField;
	private JLabel label_1;
	private JLabel label_4;
	private JButton summit;
	private JLabel registe;
	private JLabel forgetps;
	private String hint;
	//类型
	User user = new User();
	LoginController controller;
	MouseHandler handler = new MouseHandler();
	
	public JInternalFrame getFrame_info()
	{
		return frame;
	}

	/**
	 * Create the application.
	 */
	public Login(LoginController loginController) {
		this.controller = loginController;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JInternalFrame("优股", true, true, false, false);
		//JInternalFrame frame = new JInternalFrame( "Internal Frame ", true, true, true, true);  
		//frame.setTitle("优股");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 800, 600);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JLabel label = new JLabel("登录");
		label.setFont(new Font("用户名", Font.BOLD, 24));
		label.setBounds(376, 132, 100, 30);
		frame.getContentPane().add(label);
		
		nameField = new JTextField();
		nameField.setFont(new Font("����", Font.PLAIN, 14));
		nameField.setBounds(324, 194, 191, 30);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		forgetps = new JLabel("\u5FD8\u8BB0\u5BC6\u7801");
		forgetps.setForeground(Color.BLUE);
		forgetps.setBounds(461, 299, 54, 15);
		forgetps.addMouseListener(handler);
		frame.getContentPane().add(forgetps);
		
		
		
		summit = new JButton("\u767B\u5F55");
		summit.setBounds(361, 328, 93, 23);
		summit.addMouseListener(handler);
		frame.getContentPane().add(summit);
		
		registe = new JLabel("\u6CE8\u518C");
		registe.setForeground(Color.BLUE);
		registe.setBounds(486, 362, 54, 15);
		registe.addMouseListener(handler);
		frame.getContentPane().add(registe);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("", Font.PLAIN, 14));
		passwordField.setBounds(324, 247, 191, 30);
		frame.getContentPane().add(passwordField);
		
		label_1 = new JLabel("用户名：");
		label_1.setFont(new Font("宋体", Font.BOLD, 14));
		label_1.setBounds(254, 194, 71, 23);
		frame.getContentPane().add(label_1);
		
		label_4 = new JLabel("密码：");
		label_4.setFont(new Font("宋体", Font.BOLD, 14));
		label_4.setBounds(260, 251, 54, 22);
		frame.getContentPane().add(label_4);
		
		hint = new String("用户名或者密码错误");
	
		
		frame.setClosable(true);// 设置可以关闭
		frame.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		//frame.toFront();
		frame.setVisible(true);
	}
	
	private class MouseHandler extends MouseAdapter
	{
	
			public void mouseClicked(MouseEvent e) 
            { //响应鼠标点击事件);
				if(e.getSource() == summit)
				{
					user.setUserLoginInfo(nameField.getText(), String.valueOf(passwordField.getPassword()));
					
					System.out.println(user.getName()+" " + user.getPassword());
					
					if(!controller.JudgeUserInfo(user))
					{
						new DialogThread(hint).start();
					}
				}
				else if(e.getSource() == registe)
				{
					controller.JumpRegister();
				}
				else if(e.getSource() == forgetps)
				{
					controller.JumpRetive();
				}
            }
	}
}

