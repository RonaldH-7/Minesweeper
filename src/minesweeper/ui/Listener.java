package minesweeper.ui;

import minesweeper.game.Board;
//import minesweeper.game.Player;
import minesweeper.game.Tile;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JToggleButton;

public class Listener implements ActionListener {
	private JToggleButton[][] buttonGrid;
	private Board board;
	//private Player player;
	private Tile[][] gameBoard;
	
	public Listener(JToggleButton[][] buttonGrid, Board board) {
		this.buttonGrid = buttonGrid;
		this.board = board;
		//player = new Player(board);
		gameBoard = board.getBoard();
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		for (int i = 0; i < board.getSizeY(); i++) {
			for (int j = 0; j < board.getSizeX(); j++) {
				
				if (buttonGrid[i][j] == ae.getSource()) {
					//player.click(j, i);
					board.printBoard();
					System.out.println("--------------------");
				}
				buttonGrid[i][j].setText(board.getTileText(j, i));
			}
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
}
