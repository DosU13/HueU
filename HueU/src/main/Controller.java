package main;

import MouseGestures.MyCirclesMakeDraggable;
import MouseGestures.MyNodesMakeDraggable;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import node.MyNode;
import node.MyNode.myNodeTypes;
import window.NodeEditor;
import window.Window;

public class Controller {

    public MenuItem InputImage;
    public MenuItem OutputImage;
    public MenuItem SeparateRGB;
    public MenuItem CombineRGBA;
    public MenuItem Add;
    public MenuItem Subtract;
    public MenuItem Multiply;
    public MenuItem Divide;
    public MenuItem Formatter;
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
    void CombineOnAction() {
        MyNode myNode = new MyNode(myNodeTypes.COMBINE_RGBA);
        addNewMyNode(myNode);
    }

    @FXML
    void InputOnAction() {
        MyNode myNode = new MyNode(myNodeTypes.INPUT);
        addNewMyNode(myNode);
    }

    @FXML
    void OutputOnAction() {
        MyNode myNode = new MyNode(myNodeTypes.OUTPUT);
        addNewMyNode(myNode);
    }

    @FXML
    void SeparateOnAction() {
        MyNode myNode = new MyNode(myNodeTypes.SEPARATE_RGBA);
        addNewMyNode(myNode);
    }

    @FXML
    void AddOnAction() {
        MyNode myNode = new MyNode(myNodeTypes.MATH);
        addNewMyNode(myNode);
    }

    @FXML
    void SubtractOnAction() {
        MyNode myNode = new MyNode(myNodeTypes.MATH);
        addNewMyNode(myNode);
    }

    @FXML
    void MultiplyOnAction() {
        MyNode myNode = new MyNode(myNodeTypes.MATH);
        addNewMyNode(myNode);
    }

    @FXML
    void DivideOnAction() {
        MyNode myNode = new MyNode(myNodeTypes.MATH);
        addNewMyNode(myNode);
    }

    @FXML
    void FormatterOnAction() {
        MyNode myNode = new MyNode(myNodeTypes.MATH);
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
