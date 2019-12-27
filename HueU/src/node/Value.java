package node;

import javafx.scene.control.TextField;

public class Value extends TextField {

    public Value() {
        onlyNumber(this);
    }
    private void onlyNumber(TextField textField) {
        textField.textProperty().addListener((observable, oldValue,newValue) ->{
            if (!newValue.matches("[-]?\\d*")) {                     // for float use [-]?\d*([.]\d*)?
                textField.setText(oldValue);
            }
        });
    }

    public int[][] getValue(){
        int value = Integer.valueOf(this.getText());
        return new int[][]{{value}};
    }
}
