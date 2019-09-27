package minesweeper.game;

public class Manager {
	private Board board;
	private Player player;
	
	public Manager() {
		board = new Board();
		board.generateBoard(Difficulty.EASY);
		player = new Player(board);
		player.click(0, 0);
		board.printBoard();
	}
}
