package Dao;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class DaoW {
	  
      // 创建Excel工作薄   
       
	public void writePersonInfo(String str){
		
	      String[] strarray=str.split(" "); 
	     
	      long start = System.currentTimeMillis();   
	      // 输出的excel的路径   
	      String filePath = "File/personInfo.xls";   
		
        // 准备设置excel工作表的标题   
	    	 String[] title = {"用户名","密码","邮箱"};   
        try {   
            // 获得开始时间   
        	 WritableWorkbook wwb;   
   	      // 新建立一个jxl文件,即在C盘下生成test.xls   
   	      	OutputStream os = new FileOutputStream(filePath);  
   	      
   	      	wwb=Workbook.createWorkbook(os); 
          
	            // 添加第一个工作表并设置第一个Sheet的名字   
	        WritableSheet sheet = wwb.createSheet("用户信息", 0);   
            
            
            //写入信息
            //获取用户总数
            //PersonInfo p = new PersonInfo();
            
            
            	//for(int j=0;j<title.length;j++){   
                    // Label(x,y,z)其中x代表单元格的第x+1列，第y+1行, 单元格的内容是y   
                    // 在Label对象的子对象中指明单元格的位置和内容   
            	for(int j=0;j<title.length;j++){ 
                   Label label = new Label(j,0,title[j]); 
                   sheet.addCell(label);
            	}
                    // 将定义好的单元格添加到工作表中   
                    //sheet.addCell(label);  
                    
            	//需要获取用户的总数m
            	//使下面的i = m + 1;
            	
                    Label label1 = new Label(0,1,strarray[0]);   //姓名
                    Label label2 = new Label(1,1,strarray[1]);   //密码
                    Label label3 = new Label(2,1,strarray[2]);   //邮箱
                    sheet.addCell(label1);
                    sheet.addCell(label2);
                    sheet.addCell(label3);  
                    
                    
      
               
            // 写入数据   
            wwb.write();   
            // 关闭文件   
            wwb.close();   
            long end = System.currentTimeMillis();   
            System.out.println("----完成该操作共用的时间是:"+(end-start)/1000);   
        } catch (Exception e) {   
            System.out.println("---出现异常---");   
            e.printStackTrace();   
        }   
    }   
	
	
	
	/*
	//写入的，股票名，股票信息，第几个标签，及行数
	public void writeStock(String name,String str1,String str2,int i,int j){
		//写入的，股票名，股票信息，第几个标签，及行数
			try{
				String[] strarray1=str2.split(" ");
				
				String filePath = "c:\\" + name + "_personInfo.xls";   
			
				//打开文件
				
				OutputStream os = new FileOutputStream(filePath);  
			      
				WritableWorkbook book = Workbook.createWorkbook(os);
				
				//WritableSheet sheet = book.getSheet(i); 
				WritableSheet sheet = book.createSheet(str1, i);
				//sheet.addCell(new Label(0,0,"第二页数据"));
				
				for(int m=0;m<strarray1.length;m++){ 
	                Label label = new Label(m,j,strarray1[m]); 
	                sheet.addCell(label);
	         	} 
				 
				book.write();
				book.close();
			} catch(Exception e){   
		           e.printStackTrace();   
		       }  
		
		}
		*/
	
	// 第一个参数：用户名，第二个参数：用户信息（本金等在第一个标签），第三个参数：账户持有股票（数组）
		public boolean writeUserInfo(String name,String str1,String str2){
			
			  String filePath = "File/"+ name + "_personInfo.xls";   
			  
			  String[] strarray= str1.split(" "); 
			  String[] strstock = str2.split(" ");
			  System.out.println(str2);
		      // 准备设置excel工作表的标题   
			  String[] title = {"盈亏值","盈亏率","账号总资产","现金","市值","本金"}; 
			  
		      try {   
		          // 获得开始时间   
		      	 WritableWorkbook wwb;   
		 	      // 新建立一个jxl文件,即在C盘下生成test.xls   
		 	     OutputStream os = new FileOutputStream(filePath);  
		 	      
		 	     wwb=Workbook.createWorkbook(os); 
		        
			     // 添加第一个工作表并设置第一个Sheet的名字   
			     WritableSheet sheet = wwb.createSheet("用户信息", 0);   

		            // 在Label对象的子对象中指明单元格的位置和内容   
		          	for(int j=0;j<title.length;j++){ 
		                 Label label = new Label(j,0,title[j]); 
		                 sheet.addCell(label);
		          	}
		             
		                  
		          	//需要获取用户的总数m
		          	//使下面的i = m + 1;
		          	for(int j = 0;j < 6;j++)
		          	{
		          		Label label1 = new Label(j,1,strarray[j]);   
		                sheet.addCell(label1);
		          	}
		            
		     
	                Label label = new Label(0,2,"关注股票");   
	                sheet.addCell(label);
	                
	                label = new Label(1,2,String.valueOf(strstock.length/2));
	                sheet.addCell(label);
	                System.out.println(String.valueOf(strstock.length));
	                for(int j = 0,m = 0;j < strstock.length&& strstock.length > 1;j +=2,m++)
	                {
	                	Label label1 = new Label(0,3+m,strstock[j]);   
	                	Label label2 = new Label(1,3+m,strstock[j+1]);
		                sheet.addCell(label1);
		                sheet.addCell(label2);
	                }
	                
		          // 写入数据   
		          wwb.write();   
		          // 关闭文件   
		          wwb.close();   
		          return true;
		      } catch (Exception e) {   
		          System.out.println("---出现异常---");   
		          e.printStackTrace();
		          return false;
		      }   
			}
}
		
		

  


