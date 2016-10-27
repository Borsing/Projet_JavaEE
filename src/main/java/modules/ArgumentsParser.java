package modules;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ArgumentsParser {
	
	/*
	 *  Examples: 
	 *  	String a = (String)ArgumentsParser.convertTo("java.lang.String", "toto");
	 *	
	 *		int b = (Integer)ArgumentsParser.convertTo("java.lang.Integer", "15");
	 *		
	 *		Date c = (Date)ArgumentsParser.convertTo("java.util.Date", "15/05/1994/12:10:00");
	 *
	 */
	public static Object convertTo(String className, String value) throws ClassNotFoundException, ParseException{
		Class<?> clazz = Class.forName(className);
		
		if(clazz.equals(String.class))	
			return value ;
		
		if(clazz.equals(Integer.class)){
			int intValue = Integer.parseInt(value);
			return intValue ;
		}
		
		if(clazz.equals(Date.class)){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy/HH:mm");
			Date date = sdf.parse(value);
			return date ;
		}
		
		return null ;
	}

}
