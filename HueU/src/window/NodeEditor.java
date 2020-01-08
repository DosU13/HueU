package window;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;


public class NodeEditor extends ScrollPane {

    private NodeEditorPane nodeEditorPane = new NodeEditorPane();


    public NodeEditor() {
        this.setStyle("-fx-background-color: gray");
        AnchorPane.setBottomAnchor(this, 0.0);
        AnchorPane.setTopAnchor(this, 0.0);
        AnchorPane.setRightAnchor(this, 0.0);
        AnchorPane.setLeftAnchor(this, 0.0);
        this.setContent(nodeEditorPane);
    }

    public void add(Node node) {
        nodeEditorPane.getChildren().add(node);
    }

    public class NodeEditorPane extends Pane {
        private int width = 2000, height = 2000, disBtwLines = 20;

        NodeEditorPane() {
            this.setPrefSize(width, height);
            this.setStyle("-fx-background-color: gray");
            for (int i = disBtwLines; i < width; i += disBtwLines) {
                if ((i / disBtwLines) % 5 != 0) {
                    Line line = new Line(i, 0, i, height);
                    line.setStroke(Color.GRAY.darker());
                    this.getChildren().add(line);
                } else {
                    Line line = new Line(i, 0, i, height);
                    line.setStroke(Color.BLACK);
                    this.getChildren().add(line);
                }

            }

            for (int i = disBtwLines; i < height; i += disBtwLines) {
                if ((i / disBtwLines) % 5 != 0) {
                    Line line = new Line(0, i, width, i);
                    line.setStroke(Color.GRAY.darker());
                    this.getChildren().add(line);
                } else {
                    Line line = new Line(0, i, width, i);
                    line.setStroke(Color.BLACK);
                    this.getChildren().add(line);
                }

            }
        }


    }
}
