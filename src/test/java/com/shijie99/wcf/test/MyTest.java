package com.shijie99.wcf.test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

public class MyTest {
	@Test
	public void test(){
		String s="{\"baggageInfoList\":[{\"adultBaggage\":\"1P\",\"childBaggage\":\"\",\"segmentNo\":1},{\"adultBaggage\":\"1P\",\"childBaggage\":\"\",\"segmentNo\":2}]}";
		JSONObject json = JSONObject.fromObject(s);
		JSONArray baggages = json.getJSONArray("baggageInfoList");
		for (Object object : baggages) {
			JSONObject bag = (JSONObject)object;
			String adultBaggage = bag.get("adultBaggage").toString();
			//如果是按件计算，增加说明
			if(adultBaggage.indexOf("P")>-1){
				adultBaggage = adultBaggage+",每件重量上限23KG";
				bag.put("adultBaggage", adultBaggage);
			}
		}
		
		System.out.println(json.toString());
	}
	@Test
	public void test2(){
		System.out.println(new Random().nextInt(100));
	}
	
	@SuppressWarnings("null")
	@Test
	public void test3(){
		try{
			String s= null;
			System.out.println(s.toUpperCase());
		}catch (Exception e) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(baos));  
			String exception = baos.toString();  
			System.out.println("baos:" + exception);  
		}
	}
	
	@Test
	public void test4(){
		//单程直飞
		String dczf = "2015-11-13T09:50:00/2015-11-13T14:55:00";
		System.out.println("单程直飞，预期返回：true,实际返回："+hasExceedMinTransitTime("oneWay",dczf));
		//单程中转
		String dczz = "2015-11-13T09:50:00/2015-11-13T14:45:00;2015-11-13T15:50:00/2015-11-13T14:55:00;";
		System.out.println("单程中转，预期返回：true,实际返回："+hasExceedMinTransitTime("oneWay",dczz));
		String dczz2 = "2015-11-13T09:50:00/2015-11-13T14:55:00;2015-11-13T15:00:00/2015-11-13T14:55:00;";
		System.out.println("单程中转，预期返回：false,实际返回："+hasExceedMinTransitTime("oneWay",dczz2));
		String dczz3 = "2015-11-13T09:50:00/2015-11-13T14:55:00;2015-11-13T15:56:00/2015-11-13T15:20:00;2015-11-13T15:50:00/2015-11-13T14:55:00;";
		System.out.println("单程中转，预期返回：false,实际返回："+hasExceedMinTransitTime("oneWay",dczz3));
		
		//往返直飞
		String wfzf = "2015-11-13T09:50:00/2015-11-13T14:55:00;2015-11-13T17:20:00/2015-11-13T10:20:00";
		System.out.println("往返直飞，预期返回：true,实际返回："+hasExceedMinTransitTime("roundTrip",wfzf));
		//往返中转
		String wfzz = "2015-11-13T09:50:00/2015-11-13T14:55:00;2015-11-13T17:20:00/2015-11-13T10:20:00;2015-11-13T11:50:00/2015-11-13T14:55:00;2015-11-13T18:50:00/2015-11-13T15:55:00";
		System.out.println("往返中转，预期返回：true,实际返回："+hasExceedMinTransitTime("roundTrip",wfzz));
		String wfzz2 = "2015-11-13T09:50:00/2015-11-13T14:55:00;2015-11-13T17:20:00/2015-11-13T10:20:00;2015-11-13T11:10:00/2015-11-13T14:55:00;2015-11-13T18:50:00/2015-11-13T15:55:00";
		System.out.println("往返中转，预期返回：false,实际返回："+hasExceedMinTransitTime("roundTrip",wfzz2));
		String wfzz3 = "2015-11-13T17:20:00/2015-11-13T10:20:00;2015-11-13T11:40:00/2015-11-13T14:55:00;2015-11-13T18:50:00/2015-11-13T15:55:00";
		System.out.println("往返中转，预期返回：true,实际返回："+hasExceedMinTransitTime("roundTrip",wfzz3));
	}
	public static boolean hasExceedMinTransitTime(String flighttype,String flighttime){
		boolean flag = true;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(flighttime.indexOf("T")>-1){
			flighttime = flighttime.replace("T", " ");
		}
		if(flighttime.indexOf(";")>-1){
			flighttime = flighttime.replace(";", "/");
		}
		String[] flighttimes = flighttime.split("/");
		//不是中转的直接返回
		if(flighttype.equals("oneWay") && flighttimes.length==2){//单程只有一段说明没有中转（每段都有起飞时间和到达时间）
			System.out.println("不是中转的，不需要判断了");
			return true;
		}
		if(flighttype.equals("roundTrip") && flighttimes.length==4){//往返只有两段说明没有往返（每段都有起飞时间和到达时间）
			System.out.println("不是中转的，不需要判断了");
			return true;
		}
		//只要是有中转的，就用起飞时间减去上一段的到达时间，判断是否大于最小值
		for(int i=2;i<flighttimes.length;i=i+2){
			System.out.println(i);
			try {
				long lasttime = df.parse(flighttimes[i-1]).getTime();
				long nowtime = df.parse(flighttimes[i]).getTime();
				//这一个时间与上一个时间相减获取分钟数，与最大允许时间比较
				if((nowtime-lasttime)/(1000*60)<60){
					System.out.println("不符合最小时间限制，失败,最小时间要求："+60);
					System.out.println("不符合的时间段为："+flighttimes[i-1]+","+flighttimes[i]);
					flag = false;
					break;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	@Test
	public void test5(){
		String s = System.getProperty("line.separator")+"s";
		System.out.println(s);
	}
	
	@Test
	public void test6(){
		for(Integer i=100;i<=99999;i++){
			String s= i.toString();
			int ii = 0;
			for(int j = 0;j<s.length();j++){
				ii +=Math.pow(Integer.parseInt(String.valueOf(s.charAt(j))), 3); 
			}
			if(i==ii){
				System.out.println("符合数字："+i);
			}
		}
	}
	@Test
	public void test7(){
		String s="{\"birthday\":\"\",\"field\":{\"16568\":{\"name\":\"体验密码\",\"value\":\"23456\"}},\"gender\":\"0\",\"mobile\":\"13910770118\",\"name\":\"黄瑞\",\"openid\":\"oSkDAuG82_-i1H975Ylb1NVTzxoU\",\"pid\":\"361398\"}";
		JSONObject json = JSONObject.fromObject(s);
		JSONObject js = json.getJSONObject("field");
		JSONObject j = js.getJSONObject("16568");
		j.put("name", "修改了");
//		String value = json.getJSONObject("field").getJSONObject("16568").getString("value").toString();
		System.out.println(json.toString());
	}
	@Test
	public void test8(){
		
		String s = "1; ";
		String[] ss = s.split(";");
		System.out.println(ss[1]);
	}
}
