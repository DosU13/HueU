package node;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Parameter extends Pane {

    public enum CircleType {
        GET, SET, NONE
    }

    private String name;
    private ParameterType pType;
    private CircleType cType;
    private MyCircle myCircle;
    private double parameterX;
    private double parameterY;
    private Control active = null;
    private Control extraActive = null;
    private Boolean isActiveNeeded = TRUE;
    private Value textField;

    public enum ParameterType {
        IMAGE_INPUT(CircleType.GET),
        IMAGE_OUTPUT(CircleType.SET),
        IMAGE_GETTER(CircleType.GET),
        IMAGE_SETTER(CircleType.SET),
        VALUE_GETTER(CircleType.GET),
        VALUE_SETTER(CircleType.SET),
        NAME(CircleType.NONE),
        MATH(CircleType.NONE);
        private final CircleType cType;

        ParameterType(CircleType cType) {
            this.cType = cType;
        }
    }


    Parameter(String name, ParameterType pType) {
        this.name = name;
        this.pType = pType;
        cType = pType.cType;

        if (pType.cType != CircleType.NONE) {
            myCircle = new MyCircle(Color.RED);
        }
        AnchorPane.setBottomAnchor(this, 0.0);
        AnchorPane.setTopAnchor(this, 0.0);
        AnchorPane.setRightAnchor(this, 20.0);
        AnchorPane.setLeftAnchor(this, 20.0);
        Label labelName = new Label(name);
        if (pType.cType == CircleType.SET) {
            labelName.layoutXProperty().bind(this.widthProperty().subtract(labelName.widthProperty()));
        }
        labelName.layoutYProperty().bind(this.heightProperty().subtract(labelName.heightProperty()).divide(2));
        this.getChildren().add(labelName);


        if (pType == ParameterType.IMAGE_OUTPUT) {
            Setter_Image setter_image = new Setter_Image();
            setter_image.getImageName().setLayoutX(58);
            setter_image.getImageName().layoutYProperty().bind(this.heightProperty().subtract(setter_image.getImageName().heightProperty()).divide(2));
            setter_image.getImageName().setMaxWidth(67);
            setter_image.getImageName().setMinWidth(67);
            this.getChildren().add(setter_image.getImageName());
            active = setter_image;
            setter_image.getImageName().setStyle("-fx-background-color:white ; -fx-border-color: gray");
            setter_image.setSuperMyCircle(myCircle);
        } else if (pType == ParameterType.IMAGE_INPUT) {
            Getter_Image getter_image = new Getter_Image();
            getter_image.setLayoutX(500);
            getter_image.setSuperMyCircle(this.myCircle);
            active = getter_image;
            extraActive = getter_image.getImageOpener();
        } else if (pType == ParameterType.VALUE_GETTER) {
            textField = new Value();
            textField.setMaxWidth(120);
            active = textField;
        } else {
            isActiveNeeded = FALSE;
        }
    }

    Parameter deepClone() {
        return new Parameter(name, pType);
    }


    Control getActive() {
        return active;
    }

    Control getExtraActive() {
        return extraActive;
    }

    void layoutsChanged() {
        myCircle.layoutsChanged();
//        active.setLayoutX(this.getParameterX());
        if (isActiveNeeded) {
            if (pType == ParameterType.IMAGE_INPUT)
                active.setLayoutX(this.getParameterX() + 160 - 40.50390625);//40.50390625=active.getWidth()
            else if (pType == ParameterType.IMAGE_OUTPUT) active.setLayoutX(this.getParameterX());
            else if (pType == ParameterType.VALUE_GETTER) {
                if (myCircle.isSet) active.setLayoutX(-500);
                else active.setLayoutX(this.getParameterX() + 160 - 120);
            }
            active.setLayoutY(this.getParameterY() + 3);
        }
        if (pType == ParameterType.IMAGE_INPUT) {
            extraActive.setLayoutX(this.getParameterX() + (160 - 45.16796875) / 2);//45.16796875=extraActive.getWidth()
            extraActive.setLayoutY(this.getParameterY() + 3);
        }
    }

    private double getParameterX() {
        return parameterX;
    }

    private double getParameterY() {
        return parameterY;
    }

    void setParameterX(double parameterX) {
        this.parameterX = parameterX;
    }

    void setParameterY(double parameterY) {
        this.parameterY = parameterY;
    }

    MyCircle getMyCircle() {
        return myCircle;
    }

    CircleType getCircleType() {
        return cType;
    }

    public class MyCircle extends Circle {
        String debugger = Parameter.this.name + " " + pType.toString() + "'s MyCircle  ";

        Connector gettersConnector = new Connector();
        ArrayList<Connector> connectors = new ArrayList<>();
        BufferedImage bufferedImage;
        Boolean isSet = FALSE;
        Converter converter = null;
        Math math = null;
        int[][] value;

        Paint paint;

        MyCircle(Paint paint) {
            super(0, 0, 5, paint);
            this.paint = paint;
        }

        void setConverter(Converter converter) {
            this.converter = converter;
        }

        void setMath(Math math) {
            this.math = math;
        }

        //FOR IMAGE_INPUT
        BufferedImage getBufferedImage() {
            if (converter == null) {
                if (pType == ParameterType.IMAGE_INPUT) {
                    if (gettersConnector.getSetterPoint() != null) {
                        System.out.println(debugger + "input");
                        return gettersConnector.getSetterPoint().getBufferedImage();
                    } else {
                        System.out.println(debugger + "ERROR not Connected");
                        return bufferedImage;
                    }
                } else if (pType == ParameterType.IMAGE_GETTER) {
                    if (gettersConnector.getSetterPoint() != null) {
                        System.out.println(debugger + "setter");
                        return gettersConnector.getSetterPoint().getBufferedImage();
                    } else {
                        System.out.println(debugger + "ERROR not Connected");
                        return bufferedImage;
                    }
                } else {
                    System.out.println(debugger + "it must be image_output");
                    return bufferedImage;
                }
            } else {
                System.out.println(debugger + "converter!=null");
                BufferedImage result = converter.getImage();
                System.out.println("returned");
                return result;
            }
        }

        int[][] getValue() {
            if (math != null) {
                System.out.println(debugger + "  math");
                return math.getResultArray();
            } else if (converter != null) {
                switch (name) {
                    case "Red":
                        return converter.getRedArray();
                    case "Green":
                        return converter.getGreenArray();
                    case "Blue":
                        return converter.getBlueArray();
                    case "Alpha":
                        return converter.getAlphaArray();
                    default:
                        System.out.println(debugger + "converter.getValue has avoided by if");
                        return null;
                }
            } else {
                if (isSet) {
                    if (pType == ParameterType.VALUE_GETTER) {
                        if (gettersConnector.getSetterPoint() != null) {
                            return gettersConnector.getSetterPoint().getValue();
                        } else {
                            System.out.println(debugger + "not Connected");
                            return value;
                        }
                    } else {
                        System.out.println(debugger + "CODE ERROR pType != value_getter");
                        return value;
                    }
                } else {
                    System.out.println(debugger + "  text");
                    return textField.getValue();
                }
            }
        }


        //FOR IMAGE-OUTPUT
        void setBufferedImage(BufferedImage bufferedImage) {
            this.bufferedImage = bufferedImage;
        }

        public Connector getGettersConnector() {
            return gettersConnector;
        }

        public Boolean isGetter() {
            return cType == CircleType.GET;
        }

        public void setIsSet(Boolean set) {
            isSet = set;
            if (pType == ParameterType.VALUE_GETTER) {
                if (set) active.setLayoutX(-500);
                else active.setLayoutX(Parameter.this.getParameterX() + 160 - 120);
            }
        }

        List<Connector> getConnectors() {
            return connectors;
        }

        void layoutsChanged() {
            this.setCenterY(Parameter.this.getParameterY() + 15);
            if (cType == CircleType.GET) {
                this.setCenterX(Parameter.this.getParameterX() - 20);
            } else if (cType == CircleType.SET) {
                this.setCenterX(Parameter.this.getParameterX() + 180);
            }
            for (Connector i : connectors) i.layoutsChanged();
        }

        public void hoverHighlight() {
            this.setFill(Color.WHITE);
        }

        public void hoverUnhighlight() {
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
