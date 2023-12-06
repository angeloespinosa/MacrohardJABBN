package application;

import javafx.scene.input.KeyEvent;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        view.setOnTextChanged(this::handleTextChanged);
    }

    private void handleTextChanged(KeyEvent event) {
        model.setText(view.getText());
    }
}
