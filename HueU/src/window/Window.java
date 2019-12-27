package window;

import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;


public class Window extends SplitPane {

    private AnchorPane anchorPane ;

    public Window() {
        anchorPane = new AnchorPane();
        AnchorPane.setBottomAnchor(this , 2.0);        AnchorPane.setTopAnchor(this, 2.0);
        AnchorPane.setRightAnchor(this , 2.0);         AnchorPane.setLeftAnchor(this, 2.0);
        this.getItems().add(anchorPane);
    }

    public void add(Node node){
        anchorPane.getChildren().add(node);
    }

}
