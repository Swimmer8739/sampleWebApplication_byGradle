package jp.co.seattle.calorieStock;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import jp.co.seattle.calorieStock.entity.T01tasty;
import jp.co.seattle.calorieStock.repository.T01tastyRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebModelTest {

	@Autowired
	T01tastyRepository repository;

	MockMvc mockMvc;

	// Viewパラメータテスト ---------------------
	@Test
    public void paramPageShouldReturnMessage1() throws Exception {

        this.mockMvc.perform(get("/list"))
        	.andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("list"))
            .andExpect(model().hasNoErrors())
        ;
    }

	//findAll
	@Test
	public void testFindAll_OK() throws ParseException {
		//DataSetup
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        Date formatDate = sdf.parse("20001111 00:00:00");

		repository.flush();
		repository.save(new T01tasty(1,formatDate,"",0.0,1));

		List<T01tasty> items = new ArrayList<T01tasty>();

		//test
		items=repository.findAll();		//※MySQL is already inserted 4records.

		//assert
		assertEquals(4, items.size());
		assertEquals((Integer)1, items.get(0).getId());
		assertEquals(formatDate, items.get(0).getDate());
		assertEquals("ごはん", items.get(0).getEats());
		assertEquals((Double)100.1 , (Double)items.get(0).getCalorie_kcal());
		assertEquals("tanaka", items.get(0).getUserID());
	}

	@Test
	public void testFindAll_NG() throws ParseException {

		repository.flush();

		List<T01tasty> items = new ArrayList<T01tasty>();
		items=repository.findAll();

		assertEquals(null, items.size());
	}
}
