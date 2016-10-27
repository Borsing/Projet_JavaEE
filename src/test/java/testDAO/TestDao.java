package testDAO;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.PersistenceException;

import modules.ArgumentsParser;
import modules.DatabaseManager;
import modules.event.EventService;


public class TestDao {

	private static final DatabaseManager databaseManager = DatabaseManager.getInstance();
	public static void main(String[] args) throws PersistenceException, ClassNotFoundException, ParseException {
		databaseManager.populate();
		EventService es = new EventService();
		System.out.println(es.getAllEvents());
		//fonctionne !
		System.out.println(es.getEventById(2));
		
		System.out.println("EventOnDay 14 = " + es.getEventsOnDay((Date)ArgumentsParser.convertTo("java.util.Date", "14/05/2016/00:00")));
		
		System.out.println("EventOnDay 15 = " + es.getEventsOnDay((Date)ArgumentsParser.convertTo("java.util.Date", "15/05/2016/00:00")));
		
		System.out.println("EventOnDay 16 = " + es.getEventsOnDay((Date)ArgumentsParser.convertTo("java.util.Date", "16/05/2016/00:00")));

		/*
		try {
			String a = (String)ArgumentsParser.convertTo("java.lang.String", "toto");
			System.out.println(a);
			
			int b = (Integer)ArgumentsParser.convertTo("java.lang.Integer", "15");
			System.out.println(b);
			
			Date c = (Date)ArgumentsParser.convertTo("java.util.Date", "15/05/1994/12:00:00");
			System.out.println(c);
			
		} catch (ClassNotFoundException | ParseException e) {
			e.printStackTrace();
		}
		*/
	}
}
