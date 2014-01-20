package org.jacademie.examenDecembre;

import static org.jacademie.examenDecembre.utils.HibernateUtil.beginTransaction;
import static org.jacademie.examenDecembre.utils.HibernateUtil.closeSession;
import static org.jacademie.examenDecembre.utils.HibernateUtil.commitTransaction;
import static org.jacademie.examenDecembre.utils.HibernateUtil.flush;
import static org.jacademie.examenDecembre.utils.HibernateUtil.openSession;
import static org.jacademie.examenDecembre.utils.HibernateUtil.rollbackTransaction;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueObjectException;
import org.jacademie.examenDecembre.bo.Album;
import org.jacademie.examenDecembre.bo.Artiste;
import org.jacademie.examenDecembre.bo.Chanson;
import org.jacademie.examenDecembre.dao.IAlbumDAO;
import org.jacademie.examenDecembre.dao.IArtisteDAO;
import org.jacademie.examenDecembre.dao.IChansonDAO;
import org.jacademie.examenDecembre.dao.impl.AlbumHibernateDAO;
import org.jacademie.examenDecembre.dao.impl.ArtisteHibernateDAO;
import org.jacademie.examenDecembre.dao.impl.ChansonHibernateDAO;

import au.com.bytecode.opencsv.CSVReader;

public class ReadFileMusic {

	private static Logger logger = Logger.getLogger(ReadFileMusic.class);
	static IArtisteDAO artisteDAO = new ArtisteHibernateDAO();
	static IAlbumDAO albumDAO = new AlbumHibernateDAO();
	static IChansonDAO chansonDAO = new ChansonHibernateDAO();

	public static boolean readFile(String pathFileMusic) {
		logger.info("Read file : " + pathFileMusic);
		CSVReader csvReader;
		try {
			csvReader = new CSVReader(new FileReader(pathFileMusic));

			String[] rowAsTokens;

			openSession();
			beginTransaction();
			while ((rowAsTokens = csvReader.readNext()) != null) {

				if (rowAsTokens.length != 7) {
					rollbackTransaction();
					closeSession();
					return false;
				}

				Integer codeArtist = Integer.parseInt(rowAsTokens[0]);
				String nomArtiste = rowAsTokens[1];
				Integer codeAlbum = Integer.parseInt(rowAsTokens[2]);
				String nomAlbum = rowAsTokens[3];
				Integer numeroChanson = Integer.parseInt(rowAsTokens[4]);
				String titreChanson = rowAsTokens[5];
				Integer dureeChanson = Integer.parseInt(rowAsTokens[6]);

				
				Artiste artist = artisteDAO.getById(codeArtist);
				if (artist == null) {
					artist = new Artiste(codeArtist, nomArtiste);
				}
				else
				{
					artist.setNom(nomArtiste);
				}
				
				
				
				Album album = albumDAO.getById(codeAlbum);
				if (album == null) {
					album = new Album(codeAlbum,nomAlbum);
					artist.addAlbum(album);
				}
				else
				{
					album.setNom(nomAlbum);
				}
					
					
				
				Chanson chanson = chansonDAO.getByAlbumAndNum(album,numeroChanson);
				if (chanson == null) {
					chanson = new Chanson(numeroChanson, titreChanson,dureeChanson);
					album.addChanson(chanson);
				}
				else
				{
					chanson.setTitre(titreChanson);
					chanson.setDuree(dureeChanson);
				}
				
					artisteDAO.save(artist);
					flush();
				
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rollbackTransaction();
			closeSession();
			return false;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rollbackTransaction();
			closeSession();
			return false;
		} catch (NonUniqueObjectException e) {
			e.printStackTrace();
			rollbackTransaction();
			closeSession();
			return false;
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackTransaction();
			closeSession();
			return false;
		}

		commitTransaction();
		closeSession();
		return true;
	}

	private static boolean extractData() {
		return false;
	}

}
