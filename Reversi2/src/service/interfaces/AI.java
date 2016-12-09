package service.interfaces;

import service.structure.Board;
import service.structure.PlayerColor;
import service.structure.TurnRecord;

public class AI implements Player{

	int Level=1;//defult

	PlayerColor color;

	public AI(PlayerColor color,int level) {
		this.color = color;
	}

	@Override
	public TurnRecord doYourBest(Board board) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
