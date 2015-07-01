package ui;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Window;

import javax.swing.JRadioButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.JButton;

import org.apache.log4j.PropertyConfigurator;

import ClassType.DialogThread;
import ClassType.UserAccount;
import Controller.MainFrameController;
import jxl.common.Logger;

public class FixCash extends JPanel {
	JRadioButton raddcash,rsubcash;
	String hint;
	private JTextField textField;
	MouseHandler handler = new MouseHandler();
	MainFrameController controller;
	boolean flag;
	/**
	 * Create the panel.
	 */
	public FixCash(MainFrameController controller) {
		this.controller = controller;
		
		setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("金额修改");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 37));
		lblNewLabel.setBounds(98, 70, 235, 54);
		add(lblNewLabel);
		
		raddcash = new JRadioButton("转入");
		raddcash.setBackground(Color.WHITE);
		raddcash.setBounds(101, 155, 121, 23);
		add(raddcash);
		
		rsubcash = new JRadioButton("转出");
		rsubcash.setBackground(Color.WHITE);
		rsubcash.setBounds(253, 155, 121, 23);
		add(rsubcash);
		
		ButtonGroup group = new ButtonGroup ();
		group.add(raddcash);
		group.add(rsubcash);
		raddcash.setSelected(true);
		
		JLabel label = new JLabel("金额：");
		label.setBounds(101, 214, 54, 15);
		add(label);
		
		textField = new JTextField();
		textField.setBounds(148, 211, 208, 21);
		add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("确定");
		button.setBounds(171, 266, 93, 23);
		add(button);
		
		hint = new String("现金不足");
	
		
		button.addMouseListener(handler);
	}
	
	private class MouseHandler extends MouseAdapter
	{
		public void mouseClicked(MouseEvent e)
		{
			System.out.println("here is in fixcash");
			flag = raddcash.isSelected();
			if(!judge(textField.getText()))
			{
				new DialogThread("输入金额必须是数值").start();
				return;
			}
			double cash = Double.valueOf(textField.getText());
			if(cash < 0)
			{
				new DialogThread("输入金额不能小于0").start();
				return;
			}
			if(!flag && controller.getCash() < cash)
			{
				new DialogThread(hint).start();
				return;
			}
			else
			{
				controller.setCash(flag,cash);
			}
				
		}
	}
	private boolean judge(String str)
	{
		try{
			Double.parseDouble(str);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
}
