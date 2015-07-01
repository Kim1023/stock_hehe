package ui;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPasswordField;

public class ChangeInfo {

	private JFrame frame_info;
	private JLabel lblNewLabel;
	private JLabel label_7;
	private JLabel label_8;
	public JFrame getFrame_info() {
		return frame_info;
	}

	public void setFrame_info(JFrame frame_info) {
		this.frame_info = frame_info;
	}

	private JTextField textField;
	private JTextField textField_3;
	private JRadioButton radioButton;
	private JRadioButton radioButton_1;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangeInfo window = new ChangeInfo();
					window.frame_info.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChangeInfo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame_info = new JFrame();
		frame_info.getContentPane().setBackground(Color.WHITE);
		frame_info.setBounds(100, 100, 900, 600);
		frame_info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame_info.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(410, 124, 164, 31);
		frame_info.getContentPane().add(textField);
		
		JLabel label = new JLabel("*\u7528\u6237\u540D\uFF1A");
		label.setFont(new Font("����", Font.PLAIN, 14));
		label.setBounds(259, 120, 84, 39);
		frame_info.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("*\u91CD\u8BBE\u5BC6\u7801\uFF1A");
		label_1.setFont(new Font("����", Font.PLAIN, 14));
		label_1.setBounds(259, 170, 84, 39);
		frame_info.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("*\u5BC6\u7801\u786E\u8BA4\uFF1A");
		label_2.setFont(new Font("����", Font.PLAIN, 14));
		label_2.setBounds(259, 219, 84, 39);
		frame_info.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("\u4FEE\u6539\u4E2A\u4EBA\u4FE1\u606F");
		label_3.setFont(new Font("����", Font.PLAIN, 24));
		label_3.setBounds(329, 62, 227, 39);
		frame_info.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("\u6027\u522B\uFF1A");
		label_4.setFont(new Font("����", Font.PLAIN, 14));
		label_4.setBounds(259, 283, 84, 39);
		frame_info.getContentPane().add(label_4);
		
		radioButton = new JRadioButton("\u7537");
		radioButton.setBackground(Color.WHITE);
		radioButton.setBounds(410, 287, 78, 31);
		frame_info.getContentPane().add(radioButton);
		
		radioButton_1 = new JRadioButton("\u5973");
		radioButton_1.setBackground(Color.WHITE);
		radioButton_1.setBounds(496, 287, 78, 31);
		frame_info.getContentPane().add(radioButton_1);
		
		ButtonGroup group = new ButtonGroup ();
		group.add(radioButton);
		group.add(radioButton_1);
		
		
		JLabel label_5 = new JLabel("\u5E74\u9F84\uFF1A");
		label_5.setFont(new Font("����", Font.PLAIN, 14));
		label_5.setBounds(259, 327, 84, 39);
		frame_info.getContentPane().add(label_5);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(410, 331, 164, 31);
		frame_info.getContentPane().add(textField_3);
		
		JLabel label_6 = new JLabel("\u5934\u50CF\uFF1A");
		label_6.setFont(new Font("����", Font.PLAIN, 14));
		label_6.setBounds(259, 376, 84, 39);
		frame_info.getContentPane().add(label_6);
		
		JButton btnNewButton = new JButton("\u4FDD\u5B58");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String password1 = new String(passwordField.getPassword());
				String password2 = new String(passwordField_1.getPassword());
				
				if(textField.getText().trim().length() >= 1 
						&&password1.trim().length() >= 1 
						&&password2.trim().length() >= 1){
					
				}
				if(password1.equals(password2)){
					//frame_info.setVisible(false);
					//����Ӧ����ת����ҳ�����������Ĵ���
					//ChangeInfo changeInfo=new ChangeInfo();
					//changeInfo.getFrame_info().setVisible(true);
				}
				if(password1.equals(password2)!=true){
				label_8.setVisible(true);	 
				}
				if(textField.getText().trim().length()<1 ){
					lblNewLabel.setVisible(true);	
				}
				if(password1.trim().length()<1){
					label_7.setVisible(true);
				}
				if (textField.getText().trim().length() >= 1) {
					lblNewLabel.setVisible(false);
				}
				if(password1.trim().length() >= 1){
					label_7.setVisible(false);
				}
			}
		});
		btnNewButton.setBounds(361, 497, 112, 31);
		
		/*btnNewButton.addMouseListener(new  MouseAdapter(){
			public void mouseClicked(MouseEvent e) 
            { //响应鼠标点击事件);
				frame_info.setVisible(false);
				Login login = new Login();
				login.getFrame_info().setVisible(true);
            }
			});*/
		frame_info.getContentPane().add(btnNewButton);
		
		lblNewLabel = new JLabel("\u7528\u6237\u540D\u4E0D\u80FD\u4E3A\u7A7A");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(625, 124, 153, 31);
		frame_info.getContentPane().add(lblNewLabel);
		lblNewLabel.setVisible(false);
		
		label_7 = new JLabel("\u5BC6\u7801\u4E0D\u80FD\u4E3A\u7A7A");
		label_7.setForeground(Color.RED);
		label_7.setBounds(625, 174, 153, 31);
		frame_info.getContentPane().add(label_7);
		label_7.setVisible(false);
		
		label_8 = new JLabel("\u4E24\u6B21\u5BC6\u7801\u4E0D\u540C\uFF0C\u8BF7\u91CD\u65B0\u8F93\u5165");
		label_8.setForeground(Color.RED);
		label_8.setBounds(625, 229, 208, 31);
		frame_info.getContentPane().add(label_8);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(410, 175, 164, 30);
		frame_info.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(410, 228, 164, 30);
		frame_info.getContentPane().add(passwordField_1);
		label_8.setVisible(false);
	}
}
