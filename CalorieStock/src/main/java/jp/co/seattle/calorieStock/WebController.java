package jp.co.seattle.calorieStock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.seattle.calorieStock.constant.ResponseForm;
import jp.co.seattle.calorieStock.entity.SessionAttribute;
import jp.co.seattle.calorieStock.entity.T01tasty;
import jp.co.seattle.calorieStock.entity.T02user;
import jp.co.seattle.calorieStock.web.form.Item;
import jp.co.seattle.calorieStock.web.form.LoginForm;

/**
 * Push形式MVCモデルのContoroller層です。
 */
@Controller
public class WebController {

	@Autowired
	protected HtmlAttributer webModel;
	@Autowired
	T01tastyService t01tastyService;
	@Autowired
	T02userService t02userService;

// Implementation in pages --------------------------

	@RequestMapping(value = "/login" , method = RequestMethod.GET)
	public String loginView(@ModelAttribute("form") LoginForm userForm) {
		return webModel.makeForm_Login(userForm);
	}

	@RequestMapping(value = "/list" , method = RequestMethod.GET)
	public String goToList(HttpServletRequest request, Model model) {

		SessionAttribute sessionAttribute = (SessionAttribute) (request.getSession()).getAttribute("sessionAttribute");

		if (sessionAttribute == null) {
			return "redirect:/" + ResponseForm.LOGIN.getString();
		} else {
			return webModel.makeForm_List(model, sessionAttribute.getT02user().getId(),
					sessionAttribute.getT02user().getName());
		}
	}

// Implementation in SubmitButton --------------------------
	/**
	 * login/submitの目的は大きく２つ。
	 *  ひとつはパスワードによるユーザー照合。
	 *  もうひとつはgetSessionのコール。
	 */
	@RequestMapping(value = "/login/submit", method = RequestMethod.POST)
	public String loginSubmit(HttpServletRequest request, @RequestParam("name") String userName,
			@RequestParam("password") String password) {

		// [1]permit user
		Integer userID = t02userService.findId(userName, password);

		if (userID != null) {

			// [1-1]call 'getSession'
			HttpSession httpSession = request.getSession();
			if (httpSession.isNew()) {
				// [1-1-1]attribute set up
				SessionAttribute sessionAttribute = (new SessionAttribute(new T02user(userID, userName, password)));
				httpSession.setAttribute("sessionAttribute", sessionAttribute);
			} else {
				// [1-1-2]
				// Do nothing
			}
			return "redirect:/" + ResponseForm.LIST.getString();
		} else {
			return "redirect:/" + ResponseForm.LOGIN.getString();
		}
	}

	/**
	 * リストへのadd
	 */
	//松村勉強メモ
	//明示的なBindingResultが強制されましたがresultの使い道はありません。
	@RequestMapping(value = "/list/submit", params = "add", method = RequestMethod.POST)
	public String add(@Valid @ModelAttribute("target") Item target, BindingResult result, HttpServletRequest request) {

		SessionAttribute sessionAttribute = (SessionAttribute) (request.getSession()).getAttribute("sessionAttribute");

		T01tasty entity = new T01tasty(target.getId(), target.getDate(), target.getEats(), target.getCalorie_kcal(),
				sessionAttribute.getT02user().getId());
		t01tastyService.add(entity);

		return "redirect:/" + ResponseForm.LIST.getString();
	}
	/**
	 * リストへのdelete
	 */
	//松村勉強メモ
	//明示的なBindingResultが強制されましたがresultの使い道はありません。
	@RequestMapping(value = "/list/submit", params = "delete", method = RequestMethod.POST)
	public String delete(@Valid @ModelAttribute("target") Item target, BindingResult result,
			HttpServletRequest request) {

		t01tastyService.delete(target.getId());

		return "redirect:/" + ResponseForm.LIST.getString();
	}
	/**
	 * リストへのupdate
	 */
	//松村勉強メモ
	//明示的なBindingResultが強制されましたがresultの使い道はありません。
	@RequestMapping(value = "/list/submit", params = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("target") Item target, BindingResult result,
			HttpServletRequest request) {
		SessionAttribute sessionAttribute = (SessionAttribute) (request.getSession()).getAttribute("sessionAttribute");

		T01tasty entity = new T01tasty(target.getId(), target.getDate(), target.getEats(), target.getCalorie_kcal(),
				sessionAttribute.getT02user().getId());
		t01tastyService.delete(entity.getId());
		t01tastyService.add(entity);

		return "redirect:/" + ResponseForm.LIST.getString();
	}

}