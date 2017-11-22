package com.lmtuan.app.simplequestion;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI extends JPanel {
	public static final int DELAY = 2; // in ms
	
	public static final int ROWS = 9;
	public static final int COLS = 10;
	
	public static final int YES_BTN_ROW = ROWS/2;
	public static final int YES_BTN_COL = COLS/2 - 1;
	
	private int noBtnRow, noBtnCol;
	
	private static final long serialVersionUID = -9158306483032571605L;
	
	private JPanel ansPane;
	private JButton yesButton;
	private JButton noButton;
	
	public GUI(ActionListener yesListener, ActionListener noListener) {
		initComponents();
		
		noButton.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				resetButtons();
			}
		});

		noButton.addActionListener(noListener);
		yesButton.addActionListener(yesListener);
	}
	
	private void initComponents() {
		this.setLayout(new BorderLayout());
		
		JPanel questionPane = new JPanel();
		JLabel q1 = new JLabel("I have just a simple question for you:");
		JLabel q2 = new JLabel("Are you stupid?");
		questionPane.setLayout(new BoxLayout(questionPane, BoxLayout.PAGE_AXIS));
		questionPane.add(q1);
		questionPane.add(q2);
		this.add(questionPane, BorderLayout.PAGE_START);
		
		ansPane = new JPanel();
		ansPane.setLayout(new GridLayout(ROWS, COLS));
		yesButton = new JButton("Yes");
		noButton = new JButton("No");
		resetButtons(YES_BTN_ROW, YES_BTN_COL + 1);
		this.add(ansPane, BorderLayout.CENTER);
	}
	
	public void resetButtons() {
		int r, c;
		do {
			r = (int)(Math.random() * ROWS);
			c = (int)(Math.random() * COLS);
		} while ((r == YES_BTN_ROW && c == YES_BTN_COL)
				|| (r == noBtnRow && c == noBtnCol));
		resetButtons(r, c);
	}
	
	public void resetButtons(int r, int c) {
		ansPane.removeAll();
		ansPane.revalidate();
		ansPane.repaint();
		for (int i = 0; i < ROWS; ++i) {
			for (int j = 0; j < COLS; ++j) {
				if (i == r && j == c)
					ansPane.add(noButton);
				else if (i == YES_BTN_ROW && j == YES_BTN_COL)
					ansPane.add(yesButton);
				else
					ansPane.add(new JLabel());
			}
		}
		noBtnRow = r;
		noBtnCol = c;
	}
}
