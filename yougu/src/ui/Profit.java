package ui;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

import org.jfree.data.xy.XYDataset;

import cModel.Yield;
import chart.TimeSeriesChart;
import ClassType.UserAccount;
import Controller.MainFrameController;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

public class Profit extends JPanel {

	MainFrameController controller;
	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public Profit(MainFrameController mainFrameController) throws IOException {
		// TODO Auto-generated constructor stub
		setBackground(Color.WHITE);
		setLayout(null);
		controller = mainFrameController;
		
		JLabel label = new JLabel("收益率");
		label.setFont(new Font("宋体", Font.BOLD, 14));
		label.setForeground(Color.BLACK);
		label.setBounds(10, 10, 78, 15);
		add(label);
		
		JPanel panel = new JPanel();
		
		
		String st = new Yield().yield(controller.getStockModel(),controller.getUser());
		//System.out.println("rate[0]=" + rate[0]);
		/*String str = "2015,10,1,200,2015,10,2,300,2015,10,3,"
		 		+ "400,2015,10,4,200,2015,10,5,600,2015,10,6,100,2015,10,7,800,2015,10,"
		 		+ "8,500,2015,10,9,800,2015,10,10,300,2015,10,11,800,2015,10,12,500,2015,10,13,"
		 		+ "800,2015,10,14,800,2015,10,15,600,2015,10,16,800,2015,10,17,100,2015,10,18,200,"
		 		+ "2015,10,19,400,2015,10,20,300,2015,10,21,800,2015,10,22,800,2015,10,23,400,2015,10,"
		 		+ "24,300,2015,10,25,300";
	 	*/
		
		 TimeSeriesChart timeSeriesChart = new TimeSeriesChart();
		    //添加折线图的参数。年，月，日，收益，年，月，日，收益，年，月，日，收益，这样的字符串
		 XYDataset xyDataset = timeSeriesChart.createDataset(st);
		 timeSeriesChart.createTimeSeries(xyDataset,controller.getUserName());  
		 
		 JLabel	lb = new JLabel();
		 lb.setIcon(new ImageIcon(ImageIO.read(new File(controller.getUserName() + "折线图.jpg"))));
		 panel.add(lb);
		 panel.setBounds(0, 30, 637, 449);
		 add(panel);
	}
}
