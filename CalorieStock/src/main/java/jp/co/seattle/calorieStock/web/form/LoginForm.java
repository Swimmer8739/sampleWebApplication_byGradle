package jp.co.seattle.calorieStock.web.form;

import java.util.Date;

public class LoginForm {

	// column ---------------------
	private String message1;
	private String message2;
	private Date today;
	private String name;
	private String password;

	// setter & getter ---------------------
	public final String getMessage1() {
		return message1;
	}

	public final void setMessage1(String message1) {
		this.message1 = message1;
	}

	public final String getMessage2() {
		return message2;
	}

	public final void setMessage2(String message2) {
		this.message2 = message2;
	}

	public final Date getToday() {
		return today;
	}

	public final void setToday(Date today) {
		this.today = today;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getPassword() {
		return password;
	}

	public final void setPassword(String password) {
		this.password = password;
	}

}
