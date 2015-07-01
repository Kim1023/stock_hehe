package ClassType;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import urldemo.DataAccessThread;

public class PriceThread extends Thread{
	private ThirtyDayProfit price; //指向引用的共享对象
	private String[] Stockinfo;
	

	public PriceThread(ThirtyDayProfit p)
	{
		price = p;
	}
	
	public void setStockInfo(String info)
	{
		Stockinfo = info.split(" ");
	}
	
	public void run()
	{
		getinfo();
	}
	
	public void getinfo()
	{
		if(!price.check(Stockinfo[0]))
		{
			DataAccessThread a = new DataAccessThread();
	
			Calendar c = Calendar.getInstance();
			String str = new String();
			String st = new String();//放置每天收盘价
			// 获取该格式的当前时间
			//Date now = new Date();
			SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
		
			// 从30天前向后遍历
			c.add(Calendar.DAY_OF_YEAR, -30);
			for (int j = 0; j < 30; j++) {
				// 该循环是循环一天的所有股票的数据
				Date dt1 = c.getTime();
				
				String reStr = matter1.format(dt1);  //获取"2015-05-24"日期字符串
					
					// 股票代码搜索收盘价，日期为当前时间的j天前
				
				//日期到j天前
				if((c.get(Calendar.DAY_OF_WEEK)!=Calendar.SATURDAY&&c.get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY)|| j==0)
				{
					str = a.GetHistoryData(Stockinfo[1], reStr);//获取当天收盘价
				}
					
				if(j==0)
					st = str;
				else
					st+= ","+str;
				// 需要在UserAccount里加一个简单的getPricipal()方法。
				c.add(Calendar.DAY_OF_YEAR, 1);
			}
			
			price.add(Stockinfo[0], st);
			//返回Double[]数组，rate[0]是当天的，rate[30]是30天前的
			//2015,10,1,200,
		}
	}
}
