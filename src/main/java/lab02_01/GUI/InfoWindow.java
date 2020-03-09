package lab02_01.GUI;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class InfoWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JTextArea area = new JTextArea();
	
	String agreement = "Example";
	String path = "C:\\Users\\kukik\\eclipse-workspace\\zadanie\\src\\DPP\\zdjecie.jpg";
	
	ImageIcon icon = new ImageIcon(path);
	JLabel label = new JLabel("",icon,JLabel.CENTER);
	
	InfoWindow(JTable table){
		
		setLayout(new GridLayout(1,2));
		setTitle("Info");  
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(800, 400);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
		area.setText(agreement);
		area.setEditable(false);
		
		add(label);
		add(area);
		
	}

}
