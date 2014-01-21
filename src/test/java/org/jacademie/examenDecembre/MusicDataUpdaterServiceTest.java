package org.jacademie.examenDecembre;


import java.util.ArrayList;
import java.util.List;

import org.jacademie.examenDecembre.services.MusicDataException;
import org.jacademie.examenDecembre.services.MusicData;
import org.jacademie.examenDecembre.services.impl.MusicDataUpdaterServiceImpl;
import org.junit.Test;

public class MusicDataUpdaterServiceTest {

	@Test
	public void test() throws MusicDataException {
		List<MusicData> datas = new ArrayList<MusicData>();
		datas.add(new MusicData(2100, "Artiste 1", 2110, "Album 1", 2111, "Chanson 1", 111));
		datas.add(new MusicData(2100, "Artiste 1", 2110, "Album 1", 2112, "Chanson 2", 112));
		datas.add(new MusicData(2300, "Artiste 3", 2310, "Album 1", 2311, "Chanson 1", 111));
		datas.add(new MusicData(2300, "Artiste 3", 2320, "Album 2", 2321, "Chanson 1", 112));
		datas.add(new MusicData(2300, "Artiste 3", 2320, "Album 2", 2322, "Chanson 2", 112));
		
		MusicDataUpdaterServiceImpl updaterService = new MusicDataUpdaterServiceImpl(datas);
		
		updaterService.update();
		
	}

}
