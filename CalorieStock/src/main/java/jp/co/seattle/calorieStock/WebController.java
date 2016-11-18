package jp.co.seattle.calorieStock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.seattle.calorieStock.web.form.LoginForm;

@Controller
public class WebController {

	@Autowired
	protected WebModel webModel;

	@Autowired
	T01tastyService t01tastyService;
	@Autowired
	T02userService t02userService;

	@RequestMapping("/login")
	public String login(@ModelAttribute("form") LoginForm userForm){
		return webModel.makeForm_Login(userForm);
	}

    @RequestMapping("/list")
    public String goToList(Model model,@RequestParam("name") String name,@RequestParam("password") String password) {

    	Integer input =t02userService.permitUser(name, password);
    	if (input ==null ){
        	model.addAttribute("message1","ログインに失敗しました。");
    		return "login";
    	}else{


        	model.addAttribute("message1","ログイン完了");
        	model.addAttribute("name",name);
        	model.addAttribute("items",t01tastyService.narrow(input));

        	return "list";

    	}
    }
}
