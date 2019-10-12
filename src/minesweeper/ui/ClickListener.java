package minesweeper.ui;

import minesweeper.game.Board;
import minesweeper.game.Difficulty;
import minesweeper.game.Tile;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JComboBox;

public class ClickListener implements MouseListener {
	private JButton[][] buttonGrid;
	private Board board;
	private JLabel flagsRemaining;
	private Tile[][] tileGrid;
	private JFrame frame;
	private JComboBox dropDown;
	
	public ClickListener(JButton[][] buttonGrid, Board board, JLabel flagsRemaining, JFrame frame, JComboBox dropDown) {
		this.buttonGrid = buttonGrid;
		this.board = board;
		this.flagsRemaining = flagsRemaining;
		this.frame = frame;
		this.dropDown = dropDown;
		tileGrid = board.getBoard();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			leftClick(e);
		} else if (SwingUtilities.isRightMouseButton(e)) {
			rightClick(e);
		}
		
		reveal();
	}
	
	private void leftClick(MouseEvent e) {
		for (int i = 0; i < board.getSizeY(); i++) {
			for (int j = 0; j < board.getSizeX(); j++) {

				if (buttonGrid[i][j] == e.getSource() && buttonGrid[i][j].isEnabled()) {
					board.click(j, i);
					board.printBoard();
					System.out.println("--------------------");
				}
				
			}
		}
		loseCheck();
		winCheck();
	}
	
	private void rightClick(MouseEvent e) {
		for (int i = 0; i < board.getSizeY(); i++) {
			for (int j = 0; j < board.getSizeX(); j++) {
				
				if (buttonGrid[i][j] == e.getSource()) {
					if (tileGrid[i][j].isFlag()) {
						board.removeFlag(j, i);
						buttonGrid[i][j].setIcon(getImageIcon("grass"));
						buttonGrid[i][j].setEnabled(true);
					} else {
						board.setFlag(j, i);
						buttonGrid[i][j].setDisabledIcon(getImageIcon("F"));
						buttonGrid[i][j].setEnabled(false);
					}
					
					board.printBoard();
					System.out.println("--------------------");
				}
				
			}
		}
		
		flagsRemaining.setText("Flags remaining: " + board.getMines());
	}
	
	public void reveal() {
		for (int i = 0; i < board.getSizeY(); i++) {
			for (int j = 0; j < board.getSizeX(); j++) {
				buttonGrid[i][j].setText(board.getTileText(j, i));
				if (!buttonGrid[i][j].getText().isEmpty()) {
					buttonGrid[i][j].setEnabled(false);
				}
				
				String text = board.getTileText(j, i);
				ImageIcon icon = getImageIcon(text);
				buttonGrid[i][j].setDisabledIcon(icon);
			}
		}
	}
	
	public void winCheck() {
		if (board.hasWon()) {
			if (JOptionPane.showConfirmDialog(frame, "You Win!\nDo you want to play again?") == JOptionPane.YES_OPTION) {
				this.restart();
			} else {
				frame.dispose();
			}
		}
	}
	
	public void loseCheck() {
		if (board.hasLost()) {
			if (JOptionPane.showConfirmDialog(frame, "You Lose!\nDo you want to play again?") == JOptionPane.YES_OPTION) {
				this.restart();
			} else {
				frame.dispose();
			}
		}
	}
	
	public void restart() {
		frame.dispose();
		Board board = new Board();
		String difficulty = dropDown.getSelectedItem().toString().toUpperCase();
		UserInterface ui = new UserInterface(board, Difficulty.valueOf(difficulty));
		ui.run();
	}

	public ImageIcon getImageIcon(String text) {
		String path = "images\\" + text + ".png";
		return new ImageIcon(((new ImageIcon(path).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH))));
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
}
