package modules.event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import exception.BeanException;
import exception.EnumException;
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
	public List<EventEntity> findEventsByOrganizer(String organizer_id){
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
	
	public void updateEvent(int id, String name, String description, Date begin_date, String begin_time, Date end_date, String end_time, String address, String organizer_id) throws BeanException // can not change id. The organizerId field is the stored value in the session
	{
		try {
			EventEntity event = eventDao.findById(id);

			OrganizerEntity organizer = orgaDao.findById(organizer_id);
		
			event.setName(name);
			event.setDescription(description);
			event.setAddress(address);
			event.setEnd_date(fusionDateAndTime(begin_date,begin_time));
			event.setEnd_date(fusionDateAndTime(end_date,end_time));
			event.setOrganizer_id(organizer);

			boolean ok = eventDao.update(event);
			if(!ok)
				throw new BeanException(EnumException.SOMETHING_WRONG);

			event.setBegin_date(fusionDateAndTime(begin_date,begin_time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void removeEvent(int eventId)	{
		eventDao.delete(eventDao.findById(eventId));
	}
	
	public void createEvent(String name, String description, Date begin_date, String begin_time, Date end_date, String end_time,String address, String organizer_id) throws BeanException {

		try {
			
			OrganizerEntity organizer = orgaDao.findById(organizer_id);
			EventEntity event = new EventEntity(name,description,fusionDateAndTime(begin_date, begin_time),fusionDateAndTime(end_date, end_time),address,organizer,new ArrayList<>());
			eventDao.create(event);

		} catch (ParseException e) {
			throw new BeanException(EnumException.SOMETHING_WRONG);
		}
	}

	private Date fusionDateAndTime(Date begin_date, String begin_time) throws ParseException {

		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

		Calendar beginTime = Calendar.getInstance();
		beginTime.setTime(timeFormat.parse(begin_time));

		Calendar beginDate = Calendar.getInstance();
		beginDate.setTime(begin_date);
		beginDate.set(Calendar.HOUR_OF_DAY, beginTime.get(Calendar.HOUR_OF_DAY));
		beginDate.set(Calendar.MINUTE, beginTime.get(Calendar.MINUTE));

		return beginDate.getTime();
	}


}
