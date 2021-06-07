package  service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.MusicDao;
import dao.MusicSheetDao;
import dao.MusicSheetToMusicDao;
import dao.impl.MusicDaoImpl;
import dao.impl.MusicSheetDaoImpl;
import dao.impl.MusicSheetToMusicDaoImpl;
import po.MusicPo;
import po.MusicSheet;
import po.MusicSheetToMusic;


public class MusicSheetService {

	MusicSheetDao musicSheetDao = new MusicSheetDaoImpl();
	MusicDao musicDao = new MusicDaoImpl();
	MusicSheetToMusicDao mstmDao = new MusicSheetToMusicDaoImpl();

	public MusicSheetService() {
	}

	public int create(MusicSheet ms) throws SQLException {
		return musicSheetDao.insert(ms);
	}

	public void createOrUpdate(MusicSheet ms) throws SQLException {

		MusicSheet musicSheet = musicSheetDao.findByUuid(ms.getUuid());

		MusicPo mu = null;
		MusicSheetToMusic mstm = null;
		int musicSheetId;
		int musicId;

		if (musicSheet == null) {
			musicSheetId = musicSheetDao.insert(ms);

			for (String key : ms.getMusicItems().keySet()) {
				mu = musicDao.findByMd5value(key);

				if (mu == null) {
					mu = new MusicPo(key, ms.getMusicItems().get(key));
					musicId = musicDao.insert(mu);

				} else {
					mu.setName(ms.getMusicItems().get(key));
					musicDao.update(mu);
					musicId = mu.getId();
				}

				mstm = new MusicSheetToMusic(musicSheetId, musicId);
				mstmDao.insert(mstm);
			}

		} else {
			musicSheetDao.update(ms);
			musicSheetId = musicSheet.getId();

			musicSheet.getMusicItems();

			for (String key : ms.getMusicItems().keySet()) {
				mu = musicDao.findByMd5value(key);

				if (mu == null) {
					mu = new MusicPo(key, ms.getMusicItems().get(key));
					musicId = musicDao.insert(mu);
					mstm = new MusicSheetToMusic(musicSheetId, musicId);
					mstmDao.insert(mstm);

				} else {
					mu.setName(ms.getMusicItems().get(key));
					musicDao.update(mu);
				}
			}
		}
	}

	public List<MusicSheet> getAll() throws SQLException {
		List<MusicSheet> mslist = musicSheetDao.findAll();

		MusicPo mu = null;
		for (MusicSheet ms : mslist) {
			Map<String, String> musicItems = new HashMap<String, String>();

			for (int musicId : mstmDao.findByMusicSheetId(ms.getId())) {
				mu = musicDao.findById(musicId);
				musicItems.put(mu.getMd5value(), mu.getName());
			}

			ms.setMusicItems(musicItems);
		}

		return mslist;
	}

	public String getMusicSheetPictureUrl(String uuid) throws SQLException {
		MusicSheet musicSheet = musicSheetDao.findByUuid(uuid);
		return musicSheet.getPicture();
	}

	public void delete(String uuid) throws SQLException {
		musicSheetDao.deleteByUuid(uuid);
	}
}
