package org.jacademie.examenDecembre.services;

import java.io.File;
import java.io.IOException;

public interface FileMoverService {
	public void moveFileToFolder(File file, String destinationPath) throws IOException;
}
