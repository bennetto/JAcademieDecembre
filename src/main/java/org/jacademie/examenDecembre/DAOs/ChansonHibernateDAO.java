package org.jacademie.examenDecembre.DAOs;

import java.io.Serializable;
import java.util.List;

public class ChansonHibernateDAO extends GeneriqueHibernateDAO<Chanson> implements
		IChansonDAO {

	public ChansonHibernateDAO() {
		super(Chanson.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Chanson> searchByName(String searchPattern) {
		searchByPattern("titre", searchPattern);
		return null;
	}

}
