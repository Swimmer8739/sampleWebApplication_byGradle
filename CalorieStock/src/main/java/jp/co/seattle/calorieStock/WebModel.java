package jp.co.seattle.calorieStock;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.seattle.calorieStock.constant.ResponseForm;
import jp.co.seattle.calorieStock.entity.T02user;
import jp.co.seattle.calorieStock.web.form.LoginForm;

@Service
public class WebModel {

	@Autowired
	protected static T02user loginUser;



	public String makeForm_Login( LoginForm form){

		loginUser=new T02user();
		form.setMessage1("<h1>CalorieStock</h1>");
		form.setMessage2("Thymeleafを使用したサンプルWebアプリケーションです。");
		form.setToday(Calendar.getInstance().getTime());

		return ResponseForm.LOGIN.getString();
	}

	public void setUserName (String name){
		loginUser.setName(name);
	}
	public void setPassword (String password){
		loginUser.setPassword(password);
	}
}
