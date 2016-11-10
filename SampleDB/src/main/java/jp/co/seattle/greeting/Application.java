package jp.co.seattle.greeting;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class Application {

	@Autowired
	private JdbcTemplate jdbc;

	public void method() {
		List<Map<String, Object>> list = jdbc.queryForList("SELECT * FROM TBL_CUSTOMER");
		for(Map<String,Object> a: list){
			System.out.println(a);
		}
	}

    public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);
    }
}

