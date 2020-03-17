package lab02_01.GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import lab02_01.database.Database;
import lab02_01.logic.Logic;


public class AddWindow extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;


	Font font = new Font("MonoSpaced", Font.BOLD, 12);
	
	JLabel idLabel   = 		new JLabel(" ID: ");
	JLabel nameLabel   = 	new JLabel(" Nazwa najemcy: ");
	JLabel addressLabel  = 	new JLabel(" Adres: ");
	JLabel nomLabel = 		new JLabel(" Cena oczekiwana: ");
	JLabel renLabel   = 	new JLabel(" Czynsz: ");
	JLabel depLabel   = 	new JLabel(" Kaucja: ");
	JLabel paidLabel   = 	new JLabel(" Opłacone do: ");
	JLabel fromLabel  = 	new JLabel(" Wynajem od: ");
	JLabel toLabel = 		new JLabel(" Wynajem do: ");
	
	JTable table;
	
	JPanel panel = new JPanel(); 
	JPanel panel2 = new JPanel(); 


	JTextField idField = new JTextField(10);
	JTextField nameField = new JTextField(10);
	JTextField addressField = new JTextField(10);
	JTextField nomField = new JTextField(10);
	JTextField renField = new JTextField(10);
	JTextField depField = new JTextField(10);
	JTextField paidField = new JTextField(10);
	JTextField fromField = new JTextField(10);
	JTextField toField = new JTextField(10);

	JButton OKButton = new JButton("  OK  ");
	JButton CancelButton = new JButton("Cancel");
	
	Logic logic;
	AppWindow parent;
	Database db;

	AddWindow(AppWindow parent, Logic logic, Database db) {
		
		this.logic = logic;
		this.parent = parent;
		this.db=db;
		
		setTitle("AddApartment");  
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(270, 300);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		OKButton.addActionListener( this );
		CancelButton.addActionListener( this );
		
		panel.setLayout(new GridLayout(10,1));
		panel2.setLayout(new GridLayout(10,1));
		
		panel.add(idLabel);
		panel2.add(idField);
		
		panel.add(nameLabel);
		panel2.add(nameField);
		
		panel.add(addressLabel);
		panel2.add(addressField);
		
		panel.add(nomLabel);
		panel2.add(nomField);
		
		panel.add(renLabel);
		panel2.add(renField);

		panel.add(depLabel);
		panel2.add(depField);
		
		panel.add(paidLabel);
		panel2.add(paidField);
		
		panel.add(fromLabel);
		panel2.add(fromField);
		
		panel.add(toLabel);
		panel2.add(toField);
		
		panel.add(OKButton);
		panel2.add(CancelButton);
		
		add(panel,BorderLayout.WEST);
		add(panel2,BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		Object eventSource = event.getSource();

			if (eventSource == OKButton) { 
				logic.add(Integer.parseInt(idField.getText()), addressField.getText(), Float.parseFloat(nomField.getText()));
				String[] p = paidField.getText().split("-");
				String[] from = fromField.getText().split("-");
				String[] to = toField.getText().split("-");
				logic.rent(Integer.parseInt(idField.getText()), nameField.getText(),
						Float.parseFloat(renField.getText()), Float.parseFloat(depField.getText()), Integer.parseInt(p[0]), Integer.parseInt(p[1]),
						Integer.parseInt(p[2]), Integer.parseInt(from[0]), Integer.parseInt(from[1]),
						Integer.parseInt(from[2]), Integer.parseInt(to[0]), Integer.parseInt(to[1]), Integer.parseInt(to[2]),
						"", "");
				db.insertApartment(logic.apartments.get(logic.apartments.size()-1));
				parent.refresh();
				dispose();
			}
			else if (eventSource == CancelButton) { 
				dispose();
			}
		
		
		
	}

	
}
