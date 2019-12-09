package MouseGestures;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import node.MyNode;
import node.MyNode.MainAnchorPane;

public class MyNodeMakeDraggable {
    MyNode draggableMyNode;
    MainAnchorPane draggableMainPane;

    public void makeDraggable(MyNode draggableMyNode , MainAnchorPane draggableMainPane) {
        this.draggableMainPane = draggableMainPane;
        this.draggableMyNode = draggableMyNode;
        boolean showHoverCursor = true;

        if( showHoverCursor) {
            draggableMainPane.hoverProperty().addListener(new ChangeListener<Boolean>(){
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                    System.out.println( observable + ": " + newValue);

                    if( newValue) {
                        draggableMainPane.hoverHighlight();
                    } else {
                        draggableMainPane.hoverUnhighlight();
                    }

                    for( String s: draggableMainPane.getStyleClass())
                        System.out.println( draggableMainPane + ": " + s);
                }

            });
        }

//        draggableNode.setOnMousePressed( onMousePressedEventHandler);
//        draggableNode.setOnDragDetected( onDragDetectedEventHandler);
        draggableMainPane.setOnMouseDragged(onMouseDraggedEventHandler );

    }



    EventHandler<MouseEvent> onMouseDraggedEventHandler = event -> {
        System.out.println("drag entered");
        draggableMyNode.setLayoutX(event.getX());
        draggableMyNode.setLayoutY(event.getY());
    };
}
