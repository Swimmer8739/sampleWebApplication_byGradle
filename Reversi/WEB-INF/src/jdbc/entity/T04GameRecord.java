package jdbc.entity;

public class T04GameRecord {
	final int id;
	final int turnCount;
	final String turnPlayer;
	final int row;
	final int col;

	public T04GameRecord(int id, int turnCount, String turnPlayer, int row, int col) {
		super();
		this.id = id;
		this.turnCount = turnCount;
		this.turnPlayer = turnPlayer;
		this.row = row;
		this.col = col;
	}

	public final int getId() {
		return id;
	}

	public final int getTurnCount() {
		return turnCount;
	}

	public final String getTurnPlayer() {
		return turnPlayer;
	}

	public final int getRow() {
		return row;
	}

	public final int getCol() {
		return col;
	}


}
