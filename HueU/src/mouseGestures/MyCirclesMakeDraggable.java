package mouseGestures;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import node.Parameter.MyCircle;
import node.Connector;

import java.util.ArrayList;
import java.util.List;

public class MyCirclesMakeDraggable {

    private Pane drawingPane;
    private List<MyCircle> inputPoints = new ArrayList<>();
    private List<MyCircle> outputPoints = new ArrayList<>();
    private List<MyCircle> allPoints = new ArrayList<>();
    private List<MyCircle> activePoints = allPoints;

    public void setDrawingPane(Pane drawingPane) {
        this.drawingPane = drawingPane;
    }

    public void addInputPoints(ArrayList<MyCircle> inputPoints) {
        this.inputPoints.addAll(inputPoints);
        this.allPoints.addAll(inputPoints);
        for (MyCircle i: inputPoints){
            new MyCircleMakeDraggable().makeDraggable(i);
        }
    }

    public void addOutputPoints(ArrayList<MyCircle> outputPoints) {
        this.outputPoints.addAll(outputPoints);
        this.allPoints.addAll(outputPoints);
        for (MyCircle i : outputPoints){
            new MyCircleMakeDraggable().makeDraggable(i);
        }
    }


    private void hoverHighlight(MyCircle myCircle){
        myCircle.hoverProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    myCircle.hoverHighlight();
                } else {
                    myCircle.hoverUnhighlight();
                }
            }
        });
    }



    private MyCircle dragStart;
    private MyCircle dragEnd;
    private Line line = new Line();
    public class MyCircleMakeDraggable{
        MyCircle draggableMyCircle;


        void makeDraggable(MyCircle draggableMyCircle) {
            this.draggableMyCircle = draggableMyCircle;

            MyCirclesMakeDraggable.this.hoverHighlight(draggableMyCircle);

            draggableMyCircle.setOnMousePressed(onMousePressed);
            draggableMyCircle.setOnMouseDragged(onMouseDragged);
            draggableMyCircle.setOnMouseReleased(onMouseReleased);
            draggableMyCircle.setOnDragDetected(onDragDetected);
            draggableMyCircle.setOnMouseDragEntered(onDragEntered);
            draggableMyCircle.setOnMouseDragExited(onDragExited);
        }

        private EventHandler<MouseEvent> onMousePressed = event -> {
            dragStart = draggableMyCircle;
            if (dragStart.isGetter()){
                drawingPane.getChildren().remove(dragStart.getGettersConnector());
                dragStart.setIsSet(Boolean.FALSE);
            }
            line = new Line();
            line.setStroke(Color.WHITE);
            drawingPane.getChildren().add(line);
        };

        private EventHandler<MouseEvent> onMouseDragged = event -> {
            dragStart.hoverHighlight();

            line.setStartX(dragStart.getCenterX());
            line.setStartY(dragStart.getCenterY());
            if (dragEnd==null){
                line.setEndX(event.getX());
                line.setEndY(event.getY());
            }
            else if (activePoints.contains(dragEnd)){
                line.setEndX(dragEnd.getCenterX());
                line.setEndY(dragEnd.getCenterY());
            }
        };

        private EventHandler<MouseEvent> onMouseReleased = event -> {
            drawingPane.getChildren().remove(line);
            dragStart.hoverUnhighlight();
            if (dragEnd != null){
                Connector connector;
                if (dragStart.isGetter()){
                    connector = dragStart.getGettersConnector();
                    connector.setGetterPoint(dragStart);
                    connector.setSetterPoint(dragEnd);
                    dragStart.setIsSet(Boolean.TRUE);
                }
                else {
                    drawingPane.getChildren().remove(dragEnd.getGettersConnector());
                    connector = dragEnd.getGettersConnector();
                    connector.setGetterPoint(dragEnd);
                    connector.setSetterPoint(dragStart);
                    dragEnd.setIsSet(Boolean.TRUE);
                }
                drawingPane.getChildren().add(connector);
            }
            activePoints = allPoints;
        };


        private EventHandler<MouseEvent> onDragDetected = event -> {
            MyCircle cell = (MyCircle) event.getSource();
            cell.startFullDrag();

            if (inputPoints.contains(dragStart)){
                activePoints = outputPoints;
            }
            else if (outputPoints.contains(dragStart)){
                activePoints = inputPoints;
            }

        };

        private EventHandler<MouseEvent> onDragEntered = event -> {
            if (activePoints.contains(draggableMyCircle)){
                dragEnd = draggableMyCircle;
                dragEnd.hoverHighlight();

                line.setEndX(dragEnd.getCenterX());
                line.setEndY(dragEnd.getCenterY());
            }
        };

        private EventHandler<MouseEvent> onDragExited = event -> {
            if (activePoints.contains(draggableMyCircle)){
                if (dragEnd!=null)dragEnd.hoverUnhighlight();
                dragEnd = null;
            }
        };
    }


}

