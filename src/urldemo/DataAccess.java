package urldemo;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;

public class DataAccess
{
	String url;
	public String daily_k,weekly_k,monthly_k;
	String history_url;

	public  DataAccess(String target)
	{
                
        if(target.charAt(0) =='0')
                target="sz"+target;
        else if(target.charAt(0) == '6')
        		target="sh"+target;
 
		url="http://hq.sinajs.cn/list="+target;
		daily_k="http://image.sinajs.cn/newchart/daily/n/"+target+".gif";
		weekly_k="http://image.sinajs.cn/newchart/weekly/n/"+target+".gif";
		monthly_k="http://image.sinajs.cn/newchart/monthly/n/"+target+".gif";
	}
	public void download(String urlString, String filename) throws Exception 
	{
	    // 构造URL
	    URL url = new URL(urlString);
	    // 打开连接
	    URLConnection con = url.openConnection();
	    // 输入流
	    InputStream is = con.getInputStream();
	    // 1K的数据缓冲
	    byte[] bs = new byte[1024];
	    // 读取到的数据长度
	    int len;
	    // 输出的文件流
	    OutputStream os = new FileOutputStream(filename);
	    // 开始读取
	    while ((len = is.read(bs)) != -1) {
	      os.write(bs, 0, len);
	    }
	    // 完毕，关闭所有链接
	    os.close();
	    is.close();
	}
	public String GetHistoryData(String target,String data)
	{
		if(target.charAt(0) =='0')
                target="sz"+target;
        else if(target.charAt(0) == '6')
        		target="sh"+target;
		System.out.println("gethistorydata:"+target);
		history_url = "http://vip.stock.finance.sina.com.cn/quotes_service/view/vMS_tradehistory.php?symbol="+target+"&date="+data;
		String string="";
		try{
			URL tirc = new URL(history_url);// 构建一URL对象
			BufferedReader in = new BufferedReader(new InputStreamReader(tirc.openStream(),"gbk"));
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				// 从输入流不断的读数据，直到读完为止
				string+=inputLine; 
			in.close(); // 关闭输入流
		}
		catch(MalformedURLException e)
		{
			System.out.println(e);
		}
		 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		int i=string.indexOf("收盘价");
		string=string.substring(i);
		i=string.indexOf("style");
		string=string.substring(i);
		i=string.indexOf(">");
		i++;
		string=string.substring(i);
		i=string.indexOf("<");
		String s=string.substring(0,i);
		return s;
	}
	public String[] GetData()
	{
		String string="";
		try{
			URL tirc = new URL(url);// 构建一URL对象
			BufferedReader in = new BufferedReader(new InputStreamReader(tirc.openStream(),"gbk"));
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				// 从输入流不断的读数据，直到读完为止
				string+=inputLine; 
			in.close(); // 关闭输入流
		}
		catch(MalformedURLException e)
		{
			System.out.println(e+"错误");
		}
		 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		String[] s = new String[10];
		System.out.println(string);
		int length = string.length();
		if(string.indexOf("FAILED") < 0)
		{
			s=string.split(",");
			int i=0;
			char c=s[0].charAt(i);
			while(c!='"')
			{
				i++;
				c=s[0].charAt(i);
			}
			i++;
			s[0]=s[0].substring(i);
		}
		else
			s[0] = "error";
		return s;
	}
}