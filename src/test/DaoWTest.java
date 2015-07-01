package test;

import static org.junit.Assert.assertEquals;

import java.util.Vector;

import org.junit.Test;

import ClassType.User;
import Dao.DaoW;

public class DaoWTest {

	@Test
	public void addUserInfotest()
	{
		DaoW w = new DaoW();
		assertEquals(true,w.writeUserInfo("liujiyang","56 5 9 6 8 9", "工商银行"));
	}
}
