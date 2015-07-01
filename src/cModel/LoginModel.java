package cModel;

import java.io.File;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

import ClassType.User;
import Controller.LoginController;
import Dao.DaoR;
import Dao.DaoUthis;

public class LoginModel {
	File file;
	Vector<User> userVec;
	LoginController controller;

	public LoginModel()
	{//进行读取信息放到vector<User>中
		userVec = new DaoR().readPersonInfo();
		
		Iterator iter = userVec.iterator();
		while(iter.hasNext())
		{
			User u = (User)iter.next();
			System.out.println(u.getName()+u.getPassword()+u.getEmail());
		}
	}
	
	public boolean searchUser(User user)
	{//查找在vec里面有没有这个用户
		Enumeration enu = userVec.elements();
		User tuser;
		while(enu.hasMoreElements())
		{
			tuser = (User) enu.nextElement();
			if(tuser.equalLoginInfo(user))
				return true;
		}
		return false;
	}
	
	public int searchEmail(User user)
	{
		Iterator iter = userVec.iterator();
		User tuser;
		int i = -1;
		
		while(iter.hasNext())
		{	
			i++;
			tuser = (User)iter.next();
			if(tuser.equalRetieveInfo(user))
			{
				System.out.println(i);
				return i;
			}
		}
		return -1;
	}
	
	public int AddUser(User user)
	{//添加新用户
		userVec.add(user);
		System.out.println(user.toString());
		writedown(user.toString(),(int) userVec.size());
		return userVec.size();
	}
	
	public void changePassword(int num,String password)
	{//在searchEmail获得的num传进来，需要修改密码
		User user = (User)userVec.get(num);
		user.setPassword(password);
		//把修改的密码写回文本
		writedown(user.toString(),num+1);
		//System.out.println(user.getName()+user.getPassword()+user.getEmail());
	}
	
	public void writedown(String s,int i)
	{//把修改写回文件
		DaoUthis d = new DaoUthis();
		d.updatePersonInfo(s,i);
	}

	public boolean searchName(User user) {
		// TODO Auto-generated method stub
		Iterator iter = userVec.iterator();
		User tuser;
		while(iter.hasNext())
		{
			tuser = (User)iter.next();
			if(tuser.equalName(user))
				return true;
		}
		return false;
	}
	
	public int UserNum()
	{
		return userVec.size();
	}
}
