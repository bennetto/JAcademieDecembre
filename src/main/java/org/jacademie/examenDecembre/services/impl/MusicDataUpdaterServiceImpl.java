package org.jacademie.examenDecembre.services.impl;

import static org.jacademie.examenDecembre.utils.HibernateUtil.beginTransaction;
import static org.jacademie.examenDecembre.utils.HibernateUtil.closeSession;
import static org.jacademie.examenDecembre.utils.HibernateUtil.commitTransaction;
import static org.jacademie.examenDecembre.utils.HibernateUtil.flush;
import static org.jacademie.examenDecembre.utils.HibernateUtil.openSession;
import static org.jacademie.examenDecembre.utils.HibernateUtil.rollbackTransaction;

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

public class MusicDataUpdaterServiceImpl implements MusicDataUpdaterService{
	private static final Logger logger = Logger.getLogger(MusicDataUpdaterServiceImpl.class);

	private List<MusicData> datas;
	private ArtisteDAO artisteDAO = new ArtisteHibernateDAO();
	private AlbumDAO albumDAO = new AlbumHibernateDAO();
	private ChansonDAO chansonDAO = new ChansonHibernateDAO();

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

		openSession();
		beginTransaction();

		for( MusicData data : datas ){
			if(!isDataComplete(data)){
				rollbackTransaction();
				closeSession();

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
			flush();
		}

		commitTransaction();
		closeSession();
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
