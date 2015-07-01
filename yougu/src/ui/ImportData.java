package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ImportData extends JDialog {

	/**
	 * Launch the application.
	 */
	private MouseHandle handle = new MouseHandle();
	private JButton okButton,cancelButton;
	public static void main(String[] args) {
		try {
			ImportData dialog = new ImportData();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ImportData() {
		setBounds(100, 100, 363, 203);
		
		getContentPane().setLayout(null);
		
		{
			JLabel lblExcel = new JLabel("excel表格的地址：");
			lblExcel.setBounds(10, 10, 108, 54);
			getContentPane().add(lblExcel);
		}
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 132, 347, 33);
		getContentPane().add(buttonPane);
		
		buttonPane.setLayout(null);
		{
			okButton = new JButton("确定");
			okButton.setBounds(98, 5, 69, 23);
			okButton.setActionCommand("OK");
			okButton.addMouseListener(handle);
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		}
		{
			cancelButton = new JButton("取消");
			cancelButton.setBounds(177, 5, 69, 23);
			cancelButton.addMouseListener(handle);
			cancelButton.setActionCommand("Cancel");
			buttonPane.add(cancelButton);
		}
		
		JLabel lblFilestockinfo = new JLabel("File/stockinfo.......正在导入");
		lblFilestockinfo.setBounds(20, 74, 243, 15);
		getContentPane().add(lblFilestockinfo);
		
		
	}
	
	private class MouseHandle extends MouseAdapter
	{
		public void mouseClicked(MouseEvent e)
		{
			if(e.getSource() == okButton)
			{
				//导入数据
				setVisible(false);
			}
			else
			{
				setVisible(false);
			}
		}
	}
}
