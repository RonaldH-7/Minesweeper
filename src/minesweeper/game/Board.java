package minesweeper.game;

import java.util.Random;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

public class Board {
	private Tile[][] gameBoard;
	private int sizeX;
	private int sizeY;
	private int mines;
	private Queue<Tile> queue;
	private List<Tile> undiscoveredTiles;
	private boolean isFirstClick;
	private boolean hasLost;
	
	public Board() {
		this.queue = new LinkedList<Tile>();
		undiscoveredTiles = new ArrayList<Tile>();
		isFirstClick = true;
		hasLost = false;
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
				undiscoveredTiles.add(gameBoard[i][j]);
			}
		}
	}
	
	public void click(int x, int y) {
		if (isFirstClick) {
			placeMines(x, y);
			isFirstClick = false;
		}

		Tile tile = gameBoard[y][x];
		if (tile.isMine()) {
			hasLost = true;
		} else if (numberOfSurroundingMines(x, y) == 0) {
			searchNeighbours(x, y);
		} else if (numberOfSurroundingMines(x, y) > 0) {
			tile.setRevealed();
			undiscoveredTiles.remove(gameBoard[y][x]);
		}
		
//		if (undiscoveredTiles.isEmpty()) {
//			System.out.println("You win");
//		}
	}
	
	public boolean hasWon() {
		if (undiscoveredTiles.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public boolean hasLost() {
		return hasLost;
	}
	
	public void setFlag(int x, int y) {
		gameBoard[y][x].setFlag();
		
		//TEST
		mines--;
	}
	
	public void removeFlag(int x, int y) {
		gameBoard[y][x].removeFlag();
		mines++;
	}

	
	public Tile[][] getBoard() {
		return gameBoard;
	}
	
	public int getSizeX() {
		return sizeX;
	}
	
	public int getSizeY() {
		return sizeY;
	}
	
	public void searchNeighbours(int x, int y) {
		queue.offer(gameBoard[y][x]);
		
		while (!queue.isEmpty()) {
			Tile tile = queue.poll();
			int currentX = tile.getX();
			int currentY = tile.getY();
			
			tile.setRevealed();
			undiscoveredTiles.remove(tile);
			
			if (this.numberOfSurroundingMines(currentX, currentY) == 0) {
				this.connectNeighbours(gameBoard[currentY][currentX]);
			}
		}
	}
	
	// Will add to queue, any neighbours that are in boundary and not already revealed
	public void connectNeighbours(Tile tile) {
		int x = tile.getX();
		int y = tile.getY();
		
		Coordinate northWest = new Coordinate(x-1, y-1);
		if (inBoundary(northWest))
			if (!gameBoard[northWest.y][northWest.x].isRevealed())
				queue.offer(gameBoard[northWest.y][northWest.x]);
		
		Coordinate north = new Coordinate(x, y-1);
		if (inBoundary(north))
			if (!gameBoard[north.y][north.x].isRevealed())
				queue.offer(gameBoard[north.y][north.x]);
		
		Coordinate northEast = new Coordinate(x+1, y-1);
		if (inBoundary(northEast))
			if (!gameBoard[northEast.y][northEast.x].isRevealed())
				queue.offer(gameBoard[northEast.y][northEast.x]);
		
		Coordinate east = new Coordinate(x+1, y);
		if (inBoundary(east))
			if (!gameBoard[east.y][east.x].isRevealed())
				queue.offer(gameBoard[east.y][east.x]);
		
		Coordinate southEast = new Coordinate(x+1, y+1);
		if (inBoundary(southEast))
			if (!gameBoard[southEast.y][southEast.x].isRevealed())
				queue.offer(gameBoard[southEast.y][southEast.x]);
		
		Coordinate south = new Coordinate(x, y+1);
		if (inBoundary(south))
			if (!gameBoard[south.y][south.x].isRevealed())
				queue.offer(gameBoard[south.y][south.x]);
		
		Coordinate southWest = new Coordinate(x-1, y+1);
		if (inBoundary(southWest))
			if (!gameBoard[southWest.y][southWest.x].isRevealed())
				queue.offer(gameBoard[southWest.y][southWest.x]);
		
		Coordinate west = new Coordinate(x-1, y);
		if (inBoundary(west))
			if (!gameBoard[west.y][west.x].isRevealed())
				queue.offer(gameBoard[west.y][west.x]);
	}
	
	// Will randomly place mines. Cannot place at location of first click or immediately around it
	public void placeMines(int x, int y) {
		Random rand = new Random();
		
		for (int i = 0; i < mines; i++) {
			int randX = rand.nextInt(sizeX);
			int randY = rand.nextInt(sizeY);
			
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
				undiscoveredTiles.remove(gameBoard[randY][randX]);
			}
		}
	}
	
	public int numberOfSurroundingMines(int x, int y) {
		int count = 0;
		
		Coordinate northWest = new Coordinate(x-1, y-1);
		if (this.inBoundary(northWest))
				if (gameBoard[northWest.y][northWest.x].isMine())
					count++;
		
		Coordinate north = new Coordinate(x, y-1);
		if (this.inBoundary(north))
			if (gameBoard[north.y][north.x].isMine())
				count++;

		Coordinate northEast = new Coordinate(x+1, y-1);
		if (this.inBoundary(northEast))
			if (gameBoard[northEast.y][northEast.x].isMine())
				count++;
		
		Coordinate east = new Coordinate(x+1, y);
		if (this.inBoundary(east))
			if (gameBoard[east.y][east.x].isMine())
				count++;
		
		Coordinate southEast = new Coordinate(x+1, y+1);
		if (this.inBoundary(southEast))
			if (gameBoard[southEast.y][southEast.x].isMine())
				count++;
		
		Coordinate south = new Coordinate(x, y+1);
		if (this.inBoundary(south))
			if (gameBoard[south.y][south.x].isMine())
				count++;
		
		Coordinate southWest = new Coordinate(x-1, y+1);
		if (this.inBoundary(southWest))
			if (gameBoard[southWest.y][southWest.x].isMine())
				count++;
		
		Coordinate west = new Coordinate(x-1, y);
		if (this.inBoundary(west))
			if (gameBoard[west.y][west.x].isMine())
				count++;
		
		return count;
	}
	
	public boolean inBoundary(Coordinate coordinate) {
		if (coordinate.x >= 0 && coordinate.x < sizeX) {
			if (coordinate.y >= 0 && coordinate.y < sizeY) {
				return true;
			}
		}
		return false;
	}
	
	public int getMines() {
		return mines;
	}
	
	public List<Tile> getUndiscoveredTiles() {
		return undiscoveredTiles;
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
	
	public String getTileText(int x, int y) {
		if (gameBoard[y][x].isRevealed()) {
			return Integer.toString(this.numberOfSurroundingMines(x, y));
		} else if (gameBoard[y][x].isFlag() ) {
			return "F";
		} else {
			return "";
		}
	}
}