package modules;

import javax.servlet.http.HttpServletRequest;
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
	public static Object convertTo(Class classType, Object value) throws ClassNotFoundException, ParseException{

		if(classType.equals(String.class))
			return value ;
		
		if(classType.equals(Integer.class)){
			int intValue = Integer.parseInt((String) value);
			return intValue ;
		}

		if(classType.equals(int.class)){
			int intValue = Integer.parseInt((String) value);
			return intValue ;
		}
		
		if(classType.equals(Date.class)){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy/HH:mm");
			Date date = sdf.parse((String) value);
			return date ;
		}

		if(classType.equals(HttpServletRequest.class)){
			return value ;
		}
		
		return null ;
	}

}
