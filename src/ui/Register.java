package ui;


import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPasswordField;

import ClassType.DialogThread;
import ClassType.User;
import Controller.RegisterController;

import javax.swing.SwingConstants;

public class Register{

	//组件
	public static JInternalFrame frame;
	private String hintPS2;
	private JTextField tfUserName;
	private JTextField tfEmail;
	private JPasswordField Password1;
	private JPasswordField Password2;
	private String hintName;
	private String hintEmail;
	private String hintPS,hintNameError,hintEmailError;
	
	//变量
	RegisterController controller;
	User user = new User();
	ActionHandler handler = new ActionHandler();
	private String hintExist;
	
	public static JInternalFrame getFrame_info()
	{
		return frame;
	}

	/**
	 * Create the application.
	 * @param registerController 
	 */
	public Register(RegisterController registerController) {
		controller = registerController;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JInternalFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setForeground(Color.RED);
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel luserName = new JLabel("*用户名：");
		luserName.setFont(new Font("����", Font.PLAIN, 14));
		luserName.setBounds(206, 169, 84, 39);
		frame.getContentPane().add(luserName);
		
		tfUserName = new JTextField();
		tfUserName.setBounds(316, 173, 164, 31);
		frame.getContentPane().add(tfUserName);
		tfUserName.setColumns(10);
		
		
		JLabel lps = new JLabel("*密码：");
		lps.setFont(new Font("����", Font.PLAIN, 14));
		lps.setBounds(206, 218, 84, 31);
		frame.getContentPane().add(lps);
		
		JLabel lpsagin = new JLabel("*确认密码：");
		lpsagin.setFont(new Font("����", Font.PLAIN, 14));
		lpsagin.setBounds(206, 268, 84, 31);
		frame.getContentPane().add(lpsagin);
		
		JLabel lemial = new JLabel("*\u90AE\u7BB1\uFF1A");
		lemial.setFont(new Font("����", Font.PLAIN, 14));
		lemial.setBounds(206, 318, 84, 31);
		frame.getContentPane().add(lemial);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(316, 318, 164, 31);
		frame.getContentPane().add(tfEmail);
		
		Password1 = new JPasswordField();
		Password1.setBounds(316, 218, 164, 31);
		frame.getContentPane().add(Password1);
		
		Password2 = new JPasswordField();
		Password2.setBounds(316, 268, 164, 31);
		frame.getContentPane().add(Password2);
		
		hintPS2 = new String("两次密码输入不相同");
		
		
		hintName = new String("用户名不能为空");
		hintNameError = new String("用户名格式错误");
		hintEmail = new String("邮箱不能为空");
		hintPS = new String("密码为空");
		hintExist = new String("用户名已存在");
		hintEmailError = new String("邮箱格式不正确");
		
		
		JButton ret = new JButton("返回");
		ret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.jumpLogin();
			}
		});
		ret.setBounds(409, 388, 96, 31);
		frame.getContentPane().add(ret);
		
		
		JButton summit = new JButton("提交");
		summit.setBounds(267, 388, 103, 31);
		frame.getContentPane().add(summit);
		summit.addActionListener(handler);	
	}
	
	private class ActionHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String password1 = new String(Password1.getPassword());
			String password2 = new String(Password2.getPassword());
			user.setName(tfUserName.getText().trim());
			
			if(tfUserName.getText().trim().length()<1 ){
				new DialogThread(hintName).start();
				return;
			}
			if(tfUserName.getText().indexOf(" ") > 0)
			{
				new DialogThread(hintNameError).start();
				return;
			}
			
			
			//用户名没有祖册过
			if(!controller.SearchName(user))
			{
				if(password1.trim().length()<1){
					new DialogThread(hintPS).start();
					return;
				}
				if (tfEmail.getText().trim().length()<1 ) {
					new DialogThread(hintEmail).start();
					return;
				}
				if(tfEmail.getText().indexOf("@") < 0)
				 {
					new DialogThread(hintEmailError).start();
					return;
				}
				if(tfUserName.getText().trim().length() >= 1 
						&& tfEmail.getText().trim().length() >= 1
						&& password1 .trim().length() >= 1
						&& password2 .trim().length() >= 1){
					if(password1.equals(password2)!=true){
						new DialogThread(hintPS2).start();
						return;
						}
					if(password1.equals(password2)){
						user.setUserInfo(tfUserName.getText().trim(), password1.trim(), tfEmail.getText().trim());
						controller.addUser(user);
					}
					
				}
			
			}
			else
				new DialogThread("用户名已存在").start();
		
		}
	}
}