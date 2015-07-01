package ui;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.JTabbedPane;

import urldemo.StocksPrice;
import ClassType.DialogThread;
import ClassType.PriceThread;
import ClassType.StockInfo;
import Controller.StockJPaneController;

public class StockJPane extends JPanel {

	//组件
	private JLabel ltodayPrice,lyesPrice,lupStop,ldownStop,lhighPrice,ldownPrice,lprice;
	private JLabel  lNameandNo;
	private StockInfo info;
	private JLabel lSale,lHold;
	String hint;
	private JLabel lfocus;
	private StockJPaneController controller;
	private JTabbedPane tKgraph;
	private SaleAndBuy saleDialog;
	private HoldCon holdDialog;
	
	MouseHandler handler = new MouseHandler();
	private int key = 0;
	/**
	 * Create the panel.
	 */
	
	public StockJPane(StockJPaneController con) {
		controller = con;
		setBackground(Color.WHITE);		
		setLayout(null);
		info = controller.getStorkInfo();
		initialize();
	}
	public StockJPane(StockJPaneController con,int l) {
		controller = con;
		setBackground(Color.WHITE);		
		setLayout(null);
		info = controller.getStorkInfo();
		initialize();
		lfocus.setText("关注");
	}
	public void initialize()
	{
		lNameandNo = new JLabel("工商银行(SH：6101398)");
		lNameandNo.setForeground(Color.BLACK);
		lNameandNo.setFont(new Font("宋体", Font.PLAIN, 28));
		lNameandNo.setBounds(20, 10, 294, 63);
		add(lNameandNo);
		
		JLabel label_3 = new JLabel("今开：");
		label_3.setBounds(180, 89, 54, 15);
		add(label_3);
		
		JLabel label_4 = new JLabel("昨收：");
		label_4.setBounds(333, 89, 54, 15);
		add(label_4);
		
		JLabel label_5 = new JLabel("涨停价：");
		label_5.setBounds(28, 152, 54, 15);
		add(label_5);
		
		JLabel label_6 = new JLabel("跌停价：");
		label_6.setBounds(180, 152, 54, 15);
		add(label_6);
		
	
		
		ltodayPrice = new JLabel("--");
		ltodayPrice.setBounds(238, 89, 54, 15);
		add(ltodayPrice);
		
		lyesPrice = new JLabel("--");
		lyesPrice.setBounds(420, 89, 54, 15);
		add(lyesPrice);
		
		lupStop = new JLabel("--");
		lupStop.setBounds(88, 152, 54, 15);
		add(lupStop);
		
		ldownStop = new JLabel("--");
		ldownStop.setBounds(240, 152, 54, 15);
		add(ldownStop);
		
		JLabel label_8 = new JLabel("最高：");
		label_8.setBounds(28, 120, 54, 15);
		add(label_8);
		
		JLabel lblNewLabel_6 = new JLabel("最低：");
		lblNewLabel_6.setBounds(180, 120, 54, 15);
		add(lblNewLabel_6);
	
		lhighPrice = new JLabel("--");
		lhighPrice.setBounds(88, 120, 54, 15);
		add(lhighPrice);
		
		ldownPrice = new JLabel("--");
		ldownPrice.setBounds(238, 120, 54, 15);
		add(ldownPrice);
		
		lSale = new JLabel("买卖/卖空补仓");
		lSale.setForeground(Color.BLUE);
		lSale.setBounds(347, 39, 88, 15);
		lSale.addMouseListener(handler);
		add(lSale);
		
		lHold = new JLabel("持仓");
		lHold.setForeground(Color.BLUE);
		lHold.setBounds(445, 39, 54, 15);
		lHold.addMouseListener(handler);
		add(lHold);
		
		lfocus = new JLabel("取消关注");
		lfocus.addMouseListener(handler);
		
		lfocus.setForeground(Color.BLUE);
		lfocus.setBounds(500, 39, 88, 15);
		add(lfocus);
		
		tKgraph = new JTabbedPane(JTabbedPane.TOP);
		tKgraph.setBounds(30, 211, 520, 299);
		
		add(tKgraph);
		
		JLabel label_1 = new JLabel("现价：");
		label_1.setBounds(28, 88, 54, 15);
		add(label_1);
		
		lprice = new JLabel("--");
		lprice.setBounds(88, 89, 54, 15);
		add(lprice);
		
		hint = new String("持股数不等于0，不能取消关注");
		
		
		setInfo();
	}
	
	public void setInfo()
	{
		String str = info.getStockInfo();
		String st[] = str.split(" ");
		lNameandNo.setText(st[0]+"("+st[1]+")");
		
		
		for(int i = 0;i< StocksPrice.num;i++)
		{

			if(StocksPrice.stocks[i].name.equals(st[0]))
			{
				key = i;
				break;
			}
		}
		lprice.setText(StocksPrice.stocks[key].dqjg);
		ltodayPrice.setText(StocksPrice.stocks[key].kpj);
		lyesPrice.setText(StocksPrice.stocks[key].spj);
		lhighPrice.setText(StocksPrice.stocks[key].zgj);
		ldownPrice.setText(StocksPrice.stocks[key].zdj);
		double d = Double.valueOf(StocksPrice.stocks[key].kpj);
		lupStop.setText(format(String.valueOf(d*1.1)));
		ldownStop.setText(format(String.valueOf(d*0.9)));
		
		String dailyPath = StocksPrice.StockNums[key]+"daily.gif";
		ImageIcon dayicon = new ImageIcon(dailyPath);
		JLabel dayLabel = new JLabel(dayicon);
		
		String weekPath = StocksPrice.StockNums[key]+"weekly.gif";
		ImageIcon weekicon = new ImageIcon(weekPath);
		JLabel weekLabel = new JLabel(weekicon);
		
		String monthPath = StocksPrice.StockNums[key]+"monthly.gif";
		System.out.println(monthPath);
		ImageIcon monthicon = new ImageIcon(monthPath);
		JLabel monthLabel = new JLabel(monthicon);
		
		tKgraph.add(dayLabel,"日k线");
		tKgraph.add(weekLabel,"周k线");
		tKgraph.add(monthLabel,"月k线");
		validate();
		holdDialog = controller.createHoldDialog(key);
		saleDialog = controller.createSaleDialog(key);
	}
	
	public String getName()
	{
		return info.getName();
	}
	
	private class MouseHandler extends MouseAdapter
	{
		public void mouseClicked(MouseEvent e)
		{
				if(e.getSource() == lSale)
				{
					
					saleDialog.setVisible(true);
				}
				else if(e.getSource() == lHold)
				{
					if(lfocus.getText().equals("取消关注"))
					{
						holdDialog.setVisible(true);
					}
					else
					{
						new DialogThread("没有交易记录").start();
					}
				}
				else if(e.getSource() == lfocus)
				{//取消关注要保证没有持股，没有持股则做到删除股票，还有文件中删除股票，界面上删除股票，删除暂存在股票中的交易记录
					if(lfocus.getText().equals("取消关注"))
					{
						if(controller.cancelFocus())
							lfocus.setText("关注");
						else
							new DialogThread(hint).start();
					}
					else if(lfocus.getText().equals("关注"))
					{
						//添加股票，到用户类中，添加股票到stockmodel中，添加股票到那个文档中，界面上进行显示。
						controller.focusStock();
						PriceThread p = new PriceThread(controller.getThirtyDayProfit());
						p.setStockInfo(info.getStockInfo());
						p.start();
						lfocus.setText("取消关注");
					}
				}
		}
	}
	private String format(String st)
	{
		DecimalFormat df=new DecimalFormat(".##");
		double d = Double.valueOf(st);
		return df.format(d);
	}
	public boolean isFocus() {
		// TODO Auto-generated method stub
		if(lfocus.getText().equals("取消关注"))
		{
			return true;
		}
		lfocus.setText("取消关注");
		return false;
	}
}
