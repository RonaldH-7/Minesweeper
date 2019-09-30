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
	
	public void click(int x, int y) {
		if (isFirstClick) {
			board.placeMines(x, y);
			isFirstClick = false;
		}
		if (gameBoard[y][x].isMine()) {
			System.out.println("You lose");
		} else if (board.numberOfSurroundingMines(x, y) == 0) {
			board.searchNeighbours(x, y);
		} else if (board.numberOfSurroundingMines(x, y) > 0) {
			gameBoard[y][x].setRevealed();
		}
	}
}
