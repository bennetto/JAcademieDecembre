package org.jacademie.examenDecembre.services.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jacademie.examenDecembre.services.FileMoverService;

public class FileMoverServiceImpl implements FileMoverService{

	private static final Logger logger = Logger.getLogger(FileMoverServiceImpl.class);
	
	public FileMoverServiceImpl() {
		super();
	}

	@Override
	public void moveFileToFolder(File file, String destinationFolder) throws IOException{
		

		//create directory if don't exist
		Path pathDirectoryTarget = new File(destinationFolder).toPath();
		if(!Files.exists(pathDirectoryTarget))
		{
			Files.createDirectories(pathDirectoryTarget);
			logger.debug("Directory " + destinationFolder + " create");
		}
		
		
		//move file
		Path pathTarget = new File(destinationFolder+"/"+file.getName()).toPath();
		Files.move(file.toPath(), pathTarget,StandardCopyOption.REPLACE_EXISTING);
		logger.debug("File " + file.getName() + " moved to " + file.getAbsolutePath());

		
	}

}
