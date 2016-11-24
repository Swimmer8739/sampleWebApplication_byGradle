package jp.co.seattle.calorieStock;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.seattle.calorieStock.constant.ResponseForm;
import jp.co.seattle.calorieStock.entity.T02user;
import jp.co.seattle.calorieStock.web.form.Item;
import jp.co.seattle.calorieStock.web.form.LoginForm;

@Controller
public class WebController {

	@Autowired
	protected HtmlAttributer webModel;

	@Autowired
	T01tastyService t01tastyService;

	//Authentication by static value 	//質問めも：本来はIPアドレスをサーバー側で保持？
	@Autowired
	 private static T02user loginUser;

	@RequestMapping("/login")
	public String login(@ModelAttribute("form") LoginForm userForm){
		loginUser=new T02user();
		return webModel.makeForm_Login(userForm);
	}

    @RequestMapping(value="/list")
    public String goToList(Model model,@RequestParam("name") String userName,@RequestParam("password") String password) {

		loginUser.setName(userName);
		loginUser.setPassword(password);
		loginUser.setId(webModel.getID(loginUser.getName(),loginUser.getPassword()));

    	if (loginUser.getId() ==null){
    		return "redirect:/" + ResponseForm.LOGIN.getString();
    	}else{

        	return webModel.makeForm_List(model,loginUser.getId(), loginUser.getName());
    	}
    }

	@RequestMapping(value="/list/submit" ,params="add", method = RequestMethod.POST)
	public String add(
			@Valid @ModelAttribute("target") Item target,
			BindingResult result,
			RedirectAttributes attributes
	){
		System.out.println("add");
		t01tastyService.add(target.getDate(), target.getEats(), target.getCalorie_kcal(), loginUser.getId());

		attributes.addAttribute("name",loginUser.getName());
		attributes.addAttribute("password",loginUser.getPassword());

		return "redirect:/" + ResponseForm.LIST.getString();
	}

	@RequestMapping(value="/list/submit" ,params="delete", method = RequestMethod.POST)
	public String delete(
			@Valid @ModelAttribute("target") Item target,
			BindingResult result,
			RedirectAttributes attributes
	){
		t01tastyService.delete(target.getId());

		attributes.addAttribute("name",loginUser.getName());
		attributes.addAttribute("password",loginUser.getPassword());
		return "redirect:/" + ResponseForm.LIST.getString();
	}
}
