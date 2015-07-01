package Controller;

import javax.swing.JDesktopPane;

import ClassType.User;
import ui.Register;
import ui.Retieve2;
import cModel.LoginModel;

public class RegisterController {

	LoginModel model;
	Register registerui;
	public RegisterController(LoginModel model) {
		// TODO Auto-generated constructor stub
		this.model = model;
		registerui = new Register(this);
		registerui.getFrame_info().setVisible(true);
	}
	public boolean SearchName(User user) {
		// TODO Auto-generated method stub
		return model.searchName(user);
	}
	public void addUser(User user) {
		// TODO Auto-generated method stub
		model.AddUser(user);
		jumpLogin();
	}
	public void jumpLogin() {
		// TODO Auto-generated method stub
		//registerui.getFrame_info().setVisible(false);
		//new LoginController(model);
		new LoginController(model);
		JDesktopPane desktopPane = Main.MainFunction.getDesktopPane();
		LoginModel model = new LoginModel();
  	  	LoginController a = new LoginController(model);
        desktopPane.add(a.ui.frame); 
        a.ui.frame.toFront();
		//Retieve2.getFrame_Info().setVisible(true);
		Register.getFrame_info().setVisible(false);
	}

}
