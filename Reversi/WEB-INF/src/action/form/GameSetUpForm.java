package action.form;

import org.apache.struts.action.ActionForm;

public class GameSetUpForm extends ActionForm {
	private String player1;
	private String player2;

	public final String getPlayer1() {
		return player1;
	}
	public final void setPlayer1(String player1) {
		this.player1 = player1;
	}
	public final String getPlayer2() {
		return player2;
	}
	public final void setPlayer2(String player2) {
		this.player2 = player2;
	}

}
