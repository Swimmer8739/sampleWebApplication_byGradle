package templates;

public enum ResponseForm {
	login("login"),list("list");

    private final String text;
    private ResponseForm(final String text) {
        this.text = text;
    }
    public String getString() {
        return this.text;
    }
}
