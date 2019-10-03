package minesweeper.ui;

import minesweeper.game.Difficulty;
import minesweeper.game.Board;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class Listener implements ActionListener {
	private JFrame frame;
	private JComboBox dropDown;
	
	public Listener(JFrame frame, JComboBox dropDown) {
		this.frame = frame;
		this.dropDown = dropDown;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		frame.dispose();
		Board board = new Board();
		
		String difficulty = dropDown.getSelectedItem().toString().toUpperCase();
		UserInterface ui = new UserInterface(board, Difficulty.valueOf(difficulty));
		ui.run();
	}
}
