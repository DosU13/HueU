package node;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import MouseGestures.MyCircleMakeDraggable;

public class Parameter extends Pane {

    MyCircleMakeDraggable myCircleMakeDraggable = new MyCircleMakeDraggable();

    public enum CircleType {
        IN , OUT , NONE
    }

    private ParameterType pType;
    private MyCircle myCircle;

    public enum ParameterType {
        IN_IMAGE (CircleType.IN),
        OUT_IMAGE(CircleType.OUT),
        IN_VALUE(CircleType.IN) ,
        OUT_VALUE(CircleType.OUT) ,
        NAME(CircleType.NONE) ,
        MATH(CircleType.NONE) ,
        BUTTON(CircleType.NONE);
        private final CircleType cType;
        ParameterType(CircleType cType){
            this.cType = cType;
        }
    }

    public Parameter(String name , ParameterType pType) {
        this.pType = pType;

        if (pType.cType != CircleType.NONE) {
            myCircle = new MyCircle(Color.RED);
            this.getChildren().add(myCircle);
        }
        AnchorPane.setBottomAnchor(this, 0.0);       AnchorPane.setTopAnchor(this, 0.0);
        AnchorPane.setRightAnchor(this, 20.0);       AnchorPane.setLeftAnchor(this, 20.0);
        Label labelName = new Label(name);
        if (pType.cType == CircleType.OUT){
            labelName.layoutXProperty().bind(this.widthProperty().subtract(labelName.widthProperty()));
        }
        labelName.layoutYProperty().bind(this.heightProperty().subtract(labelName.heightProperty()).divide(2));
        this.getChildren().add(labelName);
    }

    public class MyCircle extends Circle {
        Paint paint;
        public MyCircle(Paint paint) {
            super(0, 15, 5, paint);
            myCircleMakeDraggable.makeDraggable(this);

            this.paint = paint;
            if (pType.cType == CircleType.IN){
                super.setCenterX(-20);
            }
            else if (pType.cType == CircleType.OUT) {
                super.setCenterX(180);
            }
        }

        public void hoverHighlight(){
            this.setFill(Color.WHITE);
        }

        public void hoverUnhighlight(){
            this.setFill(paint);
        }
    }
}

//        mouseGestures.setOnMouseMovedListener(new MakeDraggable.OnMouseMovedListener() {
//@Override
//public void onMoved(Connector line) {
//        System.out.println("onMoved");
//        Parameter.this.getChildren().addAll(line);
//        }
//        });
//        mouseGestures.makePaintable(connectionPoint);
