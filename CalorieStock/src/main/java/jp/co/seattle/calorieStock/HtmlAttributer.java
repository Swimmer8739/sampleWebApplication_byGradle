package jp.co.seattle.calorieStock;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jp.co.seattle.calorieStock.constant.ResponseForm;
import jp.co.seattle.calorieStock.web.form.Item;
import jp.co.seattle.calorieStock.web.form.LoginForm;

@Service
public class HtmlAttributer {

	@Autowired
	T01tastyService t01tastyService;

	public String makeForm_Login(LoginForm form) {

		form.setMessage1("<h1>CalorieStock</h1>");
		form.setMessage2("Thymeleafを使用したサンプルWebアプリケーションです。");
		form.setToday(Calendar.getInstance().getTime());

		return ResponseForm.LOGIN.getString();
	}

	public String makeForm_List(Model model, Integer userID, String name) {
		model.addAttribute("message1", "<h1>CalorieStock</h1>");
		model.addAttribute("name", name);
		model.addAttribute("target", (new Item()));
		model.addAttribute("items", t01tastyService.narrow(userID));

		return ResponseForm.LIST.getString();
	}

}
