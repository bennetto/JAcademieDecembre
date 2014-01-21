package org.jacademie.examenDecembre;



public class ReadFileMusic {
/*
	private static Logger logger = Logger.getLogger(ReadFileMusic.class);
	static ArtisteDAO artisteDAO = new ArtisteHibernateDAO();
	static AlbumDAO albumDAO = new AlbumHibernateDAO();
	static ChansonDAO chansonDAO = new ChansonHibernateDAO();

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
*/
}
