package com.janus.views;

import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.janus.data.DataStore;
import com.janus.models.Pattern;
import com.janus.models.PatternStop;
import com.janus.views.widgets.PatternViewWidget;

@SuppressWarnings("serial")
public class PatternView extends View<Pattern> implements ActionListener {
	private PatternViewWidget widget;
	private Pattern entity;
	private ListView<PatternStopView, PatternStop> patternStopView;
	
	public PatternView(Window window, DataStore data, Pattern entity) {
		super(window, data, entity);
		this.entity = entity;
		widget = new PatternViewWidget();
		widget.getButtonCancel().addActionListener(this);
		widget.getButtonOK().addActionListener(this);
		List<PatternStop> patternStops;
		if(entity == null) {
			patternStops = new ArrayList<PatternStop>();
		} else {
			patternStops = new ArrayList<PatternStop>(entity.getStops());
		}
		widget.getPanelList().setLayout(new GridLayout(1, 1));
		patternStopView = new ListView<PatternStopView, PatternStop>(this, "Stops", getData(), patternStops) {
			@Override
			public PatternStopView create(Window window, PatternStop entity) {
				return new PatternStopView(window, getData(), entity);
			}

			@Override
			public void addElement(PatternStop entity) {
				// unneeded in this application, since the model will be extracted at the end anyway.
			}

			@Override
			public void removeElement(PatternStop entity) {
				// unneeded in this application, since the model will be extracted at the end anyway.
			}
		};
		widget.getPanelList().add(patternStopView);
		incorporate(entity);
		this.add(widget);
		this.pack();
		this.setVisible(true);
	}
	
	private void incorporate(Pattern entity) {
		if(entity == null) {
			// everything's already set up.
		} else {
			widget.getFieldName().setText(entity.getName());
			widget.getFieldHeadsign().setText(entity.getHeadsign());
			widget.getFieldShape().setText(entity.getShape());
			widget.getFieldRoute().setText(entity.getRoute());
			// pattern stops already handled earlier.
		}
	}
	
	private void save() {
		// extract the stops out, in the appropriate sequence.
		List<PatternStop> patternStops = new ArrayList<PatternStop>();
		for(int i = 0; i < patternStopView.getModel().getSize(); i++) {
			PatternStop temp = (PatternStop)patternStopView.getModel().get(i);
			// sequence starting at 10, incrementing by 10 each time.
			temp.setSequence((i + 1) * 10);
			patternStops.add(temp);
		}
		// read from GUI
		String name = widget.getFieldName().getText();
		String headsign = widget.getFieldHeadsign().getText();
		String shape = widget.getFieldShape().getText();
		String route = widget.getFieldRoute().getText();
		// assign
		try {
			if(entity == null) {
				entity = new Pattern(name,
						headsign,
						shape,
						route,
						patternStops);
				setCompleted(true);
				this.setVisible(false);
			} else {
				entity.setName(name);
				entity.setHeadsign(headsign);
				entity.setShape(shape);
				entity.setRoute(route);
				entity.setStops(patternStops);
				setCompleted(true);
				this.setVisible(false);
			}
		} catch (IllegalArgumentException e) {
			errorDialog(e.getMessage());
		}
	}

	@Override
	public Pattern getResult() {
		return entity;
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
