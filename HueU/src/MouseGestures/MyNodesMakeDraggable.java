package MouseGestures;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import node.MyNode;
import node.MyNode.MainAnchorPane;

import java.util.ArrayList;

public class MyNodesMakeDraggable {

    ArrayList<MyNode> myNodeArrayList = new ArrayList<>();

    public void add(MyNode myNode){
        myNodeArrayList.add(myNode);
        new MyNodeMakeDraggable().makeDraggable(myNode);
    }


    public class MyNodeMakeDraggable {
        MyNode draggableMyNode;
        MainAnchorPane draggableMainPane;

        public void makeDraggable(MyNode draggableMyNode) {
            this.draggableMainPane = draggableMyNode.getMainAnchorPane();
            this.draggableMyNode = draggableMyNode;
            boolean showHoverCursor = true;

            if (showHoverCursor) {
                draggableMainPane.hoverProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        if (newValue) {
                            draggableMainPane.hoverHighlight();
                        } else {
                            draggableMainPane.hoverUnhighlight();
                        }
                    }

                });
            }

            draggableMainPane.setOnMousePressed(onMousePressedEventHandler);
            draggableMainPane.setOnMouseDragged(onMouseDraggedEventHandler);
            draggableMainPane.setOnMouseReleased(onMouseReleasedEventHandler);

        }


        private double mouseLayoutX_OnMyNode;
        private double mouseLayoutY_OnMyNode;

        private EventHandler<MouseEvent> onMousePressedEventHandler = event -> {
            mouseLayoutX_OnMyNode = event.getSceneX() - draggableMyNode.getLayoutX();
            mouseLayoutY_OnMyNode = event.getSceneY() - draggableMyNode.getLayoutY();
            draggableMyNode.layoutsChanged();
        };

        private EventHandler<MouseEvent> onMouseDraggedEventHandler = event -> {
            draggableMainPane.hoverHighlight();
            draggableMyNode.setLayoutX(event.getSceneX() - mouseLayoutX_OnMyNode);
            draggableMyNode.setLayoutY(event.getSceneY() - mouseLayoutY_OnMyNode);
            draggableMyNode.layoutsChanged();
        };

        private EventHandler<MouseEvent> onMouseReleasedEventHandler = event -> {
            draggableMyNode.layoutsChanged();
            draggableMainPane.hoverUnhighlight();
        };
    }
}
