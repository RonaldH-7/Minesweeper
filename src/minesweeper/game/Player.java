package minesweeper.game;

public class Player {
	private Board board;
	private Tile[][] gameBoard;
	private boolean isFirstClick;
	
	public Player(Board board) {
		this.board = board;
		isFirstClick = true;
		gameBoard = board.getBoard();
	}
	
	// TODO Make click set the tile to isRevealed. And for first click, the surrounding tiles isRevealed
	/*
	public void click(int x, int y) {
		if (isFirstClick) {
			board.placeMines(x, y);
			board.revealTile(x, y);
			board.revealNeighbours(x, y);
			isFirstClick = false;
			//gameBoard[y][x].setRevealed();
		} else {
			board.revealTile(x, y);
		}
	}
	*/
	
	public void click(int x, int y) {
		if (isFirstClick) {
			board.placeMines(x, y);
			gameBoard[y][x].setRevealed();
			board.revealNeighbours(x, y);
			isFirstClick = false;

		} else {
			gameBoard[y][x].setRevealed();
		}
		
		if (gameBoard[y][x].isMine()) {
			System.out.println("You lose");
		}
	}
}
