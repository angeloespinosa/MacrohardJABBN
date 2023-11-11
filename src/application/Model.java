package application;

public class Model {
    private StringBuilder text;

    public Model() {
        text = new StringBuilder();
    }

    public String getText() {
        return text.toString();
    }

    public void setText(String newText) {
        text = new StringBuilder(newText);
    }

    public void appendText(String newText) {
        text.append(newText);
    }

    public void clearText() {
        text = new StringBuilder();
    }
}
