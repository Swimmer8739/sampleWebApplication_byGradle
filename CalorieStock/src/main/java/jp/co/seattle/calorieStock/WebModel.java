package jp.co.seattle.calorieStock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jp.co.seattle.calorieStock.repository.t01tastyRepository;
import templates.ResponseForm;

@Service
public class WebModel {

	@Autowired //リポジトリを紐づけます
	t01tastyRepository repository;

	public String makeResponse(Model model){

		model.addAttribute("name", "皆さん");
		model.addAttribute("items", repository.findAll());

		return ResponseForm.list.getString();
	}
}
