package org.jacademie.examenDecembre.DAOs;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.jacademie.examenDecembre.utils.HibernateUtil;

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
		Chanson tempChanson = new Chanson();
		tempChanson.setAlbum(album);
		tempChanson.setNumero(numero);
		return getById(tempChanson);
		
	}

}
