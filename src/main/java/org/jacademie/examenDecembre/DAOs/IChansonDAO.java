package org.jacademie.examenDecembre.DAOs;

public interface IChansonDAO extends IGeneriqueDAO<Chanson>{
	public Chanson getByAlbumAndNum(Album album, Integer numero);
}
