package urldemo;

import Dao.DaoR;

public class DataAccessThread 
{
	public String GetHistoryData(String target,String data)
	{
		DataAccess a =new DataAccess(target);
		String s=a.GetHistoryData(target, data);
		return s;
	}
	public void run()
	{
		String[][] str = getStockE();
		for(int i = 0; i < StocksPrice.num;i++)
		{
				boolean flag = false; //如果股票没有在stocke里面，需要用到run1
				for(int j = 0; j < str.length; j++)
				{
					String s = new String(StocksPrice.StockNums[i]);
					if(str[j][0].equals(s))
					{
						
						StocksPrice.stocks[i]= new StockBase();
						StocksPrice.stocks[i].name=str[j][1];
						StocksPrice.stocks[i].kpj=str[j][2];
						StocksPrice.stocks[i].spj=str[j][3];
						StocksPrice.stocks[i].dqjg=str[j][4];
						StocksPrice.stocks[i].zgj=str[j][5];
						StocksPrice.stocks[i].zdj=str[j][6];
						flag = true;
						break;
					}
				}
				if(!flag)
				{
					StocksPrice.updateStock(StocksPrice.StockNums[i]);
					run1();
				}
				
		}
/*	
			for(int i=0;i<StocksPrice.num;i++)
			{
			DataAccess a ;
			a = new DataAccess(StocksPrice.StockNums[i]);
			try {
				a.download(a.daily_k,StocksPrice.StockNums[i]+"daily.gif");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				a.download(a.monthly_k,StocksPrice.StockNums[i]+"monthly.gif");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				a.download(a.weekly_k,StocksPrice.StockNums[i]+"weekly.gif");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String s[]=a.GetData();
			StocksPrice.stocks[i]= new StockBase();
			StocksPrice.stocks[i].name=s[0];
			StocksPrice.stocks[i].kpj=s[1];
			StocksPrice.stocks[i].spj=s[2];
			StocksPrice.stocks[i].dqjg=s[3];
			StocksPrice.stocks[i].zgj=s[4];
			StocksPrice.stocks[i].zdj=s[5];
			System.out.println(StocksPrice.stocks[i].name + StocksPrice.stocks[i].dqjg);
			}
*/
	}
	
	public boolean run1()
	{
		DataAccess a ;
		
		a = new DataAccess(StocksPrice.singleNum);
		try{
			String s[]=a.GetData();
			if(s[0].equals("error"))
			{
				return false;
			}
			
			StocksPrice.singlestock= new StockBase();
			StocksPrice.singlestock.name=s[0];
			StocksPrice.singlestock.kpj=s[1];
			StocksPrice.singlestock.spj=s[2];
			StocksPrice.singlestock.dqjg=s[3];
			StocksPrice.singlestock.zgj=s[4];
			StocksPrice.singlestock.zdj=s[5];
			
			for(int i = 0; i < StocksPrice.stocks.length;i++)
			{
				if(StocksPrice.singleNum.equals(StocksPrice.StockNums[i]))
				{
					StocksPrice.stocks[i]= new StockBase();
					StocksPrice.stocks[i].name=s[0];
					StocksPrice.stocks[i].kpj=s[1];
					StocksPrice.stocks[i].spj=s[2];
					StocksPrice.stocks[i].dqjg=s[3];
					StocksPrice.stocks[i].zgj=s[4];
					StocksPrice.stocks[i].zdj=s[5];
					break;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		try {
			a.download(a.daily_k,StocksPrice.singleNum+"daily.gif");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		try {
			a.download(a.monthly_k,StocksPrice.singleNum+"monthly.gif");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			a.download(a.weekly_k,StocksPrice.singleNum+"weekly.gif");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	private String[][] getStockE()
	{
		DaoR daoR = new DaoR();
		String str = daoR.readStockE();
		
		String[] st = str.split(";");
		String[][] ss = new String[st.length][7];
		for(int i = 0; i < st.length;i++)
		{
			ss[i] = st[i].split(",");		
		}
		return ss;
	}
}