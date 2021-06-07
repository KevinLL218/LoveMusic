package combine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

import database.Find;
import panels.MyList_Box;
import panels.OtherList_Box;

/**
 * 
 *  作者： 刘路
 *	描述：MyList & OthersLists
 *	修改时间：Nov 26, 2019
 *	备注信息：
 *	版本：1.0.0
 *	©copyright by Liulu 2019-2069
 */
public class PlayList_Panel {
	public static JPanel p= null;
	//create a playList
	public static void createPlayList(){
		p = new JPanel(null);
		JPanel p1 = OtherList_Box.myListPanel();
		JPanel p2 = MyList_Box.myListPanel();
		p1.setBounds(10,10,290,200);
		p2.setBounds(10,220,290,500);
		p1.setBackground(Color.BLACK);
		p2.setBackground(Color.gray);
		//TODO add PlayLists here
		p.setPreferredSize(new Dimension(300,768));
		p.add(p1);
		p.add(p2);
	}
	/**
	 * 描述：生成MyList的Panel，格局为Box
	 */
    private static JPanel myListPanel(){
        // 创建内容面板，使用边界布局
        JPanel panel = new JPanel(new BorderLayout());
        // 表头（列名）
        String[][] rowData = Find.findMyList(" ");
       // Object[][] rowData = {{"歌单01"}, {"歌单02"}, {"歌单03"}, {"歌单04"}, {"歌单05"}};
        // 表格所有行数据
        String[] columnNames = {"本地歌单"};
        // 创建一个表格，指定 所有行数据 和 表头
        JTable table = new JTable(rowData, columnNames){
        	@Override
        	public boolean isCellEditable(int row, int column){
        		return false;
        	}
        };
        table.isCellEditable(0, 0);
        //////////////////add mouse listener
        table.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println(table.getSelectedColumn());
				
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
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        
        
        
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane localMusicSheetTablePanel = new JScrollPane(table);
        //table.setSelectionMode(0);
        // 把 表头 添加到容器顶部（使用普通的中间容器添加表格时，表头 和 内容 需要分开添加）
        panel.add(table.getTableHeader(), BorderLayout.NORTH);
        panel.add(localMusicSheetTablePanel);
        // 把 表格内容 添加到容器中心
        panel.add(table, BorderLayout.CENTER);
		return panel;
    }
}
