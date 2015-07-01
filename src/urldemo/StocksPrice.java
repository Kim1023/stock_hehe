package urldemo;

import ClassType.DialogThread;

public class StocksPrice
{
	static public String StockNums[] = new String[1000];
	static public StockBase stocks[] = new StockBase[1000];
	static public int num = 0;
	
	
	static public String singleNum = new String();
	static public StockBase singlestock = new StockBase();
	
	/*static {
		StockNums[0]="sh601005";
		StockNums[1]="sh601006";
		num=2;
	}*/
	static public void AddStock (String s)
	{
		int flag=0;
		
		for(int i=0;i<num;i++)
		{
			if(StockNums[i].compareTo(s)==0)
			{
				flag=1;
				break;
			}
				
		}
		if(flag==0)
		{
		StockNums[num] = s;
		num++;
		}
	}
	
	static public void updateStock(String s)
	{
		singleNum = s;
		System.out.println("查找股票"+singleNum);
	}
}