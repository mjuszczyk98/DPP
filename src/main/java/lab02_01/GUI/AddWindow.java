package DPP;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class AddWindow extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;

	//private Animal animal;

	Font font = new Font("MonoSpaced", Font.BOLD, 12);
	
	JLabel idLabel   = 		new JLabel("            ID: ");
	JLabel nameLabel   = 	new JLabel("          name: ");
	JLabel addressLabel  = 	new JLabel("       address: ");
	JLabel nomLabel = 		new JLabel(" nominal price: ");
	JLabel renLabel   = 	new JLabel(" renting price: ");
	JLabel paidLabel   = 	new JLabel("       paid to: ");
	JLabel fromLabel  = 	new JLabel("   rented from: ");
	JLabel toLabel = 		new JLabel("     rented to: ");
	JLabel freeLabel   = 	new JLabel("          free: ");
	
	JTable table;

	JTextField idField = new JTextField(10);
	JTextField nameField = new JTextField(10);
	JTextField addressField = new JTextField(10);
	JTextField nomField = new JTextField(10);
	JTextField renField = new JTextField(10);
	JTextField paidField = new JTextField(10);
	JTextField fromField = new JTextField(10);
	JTextField toField = new JTextField(10);
	JTextField freeField = new JTextField(10);

	JButton OKButton = new JButton("  OK  ");
	JButton CancelButton = new JButton("Cancel");

	AddWindow(JTable table) {
		
		this.table = table;
		
		setTitle("AddApartment");  
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(270, 300);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(10, 2));
		
		OKButton.addActionListener( this );
		CancelButton.addActionListener( this );
		
		add(idLabel);
		add(idField);
		
		add(nameLabel);
		add(nameField);
		
		add(addressLabel);
		add(addressField);
		
		add(nomLabel);
		add(nomField);
		
		add(renLabel);
		add(renField);
		
		add(paidLabel);
		add(paidField);
		
		add(fromLabel);
		add(fromField);
		
		add(toLabel);
		add(toField);
		
		add(freeLabel);
		add(freeField);
		
		add(OKButton);
		add(CancelButton);
		
		
		setVisible(true);
	}
	
	public void addRow(JTable table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		String[] row = {idField.getText(),nameField.getText(),addressField.getText(),
		nomField.getText(),renField.getText(),paidField.getText(),fromField.getText(),
		toField.getText(),freeField.getText()};
		model.addRow(row);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		Object eventSource = event.getSource();

		try {
			if (eventSource == OKButton) { 
				addRow(table);
			}
			else if (eventSource == CancelButton) { 
				dispose();
			}
		
		} catch(Exception e) {
			
		}
		
	}

	
}
