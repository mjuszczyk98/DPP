package lab02_01.GUI;

import java.awt.event.ActionEvent;

import lab02_01.database.Database;
import lab02_01.logic.Apartment;
import lab02_01.logic.Logic;

public class EditWindow extends AddWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Logic logic;
	int position;
	AppWindow parent;
	Apartment apartment;
	
	String[] data;

	EditWindow(AppWindow parent, Logic logic, int position, Database db) {
		super(parent, logic,db);
		
		this.parent = parent;
		this.logic = logic;
		this.position = position;
		apartment = logic.getApartment(position);
				
		setTitle("EditApartment");
		
		data = logic.get(position);
		
		 idField.setText(data[0]);
		 nameField.setText(data[1]);
		 addressField.setText(data[2]);
		 nomField.setText(data[3]);
		 renField.setText(data[4]);
		 depField.setText(data[5]);
		 paidField.setText(data[6]);
		 fromField.setText(data[7]);
		 toField.setText(data[8]);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		Object eventSource = event.getSource();

		try {
			if (eventSource == OKButton) {
				int tempId = apartment.getId();
				apartment.edit(Integer.parseInt(idField.getText()), addressField.getText(), Float.parseFloat(nomField.getText()));
				String[] p = paidField.getText().split("-");
				String[] from = fromField.getText().split("-");
				String[] to = toField.getText().split("-");
				logic.rent(Integer.parseInt(idField.getText()), nameField.getText(),
						Float.parseFloat(renField.getText()), Float.parseFloat(depField.getText()), Integer.parseInt(p[0]), Integer.parseInt(p[1]),
						Integer.parseInt(p[2]), Integer.parseInt(from[0]), Integer.parseInt(from[1]),
						Integer.parseInt(from[2]), Integer.parseInt(to[0]), Integer.parseInt(to[1]), Integer.parseInt(to[2]),
						"", "");
				db.editApartment(tempId, apartment);
				parent.refresh();
				dispose();

			}
			else if (eventSource == CancelButton) { 
				dispose();
			}
		
		} catch(Exception e) {
			
		}
		
	}

}
