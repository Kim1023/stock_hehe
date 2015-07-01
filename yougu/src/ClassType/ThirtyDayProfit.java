package ClassType;

import java.sql.Struct;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import Dao.DaoR;

public class ThirtyDayProfit {
	private class Profit
	{
		Vector<Double> price = new Vector<Double>();
		void addPrice(Double k)
		{
			price.add(k);
		}
		public String toString()
		{
			boolean flag = false;
			String str =new String();
			Iterator iter = price.iterator();
			while(iter.hasNext())
			{
				Double d = (Double) iter.next();
				if(!flag)
				{
					str = String.valueOf(d);
					flag = true;
					continue;
				}
				str += ","+String.valueOf(d);
			}
			return str;
		}
	};
	private Map<String,Profit> stockprofit = new HashMap<String,Profit>();
	
	public ThirtyDayProfit()//先初始化一些股票在里面
	{
		String str = new DaoR().readStockT();
		String[] st = str.split(";");
		String[][] ss = new String[st.length][31];
		String news = new String();
		String name = new String();
		for(int i = 0; i < st.length;i++)
		{
			ss[i] = st[i].split(",");
			name = ss[i][0];
			for(int j = 1; j < ss[i].length;j++)
				if(j == 1)
				{
					news = ss[i][j];
				}
				else
					news += "," + ss[i][j];
			add(name,news);
		}
		
	}
	
	public synchronized void add(String stockname,String profit)
	{
		String[] str = profit.split(",");
		Profit p = new Profit();
		for(int i = 0; i < str.length;i++)
		{
			p.addPrice(Double.valueOf(str[i]));
		}
		stockprofit.put(stockname, p);
	}
	
	public synchronized String getStockPrice(String name)
	{
		String[] n = name.split(",");
		String result = new String();
		boolean flag = false;
		for(int i = 0; i < n.length;i++)
		{
			 Iterator<Map.Entry<String, Profit>> iterator = stockprofit.entrySet().iterator(); 
			 String str = new String();
		     while (iterator.hasNext()) {  
		    	 Map.Entry<String, Profit> entry = iterator.next();  
		    	 if(n[i].equals(entry.getKey()))
		    	 {
		    		 str = entry.getValue().toString();
		    		 break;
		    	 }	    	 
		     }
		     if(!flag)
	    	 {
	    		 result = str;
	    		 flag = true;
	    		 continue;
	    	 }
		     result += ";" + str;
		}
		
		return result;
	}
	
	public synchronized String getStockName()
	{
		String str = new String();
		boolean flag = false;
	     Iterator<Map.Entry<String, Profit>> iterator = stockprofit.entrySet().iterator();  
	     while (iterator.hasNext()) {  
	          Map.Entry<String, Profit> entry = iterator.next();  
	          if(!flag)
	          {
	        	  str = entry.getKey();
	        	  flag = true;
	        	  continue;
	          }
	          str += "," + entry.getKey();
	     }  
		return str;
	}
	
	public synchronized boolean check(String name)
	{
		return stockprofit.containsKey(name);
	}
}
