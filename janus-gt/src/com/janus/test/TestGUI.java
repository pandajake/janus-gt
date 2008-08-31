package com.janus.test;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.janus.views.StopView;

public class TestGUI {
	public static void main(String[] args) {
		final JFrame window = new JFrame();
		window.setLayout(new GridLayout(1, 1));
		window.setPreferredSize(new Dimension(100, 100));
		JButton invoker = new JButton("Invoke");
		invoker.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				StopView view = new StopView(window, null);
				System.err.println(view.isCompleted());
			}
		});
		window.add(invoker);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);
	}
}
