package jp.co.seattle.calorieStock.entity;

public class SessionAttribute {

	// column ---------------------
	T02user t02user;

	// setter & getter ---------------------
	public final T02user getT02user() {
		return t02user;
	}

	public final void setT02user(T02user t02user) {
		this.t02user = t02user;
	}

	// constructor --------------------------
	public SessionAttribute() {
		super();
		this.t02user = new T02user();
	}

	public SessionAttribute(T02user t02user) {
		super();
		this.t02user = t02user;
	}

}
