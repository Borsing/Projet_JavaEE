package modules.organizer;


import modules.Field;
import modules.event.EventDAO;
import modules.event.EventEntity;

public class OrganizerService {
	
	OrganizerDAO orgaDao = new OrganizerDAO();
	EventDAO eventDao = new EventDAO();
	
	public OrganizerEntity findOrganizerById(String user){
		return  orgaDao.findById(user);
		
	}

	//select
	public boolean checkLogin(String user, String password){
		Field<String> fieldUser = new Field<>();
		
		fieldUser.setFieldTarget("mail")
			  	 .setOperator(Field.Operator.EQ)
			  	 .setValueTarget(user);
		
		Field<String> fieldPassword = new Field<>();
		
		fieldUser.setFieldTarget("password")
			  	 .setOperator(Field.Operator.EQ)
			  	 .setValueTarget(password);
		
		return orgaDao.findByCriteria(Field.BooleanOperator.AND, fieldUser, fieldPassword).size() ==  1 ;
	}
	
	public boolean updateProfil(String lastMail, String newMail, String password, String last_name, String first_name, String company){
		OrganizerEntity orga = orgaDao.findById(lastMail);
		
		//new Mail allready exist
		if(!lastMail.equals(newMail) && exists(newMail))
			return false;
		
		orga.setMail(newMail);
		orga.setPassword(password);
		orga.setLast_name(last_name);
		orga.setFirst_name(first_name);
		orga.setCompany(company);
		
		return orgaDao.update(orga);
	}
	
	public void changePassword(String mail, String password){
		OrganizerEntity orga = orgaDao.findById(mail);
		this.updateProfil(orga.getMail(), orga.getMail(), password, orga.getLast_name(), orga.getFirst_name(), orga.getCompany());
	}
	
	//create
	public OrganizerEntity register(String mail, String password, String last_name, String first_name, String company){
		OrganizerEntity orga = new OrganizerEntity(mail, password, last_name, first_name, company, null);
		orgaDao.create(orga);
		return orga ;
	}
	
	//delete
	public void unregister(String id) {
		orgaDao.deleteById(id);
	}
	
	public boolean exists(String userId){
		return orgaDao.findById(userId) != null ;
	}
	
	public void removeEvent(String event_id){
		EventEntity event = eventDao.findById(event_id);
		
		eventDao.delete(event);
	}
}
