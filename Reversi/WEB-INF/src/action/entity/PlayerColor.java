package action.entity;

public enum PlayerColor {
    BLACK("●"),
    WHITE("○"),
    BLANK("　");

    private final String text;

    private PlayerColor(final String text) {
        this.text = text;
    }

    public String getString() {
        return this.text;
    }
}
