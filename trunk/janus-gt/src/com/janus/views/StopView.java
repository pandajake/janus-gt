package com.janus.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.janus.models.Stop;
import com.janus.views.widgets.StopViewWidget;

@SuppressWarnings("serial")
public class StopView extends View<Stop> implements ActionListener {
	private StopViewWidget widget;
	private Stop entity;
	
	public StopView(JFrame parent, Stop entity) {
		super(parent, entity);
		this.entity = entity;
		widget = new StopViewWidget();
		widget.getButtonCancel().addActionListener(this);
		widget.getButtonOK().addActionListener(this);
		incorporate(entity);
		this.add(widget);
		this.pack();
		this.setVisible(true);
	}
	
	private void incorporate(Stop entity) {
		if(entity == null) {
			// creating fresh entity.
			// already set up in GUI
		} else {
			widget.getFieldCode().setText(entity.getId());
			widget.getFieldName().setText(entity.getName());
			widget.getFieldHiddenName().setText(entity.getHiddenName());
			widget.getFieldDescription().setText(entity.getDescription());
			widget.getFieldLatitude().setText("" + entity.getLat());
			widget.getFieldLongitude().setText("" + entity.getLon());
			widget.getFieldZoneId().setText(entity.getZoneId());
			widget.getFieldUrl().setText(entity.getUrl());
		}
	}
	
	private void save() {
		// read from GUI
		String code = widget.getFieldCode().getText();
		String name = widget.getFieldName().getText();
		String hiddenName = widget.getFieldHiddenName().getText();
		String description = widget.getFieldDescription().getText();
		double latitude, longitude;
		try {
			latitude = Double.parseDouble(widget.getFieldLatitude().getText());
			longitude = Double.parseDouble(widget.getFieldLongitude().getText());
		} catch (NumberFormatException e) {
			errorDialog("Invalid number values for latitude/longitude.");
			return;
		}
		String zoneId = widget.getFieldZoneId().getText();
		String url = widget.getFieldUrl().getText();
		// assign
		try {
			if(entity == null) {
				entity = new Stop(code,
						name,
						hiddenName,
						description,
						latitude,
						longitude,
						zoneId,
						url);
				setCompleted(true);
				this.setVisible(false);
			} else {
				entity.setId(code);
				entity.setName(name);
				entity.setHiddenName(hiddenName);
				entity.setDescription(description);
				entity.setLat(latitude);
				entity.setLon(longitude);
				entity.setZoneId(zoneId);
				entity.setUrl(url);
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
