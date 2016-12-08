package jdbc.entity;

import java.util.Date;

public class T03GameConfig {
	final String saveUser;
	final int id;
	final Date saveDate;
	final boolean AI_flg1;
	final boolean AI_flg2;
	final int AI_Level1;
	final int AI_Level2;

	public T03GameConfig(String saveUser, int id, Date saveDate, boolean aI_flg1, boolean aI_flg2, int aI_Level1,
			int aI_Level2) {
		super();
		this.saveUser = saveUser;
		this.id = id;
		this.saveDate = saveDate;
		AI_flg1 = aI_flg1;
		AI_flg2 = aI_flg2;
		AI_Level1 = aI_Level1;
		AI_Level2 = aI_Level2;
	}

	public final String getSaveUser() {
		return saveUser;
	}

	public final int getId() {
		return id;
	}

	public final Date getSaveDate() {
		return saveDate;
	}

	public final boolean isAI_flg1() {
		return AI_flg1;
	}

	public final boolean isAI_flg2() {
		return AI_flg2;
	}

	public final int getAI_Level1() {
		return AI_Level1;
	}

	public final int getAI_Level2() {
		return AI_Level2;
	}

}
