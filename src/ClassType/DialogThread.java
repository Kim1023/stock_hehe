package ClassType;

import javax.swing.JOptionPane;

public class DialogThread extends Thread{
	private String str = new String();
	
	public DialogThread(String hint)
	{
		str = hint;
	}
	  public void run() {
	      JOptionPane.showMessageDialog(null, str,"提示",JOptionPane.PLAIN_MESSAGE);
	 }
}
