package minesweeper;

import minesweeper.game.Board;
import minesweeper.game.Difficulty;
import minesweeper.game.Manager;
import minesweeper.ui.UserInterface;

public class Main {
	public static void main(String[] args) {
		//Manager m = new Manager();
		Board board = new Board();
		UserInterface ui = new UserInterface(board, Difficulty.HARD);
		ui.run();
	}
}
