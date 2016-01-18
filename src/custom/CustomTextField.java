package custom;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
/**
*
* @author Long Huynh
*/

public class CustomTextField {
    public void clearTextFieldByButton(TextField value, Button button) {
        button.setVisible(false);
        button.setText(null);
        button.minHeight(35.0);
        button.minHeight(25.0);
        button.getStylesheets().add("");
        value.setOnKeyReleased((KeyEvent event) -> {
            if ((value.textProperty().get().length() < 0) || (value.textProperty().get().equals(""))) {
                button.setVisible(false);
            } else if (value.textProperty().get().length() > -1 || (!value.textProperty().get().equals(""))) {
                button.setVisible(true);
            }
        });
        value.setOnKeyTyped((KeyEvent event) -> {
            if ((value.textProperty().get().length() < 0) || (value.textProperty().get().equals(""))) {
                button.setVisible(false);
            } else if (value.textProperty().get().length() > -1 || (!value.textProperty().get().equals(""))) {
                button.setVisible(true);
            }
        });
        value.setOnKeyPressed((KeyEvent event) -> {
            if ((value.textProperty().get().length() < 0) || (value.textProperty().get().equals(""))) {
                button.setVisible(false);
            } else if (value.textProperty().get().length() > -1 || (!value.textProperty().get().equals(""))) {
                button.setVisible(true);
            }
        });
        button.setOnAction((ActionEvent event) -> {
            value.clear();
            button.setVisible(false);
            value.requestFocus();
        });

    }

    public void numaricTextfield(TextField textField) {
        textField.setOnKeyReleased((KeyEvent event) -> {
            if (!textField.getText().matches("[0-9.]*")) {
                textField.deletePreviousChar();
            }
        });
        textField.setOnKeyPressed((KeyEvent event) -> {
            if (!textField.getText().matches("[0-9.]*")) {
                textField.deletePreviousChar();
            }
        });
        textField.setOnKeyTyped((KeyEvent event) -> {
            if (!textField.getText().matches("[0-9.]*")) {
                textField.deletePreviousChar();
            }
        });
    }
}
