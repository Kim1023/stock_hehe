package cModel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.File;
import java.io.OutputStream;

import javax.swing.JLabel;

import urldemo.StocksPrice;
import jxl.Cell;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import ClassType.StockInfo;
import ClassType.UserAccount;
import Dao.DaoR;
import Dao.DaoUothers;
import Dao.DaoUthis;
import Dao.DaoW;

public class UserInfoModel {

	public UserAccount useraccount;
	public StockModel stockmodel = new StockModel();
	public UserInfoModel(String name)
	{
		String sFilePath="File/"+ name + "_personInfo.xls";
        File file = new File(sFilePath);
        useraccount = new UserAccount(name);
       if(!file.exists())
        {//创建该xls
    	   new DaoW().writeUserInfo(name,useraccount.toInfoString(),useraccount.toStock());
        }
        else
        {
        	
        	//读出user的信息
        	String[] info = new DaoR().readUserInfo(name).split(" ");
        	String str1 = new String();
        	String str3 = new String();
        	
        	for(int i = 0;i < 6 ;i++)
        		str1 += info[i]+" ";
        	
        	useraccount.setInfo(str1);
        	
        	for(int i = 6;i < info.length && info.length>1;i++)
        		str3+= info[i]+" ";
        
        	useraccount.setStock(str3);
        	
        	//读出所偶股票的信息
        	for(int i = 0; i < useraccount.getStockNum();i++)
        	{
        		int k = i+1;
        		//读出股票基本信息
        		String str = new DaoR().readStock(useraccount.getName(), k);
        		String[] st = str.split(" ");
        		
        		//读出股票记录
        		String trecord = new DaoR().readStockRecord(useraccount.getName(), k);
        		String[] trec = trecord.split(";");
        
        		stockmodel.addInitStock(str, trec);
        	}
        }
		
	}

	public UserAccount getUser()
	{
		return useraccount;
	}
	public void addStockRecord(String str) {
		// TODO Auto-generated method stub
		//股票信息
		String[] st = str.split(",");
		//将股票的信息添加到stockmodel中
		stockmodel.addStockRecord(useraccount.getName(),str);
		//如果是新的股票则更新用户信息中的
		if(useraccount.addStock(str))
		{
			new DaoUthis().UpdateUserInfo(useraccount.getName(), useraccount.toInfoString(), 
					 useraccount.toStock());
		}
	}

	public void changeInfo() {
		// TODO Auto-generated method stub
	
		new DaoUthis().UpdateUserInfo(useraccount.getName(), useraccount.toInfoString(), 
				useraccount.toStock());
	}
	

	public String[] getAllStockInfo() {
		// TODO Auto-generated method stub
		String[] st = stockmodel.getAllStockInfo();
		//System.out.println(st[0]);
		return st;
	}
	
	public StockInfo getStockInfo(String name)
	{
		return stockmodel.getStockInfo(name);
	}

	public int getStockNum(String stockname) {
		// TODO Auto-generated method stub
		//返回股票stockname的位置，i》1.
		return useraccount.getStockLocal(stockname);
	}

	public void removeStock(String name) {
		// TODO Auto-generated method stub
		//删除股票要在useraccount中删除，也要在stockmodel中删除,在stockmodel中也同样删除了sheet。
		useraccount.removeStock(name);
		stockmodel.removeStock(useraccount.getName(),name);
		//在文档中跟新用户信息，
		new DaoUthis().UpdateUserInfo(useraccount.getName(), useraccount.toInfoString(), 
				 useraccount.toStock());
	}

	public void addStock(StockInfo info) {
		// TODO Auto-generated method stub
		//添加股票，到用户类中，添加股票到stockmodel中，添加股票到那个文档中，界面上进行显示。
		useraccount.addNewStock(info.getStockInfo());
		stockmodel.addInitStock(info.getStockInfo(), info.getStockRecord());
		//将useraccount的信息写回文件中，在stock中创建一个stock的sheet。
		new DaoUthis().UpdateUserInfo(useraccount.getName(), useraccount.toInfoString(), 
				 useraccount.toStock());
		new DaoUothers().add(useraccount.getName(), info.getName(),info.getStockInfo(),useraccount.getStockNum());
		new DaoUthis().updateStock(useraccount.getName(), "0",useraccount.getStockNum(), 1);
	}

	public void calcash(String str) {
		// TODO Auto-generated method stub
		String[] st = str.split(",");
		useraccount.calcash(st);
	}


	public void delectRecord(String name,int row) {
		// TODO Auto-generated method stub
		stockmodel.delectRecord(useraccount.getName(),name,row);
		
	}

	public StockModel getStockModel() {
		// TODO Auto-generated method stub
		return stockmodel;
	}

	public String getallStockName() {
		// TODO Auto-generated method stub
		return stockmodel.getallStockName();
	}

	public String getThirtyConsist(int key) {
		// TODO Auto-generated method stub
		return stockmodel.getThirtyConsist(key);
	}
}
