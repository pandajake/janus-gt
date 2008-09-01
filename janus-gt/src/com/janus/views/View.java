package com.janus.views;

import java.awt.GridLayout;
import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.janus.data.DataStore;
import com.janus.models.Model;

@SuppressWarnings("serial")
public abstract class View<E extends Model> extends JDialog {
	private DataStore data;
	private boolean completed;
	
	public View(Window window, DataStore data, E entity) {
		super(window);
		this.data = data;
		completed = false;
		this.setModal(true);
		this.setLayout(new GridLayout(1, 1));
	}
	
	protected void errorDialog(String message) {
		JOptionPane.showMessageDialog(this,
				message,
				"Error",
				JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * @return the completed
	 */
	public boolean isCompleted() {
		return completed;
	}

	/**
	 * @param completed the completed to set
	 */
	protected void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	/**
	 * @return the results of the operation.
	 */
	public abstract E getResult();

	/**
	 * @return the data
	 */
	public DataStore getData() {
		return data;
	}
}
