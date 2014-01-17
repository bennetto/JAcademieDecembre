package org.jacademie.examenDecembre.DAOs;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class ChansonHibernateDAO extends GeneriqueHibernateDAO<Chanson> implements
		IChansonDAO {

	public ChansonHibernateDAO() {
		super(Chanson.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Chanson> searchByName(String searchPattern) {
		return searchByPattern("titre", searchPattern);
	}

	@Override
	public Chanson getOneByName(String searchPattern) {
		return searchOneByPattern("titre", searchPattern);
	}

	@Override
	public Chanson getByAlbumAndNum(Album album, Integer numero) {
		Session hibernateSession = getSession();
    	Query hqlQuery = hibernateSession.getNamedQuery("getChansonByID");
    	hqlQuery.setInteger("chanson_numero", numero);
    	hqlQuery.setInteger("album_code", album.getCodeAlbum());
    	List<Chanson> chansons = hqlQuery.list();
    	if(chansons.size() == 1){
    		return chansons.get(0);
    	}
    	return null;
		
	}

}
