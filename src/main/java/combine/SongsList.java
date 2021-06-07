package combine;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import database.Find;
import http.FileDownloader;
import panels.Image_Panel;
import panels.MyList_Box;
import panels.PlayControl_Panel;
import po.MusicPo;
import po.MusicSheet;

public class SongsList {
	@SuppressWarnings("serial")
	public static MusicPo[] MP = null;
	static Map<String, String> songs = new HashMap<String,String>();
	static Map<String, String> Othersongs = new HashMap<String,String>();
	private static int currentsheet=0;
	
	
	public static JPanel myListPanel(int sheet,String info){
        // 创建内容面板，使用边界布局
        JPanel panel = new JPanel(new BorderLayout());
        // 表头（列名）
        String[][] rowData;
        String[][] rowD = {{"song"},{"song2"}};
        //Object[][] rowData = {{"song1"},{"song2"},{"song3"},{"song4"},{"song5"},{"song6"},{"song7"},{"song8"}};
        if(!info.equals("")){
        	currentsheet = sheet;//获取当前歌单数
        	rowData = Find.findSongs(info);
        	for(int i=0;i<rowData.length;i++){
        		songs.put(""+i,MP[i].getMd5value());
        	}
        	MyList_Box.MS[sheet].setMusicItems(songs);

        }
        else
        	rowData = rowD;
        // 表格所有行数据
        String[] columnNames = {""};
        // 创建一个表格，指定 所有行数据 和 表头
        JTable table = new JTable(rowData, columnNames){
        	@Override
        	public boolean isCellEditable(int row, int column){
        		return false;
        	}
        };
        table.isCellEditable(0, 0);// 内容不可编辑
        /*		添加鼠标事件
         * 
         */
        table.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getClickCount()==2){// 双击
					
					int row = table.getSelectedRow();//获取选中的行数

					System.out.println("double clicked");
					//获取歌曲的md5值
					System.out.println("" + songs.get(""+row));	
					//进度条重新播放
					PlayControl_Panel.rePlay();
					PlayWindow_Panel.panel.repaint();
					
				}
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        // 大小可变
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane localMusicSheetTablePanel = new JScrollPane(table);
        // 把 表头 添加到容器顶部（使用普通的中间容器添加表格时，表头 和 内容 需要分开添加）
        panel.add(table.getTableHeader(), BorderLayout.NORTH);
        panel.add(localMusicSheetTablePanel);
        // 把 表格内容 添加到容器中心
        panel.add(table, BorderLayout.CENTER);
		return panel;
    }
	@SuppressWarnings("null")
	public static JPanel myListPanel(MusicSheet ms){
        // 创建内容面板，使用边界布局
        JPanel panel = new JPanel(new BorderLayout());
        int rows = 0;
        // 表头（列名）
        String[][] rowData = null;
        String[][] rowD = {{"song"},{"song2"}};
        //Object[][] rowData = {{"song1"},{"song2"},{"song3"},{"song4"},{"song5"},{"song6"},{"song7"},{"song8"}};
        if(ms!=null){
        	Othersongs = ms.getMusicItems();//获取当前歌单的歌曲
        	Set<String> keys = Othersongs.keySet();
        	Iterator<String> it = keys.iterator();
        	rowData = new String[keys.size()][2];
        	while(it.hasNext()){
        		rowData[rows++][0] = Othersongs.get(it.next());

        	}
        	
        	
        }
        else
        	rowData = rowD;
        // 表格所有行数据
        String[] columnNames = {""};
        // 创建一个表格，指定 所有行数据 和 表头
        JTable table = new JTable(rowData, columnNames){
        	/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
        	public boolean isCellEditable(int row, int column){
        		return false;
        	}
        };
        table.isCellEditable(0, 0);// 内容不可编辑
        /*		添加鼠标事件
         * 
         */
        table.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getClickCount()==2){// 双击
					
					int row = table.getSelectedRow();//获取选中的行数
					int index =0;
					Set<String> key = Othersongs.keySet();
					Iterator it = key.iterator();				
					//获取当前歌单所有歌曲
					System.out.println("double clicked");
					//获取歌曲的md5值
					//System.out.println("" + songs.get(""+row));	
					String url = "http://service.uspacex.com/music.server/downloadMusic";
					String path = "src/main/resources";
					String md5value = "";
					while(it.hasNext()){
						index++;
						md5value = (String) it.next();
						if(index==row)break;
					}
					FileDownloader.downloadMusicFile(url,md5value , path);
					//进度条重新播放
					PlayControl_Panel.rePlay();
					PlayWindow_Panel.panel.repaint();
					
				}
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        // 大小可变
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane localMusicSheetTablePanel = new JScrollPane(table);
        // 把 表头 添加到容器顶部（使用普通的中间容器添加表格时，表头 和 内容 需要分开添加）
        panel.add(table.getTableHeader(), BorderLayout.NORTH);
        // 设置滚动条自动出现
        localMusicSheetTablePanel.setHorizontalScrollBarPolicy(
        		JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        localMusicSheetTablePanel.setVerticalScrollBarPolicy(
        		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panel.add(localMusicSheetTablePanel);
        // 把 表格内容 添加到容器中心
        panel.add(table, BorderLayout.CENTER);

		return panel;
    }
	
}
