package dao;

import java.sql.SQLException;
import java.util.List;

import po.MusicPo;

public interface MusicDao {

	public int insert(MusicPo mu) throws SQLException;

	public void update(MusicPo mu) throws SQLException;

	public void delete(int id) throws SQLException;

	public void deleteByMd5value(String md5value) throws SQLException;

	public MusicPo findById(int id) throws SQLException;

	public List<MusicPo> findAll() throws SQLException;

	public MusicPo findByMd5value(String md5value) throws SQLException;

}
