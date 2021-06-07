package mainFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * 作者： 刘路 描述：main frame 修改时间：Nov 26, 2019 备注信息： 版本：1.0.0 ©copyright by Liulu
 * 2019-2069
 */
public class Main_Frame extends JFrame {
	JPanel contentPanel = null;
	Image im = null;
	public Main_Frame() {
		super("MusicPlayer");
		setSize(new Dimension(1024, 768));
		setLocationRelativeTo(null); // 把窗口位置设置到屏幕中心
		setResizable(false);// 设置窗口为不可缩放
		ImageIcon ig = new ImageIcon("src/imgs/pause.png");//这里放上你要设置图标图片
		im = ig.getImage();
		setIconImage(im);
		// setVisible(true);//设置为窗口可见
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置用户在此窗体上发起 "close"
														// 时默认执行的操作。
		//set Layout
		setLayout(new BorderLayout(5,5));
		//set font
		setFont(new Font("Helvetica",Font.PLAIN,14));
	}
}
