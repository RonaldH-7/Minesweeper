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
	private JLabel flagsRemaining;
	
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
			frame.setPreferredSize(new Dimension(800, 700));
		} else if (difficulty == Difficulty.HARD) {
			frame.setPreferredSize(new Dimension(1000, 800));
		}
		
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.createComponents(frame.getContentPane());
		
		frame.pack();
		frame.setVisible(true);
	}
	
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
				button.addMouseListener(new ClickListener(buttonGrid, board, flagsRemaining));
				buttonGrid[i][j] = button;
				panel.add(button);
			}
		}
		return panel;
	}
	
	public JPanel createInfoPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 3));
		
		String[] difficultyList = {"Easy", "Medium", "Hard"};
		
		JComboBox dropDown = new JComboBox(difficultyList);
		
		if (difficulty == difficulty.EASY) {
			dropDown.setSelectedIndex(0);
		} else if (difficulty == difficulty.MEDIUM) {
			dropDown.setSelectedIndex(1);
		} else {
			dropDown.setSelectedIndex(2);
		}
		
		dropDown.addActionListener(new Listener(frame, dropDown));
		flagsRemaining = new JLabel("Flags remaining: " + board.getMines());
		JLabel timer = new JLabel("Timer");
		panel.add(dropDown);
		panel.add(flagsRemaining);
		panel.add(timer);
		return panel;
	}
}
