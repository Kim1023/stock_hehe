package Dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

//修改不同标签内的内容
public class DaoUothers {
	//增加标签,即增加股票
	//用户名，股票名，股票信息，第几个标签，及行数
	public void add(String name,String str1,String str2,int i){
		try{
			String[] strarray1=str2.split(" ");
		
			//打开文件
			String filePath = "File/" + name + "_personInfo.xls";
			Workbook wb = Workbook.getWorkbook(new File(filePath));
			//打开一个文本的副本，并指定数据写回到源文件
			WritableWorkbook book = Workbook.createWorkbook(new File(filePath),wb);
			
			
			 //这里有两种方法获取sheet表:名字和下标（从0开始）   
			  
            //Sheet st = rwb.getSheet("original");   
            //Sheet st = rwb.getSheet(0); 
			
			 
			WritableSheet sheet = book.createSheet(str1, i);
			//sheet.addCell(new Label(0,0,"第二页数据"));
			 
		
          
          
			for(int m=0;m<strarray1.length;m++){ 
                Label label = new Label(m,0,strarray1[m]); 
                sheet.addCell(label);
         	}
			 
			book.write();
			book.close();
			wb.close();
		} catch(Exception e){   
	           e.printStackTrace();   
	       }  
	
	}
	
	
	
	//删除sheet
	//用户名，第几个sheet
	public void subSheet(String name,int i){
		try{
			//打开文件
			String filePath = "File/" + name + "_personInfo.xls";
			Workbook wb = Workbook.getWorkbook(new File(filePath));
			//打开一个文本的副本，并指定数据写回到源文件
			WritableWorkbook book = Workbook.createWorkbook(new File(filePath),wb);
		
			
			
			book.removeSheet(i);
			
			
			book.write();
			book.close();
			wb.close();
		} catch(Exception e){   
	           e.printStackTrace();   
	    }  
	
	}
}
