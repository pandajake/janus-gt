package com.janus;


import java.awt.Component;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import com.janus.models.Stop;
import com.janus.models.Trip;
import com.janus.views.ListView;
import com.janus.views.StopView;
import com.janus.views.TripView;

public class Janus {
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//JFrame.setDefaultLookAndFeelDecorated(true);
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch(Exception e) {
					// OK, whatever, use default look and feel.
				}
				JFrame window = new JFrame("Polygon Area Calculator");
				window.setResizable(false);
				try {
					new Janus(window);
				} catch(Exception e) {
					// Ungracefully catch remaining errors not dealt with earlier in program.
					e.printStackTrace();
					errorDialog(window, "");
					System.exit(1);
				}
			}
		});
	}
	
	public static final boolean DEBUG = true;
	public static final File ERROR_LOG = new File("errors.txt");
	public static final String STANDARD_TITLE = "Janus-GT";
	public static final String STANDARD_ERROR_MESSAGE = "Janus-GT has encountered a serious error and " +
			"will be closed.\nIf the error persists, please check your program directory for\n" +
			"\""+ ERROR_LOG.getName() + "\" and follow the directions in that file.";
	
	private JFrame window;
	
	@SuppressWarnings("serial")
	public Janus(JFrame window) {
		this.window = window;
		// redirect standard System.err messages to the ERROR_LOG file.
		// make the printstream autoflushing.
		FileOutputStream fosErr;
		try	{
			fosErr = new FileOutputStream(ERROR_LOG);
		} catch(Exception e) {
			e.printStackTrace();
			errorDialog(window, "\n\nUnable to create or access the error log file.  Please " +
					"reinstall Janus-GT or correct the permissions of your program directory.");
			System.exit(2);
			return;
		}
		PrintStream errOut = new PrintStream(fosErr, true);
		if(! DEBUG)
			System.setErr(errOut);
		// Set a header for the error log.
		System.err.println("---------------------------------------------------");
		System.err.println("---- Error log for " + (new Date().toString()) + " ---");
		System.err.println("---------------------------------------------------");
		System.err.println("");
		
		// read data package
		// create GUI
		JTabbedPane pane = new JTabbedPane();
		pane.addTab("Stops", new ListView<StopView, Stop>(window, "Stops", new ArrayList<Stop>()) {
			@Override
			public StopView create(JFrame frame, Stop entity) {
				return new StopView(frame, entity);
			}
		});
		//pane.addTab("Trips", new ListView<TripView, Trip>(window, "Trips", new ArrayList<Trip>()) {
		//	@Override
		//	public TripView create(JFrame frame, Trip entity) {
		//		return new TripView(frame, entity);
		//	}
		//});
		// assign and display.
		window.setLayout(new GridLayout(1, 1));
		window.add(pane);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);
	}
	
	public static void error(JPanel parent, String message) {
		parent.removeAll();
		parent.setLayout(new GridLayout(1, 1));
		JTextArea errorScreen = new JTextArea();
		errorScreen.setEnabled(false);
		errorScreen.setLineWrap(true);
		errorScreen.setWrapStyleWord(true);
		errorScreen.setText("ERROR: " + message);
		parent.add(errorScreen);
	}
	
	public static void errorDialog(Component window, String messageSupplement) {
		JOptionPane.showMessageDialog(window, 
				STANDARD_ERROR_MESSAGE + messageSupplement,
				STANDARD_TITLE,
				JOptionPane.ERROR_MESSAGE);
	}
}
