package jp.co.seattle.calorieStock.constant;



public enum ResponseForm {
	//manege html file name.
	LIST("list"),LOGIN("login");


    private final String text;
    private ResponseForm(final String text) {
        this.text = text;
    }
    public String getString() {
        return this.text;
    }
}
