package org.jacademie.examenDecembre;

import static org.jacademie.examenDecembre.utils.HibernateUtil.beginTransaction;
import static org.jacademie.examenDecembre.utils.HibernateUtil.commitTransaction;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

import org.apache.log4j.Logger;
import org.jacademie.examenDecembre.DAOs.Album;
import org.jacademie.examenDecembre.DAOs.AlbumHibernateDAO;
import org.jacademie.examenDecembre.DAOs.Artiste;
import org.jacademie.examenDecembre.DAOs.ArtisteHibernateDAO;
import org.jacademie.examenDecembre.DAOs.Chanson;
import org.jacademie.examenDecembre.DAOs.ChansonHibernateDAO;
import org.jacademie.examenDecembre.DAOs.IAlbumDAO;
import org.jacademie.examenDecembre.DAOs.IArtisteDAO;
import org.jacademie.examenDecembre.DAOs.IChansonDAO;

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
			String out = "";
			while ((rowAsTokens = csvReader.readNext()) != null) {

				if (rowAsTokens.length != 7) {
					return false;
				}

				Integer codeArtist = Integer.parseInt(rowAsTokens[0]);
				String nomArtiste = rowAsTokens[1];
				Integer codeAlbum = Integer.parseInt(rowAsTokens[2]);
				String nomAlbum = rowAsTokens[3];
				Integer numeroChanson = Integer.parseInt(rowAsTokens[4]);
				String titreChanson = rowAsTokens[5];
				Integer dureeChanson = Integer.parseInt(rowAsTokens[6]);

				beginTransaction();
				Artiste artist = artisteDAO.getById(codeArtist);

				if (artist == null) {
					Chanson chanson = new Chanson(numeroChanson, titreChanson,dureeChanson);
					Album album = new Album(codeAlbum, nomAlbum, null);
					album.addChanson(chanson);
					artist = new Artiste(codeArtist, nomArtiste, null);
					artist.addAlbum(album);
					artisteDAO.save(artist);
				} else {
					Album album = albumDAO.getById(codeAlbum);
					if (album == null) {
						Chanson chanson = new Chanson(numeroChanson,titreChanson, dureeChanson);
						album = new Album(codeAlbum, nomAlbum, null);
						album.addChanson(chanson);
						artist.addAlbum(album);
						artisteDAO.update(artist);
					} else {
						Chanson chanson = chansonDAO.getByAlbumAndNum(album,numeroChanson);
						if (chanson == null) {
							chanson = new Chanson(numeroChanson, titreChanson,dureeChanson);
							album.addChanson(chanson);
							albumDAO.update(album);
						} else {
							chanson.setTitre(titreChanson);
							chanson.setDuree(dureeChanson);
							chansonDAO.update(chanson);
						}
					}
				}
				commitTransaction();

				logger.info(rowAsTokens[0]);
				for (String token : rowAsTokens) {
					out += token + " ";
				}
				logger.info(out);
				out = "";
			}

			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	private static boolean extractData() {
		return false;
	}

}
