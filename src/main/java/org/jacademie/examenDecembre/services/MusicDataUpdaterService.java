package org.jacademie.examenDecembre.services;

import java.util.List;

public interface MusicDataUpdaterService {
	public void update() throws MusicDataException;
	public void update(List<MusicData> datas) throws MusicDataException;
}
