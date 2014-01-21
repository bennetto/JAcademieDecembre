package org.jacademie.examenDecembre.dao.impl;

import java.util.List;

import org.jacademie.examenDecembre.bo.Artiste;
import org.jacademie.examenDecembre.dao.ArtisteDAO;

public class ArtisteHibernateDAO extends GeneriqueHibernateDAO<Artiste> implements ArtisteDAO {

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
