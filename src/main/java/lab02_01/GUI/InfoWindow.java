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
import javax.swing.JTable;
import javax.swing.JTextArea;

public class InfoWindow extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JTextArea area = new JTextArea();
	
	String agreement = "Example";
	String path = "C:\\Users\\kukik\\eclipse-workspace\\zadanie\\src\\DPP\\zdjecie2.jpg";
	
	ImageIcon icon = new ImageIcon(path);
	JLabel label = new JLabel();
	
	JPanel panel = new JPanel();
	JPanel panel2 = new JPanel();
	
	JButton pathButton = new JButton("Path");
	JButton agreeButton = new JButton("Edit agreement");
	
	InfoWindow(JTable table){
		
		label.setPreferredSize(new Dimension(400, 400));
		label.setIcon(new ImageIcon(path));
		
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
		
		area.setText(agreement);
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
				String pathme = (String)JOptionPane.showInputDialog("Path:");
				if(pathme!=null) {
					path = pathme;
					label.setIcon(new ImageIcon(path));
				}
			}
			else if(eventSource == agreeButton){
				if(!area.isEditable()) {
					area.setEditable(true);
					agreeButton.setText("Confirm");
				}
				else {
					area.setEditable(false);
					agreement = area.getText();
					agreeButton.setText("Edit agreement");
				}
			}
		
		} catch(Exception e) {
			System.out.println("error");
		}

	}

}
