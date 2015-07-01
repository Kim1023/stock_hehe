package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import ClassType.StockInfo;
import ClassType.UserAccount;
import Controller.MainFrameController;
import cModel.UserInfoModel;

public class UserInfoModelTest {
	UserInfoModel model = new UserInfoModel("liujiyang");
	String str = new String();
	/*@Test
	public void createxlsTest()
	{
		assertEquals(true,model.createxls("dabai"));
	}*//*
	@Test
	public void getUserTest()
	{
		UserAccount user = model.getUser();
		String name = "dabai";
		assertEquals(name,user.getName());
	}*/

	@Test
	public void getNameTest()
	{
		UserAccount user = model.getUser();
		assertEquals("liujiyang",user.getName());
	}
}
