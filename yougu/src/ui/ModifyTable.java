package ui;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.table.*;

import Controller.MainFrameController;

class ModifyTable extends JPanel 
{
  private MainFrameController controller;
  private String[] columnNames={"股票名", "股票编码", "成本价","持股","总价","盈亏值","现价","市值"};
  String[][] toMainFrameshow;
  Object[][] rowData=
	  {
	  };
  DefaultTableModel tableModel = new DefaultTableModel(rowData,columnNames){  
      @Override  
      public boolean isCellEditable(int row,int column){  
          return false;  
      }  
  }; 
  // Create a table  [0.��Ʊ����][1����Ʊ���][2.�ǵ��][3.�ּ�][4.��ӯ��][5.Ԥ����ӯ��][6.�о���]
  JTable jTable1 = new JTable(tableModel);
  
  public  ModifyTable(String[][] sresult,String[] pc,int total,MainFrameController con) 
  {
	  toMainFrameshow = new String[total][2]; //记录每个股票的盈亏值和市值
	  controller = con;
	  for(int i=0;i<total;i++)
	  {
		  double marketCap = Double.valueOf(pc[i])*Integer.valueOf(sresult[i][3]);
		 
		  toMainFrameshow[i][0] = sresult[i][5];
		  toMainFrameshow[i][1] = String.valueOf(marketCap);
		  String[] row={sresult[i][0],sresult[i][1],format(sresult[i][2]),sresult[i][3],format(sresult[i][4]),
				  format(sresult[i][5]),format(pc[i]),format(String.valueOf(marketCap))};
		//  System.out.println(row[0]+" "+row[1]+" "+row[2]+" "+row[3]+" "+row[4]+" "+row[5]);
		  tableModel.addRow(row);	
		//  System.out.println(tableModel.getRowCount());

	  }
	  
	  //将toMainFrameshow送出到ui上
	  controller.changeUI(toMainFrameshow);
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
              if(col == 0)
              {
            	  sname = getValueAt(row, col);//获得股票名
 
            	  if(!controller.jPaneExist(sname))
            		  controller.createStockPanel(sname);
              }
		  }
	  });
  }
  
  public ModifyTable(MainFrameController con)
  {
	  controller  =con;
	  this.setLayout(new BorderLayout());
	  JScrollPane jtable = new JScrollPane(jTable1);
	  jtable.setBackground(Color.WHITE);
	  add(jtable, BorderLayout.CENTER);
	  jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
	  jTable1.addMouseListener(new MouseAdapter()
	  {
		  public void mouseClicked(MouseEvent e)
		  {
			
		  }
	  });
  }

  public  ModifyTable(String[][] sresult,String price,int total) 
  {
	  for(int i=0;i<total;i++)
	  {
		  
		  double marketCap = Double.valueOf(price)*Integer.valueOf(sresult[i][3]);
		  String[] row={sresult[i][0],sresult[i][1],format(sresult[i][2]),sresult[i][3],format(sresult[i][4]),
				  format(sresult[i][5]),format(price),format(String.valueOf(marketCap))};
		//  System.out.println(row[0]+" "+row[1]+" "+row[2]+" "+row[3]+" "+row[4]+" "+row[5]);
		  tableModel.addRow(row);	
		//  System.out.println(tableModel.getRowCount());

	  }
	  this.setLayout(new BorderLayout());
	  add(new JScrollPane(jTable1),
      BorderLayout.CENTER);
	  //add(jTable1);
	  jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
  }
  
  

public String getValueAt(int row, int col) {
	// TODO Auto-generated method stub
	String str = (String)tableModel.getValueAt(row, col);
	return str;
  }


	private String format(String st)
	{
		DecimalFormat df=new DecimalFormat(".##");
		double a = Double.valueOf(st);
		return df.format(a);
	}
}