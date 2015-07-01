package chart;

/* ----------------------------
 * StackedXYAreaChartDemo2.java
 * ----------------------------
 * (C) Copyright 2005, by Object Refinery Limited.
 *
 */

import java.awt.Font;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StackedXYAreaRenderer2;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeTableXYDataset;
import org.jfree.data.xy.CategoryTableXYDataset;
import org.jfree.data.xy.TableXYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class StackedXYAreaChart extends ApplicationFrame {

	public String name;
	public StackedXYAreaChart(String title,String str1,String str2, String username) {
		super(title);
		name = username;
		JPanel chartPanel = createDemoPanel(str1,str2);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);
	}

	/**
	 * Creates a dataset.
	 * 
	 * @return a dataset.
	 */
	// 一只股票一个字符串
	
	public TableXYDataset createDataset(String str1, String str2) {
		//CategoryTableXYDataset dataset = new CategoryTableXYDataset();
		TimeTableXYDataset dataset = new TimeTableXYDataset();
		// System.out.println();
		// 获取该格式的当前时间
		// Date now = new Date();
		SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");

		Calendar c = Calendar.getInstance();

		String[][] array = toArray(str1);

		String[] strarray = str2.split(",");
		for (int i = 0; i < 2; i++) {
			System.out.print(strarray[i] + " ");
		}
		String str = new String();
		double day = 0;
		double[][] b = new double[array.length][30];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {

				b[i][j] = Double.parseDouble(array[i][j]);
				System.out.print(array[i][j] + " ");

				//System.out.print(j + " ");
				if (j == 0) {
					c.add(Calendar.DAY_OF_YEAR, -array[0].length);
				}
				else{
					c.add(Calendar.DAY_OF_YEAR, 1);
				}
				Date dt1 = c.getTime();
				String reStr = matter1.format(dt1);
				System.out.print(reStr + " ");
				String[] date = reStr.split("-");
				//str = date[1] + "." + date[2];
				//System.out.print(str + " ");
				System.out.print(Integer.parseInt(date[2]) + "," + Integer.parseInt(date[1]) + "," + Integer.parseInt(date[0]) + ",");
				//day = Double.parseDouble(str);
				int day2 = Integer.parseInt(date[2]);
				int month = Integer.parseInt(date[1]);
				int year = Integer.parseInt(date[0]);
				//dataset.add(day, b[i][j], strarray[i]);
				dataset.add(new Day(day2,month,year), b[i][j], strarray[i]);
				//dataset.add(new Day(14, 2, 2007), 87, "Series 1");
			}
			/*
			for (int m = 1; m < array[i].length; m++) {
				c.add(Calendar.DAY_OF_YEAR, 1);
			}
			*/
		}
		/*
		 * 
		 * for (int i = 0; i < array.length; i++) { for (int j = 0; j <
		 * array[i].length; j++) { System.out.print(array[i][j] + " "); }
		 * System.out.println(); }
		 */

		return dataset;
	}

	public static String[][] toArray(String str) {
		String[] split = str.split(";");
		String[][] array = new String[split.length][];
		// String[][] array2 = new String[split.length][];
		for (int i = 0; i < split.length; i++) {
			array[i] = split[i].split(",");
			//exchange(array[i]);
		}
		return array;

	}
	/*
	public static void exchange(String a[]) {
		int x = a.length / 2;
		for (int i = 0; i < x; i++)
			swap(a, i, (a.length - i - 1));
	}
	*/

	public static void swap(String a[], int x, int y) {
		String tmp = a[x];
		a[x] = a[y];
		a[y] = tmp;
	}

	/**
	 * Creates a chart.
	 * 
	 * @param dataset
	 *            the dataset for the chart.
	 * 
	 * @return a chart.
	 */
	private JFreeChart createChart(TableXYDataset dataset) {

		JFreeChart chart = ChartFactory.createStackedXYAreaChart(
				"持股构成(市直单位：股)", // chart title
				"", // domain axis label
				"", // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, // the plot orientation
				true, // legend
				true, // tooltips
				false // urls
				);
		XYPlot plot = (XYPlot) chart.getPlot();
        DateAxis dateAxis = new DateAxis("Date");
        plot.setDomainAxis(dateAxis);
        dateAxis.setLowerMargin(0.0);
        dateAxis.setUpperMargin(0.0);
        /*
		XYPlot plot = (XYPlot) chart.getPlot();
		StackedXYAreaRenderer2 renderer = new StackedXYAreaRenderer2();
		renderer.setRoundXCoordinates(true);
		renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
		plot.setRenderer(0, renderer);
		*/
		plot.setForegroundAlpha(0.2f);
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 15));// 设置标题字体
		chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 10));

		FileOutputStream fos_jpg = null;
		try {
			// fos_jpg=new FileOutputStream("D:\\ 饼状图.jpg"); //修改路径
			fos_jpg = new FileOutputStream(name+"堆积图.jpg");
			ChartUtilities.writeChartAsJPEG(fos_jpg, chart, 600, 400);
			fos_jpg.close();
		} catch (Exception e) {
		}
		return chart;

	}

	/**
	 * Creates a panel for the demo (used by SuperDemo.java).
	 * 
	 * @return A panel.
	 */
	public JPanel createDemoPanel(String str1,String str2) {
		//String str1 = "200,300,400,500,777,444,333,300;1500,1700,100,400,234,555,1400,888";
		//String str2 = "工商银行,伊利股份";
		JFreeChart chart = createChart(createDataset(str1, str2));
		return new ChartPanel(chart);

	}

	/**
	 * Starting point for the demonstration application.
	 *
	 * @param args
	 *            ignored.
	 */
	public static void main(String[] args) {
		String str1 = "200,300,400,500,777,300,233,400,300,567,245,466,944,566,455,388,955,466,866,833,466,255,588,466,699,366,755,722,477,300;1500,1700,100,400,234,500,200,300,400,500,777,300,233,400,300,567,245,466,944,566,455,388,955,466,866,833,466,255,588,466";
		String str2 = "工商银行,伊利股份";
		StackedXYAreaChart demo = new StackedXYAreaChart("持股构成",str1,str2,"dabai");
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);
	}

}
