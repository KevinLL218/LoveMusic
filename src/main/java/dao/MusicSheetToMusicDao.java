package dao;

import java.sql.SQLException;
import java.util.List;

import po.MusicSheetToMusic;

public interface MusicSheetToMusicDao {

	public int insert(MusicSheetToMusic mstm) throws SQLException;

	public void delete(int id) throws SQLException;

	public List<Integer> findByMusicSheetId(int id) throws SQLException;
}
