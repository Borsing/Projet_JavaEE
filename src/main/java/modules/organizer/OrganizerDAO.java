package modules.organizer;

import modules.DAOInterface;

/**
 * Created by adric on 10/10/2016.
 */
public class OrganizerDAO implements DAOInterface<OrganizerEntity> {

	@Override
	public Class<OrganizerEntity> getEntityClass() {
		return OrganizerEntity.class;
	}

}
