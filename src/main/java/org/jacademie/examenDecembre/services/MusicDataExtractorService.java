package org.jacademie.examenDecembre.services;

import java.io.Reader;
import java.util.List;

public interface MusicDataExtractorService {
	public List<MusicData> getDatas(Reader reader) throws MusicDataException;
}
