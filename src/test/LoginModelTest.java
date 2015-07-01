package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ClassType.User;
import cModel.LoginModel;

public class LoginModelTest {
	LoginModel model = new LoginModel();
	@Test
	public void searchUserTest()
	{
		User user = new User();
		user.setUserInfo("meizi", "123", "123@qq.com");
		assertEquals(true,model.searchUser(user));
	}
	
	@Test
	public void searchEmailTest()
	{
		User user = new User();
		user.setUserRetieveInfo("meizi", "123@qq.com");
		assert model.searchEmail(user) >= 0;
	}

	@Test
	public void searchNameTest()
	{
		User user = new User();
		user.setName("meizi");
		assertEquals(true,model.searchName(user));
	}
}
