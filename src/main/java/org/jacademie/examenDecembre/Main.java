package org.jacademie.examenDecembre;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.jacademie.examenDecembre.services.FileMoverService;
import org.jacademie.examenDecembre.services.MusicData;
import org.jacademie.examenDecembre.services.MusicDataException;
import org.jacademie.examenDecembre.services.MusicDataExtractorService;
import org.jacademie.examenDecembre.services.MusicDataUpdaterService;
import org.jacademie.examenDecembre.services.impl.CSVMusicDataExtractor;
import org.jacademie.examenDecembre.services.impl.FileMoverServiceImpl;
import org.jacademie.examenDecembre.services.impl.MusicDataUpdaterServiceImpl;



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
			String sucessPath = prop.getProperty("successOutputPath");
			String failPath = prop.getProperty("failOutputPath");
			String dateFormat = prop.getProperty("timeExtension");
			String dateExt = new SimpleDateFormat(dateFormat).format(new Date());
			
			File repository = new File(folderPath);
			logger.info(repository.getPath());

			String[] fileNames = repository.list();
			
			MusicDataExtractorService dataExtractor = new CSVMusicDataExtractor();
			MusicDataUpdaterService dataUpdater = new MusicDataUpdaterServiceImpl();
			FileMoverService fileMover = new FileMoverServiceImpl();
			
			
			for ( int i=0; i<fileNames.length; i++ ) {
				if ( fileNames[i].toLowerCase().endsWith(".music") == true ) {
					String pathFileMusic = folderPath + "/" + fileNames[i];
					File file = new File(pathFileMusic);
					Reader reader = new FileReader(pathFileMusic);
					try {
						try {
							List<MusicData> datas = dataExtractor.getDatas(reader);
							reader.close();
							dataUpdater.update(datas);
							logger.info("Fichier " + fileNames[i] + " mis a jour avec succès");
							
							fileMover.moveFileToFolder(file, sucessPath+dateExt);
						} catch (MusicDataException e) {
							logger.error("Erreur lors de la mise a jour a partir du fichier " + fileNames[i], e);
							reader.close();
							fileMover.moveFileToFolder(file, failPath+dateExt);
						}
					} catch(IOException e){
						reader.close();
						logger.error("Erreur lors du déplacement d'un fichier", e);
					}
				}
			}
		} catch (IOException e) {
			logger.error("Erreur lors de la lecture du fichier", e);
		}
		
    }
}
