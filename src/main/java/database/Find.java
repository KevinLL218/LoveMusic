package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.MyMD5Util;
import combine.SongsList;
import panels.MyList_Box;
import po.MusicPo;
import po.MusicSheet;

/**
 * 
 * 作者： 刘路 描述：负责查找相关操作 修改时间：Dec 7, 2019 备注信息： 版本：1.0.0 ©copyright by Liulu
 * 2019-2069
 */
public class Find {
	// 返回值为ResultSet类型
	public static String[][] findMyList(String s) {
		String[][] names = null;
		int rows = 0;
		try {
			// 0 连接SQLite的JDBC
			String sql = "jdbc:sqlite://e:/java/sqlite/TestDB.db";
			Class.forName("org.sqlite.JDBC");

			Connection conn = DriverManager.getConnection(sql);
			Statement stat = conn.createStatement();
			ResultSet res = stat.executeQuery("select * from MusicSheet as MS ");
			// ResultSet rs = stat.executeQuery("select * from tbl1;"); // 查询数据
			while(res.next())
				rows++;
			res.close();
			MyList_Box.MS = new MusicSheet[rows];
			
			for(int i=0;i<rows;i++){
				MyList_Box.MS[i] = new MusicSheet();
			}
			
			
			names = new String[rows][2];
			res = stat.executeQuery("select * from MusicSheet as MS");
			for(int i=0;i<rows && res.next();i++){
				//System.out.println("1"+res.getString(1)+" 2:"+res.getString(2)+" 3:"+res.getString(3));
				MyList_Box.MS[i].setUuid(res.getString("uuid"));//uuid  name cid date pic
				MyList_Box.MS[i].setName(res.getString("name"));
				MyList_Box.MS[i].setCreatorId(res.getString("creatorId"));
				MyList_Box.MS[i].setDateCreated(res.getString("dateCreated"));
				MyList_Box.MS[i].setCreator(res.getString("creator"));
				MyList_Box.MS[i].setPicture(res.getString("picture"));
				//System.out.println(res.getString(1));
				names[i][0] = res.getString(1);
				
			}
			//System.out.println(row);

			res.close();
			/*
			 * while (rs.next()) { // 将查询到的数据打印出来 System.out.print("name = " +
			 * rs.getString("name") + ", "); // 列属性一 System.out.println(
			 * "salary = " + rs.getString("salary")); // 列属性二 }
			 */
			// rs.close();

			// 3 修改表结构，添加字段 address varchar(20) default 'changsha';
			// stat.executeUpdate("alter table tbl1 add column address
			// varchar(20) not null default 'changsha'; ");// 创建一个表，两列
			// stat.executeUpdate("insert into tbl1
			// values('HongQi',9000,'tianjing');"); // 插入数据
			// stat.executeUpdate("insert into tbl1(name,salary)
			// values('HongQi',9000);"); // 插入数据
			// rs = stat.executeQuery("select * from tbl1;"); // 查询数据
			// System.out.println("表结构变更操作演示：");
			// while (rs.next()) { // 将查询到的数据打印出来
			// System.out.print("name = " + rs.getString("name") + ", "); //
			// 列属性一
			// System.out.print("name = " + rs.getString("name") + ", "); //
			// 列属性二
			// System.out.println("address = " + rs.getString("address")); //
			// 列属性三
			// }
			// rs.close();

			conn.close(); // 结束数据库的连接

		} catch (Exception e) {
			e.printStackTrace();
		}
		return names;
	}

	public static String[][] findSongs(String listUuid){
		String Uuid = listUuid;
		if(Uuid.equals("")){
			return null;
		}
		String md5="";
		ResultSet res = null;
		String[][] names = {{"song"},{"song3"}};
		int rows = 0;
		try {
			// 0 连接SQLite的JDBC
			String sql = "jdbc:sqlite://e:/java/sqlite/TestDB.db";
			Class.forName("org.sqlite.JDBC");

			Connection conn = DriverManager.getConnection(sql);
			Statement stat = conn.createStatement();
			//boolean flag = stat.execute("CREATE TABLE IF NOT EXISTS "+Uuid+"(name text,singer text,md5value text primary key)");
			res = stat.executeQuery("SELECT count(*) FROM sqlite_master WHERE type='table' AND name='"+Uuid+"'");
			
			if(!res.next()){
				res.close();
				conn.close();
				return names;
			}
			
			res = stat.executeQuery("select * from "+Uuid);
			// ResultSet rs = stat.executeQuery("select * from tbl1;"); // 查询数据
			while(res.next())
				rows++;
			res.close();
			SongsList.MP =  new MusicPo[rows];
			
			for(int i=0;i<rows;i++){
				SongsList.MP[i] = new MusicPo();
			}
			
			
			names = new String[rows][2];
			res = stat.executeQuery("select * from "+Uuid);
			for(int i=0;i<rows && res.next();i++){
				//System.out.println("1"+res.getString(1)+" 2:"+res.getString(2)+" 3:"+res.getString(3));
				SongsList.MP[i].setName(res.getString("name"));
				SongsList.MP[i].setSinger(res.getString("singer"));
				md5 = res.getString("md5value");
				if(md5.length()<=5)
					md5 = MyMD5Util.encrypt(res.getString("name"));
				SongsList.MP[i].setMd5value(md5);
				//System.out.println(SongsList.MP[i].getMd5value());
				//System.out.println(res.getString(1));
				names[i][0] = res.getString("name");
				
			}
			//System.out.println(row);
			
			res.close();
			/*
			 * while (rs.next()) { // 将查询到的数据打印出来 System.out.print("name = " +
			 * rs.getString("name") + ", "); // 列属性一 System.out.println(
			 * "salary = " + rs.getString("salary")); // 列属性二 }
			 */
			// rs.close();

			// 3 修改表结构，添加字段 address varchar(20) default 'changsha';
			// stat.executeUpdate("alter table tbl1 add column address varchar(20) not null default 'changsha'; ");// 创建一个表，两列
			// stat.executeUpdate("insert into tbl1
			// values('HongQi',9000,'tianjing');"); // 插入数据
			// stat.executeUpdate("insert into tbl1(name,salary) values('HongQi',9000);"); // 插入数据
			// rs = stat.executeQuery("select * from tbl1;"); // 查询数据
			// System.out.println("表结构变更操作演示：");
			// while (rs.next()) { // 将查询到的数据打印出来
			// System.out.print("name = " + rs.getString("name") + ", "); //
			// 列属性一
			// System.out.print("name = " + rs.getString("name") + ", "); //
			// 列属性二
			// System.out.println("address = " + rs.getString("address")); //
			// 列属性三
			// }
			// rs.close();

			conn.close(); // 结束数据库的连接

		} catch (Exception e) {
			e.printStackTrace();
		}
		return names;
	}

	//public static void main(String args[]) {
		//String[] s = findMyList("  ");
		//for(String o:s){
			//System.out.println(o);
		//}
		//System.out.println(s);
		//ResultSet r = findSongs("tbl1");
	//}

}
