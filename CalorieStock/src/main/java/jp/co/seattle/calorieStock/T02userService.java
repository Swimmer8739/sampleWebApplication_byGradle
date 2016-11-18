package jp.co.seattle.calorieStock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.seattle.calorieStock.entity.T02user;
import jp.co.seattle.calorieStock.repository.T02userRepository;

@Service
public class T02userService {

	@Autowired
	T02userRepository repository;

	public Integer permitUser(String userName,String password){
		//it dnot use userID.

		List<T02user> items = repository.findAll();
		Integer result=null;
		try{

		for(T02user item:items){
			if (userName.equals(item.getName())){
				if(password.equals(item.getPassword())){
					result= item.getId();
				}
			}
		}
		}catch(NullPointerException e){
			return null;

		}
		return result;

	}
}
