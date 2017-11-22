package com.lmtuan.app.simplequestion;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Application extends JFrame {
	private static final long serialVersionUID = -7579064373078078135L;
	
	public static final String TITLE = "IQ Test";
	public static long TIME_LIMIT = 3 * 60000; //3 mins
	
	private long startTime;
	
	private int quitAttempts = 0;
	
	public Application() {
		super(TITLE);
		startTime = System.currentTimeMillis();
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				quitConfirm();
			}
		});
		
		GUI mainPane = new GUI(e1 -> yesConfirm(), e2 -> noConfirm());
		this.add(mainPane);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void quitConfirm() {
		++quitAttempts;
		if (quitAttempts > 2 || (System.currentTimeMillis() - startTime > TIME_LIMIT)) {
			JOptionPane.showMessageDialog(this, "Sometimes the only way to win is not to play.", 
					"You figured it out", JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
		} else if (quitAttempts == 2) {
			JOptionPane.showMessageDialog(this, "Hint: it is possible if you are fast enough", 
					"Just a bit more", JOptionPane.WARNING_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Try harder!", 
					 "Quitting so soon?", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void yesConfirm() {
		JOptionPane.showMessageDialog(this, "Glad you realized it.", "Well..", JOptionPane.QUESTION_MESSAGE);
		this.dispose();
	}
	
	public void noConfirm() {
		long sec = (System.currentTimeMillis() - startTime) / 100;
		int ans = JOptionPane.showConfirmDialog(this, "You just wasted " + sec + " seconds.\nReplay?", 
				"You sure?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (ans == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(this, "You are stupid.", "That's it", JOptionPane.WARNING_MESSAGE);
		}
		this.dispose();
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName ());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		SwingUtilities.invokeLater( () -> new Application() );
	}
}
