package node;

import javafx.scene.control.TextField;

class Value extends TextField {

    Value() {
        onlyNumber(this);
    }
    private void onlyNumber(TextField textField) {
        textField.textProperty().addListener((observable, oldValue,newValue) ->{
            if (!newValue.matches("[-]?\\d*")) {                     // for float use [-]?\d*([.]\d*)?
                textField.setText(oldValue);
            }
        });
    }

    int[][] getValue(){
        int value = Integer.valueOf(this.getText());
        return new int[][]{{value}};
    }
}
