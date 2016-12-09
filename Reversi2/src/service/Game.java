package service;

import java.util.ArrayList;
import java.util.List;

import service.interfaces.Human;
import service.interfaces.Player;
import service.structure.Board;
import service.structure.PlayerColor;
import service.structure.TurnRecord;

public class Game extends Thread {

	public void run() {
		int turn = 0;
		Board board = new Board();
		Player player1 = new Human(PlayerColor.WHITE);
		Player player2 = new Human(PlayerColor.BLACK);
		List<TurnRecord> gameRecord = new ArrayList<TurnRecord>();

		while (true) {
			turn++;

			board.viewConsole();

			// ターンプレイヤー進行管理
			if (turn % 2 == 1) {
				System.out.println("候補>");
				for (int[] num : board.putable(PlayerColor.WHITE)) {
					System.out.println(" x:" + num[0] + " y:" + num[1]);
				}
				gameRecord.add(player1.doYourBest(board));
				board.put(gameRecord.get(gameRecord.size()-1));
			} else {
				System.out.println("候補>");
				for (int[] num : board.putable(PlayerColor.WHITE)) {
					System.out.println(" x:" + num[0] + " y:" + num[1]);
				}
				gameRecord.add(player1.doYourBest(board));
				board.put(gameRecord.get(MAX_PRIORITY));
				}
			// 終了条件管理
			if (board.count(PlayerColor.WHITE) + board.count(PlayerColor.BLACK) == 64) {
				break;
			}
		}
		System.out.println("finished");
	}
}
