package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JButton;

import ClassType.DialogThread;
import ClassType.User;
import Controller.RetieveController;


public class Retieve {

	public static JInternalFrame frame;
	private JTextField tfUserName;
	private JTextField tfUserEmail;
	private String hintName;
	private String hintEmail;
	private String hintWrong;
	private JButton ret,button;
	
	RetieveController controller;
	MouseHandler handler = new MouseHandler();
	User user = new User();
	
	public static JInternalFrame getFrame_info()
	{
		return frame;
	}
	
	/**
	 * Create the application.
	 * @param retieveController 
	 */
	public Retieve(RetieveController retieveController) {
		controller = retieveController;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JInternalFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("*\u7528\u6237\u540D\uFF1A");
		label.setFont(new Font("����", Font.PLAIN, 14));
		label.setBounds(291, 122, 84, 39);
		frame.getContentPane().add(label);
		
		tfUserName = new JTextField();
		tfUserName.setColumns(10);
		tfUserName.setBounds(402, 126, 164, 31);
		frame.getContentPane().add(tfUserName);
		
		JLabel label_1 = new JLabel("*\u90AE\u7BB1\uFF1A");
		label_1.setFont(new Font("����", Font.PLAIN, 14));
		label_1.setBounds(291, 193, 84, 39);
		frame.getContentPane().add(label_1);
		
		tfUserEmail = new JTextField();
		tfUserEmail.setColumns(10);
		tfUserEmail.setBounds(402, 197, 164, 31);
		frame.getContentPane().add(tfUserEmail);
		

		button = new JButton("\u4E0B\u4E00\u6B65");
		button.setBounds(372, 354, 101, 39);
		frame.getContentPane().add(button);
		
		hintName = new String("用户名不能为空");
		
		
		hintEmail = new String("邮箱不能为空");
		
		hintWrong = new String("用户名或邮箱错误");

		
		button.addMouseListener(handler);
		
		ret = new JButton("返回");
		ret.setBounds(500, 354, 101, 39);
		frame.getContentPane().add(ret);
		ret.addMouseListener(handler);
		
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private class MouseHandler extends MouseAdapter
	{
			public void mouseClicked(MouseEvent e) 
            { //响应鼠标点击事件);
				if(e.getSource() == button)
				{
					if(tfUserName.getText().trim().length() >= 1
						&&tfUserEmail.getText().trim().length() >= 1 ){
						user.setUserRetieveInfo(tfUserName.getText().toString(),tfUserEmail.getText().toString());
						if(!controller.JudgeEmail(user))
						{
							new DialogThread(hintWrong).start();
							return;
						}
					}
					if(tfUserName.getText().trim().length() < 1){
						new DialogThread(hintName).start();
						return;
						
					}
					if(tfUserEmail.getText().trim().length() < 1){
						new DialogThread(hintEmail).start();
						return;
					}
				}
				else
				{
					controller.jumpLogin(frame);
				}
				
			}
    }
}
