package DPP;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class AppWindow extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new AppWindow();
	}
	
	JButton addB = new JButton("Add");
	JButton infoB = new JButton("Info");
	JButton editB = new JButton("Edit");
	JButton removeB = new JButton("Remove");
	
	JPanel panel = new JPanel();
	JTable table = new JTable();
	
	public AppWindow(){
	setLayout(new BorderLayout());
	
	String[][] data = {{ "cos","cos","Nie"},{ "Ania","Bania","Tak"},{ "Ania","Bania","Tak"},{ "Ania","Bania","Tak"}};
	String[] header = {"id","address","nominal price","renting price","deposit","paid to","rented from", "rented to","free"};
	
	DefaultTableModel model = new DefaultTableModel(data,header);
	table.setModel(model);
	
	addB.addActionListener(this);
	infoB.addActionListener(this);
	editB.addActionListener(this);
	removeB.addActionListener(this);
	
	
	panel.setLayout(new GridLayout(4,1));
	
	panel.add(addB);
	panel.add(infoB);
	panel.add(editB);
	panel.add(removeB);
	
	add(new JScrollPane(table),BorderLayout.CENTER);
	add(panel,BorderLayout.EAST);
	
	table.changeSelection(0, 0, false, false);
	
	setTitle("DeveloperApp");  
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	setSize(1400, 300);
	setResizable(false);
	setLocationRelativeTo(null);
	setVisible(true);
	}
	
	public void removeRows(JTable table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		   int[] rows = table.getSelectedRows();
		   for(int i=0;i<rows.length;i++){
		     model.removeRow(rows[i]-i);
		   }
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object eventSource = event.getSource();

		try {
			if (eventSource == addB) { 
				new AddWindow(table);
			}
			else if (eventSource == editB) {
				String[] data = new String[table.getColumnCount()];
				for(int i=0;i<table.getColumnCount();i++) {
					data[i] = (String)table.getValueAt(table.getSelectedRow(), i);
				}
				
				new EditWindow(table,data);
			}
			else if (eventSource == removeB) { 
	
				removeRows(table);
				table.changeSelection(0, 0, false, false);
			}
			else if (eventSource == infoB) { 
				
				new InfoWindow(table);
			}
		
		} catch(Exception e) {
			
		}

	}
	
	
}
