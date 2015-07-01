package Main;

import java.awt.EventQueue;

import Controller.LoginController;
import jxl.read.biff.File;
import cModel.LoginModel;
import ui.Login;
import java.awt.BorderLayout; 
import java.awt.Container; 
import java.awt.Dimension; 
import java.awt.FlowLayout; 
import java.awt.Insets; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.ComponentAdapter; 
import java.awt.event.ComponentEvent; 
import java.beans.PropertyVetoException; 

import javax.swing.JButton; 
import javax.swing.JDesktopPane; 
import javax.swing.JFrame; 
import javax.swing.JInternalFrame; 
import javax.swing.JMenuBar; 
import javax.swing.JMenuItem; 
import javax.swing.JPanel; 
import javax.swing.SwingUtilities; 
import javax.swing.plaf.basic.BasicInternalFrameUI; 
public class MainFunction extends JFrame{

	final static JDesktopPane desktopPane = new JDesktopPane(); 
	  
    /** The operation pane with restore and close buttons. */ 
    final JPanel opPane = new JPanel(); 
    
    public static JDesktopPane getDesktopPane(){
    	return desktopPane;
    }
  
    /** 
     * Instantiates a new mDI frame. 
     */ 
    public MainFunction(){ 
      setTitle("优股"); 
      setSize(1000, 700); 
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
   
      final JMenuBar bar = new JMenuBar(); 
      JMenuItem Add = new JMenuItem("add"); 
      Add.addActionListener(new ActionListener(){ 

          public void actionPerformed(ActionEvent e) { 
        	  LoginModel model = new LoginModel();
        	  LoginController a = new LoginController(model);
              desktopPane.add(a.ui.frame); 
              a.ui.frame.toFront();
          } 
    
      }); 
      bar.add(Add); 
      setJMenuBar(bar); 
      Container content = getContentPane(); 
      content.add(desktopPane, BorderLayout.CENTER); 
      setVisible(true); 
      
      
    } 
 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {


		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				new MainFunction ();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}