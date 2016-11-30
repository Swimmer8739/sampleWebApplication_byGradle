package jp.co.seattle.calorieStock;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.seattle.calorieStock.entity.T01tasty;
import jp.co.seattle.calorieStock.entity.T02user;
import jp.co.seattle.calorieStock.repository.T01tastyRepository;
import jp.co.seattle.calorieStock.repository.T02userRepository;
import jp.co.seattle.calorieStock.web.form.Item;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class T01tastyServiceTest {


    @Rule
    public ExpectedException exception  = ExpectedException.none();

	@Autowired
	T01tastyService repository;

	@Autowired
	T01tastyRepository SQL_tasty;

	@Autowired
	T02userRepository SQL_user;

	@Before
	public void dataSetup(){
        SQL_tasty.deleteAll();
        SQL_user.deleteAll();
	}

	@Test
	public void test_narrow_OK() throws ParseException {
		//DataSetup
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        Date formatDate = sdf.parse("20001111 00:00:00");

        SQL_user.save(new T02user(1,"Akagi","seattle"));
        SQL_user.save(new T02user(2,"Baki","seattle"));
        SQL_user.save(new T02user(3,"Chiba","seattle"));
        SQL_user.flush();
        SQL_tasty.save(new T01tasty(1,formatDate,"いも",100.0,SQL_user.findAll().get(0).getId()));
        SQL_tasty.save(new T01tasty(2,formatDate,"いも",200.0,SQL_user.findAll().get(1).getId()));
        SQL_tasty.save(new T01tasty(3,formatDate,"いも",300.0,SQL_user.findAll().get(2).getId()));
        SQL_tasty.save(new T01tasty(4,formatDate,"いも",400.0,SQL_user.findAll().get(0).getId()));
        SQL_tasty.save(new T01tasty(5,formatDate,"いも",500.0,SQL_user.findAll().get(1).getId()));
        SQL_tasty.save(new T01tasty(6,formatDate,"いも",600.0,SQL_user.findAll().get(1).getId()));
        SQL_tasty.flush();

		//test
		List<Item> itemsA =repository.extractById(SQL_user.findAll(new Sort("id")).get(0).getId());
		List<Item> itemsB =repository.extractById(SQL_user.findAll(new Sort("id")).get(1).getId());
		List<Item> itemsC =repository.extractById(SQL_user.findAll(new Sort("id")).get(2).getId());

		//assert
		assertEquals(6, SQL_tasty.findAll().size());
		assertEquals(2, itemsA.size());
		assertEquals(3, itemsB.size());
		assertEquals(1, itemsC.size());
	}
	@Test
	public void test_narrow_OK_ZeroHit() throws ParseException {
		//DataSetup
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        Date formatDate = sdf.parse("20001111 00:00:00");

        SQL_user.save(new T02user(1,"tanaka","seattle"));
        SQL_user.flush();
        SQL_tasty.save(new T01tasty(1,formatDate,"いも",100.0,SQL_user.findAll().get(0).getId()));
        SQL_tasty.flush();

		//test
		List<Item> items =repository.extractById(-1);

		//assert
		assertEquals(0, items.size());
	}
	@Test
	public void test_narrow_OK_getEmptyRecord() {
		//DataSetup
        SQL_user.save(new T02user(1,"Akagi","seattle"));
        SQL_user.flush();

		//test
		List<Item> items =repository.extractById(SQL_user.findAll().get(0).getId());

		//assert
		assertEquals(0, items.size());
	}

	@Test
	public void test_add_OK() throws ParseException {
		//DataSetup
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        Date formatDate = sdf.parse("20001111 00:00:00");

        SQL_user.save(new T02user(1,"Akagi","seattle"));
        SQL_user.flush();

		//test
		repository.add( formatDate, "たまご", 123.4, SQL_user.findAll().get(0).getId());
		repository.add( formatDate, "にもの", 123.4, SQL_user.findAll().get(0).getId());

		//assert
		assertEquals(2, SQL_tasty.findAll().size());
	}


	@Test
	public void test_add_NG_DataIntegrityViolationException() throws ParseException {
		//DataSetup
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        Date formatDate = sdf.parse("20001111 00:00:00");

        SQL_user.save(new T02user(1,"Akagi","seattle"));
        SQL_user.flush();

		//test
        exception.expect(DataIntegrityViolationException.class);
		repository.add( formatDate, "桁数超過567890123456789012345678901", 123.4, SQL_user.findAll().get(0).getId());
		repository.add( formatDate, "たまご", 12000000003.4, SQL_user.findAll().get(0).getId());
		repository.add( formatDate, "たまご", 123.4, 100000);
	}

	@Test
	public void test_delete_OK() throws ParseException {
		//DataSetup
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        Date formatDate = sdf.parse("20001111 00:00:00");

        SQL_user.save(new T02user(1,"Akagi","seattle"));
        SQL_user.flush();
        SQL_tasty.save(new T01tasty(1,formatDate,"いも",100.0,SQL_user.findAll().get(0).getId()));
        SQL_tasty.flush();

		//test
		repository.delete( SQL_tasty.findAll().get(0).getId());

		//assert
		assertEquals(0, SQL_tasty.findAll().size());
	}

	@Test
	public void test_delete_NG_EmptyResultDataAccess() throws ParseException {
		//DataSetup
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        Date formatDate = sdf.parse("20001111 00:00:00");

        SQL_user.save(new T02user(1,"Akagi","seattle"));
        SQL_user.flush();
        SQL_tasty.save(new T01tasty(1,formatDate,"いも",100.0,SQL_user.findAll().get(0).getId()));
        SQL_tasty.flush();

		//test
        exception.expect(EmptyResultDataAccessException.class);
		repository.delete(SQL_tasty.findAll().get(0).getId()+1);
	}
}
