package org.jacademie.examenDecembre.services;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVReader;

public class FileUpdaterService {
	/**
	   * @param args
	   * @throws IOException 
	   */
	private static final Logger logger = Logger.getLogger(FileUpdaterService.class);
	  public static void main(String[] args) throws IOException {
		  
		  
	    /**
	     * Load CSV from classpath
	     */
		  
		  Properties prop = new Properties();
		  //load a properties file
		  try {
			prop.load(FileUpdaterService.class.getClassLoader().getResourceAsStream("confCSV.properties"));
			  
			
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
}
