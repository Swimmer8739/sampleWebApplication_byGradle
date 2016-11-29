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

@Controller
public class WebController {

	@Autowired
	protected HtmlAttributer webModel;

	@Autowired
	T01tastyService t01tastyService;
	@Autowired
	T02userService t02userService;

	@RequestMapping(value = "/login")
	public String loginView(@ModelAttribute("form") LoginForm userForm) {

		return webModel.makeForm_Login(userForm);
	}

	@RequestMapping(value = "/list")
	public String goToList(HttpServletRequest request, Model model) {

		SessionAttribute sessionAttribute = (SessionAttribute) (request.getSession()).getAttribute("sessionAttribute");

		if (sessionAttribute.getT02user().getId() == null) {
			return "redirect:/" + ResponseForm.LOGIN.getString();
		} else {
			return webModel.makeForm_List(model, sessionAttribute.getT02user().getId(),
					sessionAttribute.getT02user().getName());
		}
	}

	@RequestMapping(value = "/login/submit", method = RequestMethod.POST)
	public String loginSubmit(HttpServletRequest request, @RequestParam("name") String userName,
			@RequestParam("password") String password) {
		SessionAttribute sessionAttribute = new SessionAttribute(
				new T02user(t02userService.permitUser(userName, password), userName, password));

		if (sessionAttribute.getT02user().getId() != null) {
			HttpSession httpSession = request.getSession();

			if (httpSession.isNew()) {
				httpSession.setAttribute("sessionAttribute", sessionAttribute);
			} else {
				// Do nothing
			}
			return "redirect:/" + ResponseForm.LIST.getString();
		} else {
			return "redirect:/" + ResponseForm.LOGIN.getString();
		}
	}

	@RequestMapping(value = "/list/submit", params = "add", method = RequestMethod.POST)
	public String add(@Valid @ModelAttribute("target") Item target, BindingResult result, HttpServletRequest request) {

		SessionAttribute sessionAttribute = (SessionAttribute) (request.getSession()).getAttribute("sessionAttribute");
		t01tastyService.add(target.getDate(), target.getEats(), target.getCalorie_kcal(),
				sessionAttribute.getT02user().getId());

		return "redirect:/" + ResponseForm.LIST.getString();
	}

	@RequestMapping(value = "/list/submit", params = "delete", method = RequestMethod.POST)
	public String delete(@Valid @ModelAttribute("target") Item target, BindingResult result,
			HttpServletRequest request) {

		t01tastyService.delete(target.getId());

		return "redirect:/" + ResponseForm.LIST.getString();
	}

	@RequestMapping(value = "/list/submit", params = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("target") Item target, BindingResult result,
			HttpServletRequest request) {
		SessionAttribute sessionAttribute = (SessionAttribute) (request.getSession()).getAttribute("sessionAttribute");

		T01tasty entity = new T01tasty(target.getId(), target.getDate(), target.getEats(), target.getCalorie_kcal(),
				sessionAttribute.getT02user().getId());
		t01tastyService.delete(target.getId());
		t01tastyService.add(entity);

		return "redirect:/" + ResponseForm.LIST.getString();
	}

}