package jp.co.seattle.calorieStock.web.form;

import java.util.ArrayList;
import java.util.List;

public class ResultForm {


    private String message1;
    private String name;
	private List<Item> items;

	public final String getMessage1() {
		return message1;
	}

	public final void setMessage1(String message1) {
		this.message1 = message1;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final List<Item> getItems() {
        if (items == null) {
        	items = new ArrayList<Item>();
            for (int i = 0; i < items.size(); i++) {
            	items.add(new Item());
            }
        }
		return items;
	}

	public final void setItems(List<Item> tableRows) {
		this.items = tableRows;
	}

}
