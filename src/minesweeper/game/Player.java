package minesweeper.game;

import java.util.List;
import java.util.ArrayList;

public class Player {
	private Board board;
	private Tile[][] gameBoard;
	private static boolean isFirstClick = true;
	
	public Player(Board board) {
		this.board = board;
		gameBoard = board.getBoard();
	}
	
	public void click(int x, int y) {
		if (isFirstClick) {
			board.placeMines(x, y);
			isFirstClick = false;
		}
		
		List<Tile> undiscoveredTile = board.getUndiscoveredTiles();
		
		if (gameBoard[y][x].isMine()) {
			System.out.println("You lose");
		} else if (board.numberOfSurroundingMines(x, y) == 0) {
			board.searchNeighbours(x, y);
		} else if (board.numberOfSurroundingMines(x, y) > 0) {
			gameBoard[y][x].setRevealed();
			undiscoveredTile.remove(gameBoard[y][x]);
		}
		
		if (undiscoveredTile.isEmpty()) {
			System.out.println("You win");
		}
	}
	
	public void setFlag(int x, int y) {
		gameBoard[y][x].setFlag();
	}
}
