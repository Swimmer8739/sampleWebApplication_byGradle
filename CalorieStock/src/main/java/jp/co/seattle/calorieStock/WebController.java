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

    	Integer input =webModel.getID(name, password);
    	if (input ==null ){
    		return webModel.makeForm_LoginFalse(model);
    	}else{
        	return webModel.makeForm_List(model,input , name);

    	}
    }
}
