package Controller;

import javax.swing.JDesktopPane;

import ClassType.User;
import ui.Login;
import ui.MainFrame;
import ui.Register;
import ui.Retieve;
import cModel.LoginModel;
import cModel.UserInfoModel;

public class LoginController {
	LoginModel model;
	public Login ui;
	
	public LoginController(LoginModel model)
	{
		this.model = model;
		ui = new Login(this);
		//ui.getFrame_info().setVisible(true);
		//ui.getFrame_info().toFront();
	}
	
	public boolean JudgeUserInfo(User user)
	{
		if(model.searchUser(user))
		{
			ui.getFrame_info().setVisible(false);
			UserInfoModel userInfomodel = new  UserInfoModel(user.getName());
			
			MainFrameController mainframecontroller = new MainFrameController(userInfomodel);
			JDesktopPane desktopPane = Main.MainFunction.getDesktopPane();
			desktopPane.add(mainframecontroller.ui.frame); 
			mainframecontroller.ui.frame.toFront();
			mainframecontroller.ui.frame.setVisible(true);
			return true;
		}
		return false;
	}
	
	private UserInfoModel UserInfoModel(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public void JumpRegister()
	{
		ui.getFrame_info().setVisible(false);
		new RegisterController(model);
		JDesktopPane desktopPane = Main.MainFunction.getDesktopPane();
		desktopPane.add(Register.getFrame_info()); 
	}
	
	public void JumpRetive()
	{
		ui.getFrame_info().setVisible(false);
		new RetieveController(model);
		JDesktopPane desktopPane = Main.MainFunction.getDesktopPane();
		desktopPane.add(Retieve.getFrame_info()); 
	}
}
