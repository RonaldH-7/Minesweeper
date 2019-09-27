package minesweeper.ui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Container;

public class UserInterface implements Runnable {
	private JFrame frame;
	
	public UserInterface() {
		
	}
	
	@Override
	public void run() {
		frame = new JFrame("Minesweeper");
		frame.setPreferredSize(new Dimension(300, 300));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.createComponents(frame.getContentPane());
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public void createComponents(Container container) {

	}
}
