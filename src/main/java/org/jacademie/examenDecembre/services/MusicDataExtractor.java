package org.jacademie.examenDecembre.services;

import java.io.Reader;
import java.util.List;

public interface MusicDataExtractor {
	public List<MusicData> getDatas(Reader reader) throws MusicDataException;
}
