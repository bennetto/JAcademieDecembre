package org.jacademie.examenDecembre.services.impl;

import java.io.File;
import java.io.IOException;
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
		
		FileUtils.moveFileToDirectory(file, new File(destinationFolder), true);

	}

}
