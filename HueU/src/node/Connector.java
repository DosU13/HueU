package node;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import node.Parameter.MyCircle;
import java.util.List;

public class Connector extends Line {
    private MyCircle getterPoint;
    private MyCircle setterPoint;

    public Connector(){
        this.setStroke(Color.WHITE);
    }

    public void setGetterPoint(MyCircle getterPoint) {
        this.getterPoint = getterPoint;
        getterPoint.getConnectors().add(this);
        layoutsChanged();
    }

    public void setSetterPoint(MyCircle setterPoint) {
        this.setterPoint = setterPoint;
        setterPoint.getConnectors().add(this);
        layoutsChanged();
    }

    public MyCircle getSetterPoint() {
        return setterPoint;
    }

    public void layoutsChanged(){
        try {
            super.setStartX(getterPoint.getCenterX());
            super.setStartY(getterPoint.getCenterY());
            super.setEndX(setterPoint.getCenterX());
            super.setEndY(setterPoint.getCenterY());
        } catch(NullPointerException e){} ;
    }

    public List<Connector> getConnectorList(){
        return null;
    }
}