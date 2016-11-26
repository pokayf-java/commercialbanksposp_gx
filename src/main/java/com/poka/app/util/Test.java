package com.poka.app.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Test {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		java.text.SimpleDateFormat sim = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Calendar rightNow = java.util.Calendar.getInstance();
		List<String> dateList = new ArrayList<String>();
		Date date = rightNow.getTime();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		for(int i=0;i<15;i++){
			c.add(Calendar.DAY_OF_YEAR, -1);
			Date monday = c.getTime();
			System.out.println(sim.format(monday));
			dateList.add(sim.format(monday));
		}
		System.out.println(dateList.size());
//		c.add(Calendar.DAY_OF_YEAR, -1);
//		Date monday = c.getTime();
//		System.out.println(sim.format(monday));
//		c.add(Calendar.DAY_OF_YEAR, -1);
//		Date tuesday = c.getTime();
//		System.out.println(sim.format(c.getTime()));
//		System.out.println(dateList.size());
//		c.add(Calendar.DAY_OF_YEAR, -1);
//		System.out.println(sim.format(c.getTime()));
//		c.add(Calendar.DAY_OF_YEAR, -2);
//		System.out.println(sim.format(c.getTime()));
//		c.add(Calendar.DAY_OF_YEAR, -3);
//		System.out.println(sim.format(c.getTime()));
//		c.add(Calendar.DAY_OF_YEAR, -4);
//		System.out.println(sim.format(c.getTime()));
//		c.add(Calendar.DAY_OF_YEAR, -5);
//		System.out.println(sim.format(c.getTime()));
//		c.add(Calendar.DAY_OF_YEAR, -6);
//		System.out.println(sim.format(c.getTime()));
//		c.add(Calendar.DAY_OF_YEAR, -7);
//		System.out.println(sim.format(c.getTime()));
		
//		Date today_plus1 = c.getTime();
//		System.out.println("Tomorrow:/t" + sim.format(today_plus1));
//		c.add(Calendar.DAY_OF_YEAR, -1);
//		Date today_plus2 = c.getTime();
//		System.out.println("Today+2:/t" + today_plus2.toLocaleString());
//		c.add(Calendar.DAY_OF_YEAR, 1);
//		Date today_plus3 = c.getTime();
//		System.out.println("Today+3:/t" + today_plus3.toLocaleString());
//		c.add(Calendar.DAY_OF_YEAR, 1);
//		Date today_plus4 = c.getTime();
//		System.out.println("Today+4:/t" + today_plus4.toLocaleString());
//		c.add(Calendar.DAY_OF_YEAR, 1);
//		Date today_plus5 = c.getTime();
//		System.out.println("Today+5:/t" + today_plus5.toLocaleString());
//		c.add(Calendar.DAY_OF_YEAR, 1);
//		Date today_plus6 = c.getTime();
//		System.out.println("Today+6:/t" + today_plus6.toLocaleString());
//		c.add(Calendar.DAY_OF_YEAR, 1);
//		Date today_plus7 = c.getTime();
//		System.out.println("Today+7:/t" + today_plus7.toLocaleString());
		
		
		
//		java.util.Calendar rightNow = java.util.Calendar.getInstance();
//        
//        //得到当前时间，+3天
//        rightNow.add(java.util.Calendar.DAY_OF_MONTH, 3);   
//        //如果是后退几天，就写 -天数 例如：
//        rightNow.add(java.util.Calendar.DAY_OF_MONTH, -3);
//        //进行时间转换
//        String date = sim.format(rightNow.getTime()); 
//        System.out.println(rightNow.add(java.util.Calendar.DAY_OF_MONTH, 3));
	}
}
