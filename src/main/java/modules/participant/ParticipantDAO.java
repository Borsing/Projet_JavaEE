package modules.participant;

import modules.DAOInterface;

/**
 * Created by adric on 10/10/2016.
 */
public class ParticipantDAO implements DAOInterface<ParticipantEntity>{

	@Override
	public Class<ParticipantEntity> getEntityClass() {
		return ParticipantEntity.class;
	}

}
