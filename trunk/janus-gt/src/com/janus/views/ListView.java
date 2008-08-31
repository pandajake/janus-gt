package com.janus.views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.janus.models.Model;
import com.janus.views.widgets.ListViewWidget;

@SuppressWarnings("serial")
public abstract class ListView<E extends View<F>, F extends Model> extends JPanel implements ActionListener {
	private JFrame container;
	private ListViewWidget widget;
	private DefaultListModel model;
	
	public ListView(JFrame container, String title, List<F> contents) {
		super();
		this.setLayout(new GridLayout(1, 1));
		widget = new ListViewWidget();
		widget.getTextTitle().setText(title);
		// no widgets currently support this, so disable export functionality.
		widget.getButtonSave().setEnabled(false);
		widget.getButtonAdd().addActionListener(this);
		widget.getButtonDelete().addActionListener(this);
		widget.getButtonEdit().addActionListener(this);
		widget.getButtonUp().addActionListener(this);
		widget.getButtonDown().addActionListener(this);
		model = new DefaultListModel();
		for(F item : contents) {
			model.addElement(item);
		}
		widget.getDataList().setModel(model);
		this.add(widget);
	}
	
	public void add() {
		E dialog = create(container, null);
		System.err.println();
		if(dialog.isCompleted()) {
			F result = dialog.getResult();
			model.addElement(result);
		}
	}
	
	public void remove() {
		int selected = widget.getDataList().getSelectedIndex();
		if(selected >= 0) {
			int retVal = JOptionPane.showConfirmDialog(
					container,
					"Are you sure you want to remove this item?",
					"Confirm Removal",
					JOptionPane.YES_NO_OPTION);
			if(retVal == JOptionPane.YES_OPTION) {
				model.remove(selected);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void edit() {
		int selected = widget.getDataList().getSelectedIndex();
		if(selected >= 0) {
			create(container, (F)model.get(selected));
			// regardless of whether the container indicates completeness,
			// the object is mutated.  No need to check here.
			// however, changes in the GUI will not be represented without a forced update.
			widget.getDataList().repaint();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public void moveUp() {
		int selected = widget.getDataList().getSelectedIndex();
		if(selected > 0) {
			F temp = (F)model.remove(selected);
			model.add(selected - 1, temp);
			widget.getDataList().setSelectedIndex(selected - 1);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void moveDown() {
		int selected = widget.getDataList().getSelectedIndex();
		if(selected + 1 < model.getSize() && selected >= 0) {
			F temp = (F)model.remove(selected);
			model.add(selected + 1, temp);
			widget.getDataList().setSelectedIndex(selected + 1);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(widget.getButtonAdd().getActionCommand().equals(arg0.getActionCommand())) {
			add();
		} else if(widget.getButtonDelete().getActionCommand().equals(arg0.getActionCommand())) {
			remove();
		} else if(widget.getButtonEdit().getActionCommand().equals(arg0.getActionCommand())) {
			edit();
		} else if(widget.getButtonUp().getActionCommand().equals(arg0.getActionCommand())) {
			moveUp();
		} else if(widget.getButtonDown().getActionCommand().equals(arg0.getActionCommand())) {
			moveDown();
		}
	}
	
	/**
	 * Creates a standard view, using the given frame and object.
	 * <p>
	 * All that descendents of this class need implement is the standard
	 * constructor call.
	 * 
	 * @param frame the frame that the dialog should be associated with.
	 * @param entity the entity that the dialog will work on.
	 * @return the created object resulting from the dialog box operation.
	 */
	public abstract E create(JFrame frame, F entity);
}
