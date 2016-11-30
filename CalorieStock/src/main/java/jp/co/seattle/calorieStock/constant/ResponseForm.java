package jp.co.seattle.calorieStock.constant;


/**
 * HTMLファイル名の一覧です。
 * */
public enum ResponseForm {
	LIST("list"),LOGIN("login");


    private final String text;
    private ResponseForm(final String text) {
        this.text = text;
    }
    public String getString() {
        return this.text;
    }
}
