package application;

import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class View extends BorderPane {
    private TextArea textArea;

    public View() {
        textArea = new TextArea();
        this.setCenter(textArea);
    }

    public String getText() {
        return textArea.getText();
    }

    public void setText(String text) {
        textArea.setText(text);
    }

    public void setOnTextChanged(EventHandler<KeyEvent> handler) {
        textArea.setOnKeyTyped(handler);
    }
}
