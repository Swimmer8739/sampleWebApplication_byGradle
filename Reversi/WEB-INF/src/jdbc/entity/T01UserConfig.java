package jdbc.entity;

import java.util.Date;

public class T01UserConfig {
	final int id;
	final String name;
	final int gameCount;
	final int winCount;
	final int loseCount;
	final Date lastLogin;

	public T01UserConfig(int id,String name, int gameCount, int winCount, int loseCount, Date lastLogin) {
		super();
		this.id = id;
		this.name = name;
		this.gameCount = gameCount;
		this.winCount = winCount;
		this.loseCount = loseCount;
		this.lastLogin = lastLogin;
	}
	public final int getId() {
		return id;
	}
	public final String getName() {
		return name;
	}
	public final int getGameCount() {
		return gameCount;
	}
	public final int getWinCount() {
		return winCount;
	}
	public final int getLoseCount() {
		return loseCount;
	}
	public final Date getLastLogin() {
		return lastLogin;
	}
}
