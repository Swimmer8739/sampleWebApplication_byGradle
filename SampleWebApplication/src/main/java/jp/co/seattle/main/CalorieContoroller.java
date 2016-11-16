package jp.co.seattle.colorieStock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.seattle.colorieStock.entity.counter1;
import jp.co.seattle.colorieStock.repository.counter1Repository;


@Controller //コントローラーだよ、っていう定義
@EnableAutoConfiguration
@ComponentScan
public class CalorieContoroller {

	@Autowired //リポジトリを紐づけます
	counter1Repository repository;

	@RequestMapping("/DB")
	@ResponseBody
	public void home(){

		// 全件取得します
		Iterable<counter1> list = repository.findAll();

		// 取得した内容を出力します
		for(counter1 emp: list){
			System.out.println(emp.getId() + ":" + emp.getTitle());
		}
		return ;
	}
}