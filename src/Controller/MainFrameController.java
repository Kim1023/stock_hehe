package Controller;

import java.awt.Component;
import java.io.IOException;

import javax.swing.JPanel;

import ui.FixCash;
import ui.ImportData;
import ui.MainFrame;
import ui.Profit;
import ui.StockPre;
import urldemo.DataAccessThread;
import urldemo.StockBase;
import urldemo.StocksPrice;
import ClassType.PriceThread;
import ClassType.ThirtyDayProfit;
import ClassType.UserAccount;
import Dao.DaoR;
import cModel.LoginModel;
import cModel.StockModel;
import cModel.UserInfoModel;

public class MainFrameController {
	UserInfoModel model;
	public MainFrame ui;
	UserAccount user;
	
	public MainFrameController(UserInfoModel userInfomodel) {
		// TODO Auto-generated constructor stub
		model = userInfomodel;
		user = model.getUser();
		
		ui = new MainFrame(this);
		ui.getFrame_info().setVisible(true);
		
		String[] str = model.getAllStockInfo();
		String[][] st = new String[user.getStockNum()][7];
		for(int i = 0;i < user.getStockNum();i++)
		{
			st[i] = str[i].split(" ");
			StocksPrice.AddStock(st[i][1]);
		}
		
		DataAccessThread a =new DataAccessThread();
		a.run();
		
		ui.addTableRow(model.getAllStockInfo());
	}

	public ThirtyDayProfit getThirtyDayProfit()
	{
		return model.stockmodel.getThirtyDayProfit();
	}
	public UserAccount getUser() {
		// TODO Auto-generated method stub
		return model.getUser();
	}

	public void jumpLogin() {
		// TODO Auto-generated method stub
		ui.getFrame_info().setVisible(false);
		new LoginController(new LoginModel());
	}

	public void createImportData(String path) {
		// TODO Auto-generated method stub
	
		
		//读取文件
		String str = new DaoR().readStockData(path);
		String[] stocktemp = str.split(";");
		
		for(int i = 0;i < stocktemp.length;i++)
			System.out.println(stocktemp[i]);
		
		String[] stemp;
		for(int i = 0;i < stocktemp.length;i++)
		{
			if(stocktemp[i].isEmpty())
				continue;
			
			model.addStockRecord(stocktemp[i]);
		}
		
		String[] strr = model.getAllStockInfo();
		String[][] st = new String[user.getStockNum()][7];
		for(int i = 0;i < user.getStockNum();i++)
		{
			st[i] = strr[i].split(" ");
			StocksPrice.AddStock(st[i][1]);
		}
		
		DataAccessThread a =new DataAccessThread();
		a.run();
	
		ui.addTableRow(model.getAllStockInfo());
	}

	public JPanel jumpfixc() {
		// TODO Auto-generated method stub
		return (JPanel)new FixCash(this);
	}

	public Component jumpStockBase() throws IOException {
		// TODO Auto-generated method stub
	
			return new StockPre(this);
}

	public double getCash() {
		// TODO Auto-generated method stub
		
		return user.getCash();	
	}
	
	public void setCash(boolean flag,double cash)
	{
			user.setCash(flag, cash);
			ui.changeInfo();
			model.changeInfo();
	}
	//传入股票名
	public void createStockPanel(String sname) {
		// TODO Auto-generated method stub
		StockJPaneController con = new StockJPaneController(model,sname,this);
		ui.addTabbedPane(con.getJPane());
	}

	public boolean jPaneExist(String sname) {
		// TODO Auto-generated method stub
		if(ui.jPaneExist(sname))
			return true;
		return false;
	}
	
	public String getUserName()
	{
		return user.getName();
	}

	public void changeUI(String[][] toMainFrameshow) {
		// TODO Auto-generated method stub
		user.Calculate(toMainFrameshow);
		ui.changeInfo();
		model.changeInfo();
	}

	public void refreshUi() {
		// TODO Auto-generated method stub
		ui.addTableRow(model.getAllStockInfo());
	}

	public boolean checkStock() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean checkStock(String nob) {
		// TODO Auto-generated method stub
		
		if(user.checkStock(nob))
			return true;
		return false;
	}

	//传入股股票名和它在共享池中的位置
	public void createUnfocusStockPanel(String name,int key) {
		// TODO Auto-generated method stub
		StockJPaneController con = new StockJPaneController(model,name,this,key);
		ui.addTabbedPane(con.getJPane());
	}

	public String getStockPre() {
		// TODO Auto-generated method stub
		String str = new String();
		String st[] = model.getAllStockInfo();
		String[] s = st[0].split(" ");
		if(st.length > 0)
			str = s[0]+"," + s[3];
		for(int i = 1; i < st.length;i++)
		{
			s = st[i].split(" ");
			str +="," +s[0]+","+s[3];
		}
		return str;
	}

	public Component jumpProfit() throws IOException {
		// TODO Auto-generated method stub
		return new Profit(this);
	}

	public StockModel getStockModel() {
		// TODO Auto-generated method stub
		return model.getStockModel();
	}

	public String getallStockName() {
		// TODO Auto-generated method stub
		return model.getallStockName();
		
	}

	public String getThirtyConsist(int key) {
		// TODO Auto-generated method stub
		return model.getThirtyConsist(key);
	}

	public void changeStockPre() {
		// TODO Auto-generated method stub
			ui.refreshStockPre();
	}
}
