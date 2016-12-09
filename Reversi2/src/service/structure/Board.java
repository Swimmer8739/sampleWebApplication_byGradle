package service.structure;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private PlayerColor[][] mass;

	public Board() {
		// (1～8)×(1～8)マスの盤面上で裏返し処理を実装するにあたって、壁との衝突の考慮を不要にするために、内部的には一回り大きいサイズ(0～9)×(0～9)でデータ管理を行う。
		mass = new PlayerColor[10][10];

		for (int row = 0; row <= 9; row++) {
			for (int col = 0; col <= 9; col++) {
				mass[row][col] = PlayerColor.BLANK;
			}
		}
		mass[4][4] = PlayerColor.BLACK;
		mass[5][5] = PlayerColor.BLACK;
		mass[4][5] = PlayerColor.WHITE;
		mass[5][4] = PlayerColor.WHITE;
	}

	public boolean put(TurnRecord it) {

		return riversiAround(it);
	}

	public PlayerColor[][] getMass() {
		PlayerColor[][] result = new PlayerColor[8][8];

		for (int row = 0; row <= 7; row++) {
			for (int col = 0; col <= 7; col++) {
				result[row][col] = mass[row + 1][col + 1];
			}
		}
		return result;
	}

	public void viewConsole() {
		for (int row = 1; row <= 8; row++) {
			System.out.println("＋―＋―＋―＋―＋―＋―＋―＋―＋");
			for (int col = 1; col <= 8; col++) {
				System.out.print("｜" + mass[row][col].getString());
			}
			System.out.println("｜");
		}
		System.out.println("＋―＋―＋―＋―＋―＋―＋―＋―＋");
	}

	public List<int[]> putable(PlayerColor color) {
		List<int[]> result = new ArrayList<int[]>();

		TurnRecord that;

		for (int row = 1; row <= 8; row++) {
			for (int col = 1; col <= 8; col++) {

				that = new TurnRecord(color, row, col);
				if (checkAround(that) > 0) {
					int[] hoge = { row, col };
					result.add(hoge);
				}
				that = null;
			}
		}

		return result;
	}

	public int count(PlayerColor color) {
		int result = 0;
		for (int row = 1; row <= 8; row++) {
			for (int col = 0; col <= 9; col++) {
				if (mass[row][col] == color) {
					result++;
				}
			}
		}
		return result;
	}

	public Board clone() {// 値渡しするぜい。
		Board copy = new Board();
		return copy;
	}

	private int checkAround(TurnRecord it) {
		int result = 0;
		result += checkVector(it, 1, -1);
		result += checkVector(it, 1, 0);
		result += checkVector(it, 1, 1);
		result += checkVector(it, 0, 1);
		result += checkVector(it, -1, 1);
		result += checkVector(it, -1, 0);
		result += checkVector(it, -1, -1);
		result += checkVector(it, 0, -1);

		return result;
	}

	private boolean riversiAround(TurnRecord it) {
		if (checkAround(it) > 0) {
			riversiVector(it, 1, -1);
			riversiVector(it, 1, 0);
			riversiVector(it, 1, 1);
			riversiVector(it, 0, 1);
			riversiVector(it, -1, 1);
			riversiVector(it, -1, 0);
			riversiVector(it, -1, -1);
			riversiVector(it, 0, -1);

			return true;
		} else {
			return false;
		}
	}

	private int checkVector(TurnRecord it, int x, int y) {
		int result = 0;

		if (mass[it.getRow() + 0 * x][it.getCol() + 0 * y] != PlayerColor.BLANK) {
			return 0;
		}

		int i = 1;
		PlayerColor target;
		while (true) {

			target = mass[it.getRow() + i * x][it.getCol() + i * y];

			if (target == it.getColor()) {
				result = i-1;
				break;
			} else if (target == PlayerColor.BLANK) {
				result = 0;
				break;
			} else {// target!=it.getColor()
					// do nothing
			}
			i++;
		}
		return result;
	}

	private boolean riversiVector(TurnRecord it, int x, int y) {

		int count = checkVector(it, x, y);

		if (count > 0) {
			for (int i = 0; i <= count; i++) {
				mass[it.getRow() + i * x][it.getCol() + i * y] = it.getColor();
			}
			return true;
		} else {
			return false;
		}

	}
}
