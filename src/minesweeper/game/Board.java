package minesweeper.game;

import java.util.Random;

public class Board {
	private Tile[][] gameBoard;
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
		
		gameBoard = new Tile[sizeY][sizeX];
		
		for (int i = 0; i < sizeY; i++) {
			for (int j = 0; j < sizeX; j++) {
				gameBoard[i][j] = new Tile(j, i);
			}
		}
	}
	
	
	public Tile[][] getBoard() {
		return gameBoard;
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
			
			if (gameBoard[randY][randX].isMine()) {
				i--;
			} else {
				gameBoard[randY][randX].setMine();
			}
		}
	}
	
	public int numberOfSurroundingMines(int x, int y) {
		int count = 0;
		
		if (this.inBoundary(x-1, y-1))
				if (gameBoard[y-1][x-1].isMine())
					count++;
		
		if (this.inBoundary(x, y-1))
			if (gameBoard[y-1][x].isMine())
				count++;

		if (this.inBoundary(x+1, y-1))
			if (gameBoard[y-1][x+1].isMine())
				count++;
		
		if (this.inBoundary(x-1, y))
			if (gameBoard[y][x-1].isMine())
				count++;
		
		if (this.inBoundary(x+1, y))
			if (gameBoard[y][x+1].isMine())
				count++;
		
		if (this.inBoundary(x-1, y+1))
			if (gameBoard[y+1][x-1].isMine())
				count++;
		
		if (this.inBoundary(x, y+1))
			if (gameBoard[y+1][x].isMine())
				count++;
		
		if (this.inBoundary(x+1, y+1))
			if (gameBoard[y+1][x+1].isMine())
				count++;
		
		return count;
	}
	
	//TODO Reveal until number
	// if numberOfSurroundingMines == 0, continue revealing
	// BreadthFirstSearch
	public void revealTile(int x, int y) {
		gameBoard[y][x].setRevealed();
	}
	
	//TODO Merge with revealTile()
	public void revealNeighbours(int x, int y) {
		if (this.inBoundary(x-1, y-1))
			gameBoard[y-1][x-1].setRevealed();
	
		if (this.inBoundary(x, y-1))
			gameBoard[y-1][x].setRevealed();
	
		if (this.inBoundary(x+1, y-1))
			gameBoard[y-1][x+1].setRevealed();
		
		if (this.inBoundary(x-1, y))
			gameBoard[y][x-1].setRevealed();
		
		if (this.inBoundary(x+1, y))
			gameBoard[y][x+1].setRevealed();
		
		if (this.inBoundary(x-1, y+1))
			gameBoard[y+1][x-1].setRevealed();
		
		if (this.inBoundary(x, y+1))
			gameBoard[y+1][x].setRevealed();
		
		if (this.inBoundary(x+1, y+1))
			gameBoard[y+1][x+1].setRevealed();
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
				if (gameBoard[i][j].isMine()) {
					System.out.print("* ");
				} else if (gameBoard[i][j].isFlag()) {
					System.out.print("F ");
				} else if (gameBoard[i][j].isRevealed()) {
					System.out.print(this.numberOfSurroundingMines(j, i) + " ");
				} else {
					System.out.print("? ");
				}
			}
			System.out.println();
		}
	}
}