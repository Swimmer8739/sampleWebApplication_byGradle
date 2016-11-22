package jp.co.seattle.calorieStock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import jp.co.seattle.calorieStock.entity.T02user;
import jp.co.seattle.calorieStock.repository.T01tastyRepository;
import jp.co.seattle.calorieStock.repository.T02userRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebControllerTest {

	@Autowired
	T02userRepository SQL_user;

	@Autowired
	T01tastyRepository SQL_tasty;

	@Autowired
	private MockMvc mockMvc;

	// Viewパラメータテスト ---------------------
	@Test
    public void test_login_OK() throws Exception {
		//test
        this.mockMvc.perform(get("/login"))
        	.andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("login"))
            .andExpect(model().hasNoErrors())
        ;
    }

	@Test
	public void test_list_OK_ViewList() throws Exception {
		//DataSetup
		SQL_tasty.deleteAll();
		SQL_user.deleteAll();

        SQL_user.save(new T02user(1,"Akagi","seattle"));
        SQL_user.flush();
        this.mockMvc.perform(get("/list").param("name", "error").param("password", "false"))
    		.andExpect(status().isOk())
        	.andExpect(model().hasNoErrors())
        ;
	}

    @Test
    public void test_list_OK_redirectLogin() throws Exception {
		//DataSetup
		SQL_tasty.deleteAll();
		SQL_user.deleteAll();

        SQL_user.save(new T02user(1,"Akagi","seattle"));
        SQL_user.flush();

		//test
        mockMvc.perform(get("/list").param("name", "Akagi").param("password", "seattle"))
    		.andExpect(status().isOk())
        	.andExpect(model().hasNoErrors())
        ;
    }
}
