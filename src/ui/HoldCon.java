package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import urldemo.StocksPrice;
import Controller.StockJPaneController;

public class HoldCon extends JDialog {

	private ModifyTable table1;
	private RecordTable table2;
	private final JPanel contentPanel = new JPanel();
	private StockJPaneController controller;
	JPanel panelrecord,panelhold;
	int key;
	/**
	 * Create the dialog.
	 */
	public HoldCon(StockJPaneController controller,int k) 
	{
		key = k;
		this.controller = controller;
		setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		initialize();
	}	
	public void initialize()
	{

		setBounds(100, 100, 525, 584);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		panelhold = new JPanel();
		panelhold.setBackground(Color.WHITE);
		panelhold.setBounds(10, 27, 489, 53);
		String[][] st = new String[1][6];
		st[0] = controller.getStockBase().split(" ");
		table1 = new ModifyTable(st,StocksPrice.stocks[key].dqjg,1);
		panelhold.add(table1);
		contentPanel.add(panelhold);
		
	    panelrecord = new JPanel();
		panelrecord.setBackground(Color.WHITE);
		panelrecord.setBounds(10, 115, 489, 411);
		String[][] st1 = controller.getStockRecord();
		table2 = new RecordTable(st1,st1.length,controller);
		panelrecord.add(table2);
		contentPanel.add(panelrecord);
		
		JLabel label_1 = new JLabel("持股情况");
		label_1.setFont(new Font("宋体", Font.BOLD, 14));
		label_1.setBounds(10, 2, 102, 25);
		contentPanel.add(label_1);
		JLabel label = new JLabel("交易记录");
		label.setBounds(20, 90, 121, 15);
		contentPanel.add(label);
		label.setFont(new Font("宋体", Font.BOLD, 14));
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
	public void refresh() {
		// TODO Auto-generated method stub
		panelhold.remove(table1);
		String[][] st = new String[1][6];
		st[0] = controller.getStockBase().split(" ");
		table1 = new ModifyTable(st,StocksPrice.stocks[key].dqjg,1);
		panelhold.add(table1);
		
		panelrecord.remove(table2);
		String[][] st1 = controller.getStockRecord();
		table2 = new RecordTable(st1,st1.length,controller);
		panelrecord.add(table2);
		contentPanel.add(panelrecord);
		contentPanel.validate();
	}
}
