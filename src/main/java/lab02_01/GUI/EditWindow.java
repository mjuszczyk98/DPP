package lab02_01.GUI;

import java.awt.event.ActionEvent;

import javax.swing.JTable;

import lab02_01.logic.Logic;

public class EditWindow extends AddWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String[] data;

	EditWindow(AppWindow parent, Logic logic, int position) {
		super(parent, logic);
		setTitle("EditApartment");
		
		data = logic.get(position);
		
		 idField.setText(data[0]);
		 nameField.setText(data[1]);
		 addressField.setText(data[2]);
		 nomField.setText(data[3]);
		 renField.setText(data[4]);
		 paidField.setText(data[6]);
		 fromField.setText(data[7]);
		 toField.setText(data[8]);		
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		Object eventSource = event.getSource();

		try {
			if (eventSource == OKButton) { 
				/*table.setValueAt((String)idField.getText(), table.getSelectedRow(), 0);
				table.setValueAt((String)nameField.getText(), table.getSelectedRow(), 1);
				table.setValueAt((String)addressField.getText(), table.getSelectedRow(), 2);
				table.setValueAt((String)nomField.getText(), table.getSelectedRow(), 3);
				table.setValueAt((String)renField.getText(), table.getSelectedRow(), 4);
				table.setValueAt((String)paidField.getText(), table.getSelectedRow(), 5);
				table.setValueAt((String)fromField.getText(), table.getSelectedRow(), 6);
				table.setValueAt((String)toField.getText(), table.getSelectedRow(), 7);
				table.setValueAt((String)freeField.getText(), table.getSelectedRow(), 8);*/
				dispose();

			}
			else if (eventSource == CancelButton) { 
				dispose();
			}
		
		} catch(Exception e) {
			
		}
		
	}

}
