package main;

        import javafx.beans.value.ChangeListener;
        import javafx.beans.value.ObservableValue;
        import javafx.event.EventHandler;
        import javafx.scene.Node;
        import javafx.scene.input.MouseEvent;
        import node.Connector;
        import node.MyNode;
        import node.Parameter;
        import node.Parameter.MyCircle;

public class MouseGestures {
    private OnMouseMovedListener onMouseMovedListener;
    private Parameter parameter;

    public void setOnMouseMovedListener(OnMouseMovedListener listener) {
        System.out.println("set");
        this.onMouseMovedListener = listener;
    }

    public void makeDraggable(MyNode.MainAnchorPane draggableNode) {
        boolean showHoverCursor = true;

        // that's all there is needed for hovering, the other code is just for painting
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
        draggableNode.setOnDragDetected( onDragDetectedEventHandler);
        draggableNode.setOnMouseDragged(onMouseDraggedEventHandler);

    }

    private Connector line;


    //    EventHandler<MouseEvent> onMousePressedEventHandler = event -> {
//
//        Cell cell = (Cell) event.getSource();
//
//        if( event.isPrimaryButtonDown()) {
//            cell.highlight();
//        } else if( event.isSecondaryButtonDown()) {
//            cell.unhighlight();
//        }
//    };
//
//    EventHandler<MouseEvent> onMouseDraggedEventHandler = event -> {
//
//        PickResult pickResult = event.getPickResult();
//        Node node = pickResult.getIntersectedNode();
//
//        if( node instanceof Cell) {
//
//            Cell cell = (Cell) node;
//
//            if( event.isPrimaryButtonDown()) {
//                cell.highlight();
//            } else if( event.isSecondaryButtonDown()) {
//                cell.unhighlight();
//            }
//
//        }
//
//    };
//
//    EventHandler<MouseEvent> onMouseReleasedEventHandler = event -> {
//    };
//
    EventHandler<MouseEvent> onDragDetectedEventHandler = event -> {
//        System.out.println("drag detected");
//        MyCircle myCircle = (MyCircle) event.getSource();
//        Connector line = new Connector(myCircle.getCenterX() , myCircle.getCenterY() , myCircle.getCenterX() , myCircle.getCenterY());
//        this.line = line;
    };


    EventHandler<MouseEvent> onMouseDraggedEventHandler = event -> {
//        System.out.println("drag entered");
//        MyCircle e = (MyCircle) event.getSource();
//        Connector line = new Connector(parameter.getMyCircle().getCenterX() , parameter.getMyCircle().getCenterY() , e.getCenterX() , e.getCenterY());
//        if (onMouseMovedListener != null) {
//            System.out.println("not null");
//            onMouseMovedListener.onMoved(line);
//        }
    };


    public interface OnMouseMovedListener {
        void onMoved(Connector line);
    }
}