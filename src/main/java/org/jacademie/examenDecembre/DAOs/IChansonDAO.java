package org.jacademie.examenDecembre.DAOs;

import java.util.Set;

public interface IChansonDAO extends IGeneriqueDAO<Chanson>{
	public Chanson getByAlbumAndNum(Album album, Integer numero);
}
