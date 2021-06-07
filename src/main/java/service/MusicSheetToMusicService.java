package  service;

import java.sql.SQLException;

import dao.MusicSheetToMusicDao;
import dao.impl.MusicSheetToMusicDaoImpl;
import po.MusicSheetToMusic;

 
public class MusicSheetToMusicService {

	MusicSheetToMusicDao mstmDao = new MusicSheetToMusicDaoImpl();

	public MusicSheetToMusicService() {
	}

	public int create(MusicSheetToMusic mstm) throws SQLException {
		return mstmDao.insert(mstm);
	}

}
