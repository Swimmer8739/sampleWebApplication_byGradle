package jp.co.seattle.calorieStock.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class T02user {

// column ---------------------
	@Id
	@GeneratedValue
	protected Integer id;

	@Column
	protected String name;

	@Column
	protected String password;


// setter & getter ---------------------
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}

// constructor --------------------------
	public T02user() {
		super();
	}

	public T02user(Integer id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}


}
