package minesweeper.ui;

import minesweeper.game.Board;
import minesweeper.game.Tile;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;

public class ClickListener implements MouseListener {
	private JToggleButton[][] buttonGrid;
	private Board board;
	private JLabel flagsRemaining;
	private Tile[][] tileGrid;
	
	public ClickListener(JToggleButton[][] buttonGrid, Board board, JLabel flagsRemaining) {
		this.buttonGrid = buttonGrid;
		this.board = board;
		this.flagsRemaining = flagsRemaining;
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
			}
		}
	}
	
	private void leftClick(MouseEvent e) {
		for (int i = 0; i < board.getSizeY(); i++) {
			for (int j = 0; j < board.getSizeX(); j++) {
				
				if (buttonGrid[i][j] == e.getSource()) {
					board.click(j, i);
					board.printBoard();
					System.out.println("--------------------");
				}
				buttonGrid[i][j].setText(board.getTileText(j, i));
			}
		}
	}
	
	private void rightClick(MouseEvent e) {
		for (int i = 0; i < board.getSizeY(); i++) {
			for (int j = 0; j < board.getSizeX(); j++) {
				
				if (buttonGrid[i][j] == e.getSource()) {
					if (tileGrid[i][j].isFlag()) {
						board.removeFlag(j, i);
						buttonGrid[i][j].setText("");
						buttonGrid[i][j].setEnabled(true);
					} else {
						board.setFlag(j, i);
						buttonGrid[i][j].setText("F");
					}
					
					board.printBoard();
					System.out.println("--------------------");
				}
				
			}
		}
		
		flagsRemaining.setText("Flags remaining: " + board.getMines());
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
