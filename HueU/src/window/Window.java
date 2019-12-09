package window;

import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;


public class Window extends SplitPane {
    private NodeEditor nodeEditor;



    public Window() {
        AnchorPane anchorPane = new AnchorPane();
        nodeEditor = new NodeEditor();
        AnchorPane.setBottomAnchor(this , 2.0);        AnchorPane.setTopAnchor(this, 2.0);
        AnchorPane.setRightAnchor(this , 2.0);         AnchorPane.setLeftAnchor(this, 2.0);
        this.getItems().add(anchorPane);
        anchorPane.getChildren().add(nodeEditor);

    }

}
