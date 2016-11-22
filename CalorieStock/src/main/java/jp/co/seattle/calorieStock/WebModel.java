package jp.co.seattle.calorieStock;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jp.co.seattle.calorieStock.constant.ResponseForm;
import jp.co.seattle.calorieStock.web.form.LoginForm;

@Service
public class WebModel {

	@Autowired
	T01tastyService t01tastyService;
	@Autowired
	T02userService t02userService;

	public String makeForm_Login(LoginForm form){

		form.setMessage1("<h1>CalorieStock</h1>");
		form.setMessage2("Thymeleafを使用したサンプルWebアプリケーションです。");
		form.setToday(Calendar.getInstance().getTime());

		return ResponseForm.LOGIN.getString();
	}

	public String makeForm_List(Model model,Integer input,String name){
    	model.addAttribute("message1","<h1>ログイン完了</h1>");
    	model.addAttribute("name",name);

    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    	model.addAttribute("ftext1",( sdf.format(new Date().getTime()) ));
    	model.addAttribute("ftext2","食事");
    	model.addAttribute("ftext3",0);

    	model.addAttribute("items",t01tastyService.narrow(input));

		return ResponseForm.LIST.getString();
	}

	public Integer getID(String name , String password){
		return t02userService.permitUser(name, password);
	}
}
