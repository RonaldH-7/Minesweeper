package minesweeper.game;

public class Player {
	private Board board;
	private boolean isFirstClick;
	
	public Player(Board board) {
		this.board = board;
		isFirstClick = true;
	}
	
	// TODO Make click set the tile to isRevealed. And for first click, the surrounding tiles isRevealed
	public void click(int x, int y) {
		if (isFirstClick) {
			board.placeMines(x, y);
			isFirstClick = false;
		}
	}
}
