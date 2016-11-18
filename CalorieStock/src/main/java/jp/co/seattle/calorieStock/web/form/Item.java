package jp.co.seattle.calorieStock.web.form;

import java.util.Date;

public class Item {

    private Date date;
    private String eats;
    private double calorie_kcal;

	public final Date getDate() {
		return date;
	}
	public final void setDate(Date date) {
		this.date = date;
	}

	public final String getEats() {
		return eats;
	}

	public final void setEats(String eats) {
		this.eats = eats;
	}

	public final double getCalorie_kcal() {
		return calorie_kcal;
	}

	public final void setCalorie_kcal(double calorie_kcal) {
		this.calorie_kcal = calorie_kcal;
	}

	public Item(){

	}
	public Item(Date date,String eats,double calorie_kcal){
		this.date=date;
		this.eats=eats;
		this.calorie_kcal=calorie_kcal;
	}

}
