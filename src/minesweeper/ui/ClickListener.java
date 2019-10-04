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
		
		for (int i = 0; i < board.getSizeY(); i++) {
			for (int j = 0; j < board.getSizeX(); j++) {
				buttonGrid[i][j].setText(board.getTileText(j, i));
				if (!buttonGrid[i][j].getText().isEmpty()) {
					buttonGrid[i][j].setEnabled(false);
				}
				
				if (board.getTileText(j, i).equals("0")) {
					buttonGrid[i][j].setDisabledIcon(new ImageIcon(((new ImageIcon("images\\0.png").getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)))));
				}
				if (board.getTileText(j, i).equals("1")) {
					buttonGrid[i][j].setDisabledIcon(new ImageIcon(((new ImageIcon("images\\1.png").getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)))));
				}
				if (board.getTileText(j, i).equals("2")) {
					buttonGrid[i][j].setDisabledIcon(new ImageIcon(((new ImageIcon("images\\2.png").getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)))));
				}
				if (board.getTileText(j, i).equals("3")) {
					buttonGrid[i][j].setDisabledIcon(new ImageIcon(((new ImageIcon("images\\3.png").getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)))));
				}
				if (board.getTileText(j, i).equals("4")) {
					buttonGrid[i][j].setDisabledIcon(new ImageIcon(((new ImageIcon("images\\4.png").getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)))));
				}
				if (board.getTileText(j, i).equals("5")) {
					buttonGrid[i][j].setDisabledIcon(new ImageIcon(((new ImageIcon("images\\5.png").getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)))));
				}
				if (board.getTileText(j, i).equals("6")) {
					buttonGrid[i][j].setDisabledIcon(new ImageIcon(((new ImageIcon("images\\6.png").getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)))));
				}
			}
		}
	}
	
	private void leftClick(MouseEvent e) {
		for (int i = 0; i < board.getSizeY(); i++) {
			for (int j = 0; j < board.getSizeX(); j++) {
				
				if (buttonGrid[i][j] == e.getSource() && buttonGrid[i][j].isEnabled()) {
					board.click(j, i);
					board.printBoard();
					System.out.println("--------------------");
				}
				//buttonGrid[i][j].setText(board.getTileText(j, i));
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
						buttonGrid[i][j].setIcon(new ImageIcon(((new ImageIcon("images\\grass.png").getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)))));
						buttonGrid[i][j].setEnabled(true);
					} else {
						board.setFlag(j, i);
						buttonGrid[i][j].setDisabledIcon(new ImageIcon(((new ImageIcon("images\\flag.png").getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)))));
						buttonGrid[i][j].setEnabled(false);
					}
					
					board.printBoard();
					System.out.println("--------------------");
				}
				
			}
		}
		
		flagsRemaining.setText("Flags remaining: " + board.getMines());
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

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
