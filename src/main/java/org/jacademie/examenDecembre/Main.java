package org.jacademie.examenDecembre;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.jacademie.examenDecembre.services.MusicData;
import org.jacademie.examenDecembre.services.MusicDataException;
import org.jacademie.examenDecembre.services.MusicDataExtractorService;
import org.jacademie.examenDecembre.services.MusicDataUpdaterService;
import org.jacademie.examenDecembre.services.impl.CSVMusicDataExtractor;
import org.jacademie.examenDecembre.services.impl.MusicDataUpdaterServiceImpl;



/**
 * Hello world!
 *
 */
public class Main 
{
	private static Logger logger = Logger.getLogger(Main.class);
	
    public static void main( String[] args )
    {
    	logger.info("test");
    	updateMusicFromFiles();
    }
    
    public static void updateMusicFromFiles(){

		Properties prop = new Properties();
		
		try {
			prop.load(Main.class.getClassLoader().getResourceAsStream("confCSV.properties"));
			String folderPath = prop.getProperty("inputPath");
			File repository = new File(folderPath);
			logger.info(repository.getPath());

			String[] fileNames = repository.list();
			
			MusicDataExtractorService dataExtractor = new CSVMusicDataExtractor();
			MusicDataUpdaterService dataUpdater = new MusicDataUpdaterServiceImpl();
			
			for ( int i=0; i<fileNames.length; i++ ) {
				if ( fileNames[i].toLowerCase().endsWith(".music") == true ) {
					String pathFileMusic = folderPath + "/" + fileNames[i];
					Reader reader = new FileReader(pathFileMusic);
					try {
						List<MusicData> datas = dataExtractor.getDatas(reader);
						dataUpdater.update(datas);
						logger.info("Fichier " + pathFileMusic + "mis a jour avec succès");
						
						//TODO : move to Success folder
					} catch (MusicDataException e) {
						logger.info("Erreur lors de la mise a jour a partir du fichier " + pathFileMusic, e);
						
						//TODO : move to Failed folde
					}
				}
			}
		} catch (IOException e) {
			logger.error("Erreur lors de la lecture du fichier", e);
		}
		
		
    	/*
    	public static void main(String[] args){


    		  Properties prop = new Properties();
    		  //load a properties file
    		  try {
    			prop.load(DataUpdaterService.class.getClassLoader().getResourceAsStream("confCSV.properties"));


    			String folderCSV = prop.getProperty("folderIn");
    			File repository = new File(folderCSV);
    			logger.info(repository.getPath());

    			String[] listCSV = repository.list();
    			for (int i = 0; i < listCSV.length; i++) {
    				if (listCSV[i].toLowerCase().endsWith(".music") == true ) {
    						String fileCSV = folderCSV+"/"+listCSV[i];
    						boolean success =  ReadFileMusic.readFile(fileCSV);
    				}
    			}

    		  } catch (IOException ex) {
    	    		ex.printStackTrace();
    	        }

    	  }
    	 */
    }
}
