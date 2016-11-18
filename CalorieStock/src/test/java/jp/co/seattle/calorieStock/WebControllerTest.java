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
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

/*	  @Before
	  public void setup() {
	    mockMvc = webAppContextSetup(wac).build();
	  }*/

	// Viewパラメータテスト ---------------------
	@Test
    public void test_viewDisplay_loginHtml() throws Exception {

        this.mockMvc.perform(get("/login"))
        	.andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("login"))
            .andExpect(model().hasNoErrors())
        ;
    }

	public void test_viewDisplay_listHtml() throws Exception {
        this.mockMvc.perform(get("/list"))
        	.andExpect(status().isOk())
        	.andExpect(MockMvcResultMatchers.view().name("list"))
        	.andExpect(model().hasErrors())
        ;
	}

    @Test
    public void test_ViewList() throws Exception {
        int id = 123;
        mockMvc.perform(get("/list").param("name", "Akagi").param("password", "seattle"))
            .andExpect(status().isOk())
        	.andExpect(model().hasErrors())
        ;
    }
}
