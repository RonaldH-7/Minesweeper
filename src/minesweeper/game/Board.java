package minesweeper.game;

import java.util.Random;

public class Board {
	private Tile[][] board;
	private int sizeX;
	private int sizeY;
	private int mines;
	
	public Board() {
		
	}
	
	public void generateBoard(Difficulty difficulty) {
		switch (difficulty) {
		case EASY:
			sizeY = 8;
			sizeX = 10;
			mines = 10;
			break;
		case MEDIUM:
			sizeY = 14;
			sizeX = 18;
			mines = 40;
			break;
		case HARD:
			sizeY = 20;
			sizeX = 24;
			mines = 99;
			break;
		}
		
		board = new Tile[sizeY][sizeX];
		
		for (int i = 0; i < sizeY; i++) {
			for (int j = 0; j < sizeX; j++) {
				board[i][j] = new Tile(j, i);
			}
		}
	}
	
	// The parameter is for the first click
	public void placeMines(int x, int y) {
		Random rand = new Random();
		
		for (int i = 0; i < mines; i++) {
			int randX = rand.nextInt(sizeX);
			int randY = rand.nextInt(sizeY);
			
			// Can't place mines at the location of the first click or its neighbours
			while ((randX == x - 1 && randY == y - 1) ||
					(randX == x && randY == y - 1) ||
					(randX == x + 1 && randY == y - 1) ||
					(randX == x - 1 && randY == y) ||
					(randX == x && randY == y) ||
					(randX == x + 1 && randY == y) ||
					(randX == x - 1 && randY == y + 1) ||
					(randX == x && randY == y + 1) ||
					(randX == x + 1 && randY == y + 1)) {
				randX = rand.nextInt(sizeX);
				randY = rand.nextInt(sizeY);
			}
			
			if (board[randY][randX].isMine()) {
				i--;
			} else {
				board[randY][randX].setMine();
			}
		}
	}
	
	public int numberOfSurroundingMines(int x, int y) {
		int count = 0;
		
		if (this.inBoundary(x-1, y-1))
				if (board[y-1][x-1].isMine())
					count++;
		
		if (this.inBoundary(x, y-1))
			if (board[y-1][x].isMine())
				count++;

		if (this.inBoundary(x+1, y-1))
			if (board[y-1][x+1].isMine())
				count++;
		
		if (this.inBoundary(x-1, y))
			if (board[y][x-1].isMine())
				count++;
		
		if (this.inBoundary(x+1, y))
			if (board[y][x+1].isMine())
				count++;
		
		if (this.inBoundary(x-1, y+1))
			if (board[y+1][x-1].isMine())
				count++;
		
		if (this.inBoundary(x, y+1))
			if (board[y+1][x].isMine())
				count++;
		
		if (this.inBoundary(x+1, y+1))
			if (board[y+1][x+1].isMine())
				count++;
		
		return count;
	}
	
	public boolean inBoundary(int x, int y) {
		if (x >= 0 && x < sizeX) {
			if (y >= 0 && y < sizeY) {
				return true;
			}
		}
		return false;
	}
	
	public int getMines() {
		return mines;
	}
	
	public void printBoard() {
		for (int i = 0; i < sizeY; i++) {
			for (int j = 0; j < sizeX; j++) {
				if (board[i][j].isMine()) {
					System.out.print("* ");
				} else if (board[i][j].isFlag()) {
					System.out.print("F ");
				} else if (board[i][j].isRevealed()) {
					System.out.print(this.numberOfSurroundingMines(j, i) + " ");
				} else {
					System.out.print("? ");
				}
			}
			System.out.println();
		}
	}
}