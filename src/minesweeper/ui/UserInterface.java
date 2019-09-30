package minesweeper.ui;

import minesweeper.game.Board;
import minesweeper.game.Difficulty;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Container;

public class UserInterface implements Runnable {
	private JFrame frame;
	private Board board;
	private Difficulty difficulty;
	
	public UserInterface(Board board, Difficulty difficulty) {
		this.board = board;
		this.board.generateBoard(difficulty);
		this.difficulty = difficulty;
	}
	
	@Override
	public void run() {
		frame = new JFrame("Minesweeper");
		
		if (difficulty == Difficulty.EASY) {
			frame.setPreferredSize(new Dimension(450, 400));
		} else if (difficulty == Difficulty.MEDIUM) {
			frame.setPreferredSize(new Dimension(550, 500));
		} else if (difficulty == Difficulty.HARD) {
			frame.setPreferredSize(new Dimension(650, 600));
		}
		
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.createComponents(frame.getContentPane());
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public void createComponents(Container container) {
		int sizeX = board.getSizeX();
		int sizeY = board.getSizeY();
		container.setLayout(new GridLayout(sizeY, sizeX));
		
		JButton[][] buttonGrid = new JButton[sizeY][sizeX];
		
		for (int i = 0; i < sizeY; i++) {
			for (int j = 0; j < sizeX; j++) {
				JButton button = new JButton();
				buttonGrid[i][j] = button;
				container.add(button);
			}
		}
	}
}
