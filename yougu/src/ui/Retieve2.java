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

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPasswordField;

import ClassType.DialogThread;
import ClassType.User;
import Controller.RetieveController;



public class Retieve2 {
	

	private static JInternalFrame frame2;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField;
	private String hintblank,hintps2;
	
	
	
	RetieveController controller;
	ActionHandler handler = new ActionHandler();
	
	public static JInternalFrame getFrame_Info() {
		return frame2;
	}

	/**
	 * Create the application.
	 */
	public Retieve2(RetieveController retieveController) {
		// TODO Auto-generated constructor stub
		controller = retieveController;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame2 = new JInternalFrame();
		frame2.getContentPane().setBackground(Color.WHITE);
		frame2.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("*\u91CD\u7F6E\u5BC6\u7801\uFF1A");
		label.setFont(new Font("����", Font.PLAIN, 14));
		label.setBounds(310, 155, 84, 39);
		frame2.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("*\u91CD\u8F93\u5BC6\u7801\uFF1A");
		label_1.setFont(new Font("����", Font.PLAIN, 14));
		label_1.setBounds(310, 244, 84, 39);
		frame2.getContentPane().add(label_1);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(421, 248, 164, 31);
		frame2.getContentPane().add(passwordField_1);
	    
		
	    passwordField = new JPasswordField();
		passwordField.setBounds(421, 164, 164, 31);
		frame2.getContentPane().add(passwordField);
	   
	    
	   hintblank = new String("密码不能为空");
	   hintps2 = new String("两次输入密码不相同");
		
		JButton button = new JButton("\u5B8C\u6210");
		button.setBounds(376, 354, 112, 31);
		/*button.addMouseListener(new  MouseAdapter(){
			public void mouseClicked(MouseEvent e) 
            { //响应鼠标点击事件);
				frame2.setVisible(false);
				Login login = new Login();
				login.getFrame_info().setVisible(true);
            }
		});*/
		frame2.getContentPane().add(button);
		
		
		button.addActionListener(handler);
		
		frame2.setBounds(100, 100, 900, 600);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	private class ActionHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub	
			String password1 = new String(passwordField.getPassword());
			String password2 = new String(passwordField_1.getPassword());
			
			if(password1.trim().length() < 1){
				new DialogThread(hintblank).start();
				return;
			}
			if(password1.trim().length() >= 1 
					&&password2.trim().length() >= 1 ){
				if(password1.equals(password2)!=true){
					new DialogThread(hintps2).start();
					return;
					//flag=1;	 
					}
				if(password1.equals(password2)){
					controller.changePs(password1);
					System.out.print(password1+"retieve");
					controller.jumpLogin(frame2);
				}
				
			}
		
			
				
		}
	}
}


	

