package Main;

import mouseGestures.MyCirclesMakeDraggable;
import mouseGestures.MyNodesMakeDraggable;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import node.MyNode;
import node.MyNode.MyNodeTypes;
import window.NodeEditor;
import window.Window;

public class Controller {

    private MyCirclesMakeDraggable myCirclesMakeDraggable = new MyCirclesMakeDraggable();

    private MyNodesMakeDraggable myNodesMakeDraggable = new MyNodesMakeDraggable();

    private void addNewMyNode(MyNode myNode) {
        nodePane.getChildren().add(myNode);
        myNodesMakeDraggable.add(myNode);
        myCirclesMakeDraggable.addInputPoints(myNode.getInputPoints());
        myCirclesMakeDraggable.addOutputPoints(myNode.getOutputPoints());
        draggablePane.getChildren().addAll(myNode.getOutputPoints());
        draggablePane.getChildren().addAll(myNode.getInputPoints());
        draggablePane.getChildren().add(myNode.getMainAnchorPane());
        draggablePane.getChildren().addAll(myNode.getActives());
    }

    @FXML
    private AnchorPane root;

    @FXML
    void DistanceOnAction() {
        MyNode myNode = new MyNode(MyNodeTypes.MATHDISTANCE);
        addNewMyNode(myNode);
    }

    @FXML
    void EqualsOnAction() {
        MyNode myNode = new MyNode(MyNodeTypes.MATHEQUALS);
        addNewMyNode(myNode);
    }

    @FXML
    void GreaterOnAction() {
        MyNode myNode = new MyNode(MyNodeTypes.MATHGREATER);
        addNewMyNode(myNode);
    }

    @FXML
    void MaxOnAction() {
        MyNode myNode = new MyNode(MyNodeTypes.MATHMAX);
        addNewMyNode(myNode);
    }

    @FXML
    void MinOnAction() {
        MyNode myNode = new MyNode(MyNodeTypes.MATHMIN);
        addNewMyNode(myNode);
    }

    @FXML
    void CombineOnAction() {
        MyNode myNode = new MyNode(MyNodeTypes.COMBINE_RGBA);
        addNewMyNode(myNode);
    }

    @FXML
    void InputOnAction() {
        MyNode myNode = new MyNode(MyNodeTypes.INPUT);
        addNewMyNode(myNode);
    }

    @FXML
    void OutputOnAction() {
        MyNode myNode = new MyNode(MyNodeTypes.OUTPUT);
        addNewMyNode(myNode);
    }

    @FXML
    void SeparateOnAction() {
        MyNode myNode = new MyNode(MyNodeTypes.SEPARATE_RGBA);
        addNewMyNode(myNode);
    }

    @FXML
    void AddOnAction() {
        MyNode myNode = new MyNode(MyNodeTypes.MATHADD);
        addNewMyNode(myNode);
    }

    @FXML
    void SubtractOnAction() {
        MyNode myNode = new MyNode(MyNodeTypes.MATHSUBTRACT);
        addNewMyNode(myNode);
    }

    @FXML
    void MultiplyOnAction() {
        MyNode myNode = new MyNode(MyNodeTypes.MATHMULTIPLY);
        addNewMyNode(myNode);
    }

    @FXML
    void DivideOnAction() {
        MyNode myNode = new MyNode(MyNodeTypes.MATHDIVIDE);
        addNewMyNode(myNode);
    }

    @FXML
    private Window window = new Window();

    @FXML
    private NodeEditor nodeEditor = new NodeEditor();

    private Pane nodePane = new Pane();
    private Pane connectorPane = new Pane();
    private Pane draggablePane = new Pane();

    @FXML
    private void initialize() {
        myCirclesMakeDraggable.setDrawingPane(connectorPane);
        nodeEditor.add(nodePane);
        nodeEditor.add(connectorPane);
        nodeEditor.add(draggablePane);
        root.getChildren().add(window);
        window.add(nodeEditor);
    }
}
