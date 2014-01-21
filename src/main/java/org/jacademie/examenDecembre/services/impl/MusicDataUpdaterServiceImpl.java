package org.jacademie.examenDecembre.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jacademie.examenDecembre.bo.Album;
import org.jacademie.examenDecembre.bo.Artiste;
import org.jacademie.examenDecembre.bo.Chanson;
import org.jacademie.examenDecembre.dao.AlbumDAO;
import org.jacademie.examenDecembre.dao.ArtisteDAO;
import org.jacademie.examenDecembre.dao.ChansonDAO;
import org.jacademie.examenDecembre.dao.impl.AlbumHibernateDAO;
import org.jacademie.examenDecembre.dao.impl.ArtisteHibernateDAO;
import org.jacademie.examenDecembre.dao.impl.ChansonHibernateDAO;
import org.jacademie.examenDecembre.services.MusicData;
import org.jacademie.examenDecembre.services.MusicDataException;
import org.jacademie.examenDecembre.services.MusicDataUpdaterService;
import org.jacademie.examenDecembre.utils.HibernateManager;
import org.jacademie.examenDecembre.utils.PersistenceManager;

public class MusicDataUpdaterServiceImpl implements MusicDataUpdaterService{
	private static final Logger logger = Logger.getLogger(MusicDataUpdaterServiceImpl.class);

	private List<MusicData> datas;
	private ArtisteDAO artisteDAO = new ArtisteHibernateDAO();
	private AlbumDAO albumDAO = new AlbumHibernateDAO();
	private ChansonDAO chansonDAO = new ChansonHibernateDAO();
	private PersistenceManager persistenceManager = new HibernateManager();

	public MusicDataUpdaterServiceImpl(){
		this(new ArrayList<MusicData>());
	}

	public MusicDataUpdaterServiceImpl(List<MusicData> datas){
		this.datas = datas;
	}


	public void update(List<MusicData> datas) throws MusicDataException{
		this.datas = datas;  
		update();
	}

	public void update() throws MusicDataException{

		persistenceManager.openSession();
		persistenceManager.beginTransaction();

		for( MusicData data : datas ){
			if(!isDataComplete(data)){
				persistenceManager.rollbackTransaction();
				persistenceManager.closeSession();

				throw new MusicDataException();
			}


			Artiste artist = artisteDAO.getById(data.getCodeArtist());
			if (artist == null) {
				artist = new Artiste(data.getCodeArtist(), data.getNomArtiste());
			}
			else
			{
				artist.setNom(data.getNomArtiste());
			}


			Album album = albumDAO.getById(data.getCodeAlbum());
			if (album == null) {
				album = new Album(data.getCodeAlbum(),data.getNomAlbum());
				artist.addAlbum(album);
			}
			else
			{
				album.setNom(data.getNomAlbum());
			}

			Chanson chanson = chansonDAO.getByAlbumAndNum(album,data.getNumeroChanson());
			if (chanson == null) {
				chanson = new Chanson(data.getNumeroChanson(), data.getTitreChanson(),data.getDureeChanson());
				album.addChanson(chanson);
			}
			else
			{
				chanson.setTitre(data.getTitreChanson());
				chanson.setDuree(data.getDureeChanson());
			}

			artisteDAO.save(artist);
			persistenceManager.flush();
		}

		persistenceManager.commitTransaction();
		persistenceManager.closeSession();
	}



	private boolean isDataComplete(MusicData data) {
		if(data.getCodeArtist() == null)
			return false;
		if(data.getNomArtiste() == null || data.getNomArtiste() == "")
			return false;
		if(data.getCodeAlbum() == null)
			return false;
		if(data.getNomAlbum() == null || data.getNomAlbum() == "")
			return false;
		
		//TODO : Complete
		return true;
	}

}
