package jp.co.seattle.calorieStock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 松村質問めも：CopyWrite hogehogeとか書きますか？
 *
 *
 * */
@SpringBootApplication
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);
    }
}
