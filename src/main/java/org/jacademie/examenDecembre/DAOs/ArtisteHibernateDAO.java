package org.jacademie.examenDecembre.DAOs;

import java.util.List;

public class ArtisteHibernateDAO extends GeneriqueHibernateDAO<Artiste> implements IArtisteDAO {

	public ArtisteHibernateDAO() {
		super(Artiste.class);
	}

	@Override
	public List<Artiste> searchByName(String searchPattern) {
		return searchByPattern("nom", searchPattern);
	}

	@Override
	public Artiste getOneByName(String searchPattern) {
		return searchOneByPattern("nom", searchPattern);
	}


}
