package jp.co.seattle.colorieStock;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class Application {


//	private JdbcTemplate jdbc;

//	public void method() {
//		System.out.println("出力するよ～");
//		List<Map<String, Object>> list = jdbc.queryForList("SELECT * FROM TBL_CUSTOMER");
//		for(Map<String,Object> a: list){
//			System.out.println(a);
//		}
//	}

    public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);
    }
}
