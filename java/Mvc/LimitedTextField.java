package Mvc;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;


/**
 * Created by Simone on 11/01/2016.
 */
public class LimitedTextField extends TextField {

    public  LimitedTextField() {
        super();
        TextField textField = this;
        textField.lengthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    // Check if the new character is greater than LIMIT
                    if (textField.getText().length() >= 10) {
                        // if it's 11th character then just setText to previous
                        // one
                        textField.setText(textField.getText().substring(0, 10));
                    }
                }
            }
        });
    }

        public LimitedTextField(String text)
        {
            super(text);
            TextField textField = this;
            textField.lengthProperty().addListener(new ChangeListener<Number>() {

                @Override
                public void changed(ObservableValue<? extends Number> observable,
                                    Number oldValue, Number newValue) {
                    if (newValue.intValue() > oldValue.intValue()) {
                        // Check if the new character is greater than LIMIT
                        if (textField.getText().length() >= 10) {
                            // if it's 11th character then just setText to previous
                            // one
                            textField.setText(textField.getText().substring(0, 10));
                        }
                    }
                }
            });


        }
    }