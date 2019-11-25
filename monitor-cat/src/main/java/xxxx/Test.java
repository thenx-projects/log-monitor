package xxxx;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test
{
  public static void main(String[] args)
    throws InterruptedException
  {
    List<String> datelist = rangToList("2019-08-23", "2019-11-26");
    for (String date : datelist) {
    	
    	String string = excuteCMDCommand("cmd.exe /c date " + date);
        System.out.println(">>" + string);

        Thread.sleep(500L);
    	 int suiji = RandomUtil.randomInt(20);
         for (int i = 0; i < suiji; ++i) {
           String string2 = Test.excuteCMDCommand("cmd.exe start /c D:\\Users\\Administrator\\git\\log-monitor\\1.bat");
           System.out.println(date + ">>" + string2);
         }
	}
      
      
      
  }

  public static List<String> rangToList(String start_date, String end_date)
  {
    List<String> list = new ArrayList<>();
    List<DateTime> dateTimes = DateUtil.rangeToList(DateUtil.parseDateTime(start_date + " 00:00:00"), DateUtil.parseDateTime(end_date + " 00:00:00"), DateField.DAY_OF_MONTH);
    for (Iterator localIterator = dateTimes.iterator(); localIterator.hasNext(); ) { DateTime dateTime = (DateTime)localIterator.next();
      list.add(DateUtil.format(dateTime, "yyyy-MM-dd"));
    }
    return list;
  }

  public static String excuteCMDCommand(String cmdCommand)
  {
    StringBuilder stringBuilder = new StringBuilder();
    Process process = null;
    try {
      process = Runtime.getRuntime().exec(cmdCommand);
      BufferedReader bufferedReader = new BufferedReader(
        new InputStreamReader(process.getInputStream(), "GBK"));
      String line = null;
      while ((line = bufferedReader.readLine()) != null)
      {
        stringBuilder.append(line + "\n");
      }
      return stringBuilder.toString();
    } catch (Exception e) {
      e.printStackTrace(); }
    return null;
  }


}