package chart;

import java.awt.Font;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class TimeSeriesChart {
	ChartPanel frame1;

	// private String[] strarray1;
	// ={"2015.10.2","2015.10.3","2015.10.4"};
	public void createTimeSeries(XYDataset xydataset,String username) {
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("股票收益",
				"", "", xydataset, true, true, true);
		XYPlot xyplot = (XYPlot) jfreechart.getPlot();
		DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
		dateaxis.setDateFormatOverride(new SimpleDateFormat("MM-dd"));

		// XYPlot plot = jfreechart.getXYPlot();
		// XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot
		// .getRenderer();
		// renderer.setBaseItemLabelGenerator(new
		// StandardXYItemLabelGenerator());
		// renderer.setBaseItemLabelsVisible(true);

		frame1 = new ChartPanel(jfreechart, true);
		dateaxis.setLabelFont(new Font("楷体", Font.BOLD, 14)); // 水平底部标题
		dateaxis.setTickLabelFont(new Font("楷体", Font.BOLD, 12)); // 垂直标题
		ValueAxis rangeAxis = xyplot.getRangeAxis();// 获取柱状
		rangeAxis.setLabelFont(new Font("楷体", Font.BOLD, 15));
		jfreechart.getLegend().setItemFont(new Font("楷体", Font.BOLD, 15));
		jfreechart.getTitle().setFont(new Font("楷体", Font.BOLD, 20));// 设置标题字体

		// 存放图片
		FileOutputStream fos_jpg = null;
		try {
			//fos_jpg = new FileOutputStream("D:\\ 折线图.jpg");
			fos_jpg = new FileOutputStream(username+"折线图.jpg");
			ChartUtilities.writeChartAsJPEG(fos_jpg, jfreechart,600, 400);
			fos_jpg.close();
		} catch (Exception e) {
		}
	}

	public XYDataset createDataset(String str) { 
		String[] strarray = str.split(",");
		// int n = 0;
		/*
		 * for(int m = 0; m < strarray.length; m = m + 2){ strarray1 =
		 * strarray[m].split("."); }
		 */

		TimeSeries timeseries = new TimeSeries("盈亏");

		for (int i = 0; i < strarray.length; i = i + 4) {
			timeseries.add(
					new Day(Integer.parseInt(strarray[i + 2]), Integer
							.parseInt(strarray[i + 1]), Integer
							.parseInt(strarray[i])), 
							Double.valueOf(strarray[i + 3]));

		}
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		timeseriescollection.addSeries(timeseries);
		return timeseriescollection;
	}
	
	//返回frame
	public ChartPanel getChartPanel() {
		return frame1;
	}
}
