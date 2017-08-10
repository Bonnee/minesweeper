/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 *
 * @author bonnee
 */
public class NumericField extends TextField
{
    NumericField()
    {
        setPrefWidth(50);
        textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
    });
        
        
    }

    NumericField(int value)
    {
        this();
        setText(""+value);
    }

}
