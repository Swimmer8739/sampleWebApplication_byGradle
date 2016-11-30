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

/**
 * データベース間でデータの受け渡しをします。
 * 当クラスはXXXXサーバー.YYYYデータベース.T01tastyテーブルと紐付けられています。
 * 「XXXX」「YYYY」についてはプロパティファイルを参照ください。
 * */
@Service
public class T01tastyService {

	@Autowired
	T01tastyRepository repository;

	/**
	 * T01tastyテーブルからレコードをuserIDで抽出します。
	 * 抽出するデータの構造はhtmlへ提供するリスト形式に編成されています。
	 * 必ず0以上の長さのリストを返却し、例外はありません。
	 * */
	public List<Item> extractById(int userID) {
		List<Item> answer = new ArrayList<Item>();

		for (T01tasty item : repository.findAll()) {
			if (userID == item.getUserID()) {
				answer.add(new Item(item.getId(), item.getDate(), item.getEats(), item.getCalorie_kcal()));
			}
		}
		return answer;
	}

	/**
	 * T01tastyテーブルへレコードを追加します。
	 * DataIntegrityViolationExceptionを構えており、整合性エラーの検出はDB側で執り行われます。
	 * */
	public void add(Date date, String eats, double calorie_kcal, int userID) throws DataIntegrityViolationException {
		repository.saveAndFlush((new T01tasty(1, date, eats, calorie_kcal, userID)));
	}

	/**
	 * T01tastyテーブルへレコードを追加します。
	 * DataIntegrityViolationExceptionを構えており、整合性エラーの検出はDB側で執り行われます。
	 * */
	public void add(T01tasty entity) throws DataIntegrityViolationException {
		repository.saveAndFlush(entity);
	}

	/**
	 * T01tastyテーブルへレコードを削除します。
	 * */
	public void delete(int id) throws EmptyResultDataAccessException {
		repository.delete(id);
		repository.flush();
	}

	/**
	 * T01tastyテーブルのレコードを更新します。
	 * */
	public void update(T01tasty entity) throws EmptyResultDataAccessException, DataIntegrityViolationException {
		repository.delete(entity.getId());
		repository.flush();
		repository.saveAndFlush(entity);
	}
}
