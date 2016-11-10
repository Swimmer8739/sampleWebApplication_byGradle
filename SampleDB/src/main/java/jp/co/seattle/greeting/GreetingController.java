package jp.co.seattle.greeting;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.seattle.greeting.entity.counter1;
import jp.co.seattle.greeting.repository.Counter1Repository;

@Controller //コントローラーだよ、っていう定義
@EnableAutoConfiguration
public class GreetingController {

	@Autowired //リポジトリを紐づけます
	Counter1Repository repository;

	@RequestMapping("/DB")
	@ResponseBody
	public String home(){

		// 全件取得します
		Iterable<counter1> list = repository.findAll();

		// 取得した内容を出力します
		for(counter1 emp: list){
			System.out.println(emp.getId() + ":" + emp.getTitle());
		}
		return "Hello!";	//ここは気にしないでください。
							//ないと怒られますので～。
	}

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();


    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

}
