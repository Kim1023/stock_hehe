package Controller;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import ClassType.User;
import ui.Register;
import ui.Retieve;
import ui.Retieve2;
import cModel.LoginModel;

public class RetieveController {

	LoginModel model;
	Retieve retieveui;
	Retieve2 retieve2;
	int i;
	public RetieveController(LoginModel model) {
		// TODO Auto-generated constructor stub
		this.model = model;
		retieveui = new Retieve(this);
		retieveui.getFrame_info().setVisible(true);
	}
	public boolean JudgeEmail(User user) {
		// TODO Auto-generated method stub,判断正确将跳转到retieve2
		if((i = model.searchEmail(user)) > -1)
		{
			retieveui.getFrame_info().setVisible(false);
			retieve2 = new Retieve2(this);
			
			JDesktopPane desktopPane = Main.MainFunction.getDesktopPane();
			desktopPane.add(Retieve2.getFrame_Info()); 
			Retieve2.getFrame_Info().setVisible(true);
			return true;
		}
		return false;
	}
	public void jumpLogin(JInternalFrame frame) {
		// TODO Auto-generated method stub
		frame.setVisible(false);
		new LoginController(model);
		JDesktopPane desktopPane = Main.MainFunction.getDesktopPane();
		LoginModel model = new LoginModel();
  	  	LoginController a = new LoginController(model);
        desktopPane.add(a.ui.frame); 
        a.ui.frame.toFront();
	}
	public void changePs(String password1) {
		// TODO Auto-generated method stub
		model.changePassword(i, password1);
		System.out.print(password1+i+"controller");
	}

}
