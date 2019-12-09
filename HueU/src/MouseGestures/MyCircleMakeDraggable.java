package MouseGestures;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import node.Parameter;

public class MyCircleMakeDraggable{

    public void makeDraggable(Parameter.MyCircle draggableNode) {
        boolean showHoverCursor = true;

        if( showHoverCursor) {
            draggableNode.hoverProperty().addListener(new ChangeListener<Boolean>(){
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                    System.out.println( observable + ": " + newValue);

                    if( newValue) {
                        draggableNode.hoverHighlight();
                    } else {
                        draggableNode.hoverUnhighlight();
                    }

                    for( String s: draggableNode.getStyleClass())
                        System.out.println( draggableNode + ": " + s);
                }

            });
        }

//        draggableNode.setOnMousePressed( onMousePressedEventHandler);
//        draggableNode.setOnDragDetected( onDragDetectedEventHandler);
//        draggableNode.setOnMouseDragged(onMouseDraggedEventHandler);

    }
}
