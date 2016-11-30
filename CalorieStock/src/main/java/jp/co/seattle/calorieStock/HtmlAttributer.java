package jp.co.seattle.calorieStock;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jp.co.seattle.calorieStock.constant.ResponseForm;
import jp.co.seattle.calorieStock.web.form.Item;
import jp.co.seattle.calorieStock.web.form.LoginForm;

/**
 * HtmlAttributer上でHTMLの項目の洗い出しおよび、実験的な値のインプットを行います。
 */
//松村勉強めも
//form,ModelはどちらもContorollerからポインタを値渡しで受け取りますが、それぞれ異なる手法でデータをインプットしています。
//データの構造は、前者は原始的構造の自作クラス 後者はjava.util.Mapをimportされることで構成されており、インプット方法の違いはこれら構成の違いに引っ張られています。

//※
//インプットされた自作クラスは@ModelAttributeにより同名のフィールドにマッピングされます。
//また、この原始的な自作クラスはJavaでは"JavaBean"と呼称し、規則性を持ったsetter/getterを実装することで外部コンポーネントからアクセス可能になりますｓ（即ち再利用性が高い）。
//…。
//…というかCで言うところのただの構造体だこれ。
//setter/getterメソッドは他言語におけるプロパティのwriteOnly/readOnlyの下位互換として機能するようです。
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
		model.addAttribute("items", t01tastyService.extractById(userID));

		return ResponseForm.LIST.getString();
	}

}
