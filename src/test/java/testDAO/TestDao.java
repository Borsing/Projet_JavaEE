package testDAO;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.PersistenceException;

import modules.ArgumentsParser;
import modules.DatabaseManager;
import modules.event.EventEntity;
import modules.event.EventService;
import modules.participant.ParticipantEntity;
import modules.participant.ParticipantService;


public class TestDao {

	private static final DatabaseManager databaseManager = DatabaseManager.getInstance();
	public static void main(String[] args) throws PersistenceException, ClassNotFoundException, ParseException {
		databaseManager.populate();
		EventService es = new EventService();
		ParticipantService ps = new ParticipantService() ;
		//System.out.println(es.findAllEvents());
		/*System.out.println(es.getAllEvents());
		//fonctionne !
		System.out.println(es.getEventById(2));
		
		System.out.println("EventOnDay 14 = " + es.getEventsOnDay((Date)ArgumentsParser.convertTo(Date.class, "14/05/2016/00:00")));
		
		System.out.println("EventOnDay 15 = " + es.getEventsOnDay((Date)ArgumentsParser.convertTo(Date.class, "15/05/2016/00:00")));
		

		System.out.println("EventOnDay 16 = " + es.getEventsOnDay((Date)ArgumentsParser.convertTo("java.util.Date", "16/05/2016/00:00")));*/
		
		/*
		System.out.println(es.getEventById(1));
		es.update("toto", 1);
		System.out.println(es.getEventById(1));*
		System.out.println(es.getAllEvents());*/
        //EventEntity event1 = new EventEntity("Event de Adrien", "C'est l'événement d'Adrien", (Date)ArgumentsParser.convertTo("java.util.Date", "14/05/2016/11:00"),(Date)ArgumentsParser.convertTo("java.util.Date", "14/05/2016/18:00"), "Adresse d'Adrien", organizer1, null);

		//            ParticipantEntity participant1 = new ParticipantEntity("participant1@gmail.com","nomP1","prenomP1","Company1",null);
		
		//System.out.println(es.findEventById(1));
		System.out.println(ps.joinEvent(1,"participant1@gmail.com","toot","prenomP1","Company1"));
		System.out.println(es.findEventById(1));
		System.out.println(ps.findById("participant1@gmail.com"));

		/*System.out.println(ps.isParticipating("participant1@gmail.com", 1));
		System.out.println(es.findEventById(1));*/
		ps.quitEvent(1, "participant1@gmail.com");
		System.out.println(es.findEventById(1));
		System.out.println(ps.findById("participant1@gmail.com"));
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
