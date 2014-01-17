package org.jacademie.examenDecembre;

import static org.jacademie.examenDecembre.utils.HibernateUtil.beginTransaction;
import static org.jacademie.examenDecembre.utils.HibernateUtil.commitTransaction;
import static org.junit.Assert.*;

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
import org.jacademie.examenDecembre.utils.HibernateUtil;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DAOsTest  {
	
	
	private static final Logger logger = Logger.getLogger(DAOsTest.class);
	
	static private Artiste art0 = new Artiste(1000, "Abe", null);
	static private Artiste art1 = new Artiste(1001, "Bob", null);
	static private Artiste art2 = new Artiste(1002, "Carol", null);
	static private Album alb1_0 = new Album(2010, "First @lbum", null);
	static private Album alb2_0 = new Album(2020, "First album", null);
	static private Chanson chanson1 = new Chanson(1, "First Chanson", 101);
	static private Chanson chanson2 = new Chanson(2, "Second Chanson", 102);
	static private Chanson chanson3 = new Chanson(3, "Third Chanson", 103);
	
	static IArtisteDAO artisteDAO = new ArtisteHibernateDAO();
	static IAlbumDAO albumDAO = new AlbumHibernateDAO();
	static IChansonDAO chansonDAO = new ChansonHibernateDAO();
	
	@BeforeClass
	public static void initDB(){
		HibernateUtil.openSession();
		beginTransaction();
		artisteDAO.save(art0);
		commitTransaction();
	}
	
	@Test
	public void findArtistByID(){
		Artiste artiste = art0;
		Artiste artisteRetrieved; 
		beginTransaction();
		artisteRetrieved = artisteDAO.getById(artiste.getCodeArtiste());
		commitTransaction();
		assertEquals(artiste, artisteRetrieved);

	}
	@Test
	public void findArtistByName(){
		Artiste artiste = art0;
		Artiste artisteRetrieved; 
		beginTransaction();
		artisteRetrieved = artisteDAO.getOneByName(artiste.getNom());
		commitTransaction();
		assertEquals(artiste, artisteRetrieved);
	}
	@Test
	public void persistAlbumByArtistWay(){
		Artiste artiste = art1;
		Artiste artisteRetrieved;
		Album album = alb1_0;
		Album albumRetrived;
		Album albumRetrivedUpdated;
		
		beginTransaction();
		artisteDAO.save(artiste);
		artiste.addAlbum(album);
		commitTransaction();

		beginTransaction();
		albumRetrived = albumDAO.getById(album.getCodeAlbum());
		artisteRetrieved = artisteDAO.getById(artiste.getCodeArtiste());
		commitTransaction();
		assertEquals("Cannot retrieve album", album, albumRetrived);
		assertTrue("Artist doesn't contain album added", artisteRetrieved.getAlbums().contains(albumRetrived));
		
		
		albumRetrived.setNom("First album!!");
		beginTransaction();
		albumDAO.update(albumRetrived);
		commitTransaction();
		
		beginTransaction();
		artisteRetrieved = artisteDAO.getById(artiste.getCodeArtiste());
		albumRetrivedUpdated = albumDAO.getById(album.getCodeAlbum());
		commitTransaction();
		assertEquals("Update failed", albumRetrived, albumRetrivedUpdated);
		assertTrue("Artist doesn't contain album updated", artisteRetrieved.getAlbums().contains(albumRetrivedUpdated));
	}
	
	@Test
	public void persistChansonByArtistWay(){
		Artiste artiste = art2;
		Album album = alb2_0;
		Chanson chansonRetrieved;
		
		beginTransaction();
		artisteDAO.save(artiste);
		artiste.addAlbum(album);
		artisteDAO.update(artiste);
		album.addChanson(chanson1);
		album.addChanson(chanson2);
		album.addChanson(chanson3);
		commitTransaction();

		beginTransaction();
		chansonRetrieved = chansonDAO.getByAlbumAndNum(chanson1.getAlbum(), chanson1.getNumero());
		logger.info("---"+chanson1 +" vs. "+ chansonRetrieved);
		assertEquals("Cannot retrieve chanson", chansonRetrieved, chanson1);
		commitTransaction();
		
	}
	

	
	@AfterClass
	public static void closeDB(){
		HibernateUtil.closeSession();
	}

}
