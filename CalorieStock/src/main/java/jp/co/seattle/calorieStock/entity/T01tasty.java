package jp.co.seattle.calorieStock.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.*;

@Entity // このクラスはEntityとして登録しますよ、とspringに教えてます
public class T01tasty {

	@Id //　プライマリーキーのものに設定してください
	@GeneratedValue //主に数字に対して、順番に一意に設定しますよ、の意味
	protected Integer id;

	@Column //ただの変数じゃなくて、DBのカラムだよ、の意味。（DBと当ファイルの命名が異なる場合のみ、明示的な宣言が必須となる。）
	protected Date date;

	@Column
	protected String eats;

	@Column
	protected double calorie_kcal;

	@Column
	protected String userID;

	// setter & getter ---------------------
	public T01tasty(){
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public java.util.Date getDate() {
		//return (java.util.Date)date;
		return date;
	}

	public void setDate(Date date) {
		//this.date = (java.sql.Date) date;
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

	public String getUserID() {
		return userID;
	}

    // constructor --------------------------
	public void setUserID() {
        this.id =0;
	}

	public T01tasty(Integer id, Date date ,String eats,Double calorie_kcal,String userID){
        super();
        this.id =id;
        this.date = date;
        this.eats=eats;
        this.calorie_kcal=calorie_kcal;
        this.userID=userID;
    }

	// method ~motoin~ --------------------------
	//add(XX,XX,XX,XX,XX)
	//delete(XX)
	//overwrite(XX,XX,XX,XX,XX)

	// method ~property~ --------------------------
	//list()
	//list(XX)
	//list(XX,XX)

	//public Iterable<T01tasty> list(){

	//	Iterable<T01tasty> list = repository.findAll();
	//	return list;
	//}



}