package lab02_01.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import lab02_01.database.Database;
import lab02_01.logic.Logic;

public class InfoWindow extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JTextArea area = new JTextArea();
	
	JLabel label = new JLabel();
	
	JPanel panel = new JPanel();
	JPanel panel2 = new JPanel();
	
	Logic logic;
	int row;
	Database db;
	
	JButton pathButton = new JButton("Path");
	JButton agreeButton = new JButton("Edit agreement");
	
	InfoWindow(Logic logic,int row, Database db){
		
		this.logic = logic;
		this.row = row;
		this.db=db; 
		
		label.setPreferredSize(new Dimension(400, 400));
		label.setIcon(new ImageIcon(logic.getApartment(row).getImgPath()));
		
		pathButton.addActionListener(this);
		agreeButton.addActionListener(this);
		
		setLayout(new BorderLayout());
		setTitle("Info");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(800, 400);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
		panel.setLayout(new GridLayout(1,2));
		panel2.setLayout(new GridLayout(1,2));
		
		area.setText(logic.getApartment(row).getAgreement());
		area.setEditable(false);
		
		panel.add(label);
		panel.add(area);
		
		panel2.add(pathButton);
		panel2.add(agreeButton);
		
		add(panel,BorderLayout.CENTER);
		add(panel2,BorderLayout.SOUTH);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		Object eventSource = event.getSource();

		try {
			if (eventSource == pathButton) { 
				String path = (String)JOptionPane.showInputDialog("Path:");
				if(path!=null) {
					logic.getApartment(row).setImgPath(path);
					label.setIcon(new ImageIcon(path));
					db.editApartment(logic.getApartment(row).getId(), logic.getApartment(row));
				}
			}
			else if(eventSource == agreeButton){
				if(!area.isEditable()) {
					area.setEditable(true);
					agreeButton.setText("Confirm");
				}
				else {
					area.setEditable(false);
					logic.getApartment(row).setAgreement(area.getText());
					agreeButton.setText("Edit agreement");
					logic.getApartment(row).setAgreement(logic.getApartment(row).getAgreement());
					db.editApartment(logic.getApartment(row).getId(), logic.getApartment(row));
				}
			}
		
		} catch(Exception e) {
			System.out.println("error");
		}

	}

}
