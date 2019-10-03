package minesweeper;

import minesweeper.game.Board;
import minesweeper.game.Difficulty;
import minesweeper.game.Manager;
import minesweeper.game.Player;
import minesweeper.ui.UserInterface;

public class Main {
	public static void main(String[] args) {
		//Manager m = new Manager();
		Board board = new Board();
		Player player = new Player(board);
		UserInterface ui = new UserInterface(board, player, Difficulty.EASY);
		ui.run();
	}
}
