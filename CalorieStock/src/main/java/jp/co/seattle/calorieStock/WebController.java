package jp.co.seattle.calorieStock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import templates.ResponseForm;

@Controller
@RestController
public class WebController {

	@Autowired //リポジトリを紐づけます
	protected WebModel webModel;

	public WebController(){
		webModel=new WebModel();
	}

	@RequestMapping("/yasero")
	public String getList(Model model){
		return webModel.makeResponse(model);
	}

	@RequestMapping("/login")
	public String hello(Model model ){
		//, @RequestParam(value="name", defaultValue="World") String name
    	model.addAttribute("name", "sample");
    	return ResponseForm.login.getString();
	}

}
