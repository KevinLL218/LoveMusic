package panels;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

import combine.PlayWindow_Panel;
import combine.SongsDetail;
import database.Find;
import po.MusicSheet;

/**
 *  作者： 刘路
 *	描述：我的歌单Panel，Box布局
 *	修改时间：Nov 15, 2019
 *	备注信息：
 *	版本：1.0.0
 *	©copyright by Liulu 2019-2069
 */
public class MyList_Box {
	public static MusicSheet[] MS = null;//local MusicSheet
	
	
	
	/**
	 * 描述：生成MyList的Panel，格局为Box
	 * 5:05:59 PM
	 * 输入：
	 * 输出：
	 * 返回值：
	 */       
	@SuppressWarnings("serial")
    public static JPanel myListPanel(){
        // 创建内容面板，使用边界布局
        JPanel panel = new JPanel(new BorderLayout());
        // 表头（列名）
        
        String[][] rowData = Find.findMyList(" ");
        
        
        //Object[][] rowData = {{"歌单01"}, {"歌单02"}, {"歌单03"}, {"歌单04"}, {"歌单05"}};
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
				//if double clicked 
				if(arg0.getClickCount() == 2){
					int selectedRow = table.getSelectedRow();
					
					//String s = table.getValueAt(selectedRow, table.getSelectedColumn()).toString();
					int row = table.getSelectedRow();//获取选择的行数
					PlayWindow_Panel.updateInfo(selectedRow,MS[selectedRow].getUuid());
					System.out.println(MS[selectedRow].getUuid());
					PlayWindow_Panel.panel.repaint();
				}
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				table.clearSelection();
				
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
        
        table.setSelectionMode(1);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane localMusicSheetTablePanel = new JScrollPane(table);
        // 把 表头 添加到容器顶部（使用普通的中间容器添加表格时，表头 和 内容 需要分开添加）
        panel.add(table.getTableHeader(), BorderLayout.NORTH);
        panel.add(localMusicSheetTablePanel);
        // 把 表格内容 添加到容器中心
        panel.add(table, BorderLayout.CENTER);
		return panel;
    }
}
