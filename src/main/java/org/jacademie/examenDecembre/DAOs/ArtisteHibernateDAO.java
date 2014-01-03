package org.jacademie.examenDecembre.DAOs;

import java.io.Serializable;
import java.util.List;

public class ArtisteHibernateDAO extends GeneriqueHibernateDAO<Artiste> implements IArtisteDAO {

	public ArtisteHibernateDAO() {
		super(Artiste.class);
	}

	@Override
	public List<Artiste> searchByName(String searchPattern) {
		searchByPattern("nom", searchPattern);
		return null;
	}


}
