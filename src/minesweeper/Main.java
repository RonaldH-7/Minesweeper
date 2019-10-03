package minesweeper;

import minesweeper.game.Board;
import minesweeper.game.Difficulty;
import minesweeper.ui.UserInterface;

public class Main {
	public static void main(String[] args) {
		Board board = new Board();
		UserInterface ui = new UserInterface(board, Difficulty.EASY);
		ui.run();
	}
}
