package ClassType;

public class User {
	private String email;
	private String Name;
	private String password;
	
	public User()	{}
	
	public boolean equalLoginInfo(User user)
	{
		if(this.Name.equals(user.getName())&&
		this.password.equals(user.getPassword()))
			return true;
		return false;
	}
	public boolean equalRetieveInfo(User user)
	{
		if(this.Name.equals(user.getName())&&this.email.equals(user.getEmail()))
			return true;
		return false;
	}

	public void setUserInfo(String Name, String password,String email)
	{
		this.Name = Name;
		this.password = password;
		this.email = email;
	}
	
	public void setUserLoginInfo(String Name,String password)
	{
		this.Name = Name;
		this.password = password;
	}
	
	public void setUserRetieveInfo(String Name,String email)
	{
		this.Name = Name;
		this.email = email;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getName()
	{
		return Name;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public String getEmail()
	{
		return email;
	}

	public void setName(String name) {
		// TODO Auto-generated method stub
		this.Name = name;
	}

	public boolean equalName(User user) {
		// TODO Auto-generated method stub
		if(this.Name.equals(user.getName()))
			return true;
		return false;
	}

	public void setEmail(String email) {
		// TODO Auto-generated method stub
		this.email = email;
	}

	public boolean equals(User user)
	{
		if(Name.equals(user.getName())&&
			password.equals(user.getPassword())&&
			email.equals(user.getEmail()))
			return true;
		return false;
		
	}

	public String toString()
	{
		return Name+" "+password + " " + email;
	}
}
