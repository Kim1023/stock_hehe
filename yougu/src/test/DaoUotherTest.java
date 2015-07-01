package test;

import org.junit.Assert;
import org.junit.Test;

import Dao.DaoR;
import Dao.DaoUothers;
import Dao.DaoUthis;
import Dao.DaoW;

public class DaoUotherTest {
	@Test
	public void addtest()
	{
		DaoR daor = new DaoR();
		DaoUothers daoUothers = new DaoUothers();
		
		//daow.writeUserInfo("liujiyang","0 0 0 0 555 0 333 0 900", "300 200 500 45 34 45 22 11 44 90 87 2 3 4 5 6 7 8 9 20 21 22 23 24 25 26 27 28 29 30");
		String name = "liujiyang";
		String data =  "yi 87 293 43 123 33";
		//用户名，股票名，股票信息，第几个标签，及行数
		daoUothers.add(name,"工商银行", data, 1);
		String str = daor.readStock("liujiyang", 1);
		System.out.println(str);
		Assert.assertEquals(data, str);
	}

}
