package ClassType;

import java.sql.Date;
import java.util.Iterator;
import java.util.Vector;


public class StockInfo {
	private String name;
	private String nob;
	private double price;
	private int num;
	private double totalPrice;
	private double profit;
	private Vector<StockRecord> record = new Vector<StockRecord>();
	//复制stockinfo
	public StockInfo(StockInfo s)
	{
		String str = s.getStockInfo();
		String st[] = str.split(" ");
		name = st[0];
		nob = st[1];
		price = Double.valueOf(st[2]);
		num = Integer.valueOf(st[3]);
		totalPrice = Double.valueOf(st[4]);
		profit = Double.valueOf(st[5]);
		
		st = s.getStockRecord();
		
		for(int i = 0; i < s.getRecordNum();i++)
		{
			StockRecord rec = new StockRecord();
			rec.setRecord(st[i]);
			record.add(rec);
		}
	}
	//通过交易记录来添加stockinfo的
	public StockInfo(String str)
	{
		//第一条不盈利，直到价格变更。
		StockRecord sr = new StockRecord(str);
		record.add(sr);
		String s = sr.toString();
		String[]  st = s.split(" ");
		name = st[0];
		nob = st[1];
		price  = Double.valueOf(st[4]);
		num = Integer.valueOf(st[5]);
		totalPrice = num*price;
		profit = 0;
	}
	public void addStockRecord(String str)
	{
		StockRecord sr = new StockRecord(str);
		record.add(sr);
		String s = sr.toString();
		String[]  st = s.split(" ");
		int lnum = Integer.valueOf(st[5]);
		double tprice = Double.valueOf(st[4]);
		int type = Integer.valueOf(st[3]);
		if(type == 2||type == 1)//卖出和卖空，改变盈利不改变价格
		{
			//如果客户手上有股票则规定这些股票必须以现在的价格卖出，则会有盈利，然后再进行卖空，如果原有的股票卖不空，则不进行卖空，如果原先处于卖空状态则价格平均。
			//if(num > 0&& num >= Math.abs(lnum))
			//{
				profit += (tprice - price)*Math.abs(lnum);
			/*}
			else if(num > 0&& num < Math.abs(lnum))
			{
				profit += (tprice - price)*num;
			}
			else//当处于卖空状态则价格需要平均。
			{
				price = (price*Math.abs(num) + Math.abs(lnum)*tprice)/(Math.abs(num)+Math.abs(lnum));
			}*/
		}
		else if(type == 3||type == 0)//买入和补仓，改变的是价格，不改变盈利
		{
			//如果客户手上有的股票大于0，则当成正常的买入，则成本价变动，当小于0，则有有盈利
			/*if(num < 0&& Math.abs(num) >= lnum)
			{
				profit += (price - tprice)*lnum;
			}
			else if(num < 0&&Math.abs(num) < lnum)
			{
				profit += (price - tprice)*Math.abs(num);
				price = tprice;
			}
			else
			{*/
				price = (price*Math.abs(num) + Math.abs(lnum)*tprice)/(Math.abs(num)+Math.abs(lnum));
				System.out.println(Math.abs(num) + " " + Math.abs(lnum)+ " " + tprice);
			//}
		}
		
		num = num+lnum;
		totalPrice = price*num;
	}
	public StockInfo(String str,int flag)
	{
		String[] st = str.split(" ");
		name = st[0];
		nob = st[1];
		price = Double.valueOf(st[2]);
		num = Integer.valueOf(st[3]);
		totalPrice = Double.valueOf(st[4]);
		profit = Double.valueOf(st[5]);
	}
	public void setStockRecord(String str[])
	{
		for(int i = 0; i < str.length;i++)
		{
			if(str[i].length() == 0)
			{
				System.out.println("读入错误");
				return;
			}
			StockRecord sr = new StockRecord(str[i]);
			record.add(sr);
		}
	}
	
	
	public String[] getStockRecord()
	{
		Iterator<StockRecord> iter = record.iterator();
		String[] str = new String[record.size()];
		int i = 0;
		while(iter.hasNext())
		{ 
			str[i] = iter.next().toString();
			i++;
		}
		return str;
	}
	
	public String getStockRecordLast()
	{
		String str = record.get(record.size()-1).toString();
		return str;
	}
	
	private class StockRecord
	{
		private String name1;
		private String No;
		private String date;
		private int type;
		private double price;
		private int Num;
		
		public StockRecord() {}
		public StockRecord(String s)
		{
			String[] stemp = s.split(" ");
			System.out.println(s);
			name1 = stemp[0];
			No = stemp[1];
			date = stemp[2];
			Num = Integer.valueOf(stemp[5]);
			if(stemp[3].equals("买入"))
				type = 0;
			else if(stemp[3].equals("卖出"))
			{
				type = 1;
				Num = -Num;
			}
			else if(stemp[3].equals("卖空"))
			{
				type = 2;
				Num = -Num;
			}
			else if(stemp[3].equals("补仓"))
				type = 3;
			price = Double.valueOf(stemp[4]);
		}

		
		public String toString()
		{
			return name1+" " + No + " " + date +" " + type + " " + price 
					+ " " + Num;
		}
		
		public void setRecord(String s)
		{
			String[] stemp = s.split(" ");
			name1 = stemp[0];
			No = stemp[1];
			date = stemp[2];
			type = Integer.valueOf(stemp[3]);
			price =Double.valueOf(stemp[4]);
			Num = Integer.valueOf(stemp[5]);
		}
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	public int getRecordNum() {
		// TODO Auto-generated method stub
		return record.size();
	}
	public String getStockInfo() {
		// TODO Auto-generated method stub
		return name+" " +nob + " " + price + " " + num + " "+ totalPrice+" "+profit;
	}
	public void delectRecord(int row) {
		// TODO Auto-generated method stub
		//如果是加买入和补仓那么持股数量减少，如果是卖出和补仓则是持股数量增多
		StockRecord s = (StockRecord)record.get(row);
		if(s.type==0||s.type==3)
		{//买入和补仓导致价格变化	
			price = (price*Math.abs(num) - Math.abs(s.Num)*s.price)/(Math.abs(num)-Math.abs(s.Num));
			num -= s.Num;
			totalPrice = price*num;
		
		}
		else
		{//卖出和卖空不导致价格变化,但获利变化
			//price = (price*Math.abs(num) + Math.abs(s.Num)*s.price)/(Math.abs(num)+Math.abs(s.Num));
			profit += (price - s.price)*Math.abs(s.Num);
			num += Math.abs(s.Num);
			totalPrice = price*num;
		}
		System.out.println(price + " " + num +" " + totalPrice);
		record.remove(row);
	}
	
	public int getHoldNum()
	{
		return num;
	}
	public String getStockRecord(int row) {
		// TODO Auto-generated method stub
		String str = record.get(row).toString();
		String[] s = str.split(" ");
		int i = Integer.valueOf(s[3]); 
		if(i == 0)
			s[3] = "卖出";
		else if(i == 1)
			s[3] = "买入";
		else if(i == 2)
			s[3] = "补仓";
		else
			s[3] = "卖空";
		i = -Integer.valueOf(s[5]);
		s[5] = String.valueOf(i);
		str = s[0]+","+s[1]+","+s[2]+","+s[3]+","+s[4]+","+s[5];
		return str;
	}
}
