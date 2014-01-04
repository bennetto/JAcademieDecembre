package org.jacademie.examenDecembre.DAOs;

import java.util.List;

public class AlbumHibernateDAO extends GeneriqueHibernateDAO<Album> implements IAlbumDAO {

	public AlbumHibernateDAO() {
		super(Album.class);
	}

	@Override
	public List<Album> searchByName(String searchPattern) {
		return searchByPattern("nom", searchPattern);
	}

	@Override
	public Album getOneByName(String searchPattern) {
		return searchOneByPattern("nom", searchPattern);
	}
}
