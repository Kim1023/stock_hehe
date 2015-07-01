package Controller;

import ClassType.StockInfo;
import ClassType.ThirtyDayProfit;
import Dao.DaoUthis;
import ui.HoldCon;
import ui.SaleAndBuy;
import ui.StockJPane;
import urldemo.StocksPrice;
import cModel.UserInfoModel;

public class StockJPaneController {

	UserInfoModel model;
	String name;//股票名
	MainFrameController controller;
	StockInfo info;
	StockJPane stockui;
	SaleAndBuy saleDialog;
	HoldCon holdDialog;
	//股票名，
	public StockJPaneController(UserInfoModel model,String name, MainFrameController controller) {
		// TODO Auto-generated constructor stub
		this.model = model;
		this.name = name;
		this.controller = controller;
		info = new StockInfo(model.getStockInfo(name));
		createJPane(0);
	}

	public ThirtyDayProfit getThirtyDayProfit()
	{
		return controller.getThirtyDayProfit();
	}
	//key是新股票在共享池中的位置
	public StockJPaneController(UserInfoModel model, String name,
			MainFrameController controller, int key) {
		// TODO Auto-generated constructor stub
		this.model = model;
		this.name = name;
		this.controller = controller;
		String str;
		String[] st = new String[6];
		str = StocksPrice.stocks[key].name + " "+ StocksPrice.StockNums[key] + " 0" + " 0"+" 0"+" 0";
		info = new StockInfo(str,1);
		createJPane(1);
	}

	public void createJPane(int i)
	{
		if(i == 0)
			stockui = new StockJPane(this);
		else
			stockui = new StockJPane(this,1);
	}

	public StockInfo getStorkInfo()
	{
		return info;
	}
	public StockJPane getJPane()
	{
		return stockui;
	}

	public SaleAndBuy createSaleDialog(int key) {
		// TODO Auto-generated method stub
		saleDialog = new SaleAndBuy(this,key);	
		return saleDialog;
	}

	public String getStockBase() {
		// TODO Auto-generated method stub
		 return info.getStockInfo();
	}

	public String[][] getStockRecord() {
		// TODO Auto-generated method stub
		String[] str = info.getStockRecord();
		String[][] st = new String[str.length][6];
		for(int i = 0; i< str.length;i++)
		{
			st[i] = str[i].split(" ");
		}
		return st;
	}

	public HoldCon createHoldDialog(int key) {
		// TODO Auto-generated method stub
		holdDialog = new HoldCon(this,key);
		return  holdDialog;
	}

	//删除特定的行数
	public void delectRecord(int row) {
		// TODO Auto-generated method stub
		//要在stockInfo中删除，同时写入特定的sheet中，且在持仓对话框要删掉,删除新的持股数和价格
		String str = info.getStockRecord(row);
		
		model.delectRecord(info.getName(),row);
		//逆运算
		model.calcash(str);
		//需要更新holdDialog和主界面还有model里的
		controller.refreshUi();
		info = new StockInfo(model.getStockInfo(name));
		holdDialog.refresh();
		
	}

	public void SaleRecord(String str) {
		// TODO Auto-generated method stub
		//需要往info中记录消息，需要更新saleandbuy的table，需要更新mainframe，需要更新持股情况
		String[] s = info.getStockInfo().split(" ");
		str = s[0] +","+ s[1] +","+ str;
		
		System.out.println(str);
		//将股票的交易记录写到响应股票的记录中。
		model.addStockRecord(str);
		//只有在现实的股票中才能改变现金
		model.calcash(str);
		controller.refreshUi();
		info = new StockInfo(model.getStockInfo(name));
		System.out.println("refreshui");
		saleDialog.refreshUi();
	}

	public boolean cancelFocus() {
		// TODO Auto-generated method stub
		//取消关注时，必须持股数==0，然后在文档中删除该股票，在stockmodel中也删除该股票，还有在userInfo中也要删除，在ui上也要删除
		int num = info.getHoldNum();
		if(num != 0)
		{
			return false;
		}
		//在model中完成股票在文档中的删除，同时也删除了stockmodel中的股票和userinfo中的股票,删除暂存股票中的交易记录。
		/*for(int  i = 0;i < info.getRecordNum();i++)
		{
			info.delectRecord(i);
		}*/
		
		model.removeStock(info.getName());
		//在mainframecontroller上完成对用户账号信息和持股情况的更新
		controller.refreshUi();
		return true;
	}

	public void focusStock() {
		// TODO Auto-generated method stub
		//添加股票，到用户类中，添加股票到stockmodel中，添加股票到那个文档中，界面上进行显示。
		//重新构建一个info。
		String str = info.getStockInfo();
		String[] st = str.split(" ");
		str = st[0] + " "+st[1] + " 0" + " 0"+" 0"+" 0";
		info = new StockInfo(str,1);
		model.addStock(info);
		//在mainframecontroller上完成对用户账号信息和持股情况的更新
		controller.refreshUi();
	}

	public boolean isFocus() {
		// TODO Auto-generated method stub
		if(stockui.isFocus())
			return true;
		return false;
	}

	public void changeStockPre() {
		// TODO Auto-generated method stub
		controller.changeStockPre();
	}

	public double getCash() {
		// TODO Auto-generated method stub
		return model.getUser().getCash();
	}
}
