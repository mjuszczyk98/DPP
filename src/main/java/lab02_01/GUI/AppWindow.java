package lab02_01.GUI;

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

import lab02_01.logic.Logic;


public class AppWindow extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new AppWindow();
	}
	
	Logic logic;
	
	JButton addB = new JButton("Add");
	JButton infoB = new JButton("Info");
	JButton editB = new JButton("Edit");
	JButton removeB = new JButton("Remove");
	
	JPanel panel = new JPanel();
	JTable table = new JTable();
	
	String[] header = {"id", "Najemca", "Adres","Kwota oczekiwana","Czynsz","Kaucja","Oplacone do","Wynajem od", "Wynajem do"};
	DefaultTableModel model;
	public AppWindow(){
		
		logic = new Logic();
		
		setLayout(new BorderLayout());
		
		refresh();
		
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

	@Override
	public void actionPerformed(ActionEvent event) {
		Object eventSource = event.getSource();

		try {
			if (eventSource == addB) { 
				new AddWindow(this, logic);
				
			}
			else if (eventSource == editB) {
				new EditWindow(this, logic, table.getSelectedRow());
			}
			else if (eventSource == removeB) { 
	
				logic.remove(table.getSelectedRow());
				table.changeSelection(0, 0, false, false);
			}
			else if (eventSource == infoB) { 
				
				new InfoWindow(table);
			}
		
		} catch(Exception e) {
			
		}

	}
	
	public void refresh() {
		model = new DefaultTableModel(logic.editView(),header);
		table.setModel(model);
	}
	
	
}
