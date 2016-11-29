package jp.co.seattle.calorieStock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import jp.co.seattle.calorieStock.entity.T01tasty;
import jp.co.seattle.calorieStock.repository.T01tastyRepository;
import jp.co.seattle.calorieStock.web.form.Item;

@Service
public class T01tastyService {

	@Autowired
	T01tastyRepository repository;

	public List<Item> narrow(int userID) {
		// return List (Including Blank.)
		List<Item> answer = new ArrayList<Item>();

		for (T01tasty item : repository.findAll()) {
			if (userID == item.getUserID()) {
				answer.add(new Item(item.getId(), item.getDate(), item.getEats(), item.getCalorie_kcal()));
			}
		}
		return answer;
	}

	public void add(Date date, String eats, double calorie_kcal, int userID) throws DataIntegrityViolationException {
		// 桁数超過や符号の成否はDB側で怒ったものを受け取る。
		repository.saveAndFlush((new T01tasty(1, date, eats, calorie_kcal, userID)));
	}

	public void add(T01tasty entity) throws DataIntegrityViolationException {
		// 桁数超過や符号の成否はDB側で怒ったものを受け取る。
		repository.saveAndFlush(entity);
	}

	public void delete(int id) throws EmptyResultDataAccessException {
		repository.delete(id);
		repository.flush();
	}

	public void update(T01tasty entity) throws EmptyResultDataAccessException, DataIntegrityViolationException {
		repository.delete(entity.getId());
		repository.flush();
		repository.saveAndFlush(entity);
	}
}
