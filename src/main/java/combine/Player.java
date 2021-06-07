package combine;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;

import classes.MenuBar;
import mainFrame.Main_Frame;


/**
 * 
 *  作者： 刘路
 *	描述：播放器总类
 *	修改时间：Nov 15, 2019
 *	备注信息：
 *	版本：1.0.0
 *	©copyright by Liulu 2019-2069
 */
public class Player {
	//myList 我的歌单
	//othersList 别人的歌单
	//playBoard 播放主页面
	//playControl 播放控制
	public static void main(String[] args){
		Main_Frame mainFrame = new Main_Frame();
		PlayList_Panel.createPlayList();
		//add PlayList Panel
		//set size
		mainFrame.getContentPane().add("West",PlayList_Panel.p);
		PlayWindow_Panel.createPlayWindow();
		//add Music Player Main Window
		//MusicPlayerWindows.pGridLayout.add(comp);
		mainFrame.getContentPane().add("Center",PlayWindow_Panel.panel);
		//mainFrame.setJMenuBar(MenuBar.menuBar_MenuBar());
		mainFrame.setVisible(true);
		try {
			Thread.sleep(5000);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
