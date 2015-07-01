package ui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;



import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;

import java.awt.Font;
import java.awt.event.MouseEvent;

import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextField;
import javax.swing.JButton;

import urldemo.DataAccess;
import urldemo.DataAccessThread;
import urldemo.StocksPrice;
import ClassType.DialogThread;
import ClassType.PriceThread;
import ClassType.UserAccount;

import javax.swing.JFileChooser;

import Controller.MainFrameController;

import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainFrame {

	//组件
	public JInternalFrame frame;
	private ImageIcon icon;
	private JTextField searchField;
	private JTabbedPane tabbedPane;
	private JLabel lProLosS,lProLosPS,laccount_sum;
	private JLabel lcash,lmarkcap,lpricipal;
	private JLabel lUserNameS;
	private JLabel lhold_can,lLogout,fixc,stockbase,lImportD,lprofit;
	private JButton button;
	private ModifyTable table;
	
	
	//变量
	UserAccount user;
	MainFrameController controller;
	MouseHandler handler = new MouseHandler();
	Map<String,Component> tabItem = new HashMap<String,Component>();

	
	public JInternalFrame getFrame_info(){
		return frame;
	}
	
	/**
	 * Create the application.
	 
	*/
	public MainFrame(MainFrameController mainFrameController) {
		// TODO Auto-generated constructor stub
		user = mainFrameController.getUser();
		controller = mainFrameController;
		icon = user.getIcon();
		initialize();
		
		addThread();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void addThread()
	{
		String[] allStockInfo = controller.getStockModel().getAllStockInfo();
		for(int i = 0; i < allStockInfo.length;i++)
		{
			PriceThread p = new PriceThread(controller.getThirtyDayProfit());
			p.setStockInfo(allStockInfo[i]);
			p.start();
		}
	}
	
	private void initialize() {
		
		frame = new JInternalFrame("优股", true, true, false, false);
		//frame = new JInternalFrame();
		//frame.setTitle("优股");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		frame.toFront();
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 255, 551);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
	
		JLabel imageIcon = new JLabel(icon);
		imageIcon.setBounds(10, 35, 97, 92);
		panel.add(imageIcon);
		
		JLabel luserN = new JLabel("用户名：");
		luserN.setFont(new Font("宋体", Font.PLAIN, 13));
		luserN.setBounds(117, 87, 54, 15);
		panel.add(luserN);
		
		lLogout = new JLabel("注销");
		lLogout.setForeground(Color.BLUE);
		lLogout.setFont(new Font("宋体", Font.PLAIN, 13));
		lLogout.setBounds(193, 112, 31, 15);
		lLogout.addMouseListener(handler);
		panel.add(lLogout);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(0, 0, 255, 25);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237\u4FE1\u606F");
		label.setBounds(84, 10, 87, 15);
		panel_1.add(label);
		label.setFont(new Font("宋体", Font.BOLD, 16));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setForeground(Color.BLACK);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(0, 131, 255, 25);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel label_3 = new JLabel("\u8D26\u6237\u4FE1\u606F");
		label_3.setFont(new Font("宋体", Font.BOLD, 14));
		label_3.setBounds(88, 10, 76, 15);
		panel_2.add(label_3);
		
		JLabel lmarket = new JLabel("\u5E02\u573A\uFF1A");
		lmarket.setFont(new Font("宋体", Font.BOLD, 12));
		lmarket.setBounds(10, 170, 54, 15);
		panel.add(lmarket);
		
		JLabel lAstock = new JLabel("A股");
		lAstock.setFont(new Font("宋体", Font.BOLD, 12));
		lAstock.setBounds(92, 170, 54, 15);
		panel.add(lAstock);
		
		lhold_can = new JLabel("\u6301\u4ED3\u60C5\u51B5");
		lhold_can.setForeground(Color.BLUE);
		lhold_can.setBounds(175, 170, 54, 15);
		lhold_can.addMouseListener(handler);
		panel.add(lhold_can);
		
		JLabel lProLos = new JLabel("盈亏值：");
		lProLos.setBounds(10, 217, 54, 15);
		panel.add(lProLos);
		
		lProLosS = new JLabel();
		lProLosS.setForeground(Color.BLACK);
		lProLosS.setBounds(92, 217, 72, 15);
		panel.add(lProLosS);
		
		lProLosPS = new JLabel();
		lProLosPS.setForeground(Color.BLACK);
		lProLosPS.setBounds(178, 217, 77, 15);
		panel.add(lProLosPS);
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel label_8 = new JLabel("\u8D26\u6237\u603B\u8D44\u4EA7\uFF1A");
		label_8.setBounds(10, 254, 79, 15);
		panel.add(label_8);
		
		laccount_sum = new JLabel();
		laccount_sum.setBounds(92, 254, 97, 15);
		panel.add(laccount_sum);
		
		JLabel label_9 = new JLabel("\u73B0\u91D1\uFF1A");
		label_9.setBounds(10, 293, 54, 15);
		panel.add(label_9);
		
		lcash = new JLabel();
		lcash.setBounds(92, 292, 112, 15);
		panel.add(lcash);
		
		JLabel label_10 = new JLabel("\u5E02\u503C\uFF1A");
		label_10.setBounds(10, 332, 54, 15);
		panel.add(label_10);
		
		lmarkcap = new JLabel();
		lmarkcap.setBounds(94, 332, 97, 15);
		panel.add(lmarkcap);
		
		JLabel label_11 = new JLabel("\u672C\u91D1\uFF1A");
		label_11.setBounds(10, 374, 54, 15);
		panel.add(label_11);
		
		lpricipal = new JLabel();
		lpricipal.setBounds(92, 374, 85, 15);
		panel.add(lpricipal);
		//设置显示值
		
		//标签,修改股票本金
		fixc = new JLabel("\u4FEE\u6539");
		fixc.setForeground(Color.BLUE);
		fixc.setBounds(175, 374, 54, 15);
		fixc.addMouseListener(handler);
		panel.add(fixc);
		//标签，持股构成标签
		/*stockbase = new JLabel("\u80A1\u7968\u6536\u76CA\u7387/\u6301\u80A1\u6784\u6210");
		stockbase.setForeground(Color.BLUE);
		stockbase.setBounds(10, 432, 154, 15);
		stockbase.addMouseListener(handler);
		panel.add(stockbase);
		*/
		//搜索文本框
		searchField = new JTextField();
		searchField.setText("输入股票编码");
		searchField.setBounds(10, 502, 168, 21);
		panel.add(searchField);
		searchField.setColumns(10);
		//搜索按钮
		button = new JButton("\u641C\u7D22");
		button.setBounds(183, 501, 62, 23);
		button.addMouseListener(handler);
		panel.add(button);
		
		lUserNameS = new JLabel();
		lUserNameS.setBounds(170, 87, 85, 15);
		panel.add(lUserNameS);
		//导入股票
		lImportD = new JLabel("导入股票数据");
		lImportD.setForeground(Color.BLUE);
		lImportD.setBounds(10, 463, 102, 15);
		lImportD.addMouseListener(handler);
		panel.add(lImportD);
		
		stockbase = new JLabel("持股构成");
		stockbase.setForeground(Color.BLUE);
		stockbase.setBounds(10, 427, 54, 15);
		stockbase.addMouseListener(handler);
		panel.add(stockbase);
		
		lprofit = new JLabel("收益率");
		lprofit.setForeground(Color.BLUE);
		lprofit.setBounds(117, 427, 54, 15);
		lprofit.addMouseListener(handler);
		panel.add(lprofit);
		
		
		
	    
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(254, 0, 626, 552);
		table = new ModifyTable(controller);
		table.setBackground(Color.WHITE);
		tabbedPane.add(table,"持仓情况");
		tabItem.put("持仓情况", table);
		frame.getContentPane().add(tabbedPane);
		
		frame.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		tabbedPane.addMouseListener(handler);
		 
		setInfo();
	}
	
	private class MouseHandler extends MouseAdapter
	{
		public void mouseClicked(MouseEvent e) 
        { 
			if(e.getSource() == lLogout)
			{
				controller.jumpLogin();
			}
			else if(e.getSource() == lhold_can)
			{
				tabbedPane.setSelectedIndex(0);
			}
			else if(e.getSource() == fixc)
			{
				if(!tabbedPane.isAncestorOf(tabItem.get("金额修改")))
				{
					tabItem.put("金额修改", controller.jumpfixc());
					tabbedPane.add("金额修改", tabItem.get("金额修改"));
					tabbedPane.setSelectedComponent(tabItem.get("金额修改"));
				}
				else
					tabbedPane.setSelectedComponent(tabItem.get("金额修改"));
			}
			else if(e.getSource() == stockbase)
			{
				 if(!tabbedPane.isAncestorOf(tabItem.get("持股构成")))
				 {
					 Component con = null ;
					  try {
						con = controller.jumpStockBase();
					  } catch (IOException e1) {
						// TODO Auto-generated catch block
						new DialogThread("持股构成计算出错").start();
						e1.printStackTrace();
						return;
					  }
					  tabItem.put("持股构成", con);
					  tabbedPane.add("持股构成", tabItem.get("持股构成"));
					  tabbedPane.setSelectedComponent(tabItem.get("持股构成"));
				 }
				 else
					 tabbedPane.setSelectedComponent(tabItem.get("持股构成"));
			}
			else if(e.getSource() == lprofit)
			{
				 if(!tabbedPane.isAncestorOf(tabItem.get("收益率")))
				 {
					 Component con = null ;
					  try {
						con = controller.jumpProfit();
					  } catch (IOException e1) {
						// TODO Auto-generated catch block
						new DialogThread("收益率计算出错").start();
						e1.printStackTrace();
						return;
					  }
					  tabItem.put("收益率", con);
					  tabbedPane.add("收益率", tabItem.get("收益率"));
					  tabbedPane.setSelectedComponent(tabItem.get("收益率"));
				 }
				 else
					 tabbedPane.setSelectedComponent(tabItem.get("收益率"));
			}
			else if(e.getSource() == lImportD)
			{
				JFileChooser chooser = new JFileChooser();// 文件选择对话框
				chooser.setAcceptAllFileFilterUsed(false);// 取消所有文件过滤项
				chooser.setFileFilter(new FileNameExtensionFilter("Excel文件", "xls"));// 设置只过滤扩展名为.xls的Excel文件

				int returnVal = chooser.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION){
					controller.createImportData(chooser.getSelectedFile().getPath());
				}
			}
			else if(e.getSource() == button)
			{
				String str = searchField.getText();
				if(str.isEmpty())
				{
					new DialogThread("输入不能为空").start();
					return;
				}
				//如果已经在stock线程池中，则不加入，同时观察是否在tabbedpaned上，如果不在则加入
				StocksPrice.AddStock(str);
				int k = 0;
				for(int i = 0; i < StocksPrice.stocks.length;i++)
				{
					if(StocksPrice.StockNums[i].equals(str))
					{
						k = i;
						break;
					}
				}
			
				if(controller.checkStock(StocksPrice.StockNums[k]))
				{//stock在线程池中并咋股票中，需要关心tabbedpane中是否有该股票。有该股票则选中，jpanexist已完成
					//没有股票则创建
					if(!controller.jPaneExist(StocksPrice.stocks[k].name))
					{
						controller.createStockPanel(StocksPrice.stocks[k].name);
					}
				}//剩下的情况都是股票在stockprice中，但是不在用户关注股票中，需要先创建一个股票
				else
				{	
					StocksPrice.singleNum = str;
					StocksPrice.AddStock(str);
					DataAccessThread a =new DataAccessThread();
					if(a.run1())
					{
						controller.createUnfocusStockPanel(StocksPrice.singlestock.name,k);
					}
					else
					{
						new DialogThread("股票不存在").start();
					}
				}
			}
			else 
			{
				for (int i = 0; i < tabbedPane.getTabCount(); i++) {
			    	
		    		Rectangle rect = tabbedPane.getBoundsAt(i); //拿到标签的边界
		    		if (rect.contains(e.getX(), e.getY()) && e.getClickCount() == 2) { //判断是否点在边界内
		    			if(i != 0)
		    	    	{
		    				String title = tabbedPane.getTitleAt(i);
		    				tabbedPane.remove(i);
		    				tabItem.remove(title);
		    	    	}
		    		}
				}
			}
        }
	}

	public void changeInfo() {
		// TODO Auto-generated method stub
		System.out.println("ui:"+user.getCash());
		setInfo();
	}
	
	private void setInfo()
	{
		lUserNameS.setText(user.getName());
		String[] str = user.toInfoString().split(" ");
		lProLosS.setText(str[0]);
		lProLosPS.setText(str[1]+"%");
		laccount_sum.setText(str[2]);
		lcash.setText(str[3]);
		lmarkcap.setText(str[4]);
		lpricipal.setText(str[5]);
	}

	public void addStock(String stemp) {
		// TODO Auto-generated method stub
		user.addStock(stemp);
	}

	public void addTableRow(String[] str)
	{
		//table中每只股票信息
		if(str.length > 0)
		{
			
			String[][] st = new String[str.length][7];
			for(int i = 0;i < user.getStockNum();i++)
			{
				st[i] = str[i].split(" ");
			}
			
			String[] pc = new String[str.length];
			for(int i = 0; i < str.length;i++)
			{	
				
				for(int j = 0; j < StocksPrice.num;j++)
				{
					if(st[i][0].equals(StocksPrice.stocks[j].name))
					{
						pc[i] = StocksPrice.stocks[j].dqjg;
					}
				}
			}
			
			
			tabbedPane.remove(table);
			table = new ModifyTable(st,pc,str.length,controller);
			tabbedPane.add(table, "持仓情况", 0);
			frame.validate();
			
			
		}
	}

	public void addTabbedPane(StockJPane jPane) {
		// TODO Auto-generated method stub
		tabItem.put(jPane.getName(), jPane);
		tabbedPane.add(jPane.getName(), jPane);
		tabbedPane.setSelectedComponent(tabItem.get(jPane.getName()));
	}

	public boolean jPaneExist(String sname) {
		// TODO Auto-generated method stub
		if(tabItem.containsKey(sname))
		{
			tabbedPane.setSelectedComponent(tabItem.get(sname));
			return true;
		}
		return false;
	}
	
	public void refreshStockPre() {
		// TODO Auto-generated method stub
		if(tabItem.containsKey("持股构成"))
		{
			int i = tabbedPane.indexOfComponent(tabItem.get("持股构成"));
			tabbedPane.remove(i);
			tabItem.remove("持股构成");
			
			Component con = null ;
			  try {
				con = controller.jumpStockBase();
			  } catch (IOException e1) {
				// TODO Auto-generated catch block
				new DialogThread("持股构成计算出错").start();
				e1.printStackTrace();
				return;
			  }
			tabItem.put("持股构成", con);
			tabbedPane.add(tabItem.get("持股构成"),"持股构成", i);
			tabbedPane.repaint();
			tabbedPane.validate();
			frame.validate();
		}
		if(tabItem.containsKey("收益率"))
		{
			int i = tabbedPane.indexOfComponent(tabItem.get("收益率"));
			tabbedPane.remove(i);
			tabItem.remove("收益率");
			
			Component con = null ;
			  try {
				con = controller.jumpProfit();
			  } catch (IOException e1) {
				// TODO Auto-generated catch block
				new DialogThread("收益率成计算出错").start();
				e1.printStackTrace();
				return;
			  }
			tabItem.put("收益率", con);
			tabbedPane.add(tabItem.get("收益率"),"收益率", i);
			tabbedPane.repaint();
			tabbedPane.validate();
			frame.validate();
		}
	}
	

	
}



