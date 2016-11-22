package jp.co.seattle.calorieStock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.seattle.calorieStock.constant.ResponseForm;
import jp.co.seattle.calorieStock.web.form.LoginForm;

@Controller
public class WebController {

	@Autowired
	protected WebModel webModel;

	@Autowired
	T01tastyService t01tastyService;

/*	@Autowired
	T02user loginUser;*/

	@RequestMapping("/login")
	public String login(@ModelAttribute("form") LoginForm userForm){
		return webModel.makeForm_Login(userForm);
	}

    @RequestMapping("/list")
    public String goToList(Model model,@RequestParam("name") String userName,@RequestParam("password") String password) {

    	Integer userID=webModel.getID(userName, password);

    	if (userID ==null ){
    		return "redirect:/" + ResponseForm.LOGIN.getString();
    	}else{
        	return webModel.makeForm_List(model,userID, userName);

    	}
    }

	@RequestMapping(value="/list/table", method = RequestMethod.POST)
	public String delete(
			RedirectAttributes attributes,
			@RequestParam("recordId") String recordId
	){

		System.out.println("削除ボタンの押下は成功");
		System.out.println("対象:" + recordId);


		attributes.addAttribute("name","Baki");
		attributes.addAttribute("password","seattle");
		return "redirect:/" + ResponseForm.LIST.getString();
	}
}
