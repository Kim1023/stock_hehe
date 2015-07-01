package test;

import static org.junit.Assert.assertEquals;

import java.util.Vector;

import org.junit.Assert;
import org.junit.Test;

import ClassType.User;
import Dao.DaoR;
import Dao.DaoUthis;

public class DaoUthisTest {
	@Test
	public void updataPersonInfotest()
	{
		DaoR daor = new DaoR();
		int i = 2;
		String info = "dabai 123 123@qq.com";
		DaoUthis d = new DaoUthis();
		d.updatePersonInfo(info,i);
		String str = daor.readPersonInfo(i);
		Assert.assertEquals(str, info);
	}

}
