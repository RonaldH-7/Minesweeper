package minesweeper.game;

public class Tile {
	private boolean isFlag;
	private boolean isMine;
	private boolean isRevealed;
	private int x;
	private int y;
	
	public Tile(int x, int y) {
		isFlag = false;
		isMine = false;
		isRevealed = false;
		this.x = x;
		this.y = y;
	}
	
	public void setMine() {
		isMine = true;
	}
	
	public void setRevealed() {
		isRevealed = true;
	}
	
	public void setFlag() {
		isFlag = true;
	}
	
	public void removeFlag() {
		isFlag = false;
	}
	
	public boolean isMine() {
		return isMine;
	}
	
	public boolean isFlag() {
		return isFlag;
	}
	
	public boolean isRevealed() {
		return isRevealed;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
