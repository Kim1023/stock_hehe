package Dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import ClassType.User;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
public class DaoR {
	
	public Vector<User> readPersonInfo() {
    	try{
        // 1、构造excel文件输入流对象
    	Vector<User> userVec = new Vector<User>();
    	
        String sFilePath = "File/personInfo.xls";
        InputStream is = new FileInputStream(sFilePath);
        // 2、声明工作簿对象
        Workbook rwb = Workbook.getWorkbook(is);
        // 3、获得工作簿的个数,对应于一个excel中的工作表个数
        rwb.getNumberOfSheets();

        Sheet oFirstSheet = rwb.getSheet(0);// 使用索引形式获取第一个工作表，也可以使用rwb.getSheet(sheetName);其中sheetName表示的是工作表的名称
//        System.out.println("工作表名称：" + oFirstSheet.getName());
        int rows = oFirstSheet.getRows();//获取工作表中的总行数
        //System.out.println(rows);
        int columns = oFirstSheet.getColumns();//获取工作表中的总列数
        //System.out.println(columns);
        
        
        	for (int i = 1; i < rows; i++) { 
        		User p = new User();
                Cell cell1 = oFirstSheet.getCell(0,i);//需要注意的是这里的getCell方法的参数，第一个是指定第几列，第二个参数才是指定第几行  
                String result1 = cell1.getContents();
                p.setName(result1);
                Cell cell2 = oFirstSheet.getCell(1,i);//需要注意的是这里的getCell方法的参数，第一个是指定第几列，第二个参数才是指定第几行  
                String result2 = cell2.getContents();
                p.setPassword(result2);
                Cell cell3 = oFirstSheet.getCell(2,i);//需要注意的是这里的getCell方法的参数，第一个是指定第几列，第二个参数才是指定第几行  
                String result3 = cell3.getContents();
                p.setEmail(result3);
                userVec.add(p);
                System.out.println(result1); 
        		}  
           rwb.close();
           
          
           return userVec;
    	}
        catch (Exception e) {   
            System.out.println("---出现异常---");   
            e.printStackTrace();   
        }
		return null;
    }
    
    
    //读取股票信息，i为sheet号,i从1开始
    public String readStock(String name,int i){
    	try {  
            //打开文件  
    		String filePath = "File/" + name + "_personInfo.xls";
            Workbook book = Workbook.getWorkbook(new File(filePath));  
            //获得第一个表的工作对象，“0”表示第一个表  
            Sheet sheet = book.getSheet(i);  
            //得到第一列，第一行的单元格（0，0）  
            int columns = sheet.getColumns();
            String string = new String();
            for (int m = 0; m < columns; m++) {
				Cell cell1 = sheet.getCell(m, 0);// 需要注意的是这里的getCell方法的参数，第一个是指定第几列，第二个参数才是指定第几行
				String str = cell1.getContents();
				if(m != columns-1){
					string = string + str + " ";
				}
				if(m == columns-1){
					string = string + str;
				}
			}
			System.out.println(string);
        	
            book.close();
            return string;
            
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (BiffException e) {  
            e.printStackTrace();  
        }
		return null;  
		
    } 
    //读取股票信息，参数为用户名
    public String readUserInfo(String name){
    	try {  
            //打开文件  
    		String filePath = "File/" + name + "_personInfo.xls";
            Workbook book = Workbook.getWorkbook(new File(filePath));  
            //获得第一个表的工作对象，“0”表示第一个表  
            Sheet sheet = book.getSheet(0);  
            //得到第一列，第一行的单元格（0，0）  
         
            String string = new String();
            for (int m = 0; m < 6; m++) {
                Cell cell1 = sheet.getCell(m,1);//需要注意的是这里的getCell方法的参数，第一个是指定第几列，第二个参数才是指定第几行  
                String str = cell1.getContents();
                string = string + str + " ";
            }
            
            Cell cell = sheet.getCell(1,2);
            String s = cell.getContents();
            int num = Integer.valueOf(s);
            //应该改为m<30,3为测试用
            for (int m = 3; m < num+3; m++) {
                Cell cell1 = sheet.getCell(0,m);//需要注意的是这里的getCell方法的参数，第一个是指定第几列，第二个参数才是指定第几行  
                Cell cell2 = sheet.getCell(1,m);
                String str = cell1.getContents();
                String str2 = cell2.getContents();
                string = string + str + " " +  str2 + " ";
            }
            
 
            book.close();
            return string;
            
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (BiffException e) {  
            e.printStackTrace();  
        }
		return null;  
    } 
    
    //读取初始化的交易记录
    public String readStockData(String path){
    	try {  
            //打开文件  
    		String filePath = path;
            Workbook book = Workbook.getWorkbook(new File(filePath));  
            //获得第一个表的工作对象，“0”表示第一个表  
            Sheet sheet = book.getSheet(0);  
            //得到第一列，第一行的单元格（0，0）  
         
            String string = new String();
            int n,m;
            
            //12行 m  从1开始，0位
            for ( m = 1; m < 40; m++) {
            	
            	for( n=0;n<11;n++){
            		Cell cell = sheet.getCell(n,m);//需要注意的是这里的getCell方法的参数，第一个是指定第几列，第二个参数才是指定第几行
            		if(cell.getRow() == 3||cell.getRow() == 16||
                			cell.getRow() == 24||cell.getRow() == 27||
                			cell.getRow() == 31||cell.getRow() == 36){
                		//m++;
                		break;
            		}
                    String str = cell.getContents();
                    if(cell.getColumn() != 10){
                    	string = string + str + ",";
                    }
                    else{
                    	string = string + str;
                    }
            	}
            	string = string + ";";
            	
            }
            
          
        	
            book.close();
            return string;
            
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (BiffException e) {  
            e.printStackTrace();  
        }
		return null;  
    } 
    
  //读取股票交易记录
  	//用户名，sheet号
  	public String readStockRecord(String name,int i){
  		try {
  			// 打开文件
  			String filePath = "File/"+name+"_personInfo.xls";
  			Workbook book = Workbook.getWorkbook(new File(filePath));
  			// 获得第一个表的工作对象，“0”表示第一个表
  			Sheet sheet = book.getSheet(i);
  			// 得到第一列，第一行的单元格（0，0）

  			int rows = sheet.getRows();
  			String string = new String();
  			//行数
  			for (int m = 2; m < rows; m++) {
  				//列数
  				for(int n = 0; n < 6; n++){
  					Cell cell1 = sheet.getCell(n,m);// 需要注意的是这里的getCell方法的参数，第一个是指定第几列，第二个参数才是指定第几行
  					String str = cell1.getContents();
  					if(n < 5){
  					string = string + str + " ";
  					}
  					if(n == 5){
  					string = string + str + ";";
  					}
  				}
  			}
  			

  			book.close();
  			return string;

  		} catch (IOException e) {
  			e.printStackTrace();
  		} catch (BiffException e) {
  			e.printStackTrace();
  		}
  		return null;
  	}
  	public String readStockE()
  	{
  		try{
  	        // 1、构造excel文件输入流对象
  	    	String string = new String();
  	    	
  	        String sFilePath = "File/stock.xls";
  	        InputStream is = new FileInputStream(sFilePath);
  	        // 2、声明工作簿对象
  	        Workbook rwb = Workbook.getWorkbook(is);
  	        // 3、获得工作簿的个数,对应于一个excel中的工作表个数
  	        rwb.getNumberOfSheets();

  	        Sheet oFirstSheet = rwb.getSheet(0);// 使用索引形式获取第一个工作表，也可以使用rwb.getSheet(sheetName);其中sheetName表示的是工作表的名称
//  	        System.out.println("工作表名称：" + oFirstSheet.getName());
  	        int rows = oFirstSheet.getRows();//获取工作表中的总行数
  	        //System.out.println(rows);
  	        int columns = oFirstSheet.getColumns();//获取工作表中的总列数
  	        //System.out.println(columns);
  	        
  	        
  	        	for (int i = 0; i < rows; i++) { 
  	        		String string1 = new String();
  	        		for(int j = 0; j < 7;j++)
  	        		{
  	        			
  	        			Cell cell1 = oFirstSheet.getCell(j,i);//需要注意的是这里的getCell方法的参数，第一个是指定第几列，第二个参数才是指定第几行  
  	                	String result1 = cell1.getContents();
  	               
  	                	if(j == 0)
  	                	{
  	                		string1 = result1;
  	                		continue;
  	                	}
  	                	string1 += ","+result1;
  	        		}
  	        		
  	        		if(i == 0)
  	        			string = string1;
  	        		else
  	        			string += ";" + string1;
  	        	}  
  	           rwb.close();
  	           
  	          
  	         return string;
  	    	}
  	        catch (Exception e) {   
  	            System.out.println("---出现异常---");   
  	            e.printStackTrace();   
  	        }
  			return null;
  		
  	}
  	
  	public String readStockT()
  	{
  		try{
  	        // 1、构造excel文件输入流对象
  	    	String string = new String();
  	    	
  	        String sFilePath = "File/stock.xls";
  	        InputStream is = new FileInputStream(sFilePath);
  	        // 2、声明工作簿对象
  	        Workbook rwb = Workbook.getWorkbook(is);
  	        // 3、获得工作簿的个数,对应于一个excel中的工作表个数
  	        rwb.getNumberOfSheets();

  	        Sheet oFirstSheet = rwb.getSheet(1);// 使用索引形式获取第一个工作表，也可以使用rwb.getSheet(sheetName);其中sheetName表示的是工作表的名称
//  	        System.out.println("工作表名称：" + oFirstSheet.getName());
  	        int rows = oFirstSheet.getRows();//获取工作表中的总行数
  	        //System.out.println(rows);
  	        int columns = oFirstSheet.getColumns();//获取工作表中的总列数
  	        //System.out.println(columns);
  	        
  	        
  	        	for (int i = 0; i < rows; i++) { 
  	        		String string1 = new String();
  	        		for(int j = 0; j < 31;j++)
  	        		{
  	        			
  	        			Cell cell1 = oFirstSheet.getCell(j,i);//需要注意的是这里的getCell方法的参数，第一个是指定第几列，第二个参数才是指定第几行  
  	                	String result1 = cell1.getContents();
  	               
  	                	if(j == 0)
  	                	{
  	                		string1 = result1;
  	                		continue;
  	                	}
  	                	string1 += ","+result1;
  	        		}
  	        		if(i == 0)
  	        			string = string1;
  	        		else
  	        			string += ";" + string1;
  	        	}  
  	           rwb.close();
  	           
  	          
  	         return string;
  	    	}
  	        catch (Exception e) {   
  	            System.out.println("---出现异常---");   
  	            e.printStackTrace();   
  	        }
  			return null;
  	}
  	
  	public String readPersonInfo(int i) {
		try {
			// 1、构造excel文件输入流对象
			Vector<User> userVec = new Vector<User>();

			String sFilePath = "File/personInfo.xls";
			InputStream is = new FileInputStream(sFilePath);
			// 2、声明工作簿对象
			Workbook rwb = Workbook.getWorkbook(is);
			// 3、获得工作簿的个数,对应于一个excel中的工作表个数
			rwb.getNumberOfSheets();

			Sheet oFirstSheet = rwb.getSheet(0);// 使用索引形式获取第一个工作表，也可以使用rwb.getSheet(sheetName);其中sheetName表示的是工作表的名称
			// System.out.println("工作表名称：" + oFirstSheet.getName());
			int rows = oFirstSheet.getRows();// 获取工作表中的总行数
			// System.out.println(rows);
			int columns = oFirstSheet.getColumns();// 获取工作表中的总列数
			// System.out.println(columns);

			User p = new User();
			Cell cell1 = oFirstSheet.getCell(0, i);// 需要注意的是这里的getCell方法的参数，第一个是指定第几列，第二个参数才是指定第几行
			String result1 = cell1.getContents();

			Cell cell2 = oFirstSheet.getCell(1, i);// 需要注意的是这里的getCell方法的参数，第一个是指定第几列，第二个参数才是指定第几行
			String result2 = cell2.getContents();

			Cell cell3 = oFirstSheet.getCell(2, i);// 需要注意的是这里的getCell方法的参数，第一个是指定第几列，第二个参数才是指定第几行
			String result3 = cell3.getContents();

			String str = result1 + " " + result2 + " " + result3;
			System.out.println(result1);

			rwb.close();

			return str;
		} catch (Exception e) {
			System.out.println("---出现异常---");
			e.printStackTrace();
		}
		return null;
	}
}
