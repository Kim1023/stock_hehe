package cModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import urldemo.DataAccessThread;
import ClassType.ThirtyDayProfit;
import ClassType.UserAccount;

public class Yield {
	//你能不能传StockModel stockModel, UserAccount user这样的参数？不行的话，你得写StockModel和UserAccount的get方法
	//我需要StockModel和UserAccount里面的变量和方法。
	public String yield(StockModel stockModel, UserAccount user) {
		// 获取全部股票信息
		String st = new String();
		ThirtyDayProfit thirtyDay = stockModel.getThirtyDayProfit();
		
		String[] stockname =  stockModel.getallStockName().split(",");
		String[] allStockPrice = thirtyDay.getStockPrice(stockModel.getallStockName()).split(";");
		String[] allStockConsist = stockModel.getThirtyConsist(0).split(";");
		String[][] allStockDayConsist = new String[stockname.length][30];
		String[][] allStockDayPrice = new String[stockname.length][30];
		
		for(int i = 0; i < stockname.length;i++)
		{
		//	System.out.println("Yeild" + allStockConsist[i]);
			allStockDayPrice[i] = allStockPrice[i].split(",");
			allStockDayConsist[i] = allStockConsist[i].split(",");
		}
		
		//记录每天价格，
		Double account_sum = 0.0;
		//记录每天收益率
		Double[] sum = new Double[30];
		Double[] rate = new Double[30];
		Calendar c = Calendar.getInstance();
	
		c.add(Calendar.DAY_OF_YEAR, -30);
		
		for (int j = 0; j < 30; j++) {
			// 该循环是循环一天的所有股票的数据
			account_sum = 0.0;
			if(j==0)
				st = String.valueOf(c.get(Calendar.YEAR));
			else
				st +=","+ String.valueOf(c.get(Calendar.YEAR));
			st += "," + String.valueOf(c.get(Calendar.MONTH)+1);
			st += ","+String.valueOf(c.get(Calendar.DAY_OF_MONTH));
			boolean flag = false;
			for (int i = 0; i < stockname.length; i++) 
			{
				//日期到j天前
				
				if(c.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY||c.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)
				{
					flag = true;
					break;
				}
				
				//AllstockDayPrice 是每只股票30天的收盘价
				//AllstockDayConsist每只股票每天的持股数	
				account_sum = account_sum + Double.valueOf(allStockDayPrice[i][j])*Integer.valueOf(allStockDayConsist[i][j]); 
			}
			// 总资产=所有股票的价值+本金
			// 需要在UserAccount里加一个简单的getPricipal()方法。
			account_sum = account_sum + user.getPricipal();
			sum[j] = account_sum;
			
			if(flag && j!=0)
			{	
				rate[j] = rate[j-1];
				
			}
			else
			{
				rate[j] = ((sum[j]-sum[0]) / sum[0])*100;
			}
			st += ","+rate[j];
			c.add(Calendar.DAY_OF_YEAR, 1);
		}
		//返回Double[]数组，rate[0]是当天的，rate[30]是30天前的
		//2015,10,1,200,
		
		return st;
	}
}
