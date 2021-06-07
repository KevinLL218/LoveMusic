package combine;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import classes.EasyButton;
import panels.MyList_Box;
import panels.PlayControl_Panel;
import po.MusicSheet;

/**
 * 
 * 作者： 刘路 描述：Create a Panel for play windows 修改时间：Nov 26, 2019 备注信息： 版本：1.0.0
 * ©copyright by Liulu 2019-2069
 */
public class PlayWindow_Panel {
	public static boolean isPaused = false; // 控制用变量
	public static JPanel panel = null; // playwindow
	private static CardLayout card = new CardLayout();// 卡片布局
	private static JPanel p2 = new JPanel(card); // p2为歌单详细信息
	private static JPanel controlPanel = new JPanel(new BorderLayout());// 设置控制面板,采用边界布局

	public static void updateInfo(int index, String info) {
		//   //////////////歌单内容
		{// 根据左侧mylist点击的顺序，显示相应的页面
			// 判断是否是第一层所需要的int变量
			int count = p2.getComponentCount();
			// 调用数据库，设置[c3]的具体内容
			/*
			 * card.next()操作
			 */
			JPanel jp = new JPanel();
			jp.setSize(690, 400);

				jp = SongsList.myListPanel(index,info);//info 获得第index行的歌曲数据，并放入sheet中

			// 添加数据
			p2.add(jp);
			// 如果p2有很多层的话，先删除上一层, 减少开销
			if (count >= 2) {
				// [c1]->[c2]->[c3] --> [c1]->[c3] 删除了[c2] 减少开销
				p2.remove(1);
			}
			// 显示最后一页，即指定的一页
			card.last(p2);
		}
		////////////////////歌单信息
		{
			SongsDetail.changeInfo(info);		
			SongsDetail.changeIcon(index);// 双击之后改变图标
		}

		panel.repaint(10, 220, 690, 400);
		panel.validate();
	}
	public static void updateInfo(MusicSheet ms) {
		//   //////////////歌单内容
		{// 根据左侧mylist点击的顺序，显示相应的页面
			// 判断是否是第一层所需要的int变量
			int count = p2.getComponentCount();
			// 调用数据库，设置[c3]的具体内容
			/*
			 * card.next()操作
			 */
			JPanel jp = new JPanel();
			jp.setSize(690, 400);

				jp = SongsList.myListPanel(ms);//info 获得第index行的歌曲数据，并放入sheet中

			// 添加数据
			p2.add(jp);
			// 如果p2有很多层的话，先删除上一层, 减少开销
			if (count >= 2) {
				// [c1]->[c2]->[c3] --> [c1]->[c3] 删除了[c2] 减少开销
				p2.remove(1);
			}
			// 显示最后一页，即指定的一页
			card.last(p2);
		}
		////////////////////歌单信息
		{
			//SongsDetail.changeInfo(info);		
			SongsDetail.changeIcon(ms);// 双击之后改变图标
		}

		panel.repaint(10, 220, 690, 400);
		panel.validate();
	}
	public static void createPlayWindow() {
		panel = new JPanel(null);
		JPanel p1 = null;
		JPanel p3 = new JPanel();
		Rectangle R1 = new Rectangle(10, 10, 690, 200);
		Rectangle R2 = new Rectangle(10, 220, 690, 400);
		Rectangle R3 = new Rectangle(10, 630, 690, 80);
		// panel.setPreferredSize(new Dimension());
		SongsDetail.createSDetial();
		///////////////////////
		/*
		 * p1 歌曲详细信息 p2 歌单详细信息 p3 歌曲播放控制
		 */
		p1 = SongsDetail.panel;
		// 添加p2
		p2.add(SongsList.myListPanel(0, ""));
		// 创建p3
		p3 = createCPanel();
		// 设置各个组件大小
		p1.setBounds(R1);
		p2.setBounds(R2);
		p3.setBounds(R3);
		panel.setPreferredSize(new Dimension(200, 50));
		// TODO add conponents here
		// TODO add detail here
		panel.add(p1);
		// TODO add songs lists here
		panel.add(p2);
		// TODO add controlling area here
		panel.add(p3);
	}

	// 生成控制窗口
	private static JPanel createCPanel() {
		JPanel panel = new JPanel();
		JButton btn = new EasyButton(null, "src/main/java/imgs/pause1.png", null);
		JButton btnpre = new EasyButton(null, "src/main/java/imgs/pre.png", null);
		JButton btnsuc = new EasyButton(null, "src/main/java/imgs/suc.png", null);
		btn.setBorderPainted(false);
		btn.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (isPaused == true) {// 点击使他继续
					btn.setIcon(new ImageIcon("src/main/java/imgs/pause1.png"));
					PlayControl_Panel.play();
					isPaused = false;
				} else { // 暂停
					btn.setIcon(new ImageIcon("src/main/java/imgs/stop1.png"));
					PlayControl_Panel.stop();
					isPaused = true;
				}
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

		});
		PlayControl_Panel.createPanel();
		controlPanel.add("North", PlayControl_Panel.panel);
		panel.add(btnpre);
		panel.add(btn);
		panel.add(btnsuc);
		controlPanel.add("Center", panel);
		panel.setBackground(Color.white);
		return controlPanel;
	}

}
