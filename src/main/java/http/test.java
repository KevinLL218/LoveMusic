package http;

import java.util.ArrayList;
import java.util.List;
import po.MusicSheet;

public class test {
	public static void main(String[] args){
		String url = "http://service.uspacex.com/music.server/upload";
		//音乐文件路径存储对象
		List<String>filePaths = new ArrayList<String>();
		filePaths.add("src/main/resources/hotaru.mp3");
		filePaths.add("src/main/resources/koioto.mp3");
		filePaths.add("src/main/resources/hibi.mp3");
		//filePaths.add("src/main/resources/hanabi.mp3");
		//filePaths.add("src/main/resources/inochinonamae.mp3");
		//filePaths.add("src/main/resources/kazeninaru.mp3");

		//歌单对象
		MusicSheet ms = new MusicSheet();
		ms.setCreatorId("18030031014");
		ms.setPicture("src/main/resources/1.png");
		ms.setCreator("刘路");
		ms.setName("LL2");
		MusicSheetAndFilesUploader.createMusicSheetAndUploadFiles(url, ms, filePaths);
	}
	
}
