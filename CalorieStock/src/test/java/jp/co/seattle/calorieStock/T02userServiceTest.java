package jp.co.seattle.calorieStock;
import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.seattle.calorieStock.entity.T02user;
import jp.co.seattle.calorieStock.repository.T01tastyRepository;
import jp.co.seattle.calorieStock.repository.T02userRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class T02userServiceTest {

	@Autowired
	T02userService repository;

	@Autowired
	T01tastyRepository SQL_tasty;

	@Autowired
	T02userRepository SQL_user;

	@Test
	public void test_permitUser_OK_TRUE() throws ParseException {
		//DataSetup
        SQL_tasty.deleteAll();
        SQL_user.deleteAll();
        SQL_user.save(new T02user(1,"tanaka","seattle"));
        SQL_user.save(new T02user(2,"satou","seattle"));
        SQL_user.save(new T02user(3,"matsu","seattle"));
        SQL_user.flush();

		//test
		Integer result =repository.findId(
				SQL_user.findAll(new Sort("id")).get(0).getName(),
				SQL_user.findAll(new Sort("id")).get(0).getPassword()
		);

		//assert
		assertEquals(SQL_user.findAll(new Sort("id")).get(0).getId(), result);
	}

	@Test
	public void test_permitUser_OK_Unmatched() throws ParseException {
		//DataSetup
        SQL_tasty.deleteAll();
        SQL_user.deleteAll();
        SQL_user.save(new T02user(1,"tanaka","seattle"));
        SQL_user.save(new T02user(2,"satou","seattle"));
        SQL_user.flush();

		//test
        Integer result =repository.findId(
				SQL_user.findAll(new Sort("id")).get(0).getName(),
				SQL_user.findAll(new Sort("id")).get(0).getPassword()+ "addString"
		);

		//assert
        assertTrue(null== result);
	}

	@Test
	public void test_permitUser_OK_NullInput() throws ParseException {
		//DataSetup
        SQL_tasty.deleteAll();
        SQL_user.deleteAll();
        SQL_user.save(new T02user(1,"tanaka","seattle"));
        SQL_user.save(new T02user(2,"satou","seattle"));
        SQL_user.flush();

		//test
        Integer result =repository.findId(
				null,
				null
		);

		//assert
        assertTrue(null== result);
	}
}
