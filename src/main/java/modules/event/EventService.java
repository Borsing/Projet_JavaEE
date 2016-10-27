package modules.event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import modules.AbstractEntity;
import modules.Field;
import modules.FieldComparable;

public class EventService {
	
	EventDAO eventDao = new EventDAO();
	
	//To improve OF COURSE
	public List<EventEntity> getAllEvents(){
		return eventDao.findAll();
	}
	
	public EventEntity getEventById(int id){
		return eventDao.findById(id);
	}
	
	//list all services for an event
		//get Events on a Day 12/05/2016/00:00 -> 13/05/2016/00:00
	public List<EventEntity> getEventsOnDay(Date day){
		Set<EventEntity> events = new HashSet<EventEntity>() ;
		
		Calendar c = Calendar.getInstance();
		c.setTime(day);
		c.add(Calendar.DATE, 1);
		Date endDay = c.getTime();
		
		FieldComparable<Date> fieldBegin = new FieldComparable<Date>();
		
		//Date begin during the day
		fieldBegin.setFieldTarget("begin_date")
				  .setOperator(Field.Operator.GT)
				  .setValueTarget(day);
		
		FieldComparable<Date> fieldEnd = new FieldComparable<Date>();
		fieldEnd.setFieldTarget("begin_date")
				  .setOperator(Field.Operator.LT)
				  .setValueTarget(endDay);
		
		
		
		events.addAll( eventDao.findByCriteria(Field.BooleanOperator.AND,fieldBegin,fieldEnd)) ;
		
		//Date end during the day
		FieldComparable<Date> fieldBegin2 = new FieldComparable<Date>();
		fieldBegin2.setFieldTarget("end_date")
				  .setOperator(Field.Operator.GT)
				  .setValueTarget(day);
		
		FieldComparable<Date> fieldEnd2 = new FieldComparable<Date>();
		fieldEnd2.setFieldTarget("end_date")
				  .setOperator(Field.Operator.LT)
				  .setValueTarget(endDay);
		
		events.addAll( eventDao.findByCriteria(Field.BooleanOperator.AND,fieldBegin2,fieldEnd2));
		
		//Date begin before and end after the day
		FieldComparable<Date> fieldBegin3 = new FieldComparable<Date>();
		fieldBegin3.setFieldTarget("begin_date")
				  .setOperator(Field.Operator.LT)
				  .setValueTarget(day);
		
		FieldComparable<Date> fieldEnd3 = new FieldComparable<Date>();
		fieldEnd3.setFieldTarget("end_date")
				  .setOperator(Field.Operator.GT)
				  .setValueTarget(endDay);
		
		events.addAll( eventDao.findByCriteria(Field.BooleanOperator.AND,fieldBegin3,fieldEnd3));
		
		
		return new ArrayList<>(events);
	}
		//get Events in a Month
	
	public void removeDoublons(Collection<AbstractEntity> entities){
		
	}
	
		//get Events By Interval between 2 dates
		//get Events By Participant
		//get Events By Creator
		//get Events By Name	
}
