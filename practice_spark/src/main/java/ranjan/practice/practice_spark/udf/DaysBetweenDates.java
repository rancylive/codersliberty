package ranjan.practice.practice_spark.udf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.spark.sql.api.java.UDF2;

public class DaysBetweenDates implements UDF2<String, String, Integer>{

	public static final String dateFormat = "dd/mm/yyyy";
	
	@Override
	public Integer call(String t1, String t2) throws Exception {
		return dateDiff(t1, t2);
	}

	public static int dateDiff(String d1, String d2) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);
		Date start = sdf.parse(d1);
		Date end = sdf.parse(d2);
		long diff = end.getTime()-start.getTime();
		int diffInDays=(int) (diff/ (1000 * 60 * 60 * 24));
		return diffInDays;
	}
}
