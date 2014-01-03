package org.jacademie.examenDecembre.DAOs;

import java.io.Serializable;
import java.util.List;

public class AlbumHibernateDAO extends GeneriqueHibernateDAO<Album> implements IAlbumDAO {

	public AlbumHibernateDAO() {
		super(Album.class);
	}

	@Override
	public List<Album> searchByName(String searchPattern) {
		searchByPattern("nom", searchPattern);
		return null;
	}
}
