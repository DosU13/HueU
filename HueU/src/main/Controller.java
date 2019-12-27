package main;

import MouseGestures.MyCirclesMakeDraggable;
import MouseGestures.MyNodesMakeDraggable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import node.MyNode;
import node.MyNode.myNodeTypes;
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
    void CombineOnAction(ActionEvent event) {
        MyNode myNode = new MyNode(myNodeTypes.COMBINE_RGBA);
        addNewMyNode(myNode);
    }
    @FXML
    void InputOnAction(ActionEvent event) {
        MyNode myNode = new MyNode(myNodeTypes.INPUT);
        addNewMyNode(myNode);
    }
    @FXML
    void MathOnAction(ActionEvent event) {
        MyNode myNode = new MyNode(myNodeTypes.MATH);
        addNewMyNode(myNode);
    }
    @FXML
    void OutputOnAction(ActionEvent event) {
        MyNode myNode = new MyNode(myNodeTypes.OUTPUT);
        addNewMyNode(myNode);
    }
    @FXML
    void SeparateOnAction(ActionEvent event) {
        MyNode myNode = new MyNode(myNodeTypes.SEPARATE_RGBA);
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
    private void initialize(){
        myCirclesMakeDraggable.setDrawingPane(connectorPane);
        nodeEditor.add(nodePane); nodeEditor.add(connectorPane) ; nodeEditor.add(draggablePane);
        root.getChildren().add(window);
        window.add(nodeEditor);


//        System.out.println(inputPoints);


//        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) ->{
//            System.out.println(root.getWidth() +"   "+ root.getHeight());
//            window.setRootSize(root.getWidth() , root.getHeight());
//        };
//        root.widthProperty().addListener(stageSizeListener);
//        root.heightProperty().addListener(stageSizeListener);
    }
}
