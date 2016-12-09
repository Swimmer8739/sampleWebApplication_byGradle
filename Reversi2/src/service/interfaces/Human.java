package service.interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import service.structure.Board;
import service.structure.PlayerColor;
import service.structure.TurnRecord;

public class Human implements Player {

	PlayerColor color;

	public Human(PlayerColor color) {
		this.color = color;
	}

	@Override
	public TurnRecord doYourBest(Board board) {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		int row;
		int col;

		String buf = "";

		try {
			System.out.print("縦軸：");
			buf = br.readLine();
			row = Integer.parseInt(buf);

			System.out.print("横軸：");
			buf = br.readLine();
			col = Integer.parseInt(buf);

			return new TurnRecord(this.color, row, col);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
