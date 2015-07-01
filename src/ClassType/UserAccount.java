package ClassType;

import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.Vector;

import javax.lang.model.util.Elements;
import javax.swing.ImageIcon;

import Dao.DaoUothers;

import java.util.Iterator;

public class UserAccount {
	
	private String name;
	private ImageIcon icon;
	private double  profit_loss,profit_loss_pre;//盈亏值，盈亏率
	private double account_sum, cash, market_cap;//总资产，现金，市值
	private double pricipal;//本金
	private Vector<StockSample> stock = new Vector<StockSample>();//存储股票的名称，编号
	
	public UserAccount(String name)
	{
		this.name = name;
		icon = new ImageIcon("Image/icon.jpg");
		profit_loss = 0;
		profit_loss_pre = 0;
		account_sum = 500000.00;
		cash = 500000.00;
		market_cap = 0;
		pricipal = 500000.00;
	}
	
	public int findNum(String n)
	{
		Iterator iter = stock.iterator();
		int i = 0;
		while(iter.hasNext())
		{
			StockSample a = (StockSample)iter.next();
			if(a.equalsName(n))
			{
				break;
			}
			i++;
		}
		return i ;
	}
	
	public boolean addStock(String stemp)
	{
		//如果是新的股票则添加到股票中去，同时计算现金的改变
		String[] st = stemp.split(",");
		
		StockSample s = new StockSample(st[0], st[1]);
		Iterator iter =  stock.iterator();
		while(iter.hasNext())
		{
			StockSample a = (StockSample)iter.next();
			if(a.getName().equals(st[0]))
				return false;
		}
	
		stock.add(s);
		return true;
	}
	public void calcash(String[] st)
	{
		System.out.println("Calcash" + st[3]);
		if(st[3].equals("卖出")||st[3].equals("卖空"))
			cash += Double.valueOf(st[4])*Math.abs(Integer.valueOf(st[5]));
		else
			cash -= Double.valueOf(st[4])*Math.abs(Integer.valueOf(st[5]));
	}
	
	public String toInfoString()
	{
		DecimalFormat df=new DecimalFormat(".##");
		
		String str = df.format(profit_loss) + " "+df.format(profit_loss_pre*100) + " "
		 +df.format(account_sum)+ " "+ df.format(cash) + " " + df.format(market_cap) + " "	+ df.format(pricipal);
		return str;
	}
	
	
	public String toStock()
	{
		Enumeration e = stock.elements();
		String str = new String();
		while(e.hasMoreElements())
		{
			StockSample s = (StockSample) e.nextElement();
			str += s.toString()+" ";
		}
		return str;
	}
	
	public String getName()
	{
		return name;
	}
	
	public  ImageIcon getIcon()
	{
		return icon;
	}

	
	public void setInfo(String str)
	{
		String[] s = str.split(" ");
		profit_loss = Double.valueOf(s[0]);
		profit_loss_pre = Double.valueOf(s[1]);
		account_sum = Double.valueOf(s[2]);
		cash = Double.valueOf(s[3]);
		market_cap = Double.valueOf(s[4]);
		pricipal = Double.valueOf(s[5]);
	}
	
	public void setStock(String str)
	{
		String[] s = str.split(" ");
		for(int i = 0;i < s.length&&s.length > 1;i+=2)
		{
			StockSample st = new StockSample(s[i],s[i+1]);
			stock.add(st);
		}
	}
	
	//需要用户信息的函数，存储所有用户信息的函数，用户信息更改时存储到用户信息的函数
	private class StockSample
	{
		String stockname,stockNum;//股票名和股票的编号
		 public StockSample(String name, String stockNum2) {
			// TODO Auto-generated constructor stub
			 stockname = name;
			 stockNum = stockNum2;
		}
		 public String toString()
		 {
			 return stockname +" "+stockNum;
		 }
		 public String getName()
		 {
			 return stockname;
		 }
		 public boolean equalsName(String n)
		 {
			 return stockname.equals(n);
		 }
		public boolean equalsNob(String nob) {
			// TODO Auto-generated method stub
			return stockNum.equals(nob);
		}
	}

	public void setName(String name2) {
		// TODO Auto-generated method stub
		name = name2;
	}

	public int getStockNum() {
		// TODO Auto-generated method stub
		return stock.size();
	}

	public double getCash() {
		// TODO Auto-generated method stub
		return cash;
	}

	public void setCash(boolean flag, double cash2) {
		// TODO Auto-generated method stub
		if(!flag)
		{
			cash -= cash2;
			pricipal -= cash2;
			account_sum -= cash2;
		}
		else
		{
			cash += cash2;
			pricipal += cash2;
			account_sum += cash2;
		}
	}
	
	//放在controller里面
	public void Calculate(String[][] toMainFrameshow)//[][0]是盈亏，[][1]是市值
	{ 
	  //盈亏额 = 所有股票的盈亏和
      //盈亏率 = 盈亏额 / 本金
	  //现金 = 买卖股票时变动
	  //总资产 = 现金+市值
	  //市值 = 所有的股票市值
	  //本金
		//市值
		System.out.println("toMainFrameshow[0][0]:"+toMainFrameshow[0][0]);
		market_cap = 0;
		profit_loss = 0;
		 for(int i = 0;i < stock.size();i++)
		  {
			 //累加每只股票的持股数*当前价格
			 profit_loss += Double.valueOf(toMainFrameshow[i][0]);
		
			 market_cap += Double.valueOf(toMainFrameshow[i][1]);
			 
		  }
		 if(pricipal != 0.00)
			 profit_loss_pre = profit_loss / pricipal;
		 else 
			 profit_loss_pre = 0;
		 account_sum = cash + market_cap;
		
	}

	public int getStockLocal(String stockname) {
		// TODO Auto-generated method stub
		Iterator iter = stock.iterator();
		int i = 1;
		while(iter.hasNext())
		{
			StockSample sam = (StockSample) iter.next();
			if(sam.equalsName(stockname))
				return i;
			i++;
		}
		return 0;
	}

	public void removeStock(String name2) {
		// TODO Auto-generated method stub
		for(int i = 0; i < stock.size();i++)
		{
			StockSample ss = stock.get(i);
			if(ss.equalsName(name2))
			{
				stock.remove(i);
				System.out.println("when cancelfocus"+cash);
				return;
			}
		}
	}

	//添加新的股票
	public void addNewStock(String info) {
		// TODO Auto-generated method stub
		String[] st = info.split(" ");
		StockSample s = new StockSample(st[0], st[1]);
		stock.add(s);
	}

	public boolean checkStock(String nob) {
		// TODO Auto-generated method stub
		for(int i = 0; i < stock.size();i++)
		{
			StockSample ss = stock.get(i);
			if(ss.equalsNob(nob))
			{
				return true;
			}
		}
		return false;
	}

	public Double getPricipal() {
		// TODO Auto-generated method stub
		return pricipal;
	}

}
