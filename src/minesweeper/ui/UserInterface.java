package minesweeper.ui;

import minesweeper.game.Board;
import minesweeper.game.Difficulty;
//import minesweeper.game.Player;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;
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
	/*
	public void createComponents(Container container) {
		int sizeX = board.getSizeX();
		int sizeY = board.getSizeY();
		container.setLayout(new GridLayout(sizeY, sizeX));
		
		//JButton[][] buttonGrid = new JButton[sizeY][sizeX];
		JToggleButton[][] buttonGrid = new JToggleButton[sizeY][sizeX];
		
		for (int i = 0; i < sizeY; i++) {
			for (int j = 0; j < sizeX; j++) {
				//JButton button = new JButton();
				JToggleButton button = new JToggleButton();
				//button.addActionListener(new Listener(buttonGrid, board));
				button.addMouseListener(new ClickListener(buttonGrid, board));
				buttonGrid[i][j] = button;
				container.add(button);
			}
		}
	}*/
	
	public void createComponents(Container container) {
		container.setLayout(new BorderLayout());
		container.add(createInfoPanel(), BorderLayout.NORTH);
		container.add(createBoardPanel(), BorderLayout.CENTER);
	}
	
	public JPanel createBoardPanel() {
		int sizeX = board.getSizeX();
		int sizeY = board.getSizeY();
		JPanel panel = new JPanel(new GridLayout(sizeY, sizeX));
		
		JToggleButton[][] buttonGrid = new JToggleButton[sizeY][sizeX];
		
		for (int i = 0; i < sizeY; i++) {
			for (int j = 0; j < sizeX; j++) {
				JToggleButton button = new JToggleButton();
				button.addMouseListener(new ClickListener(buttonGrid, board));
				buttonGrid[i][j] = button;
				panel.add(button);
			}
		}
		return panel;
	}
	
	public JPanel createInfoPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 3));
		JComboBox difficulty = new JComboBox();
		JLabel mines = new JLabel("Mines remaining");
		JLabel timer = new JLabel("Timer");
		panel.add(difficulty);
		panel.add(mines);
		panel.add(timer);
		return panel;
	}
}
