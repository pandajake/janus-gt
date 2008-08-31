package com.janus.views;

import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class View<E> extends JDialog {
	private boolean completed;
	
	public View(JFrame parent, E entity) {
		super(parent);
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
}
