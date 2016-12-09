package service.structure;

public class TurnRecord {
	final PlayerColor color;
	final int row;
	final int col;

	public TurnRecord(PlayerColor color,int row,int col){
		this.color=color;
		this.row=row;
		this.col=col;
	}

	public final PlayerColor getColor() {
		return color;
	}

	public final int getRow() {
		return row;
	}

	public final int getCol() {
		return col;
	}

}
