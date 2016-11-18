package jp.co.seattle.calorieStock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.seattle.calorieStock.entity.T01tasty;
import jp.co.seattle.calorieStock.repository.T01tastyRepository;
import jp.co.seattle.calorieStock.web.form.Item;

@Service
public class T01tastyService {

	@Autowired
	T01tastyRepository repository;

	public List<Item> narrow (int userID){
		//return List or BlankList.
		List<Item> answer =new ArrayList<Item>();

		for(T01tasty item:repository.findAll()){
			if (userID==item.getUserID()){
				answer.add(new Item(item.getDate(),item.getEats(),item.getCalorie_kcal()));
			}
		}
		return answer;
	}

}
