package combine;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import http.FileDownloader;
import panels.Image_Panel;
import panels.MyList_Box;
import po.MusicSheet;

/**
 * 
 *  作者： 刘路
 *	描述：detail of one playlist
 *	修改时间：Nov 26, 2019
 *	备注信息：
 *	版本：1.0.0
 *	©copyright by Liulu 2019-2069
 */
public class SongsDetail {
	public static JPanel panel = null;
	private static JLabel Limg = null;// 右上角的图片
	private static CardLayout card = new CardLayout();
	private static JPanel p2 = new JPanel(card);// 用于存储图片的panel，格式为card
	private static JPanel p1 = new JPanel(card);// 用于存储歌单信息panel，格式为card
	public static void createSDetial(){
		panel = new JPanel(null);
		Rectangle R1 = new Rectangle(10,10,480,180);
		Rectangle R2 = new Rectangle(500,10,180,180);
		p1.setBounds(R1);
		p2.setBounds(R2);
		//p1.setBackground(Color.BLUE);
		//p2.setBackground(Color.yellow);
		
		createPanel();
		p2.add(Limg);
		//TODO add description here
		panel.add(p1);
		//TODO add picture here
		panel.add(p2);
		
	}
	// 读取图片
	private static void createPanel(){
		ImageIcon img = new ImageIcon("src/main/java/imgs/bg.jpg");//要设置的背景图片
		Limg = new JLabel(img);//将背景图放在标签里。
		Limg.setBounds(0, 0, 180, 180);	
	}
	//改变window上显示的歌曲图片
	public static void changeIcon(int uuid){

		//读取图片
		ImageIcon img = null;
		if(uuid<MyList_Box.MS.length){
			
			System.out.println("UUID"+uuid+MyList_Box.MS[uuid].getPicture());
			
			img = new ImageIcon(MyList_Box.MS[uuid].getPicture());
		}
		
		Limg = new JLabel(img);//将背景图放在标签里。
		Limg.setBounds(0, 0, 180, 180);	
		// 控制p2中图片的数量
		int count = p2.getComponentCount();
		if(count >= 2){
			// [c1]->[c2]->[c3]  -->  [c1]->[c3] 删除了[c2] 减少开销
			p2.remove(1);
		}
		// 将该图片放到p2中
		p2.add(Limg);
		//显示最新的图片
		card.last(p2);
		panel.validate();
	}
	public static void changeIcon(MusicSheet ms){
		//读取图片
		ImageIcon img = null;
		String url = "http://service.uspacex.com/music.server/downloadPicture";
		String PicPath = "src/main/resources";
		FileDownloader.downloadMusicSheetPicture(url, ms.getUuid(), PicPath);
		
		img = new ImageIcon(PicPath + "/" + ms.getPicture());

		img.setImage(img.getImage().getScaledInstance(180, 180,Image.SCALE_DEFAULT ));	
		
		Limg = new JLabel(img);//将背景图放在标签里。
		Limg.setBounds(0, 0, 180, 180);	
		// 控制p2中图片的数量
		int count = p2.getComponentCount();
		if(count >= 2){
			// [c1]->[c2]->[c3]  -->  [c1]->[c3] 删除了[c2] 减少开销
			p2.remove(1);
		}
		// 将该图片放到p2中
		p2.add(Limg);
		//显示最新的图片
		card.last(p2);
		panel.validate();
	}
	public static void changeInfo(String Info){
		 // 创建一个 5 行 10 列的文本区域
        final JTextArea textArea = new JTextArea(5, 10);
        // 设置自动换行
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setText(Info);
        // 添加到内容面板
        p1.removeAll();
        p1.add(textArea);
        panel.validate();
	}
}
