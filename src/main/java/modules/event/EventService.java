package modules.event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import modules.Field;
import modules.FieldComparable;
import modules.organizer.OrganizerDAO;
import modules.organizer.OrganizerEntity;
import modules.participant.ParticipantDAO;
import modules.participant.ParticipantEntity;

public class EventService {
	
	OrganizerDAO orgaDao = new OrganizerDAO();
	EventDAO eventDao = new EventDAO();
	ParticipantDAO parDao = new ParticipantDAO();


	private Date increaseDate(Date day,int timelapse){
		Calendar c = Calendar.getInstance();
		c.setTime(day);
		c.add(timelapse, 1);
		return c.getTime();
	}
	
	//To improve OF COURSE
	public List<EventEntity> findAllEvents(){
		return eventDao.findAll();
	}
	
	//list all services for an event
	//get Events on a Day 12/05/2016/00:00 -> 13/05/2016/00:00
	public List<EventEntity> findEventsOnDay(Date day){
		return this.findEventsOnInterval(day, increaseDate(day,Calendar.DATE));
	}
	
	//get Events in a Month
	public List<EventEntity> findEventsOnMonth(Date day){
		return this.findEventsOnInterval(day, increaseDate(day,Calendar.MONTH));
	}
	
	//get Events in a Month
	public List<EventEntity> findEventsOnYear(Date day){
		return this.findEventsOnInterval(day, increaseDate(day,Calendar.YEAR));
	}
	
	//get Events By Interval between 2 dates
	public List<EventEntity> findEventsOnInterval(Date day, Date endDay){
		Set<EventEntity> events = new HashSet<EventEntity>() ;
		
		FieldComparable<Date> fieldBegin = new FieldComparable<Date>();
		
		//Date begin during the day
		fieldBegin.setFieldTarget("begin_date")
				  .setOperator(Field.Operator.GE)
				  .setValueTarget(day);
		
		FieldComparable<Date> fieldEnd = new FieldComparable<Date>();
		fieldEnd.setFieldTarget("begin_date")
				  .setOperator(Field.Operator.LE)
				  .setValueTarget(endDay);
		
		
		
		events.addAll( eventDao.findByCriteria(Field.BooleanOperator.AND,fieldBegin,fieldEnd)) ;
		
		//Date end during the day
		FieldComparable<Date> fieldBegin2 = new FieldComparable<Date>();
		fieldBegin2.setFieldTarget("end_date")
				  .setOperator(Field.Operator.GE)
				  .setValueTarget(day);
		
		FieldComparable<Date> fieldEnd2 = new FieldComparable<Date>();
		fieldEnd2.setFieldTarget("end_date")
				  .setOperator(Field.Operator.LE)
				  .setValueTarget(endDay);
		
		events.addAll( eventDao.findByCriteria(Field.BooleanOperator.AND,fieldBegin2,fieldEnd2));
		
		//Date begin before and end after the day
		FieldComparable<Date> fieldBegin3 = new FieldComparable<Date>();
		fieldBegin3.setFieldTarget("begin_date")
				  .setOperator(Field.Operator.LE)
				  .setValueTarget(day);
		
		FieldComparable<Date> fieldEnd3 = new FieldComparable<Date>();
		fieldEnd3.setFieldTarget("end_date")
				  .setOperator(Field.Operator.GE)
				  .setValueTarget(endDay);
		
		events.addAll( eventDao.findByCriteria(Field.BooleanOperator.AND,fieldBegin3,fieldEnd3));
		
		
		return new ArrayList<>(events);
	}



	//get Events By Name	
	public List<EventEntity> findEventByName(String name){		
		Field<String> fieldName = new Field<>();
		
		//Date begin during the day
		fieldName.setFieldTarget("name")
				  .setOperator(Field.Operator.EQ)
				  .setValueTarget(name);
		
		return eventDao.findByCriteria(fieldName);
	}

	//findEvent by id
	public EventEntity findEventById(int id){
		return eventDao.findById(id);
	}
	
	//get Events By Creator
	public List<EventEntity> findEventByOrganizer(String organizer_id){		
		Field<OrganizerEntity> fieldName = new Field<>();
		
		OrganizerEntity organizer = orgaDao.findById(organizer_id);
		
		//Date begin during the day
		fieldName.setFieldTarget("organizer_id")
				  .setOperator(Field.Operator.EQ)
				  .setValueTarget(organizer);
		
		return eventDao.findByCriteria(fieldName);
	}

	//get Events By Participant
	public List<EventEntity> findEventsByParticipant(String participantId){
		ParticipantEntity participant = parDao.findById(participantId);
		if(participant != null)
			return new ArrayList<EventEntity>(participant.getEvents()) ;
		return new ArrayList<>();
	}
	
	public boolean isOrganizerOfEvent(String organizer_id, int eventId){
		Field<OrganizerEntity> fieldOrga = new Field<>();
		Field<Integer> fieldEventId = new Field<>();

		
		OrganizerEntity organizer = orgaDao.findById(organizer_id);
		
		//Date begin during the day
		fieldOrga.setFieldTarget("organizer_id")
				  	.setOperator(Field.Operator.EQ)
				  	.setValueTarget(organizer);
		
		fieldEventId.setFieldTarget("id")
				  	.setOperator(Field.Operator.EQ)
				  	.setValueTarget(eventId);	
		
		return eventDao.findByCriteria(Field.BooleanOperator.AND,fieldOrga,fieldEventId).size() == 1;
	}
	
	public void updateEvent(int id, String name, String description, Date begin_date, Date end_date, String address, String organizer_id) // can not change id. The organizerId field is the stored value in the session
	{
		EventEntity event = eventDao.findById(id);
		
		OrganizerEntity organizer = orgaDao.findById(organizer_id);
		
		event.setName(name);
		event.setDescription(description);
		event.setBegin_date(begin_date);
		event.setEnd_date(end_date);
		event.setOrganizer_id(organizer);
		
		eventDao.update(event);
	}
	
	public void removeEvent(int eventId)	{
		eventDao.delete(eventDao.findById(eventId));
	}
	
	public void createEvent(String name, String description, Date begin_date, Date end_date, String address, String organizer_id)	{
		OrganizerEntity organizer = orgaDao.findById(organizer_id);
		
		EventEntity event = new EventEntity(name,description,begin_date,end_date,address,organizer,new ArrayList<ParticipantEntity>());

		eventDao.create(event);
	}
	
	
	
}
