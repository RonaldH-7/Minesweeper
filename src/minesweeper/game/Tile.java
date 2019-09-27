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
	
	public boolean isMine() {
		return isMine;
	}
	
	public boolean isFlag() {
		return isFlag;
	}
	
	public boolean isRevealed() {
		return isRevealed;
	}
	/*
	@Override
	public String toString() {
		if (isMine) {
			return "*";
		} else if (isFlag) {
			return "X";
		} else {
			return "?";
		}
	}
	*/
}
