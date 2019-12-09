package node;

import javafx.scene.shape.Line;


public class Connector extends Line {

    public Connector(double v, double v1, double v2, double v3) {
        super(v, v1, v2, v3);
    }

    public void dragged(double x ,double y){
        super.setEndX(x);
        super.setEndY(y);
    }
}
