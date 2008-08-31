package com.janus.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;

import com.janus.models.Pattern;
import com.janus.models.Trip;
import com.janus.views.widgets.TripViewWidget;

@SuppressWarnings("serial")
public class TripView extends View<Trip> implements ActionListener {
	private TripViewWidget widget;
	private Trip entity;
	
	public TripView(JFrame parent, Trip entity, List<Pattern> patterns) {
		super(parent, entity);
		this.entity = entity;
		widget = new TripViewWidget();
		widget.getButtonCancel().addActionListener(this);
		widget.getButtonOK().addActionListener(this);
		widget.getListDirection().setModel(new DefaultComboBoxModel(Trip.Direction.values()));
		widget.getListPattern().setModel(new DefaultComboBoxModel(patterns.toArray()));
		incorporate(entity);
		this.add(widget);
		this.pack();
		this.setVisible(true);
	}
	
	private void incorporate(Trip entity) {
		if(entity == null) {
			// creating fresh entity.
			// already set up in GUI
		} else {
			widget.getFieldTripcode().setText(entity.getId());
			widget.getFieldCalendar().setText(entity.getCalendar());
			widget.getListDirection().setSelectedItem(entity.getDirection());
			widget.getFieldBlock().setText(entity.getBlock());
			widget.getListPattern().setSelectedItem(entity.getPattern());
		}
	}
	
	private void save() {
		// read from GUI
		String tripcode = widget.getFieldTripcode().getText();
		String calendar = widget.getFieldCalendar().getText();
		Trip.Direction direction = (Trip.Direction)widget.getListDirection().getSelectedItem();
		String block = widget.getFieldBlock().getText();
		Pattern pattern = (Pattern)widget.getListPattern().getSelectedItem();
		// assign
		try {
			if(entity == null) {
				entity = new Trip(tripcode,
						calendar,
						direction,
						block,
						pattern);
				setCompleted(true);
				this.setVisible(false);
			} else {
				entity.setId(tripcode);
				entity.setCalendar(calendar);
				entity.setDirection(direction);
				entity.setBlock(block);
				entity.setPattern(pattern);
				setCompleted(true);
				this.setVisible(false);
			}
		} catch (IllegalArgumentException e) {
			errorDialog(e.getMessage());
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(widget.getButtonCancel().getActionCommand().equals(arg0.getActionCommand())) {
			this.setVisible(false);
		} else if(widget.getButtonOK().getActionCommand().equals(arg0.getActionCommand())) {
			save();
		}
	}

	@Override
	public Trip getResult() {
		return entity;
	}
}
