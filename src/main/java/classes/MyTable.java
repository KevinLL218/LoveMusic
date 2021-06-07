package classes;

import javax.swing.JTable;

public class MyTable extends JTable{
	@Override
	public boolean isCellEditable(int row,int column){
		return false;

	}
}
