package Dao;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
/**
 * 利用jExcelAPI可以修改已有的Excel文件，修改Excel文件的时候
 * 除了打开文件的方式不同之外，其他操作和创建Excel是一样的
 * */
public class DaoUthis {
	
	//修改sheet内的数据； 传入修改的字符串，sheet号，行号
	public void updateStock(String name,String str,int i,int j){
		try{
			String[] strarray1=str.split(" "); 
			String filePath = "File/" + name + "_personInfo.xls";
		
			//打开文件
			Workbook wb = Workbook.getWorkbook(new File(filePath));
			//打开一个文本的副本，并指定数据写回到源文件
			WritableWorkbook book = Workbook.createWorkbook(new File(filePath),wb);
			
			WritableSheet sheet = book.getSheet(i); 
			
           
			for(int m=0;m<strarray1.length;m++){ 
                Label label = new Label(m,j,strarray1[m]); 
                sheet.addCell(label);
         	} 
                  
                   
			 
			book.write();
			book.close();
			wb.close();
		} catch(Exception e){   
	           e.printStackTrace();   
	       }  
	
	}
	
	
		//删除行
		//用户名，第几个标签，及行数
		public void subRow(String name,int i,int j){
			try{
			
				//打开文件
				String filePath = "File/" + name + "_personInfo.xls";
				Workbook wb = Workbook.getWorkbook(new File(filePath));
				//打开一个文本的副本，并指定数据写回到源文件
				WritableWorkbook book = Workbook.createWorkbook(new File(filePath),wb);
				
				WritableSheet sheet = book.getSheet(i); 
				
				sheet.removeRow(j);
	          		
				book.write();
				book.close();
				wb.close();
			} catch(Exception e){   
		           e.printStackTrace();   
		       }  
		
		}
		
		//修改用户信息   i为行数
		public void updatePersonInfo(String str,int i){
			try{
				String[] strarray1=str.split(" "); 
			
				//打开文件
				Workbook wb = Workbook.getWorkbook(new File("File/personInfo.xls"));
				//打开一个文本的副本，并指定数据写回到源文件
				WritableWorkbook book = Workbook.createWorkbook(new File("File/personInfo.xls"),wb);
				
				WritableSheet sheet = book.getSheet(0); 
				
	           
				for(int m=0;m<strarray1.length;m++){ 
	                Label label = new Label(m,i,strarray1[m]); 
	                sheet.addCell(label);
	         	} 
	                  
	                   
				 
				book.write();
				book.close();
				wb.close();
			} catch(Exception e){   
		           e.printStackTrace();   
		       }  
		
		}
		
		public void UpdateUserInfo(String name,String str1,String str2){
				try{
					String[] strarray1 = str1.split(" ");
					String[] strarray2 = str2.split(" ");
					
					//打开文件
					String filePath = "File/" + name + "_personInfo.xls";
					Workbook wb = Workbook.getWorkbook(new File(filePath));
					//打开一个文本的副本，并指定数据写回到源文件
					WritableWorkbook book = Workbook.createWorkbook(new File(filePath),wb);
					
					WritableSheet sheet = book.getSheet(0); 
					
					//sheet.addCell(new Label(0,0,"第二页数据"));
					String[] title1 = {"盈亏值","盈亏率","总资产","现金","市值","本金"}; 
					String title2 = "股票";
					for(int j=0;j<title1.length;j++){ 
		                   Label label1 = new Label(j,0,title1[j]); 
		                   sheet.addCell(label1);
		            	}
					Label label2 = new Label(0, 2, title2);
					sheet.addCell(label2);
					
					//写入账户信息
					for(int m=0;m<strarray1.length;m++){ 
		                Label label3 = new Label(m,1,strarray1[m]); 
		                sheet.addCell(label3);
		         	} 
					int n = strarray2.length/2;
					Label label3 = new Label(1, 2, String.valueOf(n));
					sheet.addCell(label3);
					//写入持有的股票名和编号
					for(int m=3,j = 0; m<n+3 ;m++,j+=2){ 
		                Label label4 = new Label(0,m,strarray2[j]);
		                Label label5 = new Label(1,m,strarray2[j+1]);
		                sheet.addCell(label4);
		                sheet.addCell(label5);
		         	} 
					 
					book.write();
					book.close();
					wb.close();
				} catch(Exception e){   
			           e.printStackTrace();   
			       }  
			
			}
		
}
