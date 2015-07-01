package ui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Controller.MainFrameController;
import Controller.StockJPaneController;

public class RecordTable extends JPanel {
	
	  private StockJPaneController controller;
	  private String[] columnNames={"股票名", "股票编码", "日期","操作","价格","买卖股数","删除操作"};
	  Object[][] rowData=
		  {
		  };
	  DefaultTableModel tableModel = new DefaultTableModel(rowData,columnNames){  
	      @Override  
	      public boolean isCellEditable(int row,int column){  
	          return false;  
	      }  
	  }; 
	  JTable jTable1 = new JTable(tableModel);
	  public  RecordTable(String[][] sresult,int total,StockJPaneController con) 
	  {
		  this.controller = con;
		  for(int i=0;i<total;i++)
		  {
			  String[] row = new String[7];
			  for(int j = 0;j < 6;j++)
			  {
				  if(j != 3)
					  row[j] = sresult[i][j];
				  else
				  {
					  if(sresult[i][3].equals("0"))
						  row[j] = "买入";
					  else if(sresult[i][3].equals("1"))
						  row[j] = "卖出";
					  else if(sresult[i][3].equals("3"))
						  row[j] = "补仓";
					  else
						  row[j] = "卖空";
				  }
			  }
			  row[6] = "删除";
			//  System.out.println(row[0]+" "+row[1]+" "+row[2]+" "+row[3]+" "+row[4]+" "+row[5]);
			  tableModel.addRow(row);	
			//  System.out.println(tableModel.getRowCount());

		  }
		  this.setLayout(new BorderLayout());
		  add(new JScrollPane(jTable1),
	      BorderLayout.CENTER);
		  jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		  
		  jTable1.addMouseListener(new MouseAdapter()
		  {
			  public void mouseClicked(MouseEvent e)
			  {
				  
				  int row =((JTable)e.getSource()).rowAtPoint(e.getPoint()); //获得行位置 
	              int col=((JTable)e.getSource()).columnAtPoint(e.getPoint()); //获得列位置
	              String sname;
	              if(col == 6)
	              {
	            	  //删除指定的record
	            	 controller.delectRecord(row);//删除的行数
	            	 controller.changeStockPre();
	              }
			  }
		  });
	  }
}
