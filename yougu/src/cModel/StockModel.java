package cModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.Vector;

import ClassType.StockInfo;
import ClassType.ThirtyDayProfit;
import Dao.DaoUothers;
import Dao.DaoUthis;

public class StockModel {
	private Vector<StockInfo> stock = new Vector<StockInfo>();
	private ThirtyDayProfit profit = new ThirtyDayProfit();
	public StockModel()
	{
		stock.clear();
	}
	
	public ThirtyDayProfit getThirtyDayProfit()
	{
		return profit;
	}
	//添加交易记录,在导入数据和买卖时使用
	public void addStockRecord(String name,String str) {
		// TODO Auto-generated method stub
		String[] st = str.split(",");
		String chong = st[0]+" " + st[1]+" "+ st[2]+" "+st[3] + " "+st[4]+" "+st[5];
		//如果股票已经存在则添加到记录中
		Iterator iter = stock.iterator();
		int i = 0;
		while(iter.hasNext())
		{
			StockInfo info = (StockInfo) iter.next();
			//股票已经存在则添加股票的记录,添加到第i+1个sheet中，这个sheet中第一行是股票信息，第二条行后面是交易记录
			
			if(info.getName().equals(st[0]))
			{
				info.addStockRecord(chong);
				new DaoUthis().updateStock(name,info.getStockInfo(),i+1,0);
				new DaoUthis().updateStock(name, String.valueOf(info.getRecordNum()),i+1, 1);
				new DaoUthis().updateStock(name,chong,i+1,info.getRecordNum()+1);
				return;
			}
			i++;
		}
		//如果股票不存在，则添加到stock中，并创建一个新的sheet
		//传进第一条记录
		StockInfo info = new StockInfo(chong);
		stock.add(info);
		//将新建的股票放在最后的sheet中。
		new DaoUothers().add(name, st[0],info.getStockInfo(),stock.size());
		new DaoUthis().updateStock(name, "1",stock.size(), 1);
		new DaoUthis().updateStock(name, chong,stock.size(), 2);
	}
	
	public void addInitStock(String baseInfo,String[] record)
	{//初始化stock时使用
		StockInfo info = new StockInfo(baseInfo,1);
		info.setStockRecord(record);
		stock.add(info);
	}
	//获得所有股票的信息，除交易记录
	public String[] getAllStockInfo() {
		// TODO Auto-generated method stub
		String[] str = new String[stock.size()];
		for(int i = 0; i < stock.size();i++)
		{
			str[i] = stock.get(i).getStockInfo();
		}
		return str;
	}
	public StockInfo getStockInfo(String name) {
		// TODO Auto-generated method stub
		//获得指定名字的stockInfo
		Iterator iter = stock.iterator();
		
		while(iter.hasNext())
		{
			StockInfo ts = (StockInfo) iter.next();
			if(ts.getName().equals(name))
				return ts;
		}
		return null;
	}
	public void removeStock(String username,String name) {
		// TODO Auto-generated method stub
		for(int i = 0; i < stock.size();i++)
		{
			StockInfo si = stock.get(i);
			if(si.getName().equals(name))
			{
				stock.remove(i);
				new DaoUothers().subSheet(username, i+1);
				return;
			}
		}
	}

	//股票名，行数
	public void delectRecord(String username,String name,int row) {
		// TODO Auto-generated method stub
		StockInfo si = stock.get(0);
		int k=1;
		for(int  i= 0; i < stock.size();i++)
		{
			si = stock.get(i);
			if(si.getName().equals(name))
			{
				si.delectRecord(row);
				k = i+1;
				break;
			}
		}
		//用户名，第几个sheet的第几行,更新股票信息
		new DaoUthis().updateStock(username,si.getStockInfo(),k,0);
		new DaoUthis().subRow(username, k, 2+row);
	}

	public String getallStockName()
	{
		String str = stock.get(0).getName();
		for(int i = 1; i < stock.size();i++)
		{
			StockInfo si = stock.get(i);
			str += ","+ si.getName();
		}
		return str;
	}
	
	public String getThirtyConsist(int key)
	{
		String str = new String();
		if(key == 0)
		{
			if(stock.size() == 0)
				return null;
		
			System.out.println("第一只股票的最近30天交易情况"+str);
		
			for(int j = 0;j < stock.size();j++)
			{					
				if(j == 0)
					str = getoneThirtyConsist(j);
				else
					str += ";" + getoneThirtyConsist(j);	
						
			}
		}
		else
		{
			if(stock.size() == 0)
				return null;
			for(int j = 0;j < stock.size();j++)
			{
				
				if(j == 0)
					str = getoneThirtyConsist(j);
				else
					str += ";" + getoneThirtyConsist(j);
			}
		}
		
		System.out.println(str);
		return str;
	}
	private String getoneThirtyConsist(int i)
	{
		int day = 30;
		String str = new String();
		StockInfo si = stock.get(i);
		Calendar c = Calendar.getInstance();
		Date dt =  c.getTime();
		String st[] = si.getStockRecord();
		String s[][] = new String[st.length][7];
		String pattern="yyyy-MM-dd";
		SimpleDateFormat sdf=new SimpleDateFormat(pattern);
		
		int[] all = new int[31];
		for(int j = 0; j < day;j++)
		{
			all[j] = 0;
		}
		
		int orinum = 0;
		for(int j = 0;j < st.length;j++)
		{
			s[j] = st[j].split(" ");
			Date date = c.getTime();

				s[j][2] = "20"+s[j][2];
				System.out.println(s[j][2]);
				 try {
					date = (Date)sdf.parse(s[j][2]);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			
			long minu = (dt.getTime() - date.getTime())/(1000*3600*24); 
			System.out.println("差距最小"+ dt.getTime()+" " + date.getTime()+" " + minu);
			if(minu < day)
			{
				all[day-1-(int)minu]+= Integer.valueOf(s[j][5]);
			}
			else
			{
				orinum += Integer.valueOf(s[j][5]);
			}
		}
		all[1] += all[0]+orinum;
		str = String.valueOf(all[1]);
		for(int  j = 1;j < day;j++)
		{
			all[j] += all[j-1];
			if(all[j] > 0)
				str += "," + String.valueOf(all[j]);
			else
				str += ",0";
		}
		return str;
	}
}
