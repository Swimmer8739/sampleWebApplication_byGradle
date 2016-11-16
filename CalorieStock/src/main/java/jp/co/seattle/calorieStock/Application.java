package jp.co.seattle.calorieStock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {

    	SpringApplication.run(Application.class, args);
    	//try (ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args)) {
        //	Application m = ctx.getBean(Application.class);
        //   m.hello();
        //}
    }
}
