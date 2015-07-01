package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import urldemo.StocksPrice;
import ClassType.DialogThread;
import Controller.StockJPaneController;

public class SaleAndBuy extends JDialog {

	//组件
	private JTextField tfPriceNS,tfSaleNum,tfTraceMon;
	private JRadioButton rbMargin,rbSaleOut,rbBuy,rbSale;
	private ButtonGroup btg,bthc;
	private final JPanel contentPanel = new JPanel();
	JRadioButton rhistory,rcurrent;

	//变量
	StockJPaneController controller;
	/**
	 * Launch the application.
	 */
	private JButton okButton,cancelButton;
	private JPanel pTable;
	private ModifyTable table;
	
	private MouseHandler handler =new  MouseHandler();
	private MouseHandler2 handler2 = new MouseHandler2();
	private String hintBuCang,hintBuy,hintSale,hintSaleOut;
	private int key;
	private String[][] st;
	private JTextField tftime;
	private String hint2;
	
    private static SimpleDateFormat dateFormat = null;
    static 
    {
    // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        dateFormat = new SimpleDateFormat("yy-MM-dd");
        // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
        dateFormat.setLenient(false);
    }

	/**
	 * Create the dialog.
	 */
	public SaleAndBuy(StockJPaneController controller,int k) {
		setBounds(100, 100, 560, 357);
		this.controller = controller;
		key = k;
		st = new String[1][6];
		st[0] = controller.getStockBase().split(" ");
		initialize();
		hintBuCang = new String("持股数>0,不可进行补仓，请买入");
		hintBuy = new String("持股数<0,不可进行买入，请补仓");
		hintSale = new String("买卖股数>持股数，不可卖出，请卖空");
		hintSaleOut = new String("买卖股数<持股数，不可卖空，请卖出");
	}
	
	public void initialize()
	{
		getContentPane().setLayout(null);
		
		JLabel lTrace = new JLabel("交易");
		lTrace.setFont(new Font("宋体", Font.BOLD, 14));
		lTrace.setBounds(10, 100, 30, 16);
		getContentPane().add(lTrace);
		
		JLabel lPriceNow = new JLabel("价位：");
		lPriceNow.setBounds(10, 129, 80, 15);
		getContentPane().add(lPriceNow);
		
		tfPriceNS = new JTextField(StocksPrice.stocks[key].dqjg);
		tfPriceNS.setBounds(80, 126, 135, 21);
		tfPriceNS.setEditable(true);
		getContentPane().add(tfPriceNS);
		tfPriceNS.setColumns(10);
		
		JLabel lTraceMon = new JLabel("成交金额：");
		lTraceMon.setBounds(239, 212, 80, 15);
		getContentPane().add(lTraceMon);
		
		tfTraceMon = new JTextField();
		tfTraceMon.setBounds(319, 209, 126, 21);
		tfTraceMon.setEditable(false);
		getContentPane().add(tfTraceMon);
		tfTraceMon.setColumns(10);
		
		btg = new ButtonGroup();
		
		rbSaleOut = new JRadioButton("卖空");
		rbSaleOut.setBackground(Color.WHITE);
		rbSaleOut.setBounds(141, 174, 74, 23);
		getContentPane().add(rbSaleOut);
		
		rbMargin = new JRadioButton("补仓");
		getContentPane().add(rbMargin, BorderLayout.NORTH);
		rbMargin.setBounds(10, 174, 74, 23);
		rbMargin.setBackground(Color.WHITE);
		
		rbBuy = new JRadioButton("买",true);
		rbBuy.setBackground(Color.WHITE);
		rbBuy.setBounds(241, 174, 85, 23);
		getContentPane().add(rbBuy);
		
		rbSale = new JRadioButton("卖");
		rbSale.setBackground(Color.WHITE);
		rbSale.setBounds(341, 174, 85, 23);
		getContentPane().add(rbSale);
		
		btg.add(rbMargin);
		btg.add(rbBuy);
		btg.add(rbSale);
		btg.add(rbSaleOut);
		
		JLabel lSaleNum = new JLabel("买卖股数：");
		lSaleNum.setBounds(10, 212, 80, 15);
		getContentPane().add(lSaleNum);
		
		tfSaleNum = new JTextField();
		tfSaleNum.setBounds(80, 209, 135, 21);
		tfSaleNum.getDocument().addDocumentListener(new DocumentListener()
		{
			   public void insertUpdate(DocumentEvent e) {
			    // 在文本框插入字符，到此函数
				   String snum = tfSaleNum.getText();
				   int n;
				   if(judgeInteger(snum))
				   {
					   n = Integer.valueOf(snum);
					   double d = n * Double.valueOf(tfPriceNS.getText());
					   tfTraceMon.setText(format(String.valueOf(d)));
				   }
			   }
			   public void removeUpdate(DocumentEvent e) {
			    // 在文本框删除字符，到此函数
				   String snum = tfSaleNum.getText();
				   int n;
				   if(judgeInteger(snum))
				   {
					   n = Integer.valueOf(snum);
					   double d = n * Double.valueOf(tfPriceNS.getText());
					   tfTraceMon.setText(format(String.valueOf(d)));
				   }
			   }
			   public void changedUpdate(DocumentEvent e) {
			    // 修改字符属性，到此函数
				   String snum = tfSaleNum.getText();
				   int n;
				   if(judgeInteger(snum))
				   {
					   n = Integer.valueOf(snum);
					   double d = n * Double.valueOf(tfPriceNS.getText());
					   tfTraceMon.setText(format(String.valueOf(d)));
				   }
			   }
		});
		getContentPane().add(tfSaleNum);
		tfSaleNum.setColumns(10);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		pTable = new JPanel();
		pTable.setBackground(Color.WHITE);
		pTable.setBounds(0, 10, 544, 66);
		
		table = new ModifyTable(st,StocksPrice.stocks[key].dqjg,1);
		pTable.add(table);
		contentPanel.add(pTable);
		
		
		
		JLabel label = new JLabel("时间：");
		label.setBounds(247, 130, 54, 15);
		contentPanel.add(label);
		
		tftime = new JTextField();
		tftime.setBounds(285, 127, 141, 21);
		tftime.setText(getDate());
		tftime.setEditable(true);
		contentPanel.add(tftime);
		tftime.setColumns(10);
		
		hint2 = new String("价格和时间不能为空");
		
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("确定");
				okButton.setActionCommand("OK");
				
				okButton.addMouseListener(handler);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("取消");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addMouseListener(handler);
				buttonPane.add(cancelButton);
			}
		}
	
	}

	
	private class MouseHandler extends MouseAdapter
	{
		public void mouseClicked(MouseEvent e) 
        { //响应鼠标点击事件);
			double cash = controller.getCash();
			if(e.getSource() == okButton)
			{//如果进行买卖则，需要添加持仓记录，改变股票基本信息，改变用户信息。
				String te = tftime.getText();
				if(!isValidDate(te))
				{//"日期格式错误，格式为2012-02-09"
					 new DialogThread("日期格式错误，格式为12-02-09").start();
					return;
				}
				if(tftime.getText().isEmpty()||tfPriceNS.getText().isEmpty())
				{
					new DialogThread(hint2).start();
					return;
				}
				
				if(tfSaleNum.getText().isEmpty()||!judgeInteger(tfSaleNum.getText()))
				{
					new DialogThread("买卖股票数量必须是整数").start();
					return;
				}
				
				if(rbMargin.isSelected() || rbBuy.isSelected())
				{
					double  d = Double.valueOf(tfTraceMon.getText());
					if(d > cash)
					{
						new DialogThread("买入金额，超过现金").start();
						return ;
					}
				}
				
				
				if(!controller.isFocus())
				{
					controller.focusStock();
				}
				
				if(judgeInteger(tfSaleNum.getText()))
				{
					String str=new String();
					int total = Integer.valueOf(st[0][3]);
					int n = Integer.valueOf(tfSaleNum.getText());
					if(n < 0)
					{
						new DialogThread("输入买卖股数不能小于0").start();
						return;
					}
					if(rbMargin.isSelected())//补仓,当持股数大于0，不能补仓
					{
						if(total >= 0)
						{
							new DialogThread(hintBuCang).start();
							return;
						}
						else
						{
							str = tftime.getText()+"," +"补仓,"+ String.valueOf(tfPriceNS.getText())+"," + n;
						}
					}
					else if(rbSaleOut.isSelected())
					{
						if(total >= n)
						{
							new DialogThread(hintSaleOut).start();
							return;
						}
						else
						{
							str =tftime.getText()+"," +"卖空,"+ String.valueOf(tfPriceNS.getText())+"," + n;
						}
					}
					else if(rbBuy.isSelected())
					{
						if(total < 0)
						{
							new DialogThread(hintBuy).start();
							return;
						}
						else
						{
							 str = tftime.getText()+"," +"买入,"+ String.valueOf(tfPriceNS.getText())+"," + n;
						}
					}
					else if(rbSale.isSelected())
					{
						if(total < n)
						{
							new DialogThread(hintSale).start();
							return;
						}
						else
						{
							str = tftime.getText()+"," +"卖出,"+ String.valueOf(tfPriceNS.getText())+"," + n;
						}
					}
					controller.SaleRecord(str);
					controller.changeStockPre();
				}	
				
				
			}
			else if(e.getSource() == cancelButton)
			{
				setVisible(false);
			}
        }
	}
	
	private class MouseHandler2 extends MouseAdapter
	{
		public void mouseClicked(MouseEvent e) 
        { //响应鼠标点击事件);
			if(e.getSource() == rcurrent)
			{
				tfPriceNS.setText(StocksPrice.stocks[key].dqjg);
				tfPriceNS.setEditable(false);
				tftime.setText(getDate());
				tftime.setEditable(false);
				tfSaleNum.setText("");
			}
			else if(e.getSource() == rhistory)
			{
				tfPriceNS.setEditable(true);
				tftime.setEditable(true);
				tfSaleNum.setText("");
			}
        }
	}
	private boolean judgeInteger(String str)
	{
		try {Integer.parseInt(str);
		return true;
		} catch (NumberFormatException e) {
		return false;}
	}
	

	private String getDate()
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yy-MM-dd");
		//应该是sdf直接加点出来那种,不要用网上加date的...
		Date d = new Date();
		String s = sdf.format(d); //LZ的意思是获得当前日期的字符串吧
		return s;
	}

	public void refreshUi() {
		// TODO Auto-generated method stub
		System.out.println("refreashui" +  controller.getStockBase());
		pTable.remove(table);
		st[0] = controller.getStockBase().split(" ");
		table = new ModifyTable(st,StocksPrice.stocks[key].dqjg,1);
		pTable.add(table);
		validate();
	}
	
	public static boolean isValidDate(String s)
    {
        try
        {
        	if(s.charAt(2) != '-')
        		return false;
             dateFormat.parse(s);
             return true;
         }
        catch (Exception e)
        {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }
    }

    // 下面这个方法则可以将一个日期按照你指定的格式输出
    public static String formatDate(Date d)
    {
        return dateFormat.format(d);
    }

	private String format(String st)
	{
		DecimalFormat df=new DecimalFormat(".##");
		double a = Double.valueOf(st);
		return df.format(a);
	}


}

