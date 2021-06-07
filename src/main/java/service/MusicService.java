package  service;

import java.sql.SQLException;

import dao.MusicDao;
import dao.impl.MusicDaoImpl;
import po.MusicPo;

public class MusicService {

	MusicDao musicDao = new MusicDaoImpl();

	public MusicService() {
	}

	public int create(MusicPo mu) throws SQLException {
		return musicDao.insert(mu);
	}

	public void getAll() throws SQLException {
		System.out.println(musicDao.findAll());
	}
	
	public String getFilePathByMd5value(String md5value) throws SQLException {
		MusicPo mu = musicDao.findByMd5value(md5value);
		return mu.getName();
	}

}
