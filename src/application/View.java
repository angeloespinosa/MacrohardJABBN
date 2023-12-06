package application;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.HTMLEditor;

public class View extends BorderPane {
    private HTMLEditor textArea;

    public View() {
        textArea = new HTMLEditor();
        this.setCenter(textArea);
    }

    public String getText() {
        return textArea.getHtmlText();
    }

    public void setText(String text) {
        textArea.setHtmlText(text);
    }

    public void setOnTextChanged(EventHandler<KeyEvent> handler) {
        textArea.setOnKeyTyped(handler);
    }
}
