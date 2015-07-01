package ui;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JButton;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import chart.StackedXYAreaChart;
import Controller.MainFrameController;
import javax.imageio.*;
import java.io.*;

public class StockPre extends JPanel {


	/**
	 * Create the panel.
	 */
	MainFrameController controller;
	public StockPre(MainFrameController controller) throws IOException  {
		setBackground(Color.WHITE);
		
		this.controller = controller;
		setLayout(null);
		
		JLabel label = new JLabel("持股构成");
		label.setFont(new Font("宋体", Font.BOLD, 20));
		label.setBounds(10, 20, 141, 24);
		add(label);
		
		
		//JFrame frame=new JFrame("Java数据统计图");  
		//frame.setLayout(new GridLayout(2,2,10,10));  
		    //frame.add(new BarChart().getChartPanel());           //添加柱形图  
		    //frame.add(new BarChart1().getChartPanel());          //添加柱形图的另一种效果  
		    //frame.add(new PieChart().getChartPanel());           //添加饼状图  
		
		 StackedXYAreaChart demo = new StackedXYAreaChart("持股构成", controller.getThirtyConsist(1),
		controller.getallStockName(),controller.getUserName());
		 
		
		JLabel	lb = new JLabel();
		lb.setIcon(new ImageIcon(ImageIO.read(new File(controller.getUserName() + "堆积图.jpg"))));
		 
		 /*//生成饼状图
		  PieChart pieChart = new PieChart();
		  //设置饼状图的参数。a股票名，股数，b股票名，股数.....
		  DefaultPieDataset data = pieChart.getDataSet(controller.getStockPre());
		  pieChart.createPie(data);               
		  */
		  JPanel panel = new JPanel();
		  panel.setBounds(10, 54, 627, 434);
		  panel.add(lb);
		  add(panel);
		  validate();
	}
}
