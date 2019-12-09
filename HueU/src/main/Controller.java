package main;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import window.Window;

public class Controller {

    @FXML
    private AnchorPane root = new AnchorPane();


    @FXML
    private Window window = new Window();

    @FXML
    private void initialize(){

        root.getChildren().add(window);



//        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) ->{
//            System.out.println(root.getWidth() +"   "+ root.getHeight());
//            window.setRootSize(root.getWidth() , root.getHeight());
//        };
//        root.widthProperty().addListener(stageSizeListener);
//        root.heightProperty().addListener(stageSizeListener);
    }
}
