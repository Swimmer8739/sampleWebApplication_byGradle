package jp.co.seattle.calorieStock;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
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
import jp.co.seattle.calorieStock.repository.t01tastyRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class t01tastyRepositoryTest {

    @Autowired
    private MockMvc mockMvc;

	@Autowired
	t01tastyRepository repository;


	//Viewパラメータテスト
	@Test
    public void paramPageShouldReturnMessage1() throws Exception {

        this.mockMvc.perform(get("/login").param("name", "Guest001")).andDo(print())
        	.andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("list"))
            .andExpect(model().hasNoErrors())
            //.andExpect(MockMvcResultMatchers.model().hasErrors())
            //.andExpect(MockMvcResultMatchers.model().attribute("name", "Guest001")).andReturn()
        ;

    }

	//Model域 モジュールテスト
	//findAll
	@Test
	public void testFindAll_OK() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        Date formatDate = sdf.parse("20001111 00:00:00");

		List<T01tasty> items = new ArrayList<T01tasty>();
		items=repository.findAll();

		assertEquals(4, items.size());
		assertEquals((Integer)1, items.get(0).getId());
		assertEquals(formatDate, items.get(0).getDate());
		assertEquals("ごはん", items.get(0).getEats());
		assertEquals((Double)100.1 , (Double)items.get(0).getCalorie_kcal());
		assertEquals("tanaka", items.get(0).getUserID());

	}
}
