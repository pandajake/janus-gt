package com.janus.views;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.janus.data.DataStore;
import com.janus.models.PatternStop;
import com.janus.models.Stop;
import com.janus.views.widgets.PatternStopViewWidget;

@SuppressWarnings("serial")
public class PatternStopView extends View<PatternStop> implements ActionListener {
	private PatternStopViewWidget widget;
	private PatternStop entity;
	private DefaultComboBoxModel stopModel;
	
	public PatternStopView(Window window, DataStore data, PatternStop entity) {
		super(window, data, entity);
		widget = new PatternStopViewWidget();
		widget.getButtonCancel().addActionListener(this);
		widget.getButtonOK().addActionListener(this);
		stopModel = new DefaultComboBoxModel(getData().getStops().toArray());
		widget.getListStop().setModel(stopModel);
		widget.getListPickup().setModel(new DefaultComboBoxModel(PatternStop.ServiceType.values()));
		widget.getListDropOff().setModel(new DefaultComboBoxModel(PatternStop.ServiceType.values()));
		// enable/disable the arrival spinner, since we don't need an arrival time adjustment if the stop isn't a timepoint.
		widget.getCheckIsTimepoint().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				cycleTimepoint();
			}
		});
		incorporate(entity);
		this.add(widget);
		this.pack();
		this.setVisible(true);
	}

	private void incorporate(PatternStop entity) {
		if(entity == null) {
			// default is not a timepoint.
			cycleTimepoint(false);
		} else {
			stopModel.setSelectedItem(entity.getStop());
			widget.getListPickup().setSelectedItem(entity.getPickup());
			widget.getListDropOff().setSelectedItem(entity.getDropoff());
			widget.getFieldHeadsign().setText(entity.getHeadsign());
			cycleTimepoint(entity.isTimepoint());
			widget.getSpinnerArrivalOffset().setValue(new Integer(entity.getArrivalOffset()));
		}
	}
	
	/**
	 * Sets the isTimepoint value to what the GUI element displays.
	 */
	private void cycleTimepoint() {
		cycleTimepoint(widget.getCheckIsTimepoint().isSelected());
	}
	
	/**
	 * 
	 * @param newState true if this is a timepoint, false otherwise.
	 */
	private void cycleTimepoint(boolean newState) {
		if(newState) {
			// true, enable timepoint
			widget.getSpinnerArrivalOffset().setEnabled(true);
			widget.getCheckIsTimepoint().setSelected(true);
		} else {
			// false, disable timepoint
			widget.getSpinnerArrivalOffset().setEnabled(false);
			widget.getCheckIsTimepoint().setSelected(false);
		}
	}
	
	@Override
	public PatternStop getResult() {
		return entity;
	}
	
	private void save() {
		// get values.
		Stop stop = (Stop)stopModel.getSelectedItem();
		PatternStop.ServiceType pickup = (PatternStop.ServiceType)widget.getListPickup().getSelectedItem();
		PatternStop.ServiceType dropoff = (PatternStop.ServiceType)widget.getListDropOff().getSelectedItem();
		String headsign = widget.getFieldHeadsign().getText();
		boolean isTimepoint = widget.getCheckIsTimepoint().isSelected();
		int arrivalOffset = (Integer)widget.getSpinnerArrivalOffset().getValue();
		// assign
		try {
			if(entity == null) {
				entity = new PatternStop(stop,
						pickup,
						dropoff,
						headsign,
						isTimepoint,
						arrivalOffset,
						-1);
				setCompleted(true);
				this.setVisible(false);
			} else {
				entity.setStop(stop);
				entity.setPickup(pickup);
				entity.setDropoff(dropoff);
				entity.setHeadsign(headsign);
				entity.setTimepoint(isTimepoint);
				entity.setArrivalOffset(arrivalOffset);
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
}
