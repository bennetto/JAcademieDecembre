package org.jacademie.examenDecembre.services.impl;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.jacademie.examenDecembre.services.FileMoverService;

public class FileMoverServiceImpl implements FileMoverService{

	private static final Logger logger = Logger.getLogger(FileMoverServiceImpl.class);
	
	public FileMoverServiceImpl() {
		super();
	}

	@Override
	public void moveFileToFolder(File file, String destinationFolder) throws IOException{
		// FIXME cannot move file... 
		if(file.renameTo(new File(destinationFolder + "/" + file.getName()))){
			logger.debug("File " + file.getName() + " moved to " + file.getAbsolutePath());
		}else{
			throw new IOException("Cannot copy file " + file.getName() + " to " + destinationFolder);
		}
		
	}

}
