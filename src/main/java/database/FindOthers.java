package database;

import java.io.IOException;
import java.util.Iterator;

import http.MusicSheetTaker;
import panels.OtherList_Box;
import po.MusicSheet;

public class FindOthers {
	public static String[][] findList() {
		String[][] names = {{"hello"}};
		MusicSheet msheet = new MusicSheet();
		int rows = 0;
		String url = "http://service.uspacex.com/music.server/queryMusicSheets?type=top20";
		try {
			OtherList_Box.MS = MusicSheetTaker.queryMusicSheets(url);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(OtherList_Box.MS!=null){//如果不为空
			Iterator<MusicSheet> it =(OtherList_Box.MS).iterator();
			
			names = new String[OtherList_Box.MS.size()<10?OtherList_Box.MS.size():10][2];//开辟内存
			
			
			while(it.hasNext()){
				msheet = it.next();
				names[rows++][0] = msheet.getName();
				
				//////显示前十个
				if(rows==10)break;
				
				
				
			}
			
			
		}
		
		
		
		return names;
	}
}
