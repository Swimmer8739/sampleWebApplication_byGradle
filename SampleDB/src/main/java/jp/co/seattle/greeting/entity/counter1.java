package jp.co.seattle.greeting.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity // このクラスはEntityとして登録しますよ、とspringに教えてます
public class counter1 {

	@Id //　プライマリーキーのものに設定してください
	@GeneratedValue //主に数字に対して、順番に一意に設定しますよ、の意味
	protected Integer id;

	@Column //ただの変数じゃなくて、DBのカラムだよ、の意味
	protected String title;

	@Column //ただの変数じゃなくて、DBのカラムだよ、の意味
	protected int count;

	// setter & getter ---------------------
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

    // constructor --------------------------
	public counter1(){
		super();
	}

	public counter1(Integer id, String name , int count){
        super();
        this.id =id;
        this.title = name;
        this.count=count;
    }

}