package jp.co.seattle.calorieStock;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.seattle.calorieStock.entity.T02user;
import jp.co.seattle.calorieStock.repository.T01tastyRepository;
import jp.co.seattle.calorieStock.repository.T02userRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HtmlAttributerTest {

	@Autowired
	T02userRepository SQL_user;

	@Autowired
	T01tastyRepository SQL_tasty;

	@Autowired
	protected HtmlAttributer webModel;


	@Test
	public void test_getID_OK_TRUE() {
		//DateSetUp
		SQL_tasty.deleteAll();
		SQL_user.deleteAll();
        SQL_user.save(new T02user(1,"tanaka","seattle"));
        SQL_user.flush();

		//test
        Integer result =webModel.getID("tanaka", "seattle");

		//assert
		assertEquals((Integer)SQL_user.findAll().get(0).getId(),result);
	}

	@Test
	public void test_getID_OK_ReturnNull() {
		//DateSetUp
		SQL_tasty.deleteAll();
		SQL_user.deleteAll();
        SQL_user.save(new T02user(1,"tanaka","seattle"));
        SQL_user.flush();

		//test
        Integer result =webModel.getID("tanaka", "false");

		//assert
		assertEquals(null,result);
	}

}
