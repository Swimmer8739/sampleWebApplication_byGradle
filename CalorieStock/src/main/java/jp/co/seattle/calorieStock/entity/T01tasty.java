package jp.co.seattle.calorieStock.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class T01tasty {

	// column ---------------------
	@Id
	@GeneratedValue
	protected Integer id;

	@Column
	protected Date date;	//(java.util.Date)

	@Column
	protected String eats;

	@Column
	protected double calorie_kcal;

	@Column
	protected int userID;

	// setter & getter ---------------------


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date =  date;
	}

	public String getEats() {
		return eats;
	}

	public void setEats(String eats) {
		this.eats = eats;
	}

	public double getCalorie_kcal() {
		return calorie_kcal;
	}

	public void setCalorie_kcal(double calorie_kcal) {
		this.calorie_kcal = calorie_kcal;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID() {
	}

    // constructor --------------------------
	public T01tasty(){
		super();
	}

	public T01tasty(Integer id, Date date ,String eats,Double calorie_kcal,int userID){
        super();
        this.id =id;
        this.date = date;
        this.eats=eats;
        this.calorie_kcal=calorie_kcal;
        this.userID=userID;
    }
}