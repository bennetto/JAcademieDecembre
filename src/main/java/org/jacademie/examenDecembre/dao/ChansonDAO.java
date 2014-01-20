package org.jacademie.examenDecembre.dao;

import org.jacademie.examenDecembre.bo.Album;
import org.jacademie.examenDecembre.bo.Chanson;

public interface ChansonDAO extends GeneriqueDAO<Chanson>{
	public Chanson getByAlbumAndNum(Album album, Integer numero);
}
