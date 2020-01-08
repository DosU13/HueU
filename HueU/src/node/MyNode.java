package node;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import node.Parameter.MyCircle;
import node.Parameter.ParameterType;

import java.util.ArrayList;

public class MyNode extends VBox {
    private MainAnchorPane mainAnchorPane;
    private ArrayList<MyCircle> inputPoints = new ArrayList<>();
    private ArrayList<MyCircle> outputPoints = new ArrayList<>();
    private ArrayList<Node> actives = new ArrayList<>();
    private MyNodeTypes mNT;
    private Parameter[] parameters;

    public enum MyNodeTypes {
        INPUT("#8d0000" ,"#f00" ,2, new Parameter[]{
                new Parameter("Image" , ParameterType.NAME),
                new Parameter("Image" , ParameterType.IMAGE_OUTPUT)}),
        OUTPUT( "#8d0000","#f00", 2, new Parameter[]{
                new Parameter("Output" , ParameterType.NAME),
                new Parameter("Image" , ParameterType.IMAGE_INPUT)}) ,
        SEPARATE_RGBA( "#8d8d00" ,"#ff0" , 6, new Parameter[]{
                new Parameter("Separate RGBA" , ParameterType.NAME),
                new Parameter("Red" , ParameterType.VALUE_SETTER) ,
                new Parameter("Green" , ParameterType.VALUE_SETTER) ,
                new Parameter("Blue" , ParameterType.VALUE_SETTER) ,
                new Parameter("Alpha" , ParameterType.VALUE_SETTER) ,
                new Parameter("Image" , ParameterType.IMAGE_GETTER)}) ,
        COMBINE_RGBA("#8d8d00" ,"#ff0" ,6, new Parameter[]{
                new Parameter("Combine RGBA" , ParameterType.NAME),
                new Parameter("Image" , ParameterType.IMAGE_SETTER) ,
                new Parameter("Red" , ParameterType.VALUE_GETTER) ,
                new Parameter("Green" , ParameterType.VALUE_GETTER) ,
                new Parameter("Blue" , ParameterType.VALUE_GETTER) ,
                new Parameter("Alpha" , ParameterType.VALUE_GETTER)}) ,
        MATHADD("#008d8d" ,"#0ff" , 5 , new Parameter[]{
                new Parameter("Add" , ParameterType.NAME),
                new Parameter("Value" , ParameterType.VALUE_SETTER) ,
                new Parameter("" , ParameterType.MATH) ,
                new Parameter("Value" , ParameterType.VALUE_GETTER) ,
                new Parameter("Value" , ParameterType.VALUE_GETTER)  }),
        MATHSUBTRACT("#008d8d" ,"#0ff" , 5 , new Parameter[]{
                new Parameter("Subtract" , ParameterType.NAME),
                new Parameter("Value" , ParameterType.VALUE_SETTER) ,
                new Parameter("" , ParameterType.MATH) ,
                new Parameter("Value" , ParameterType.VALUE_GETTER) ,
                new Parameter("Value" , ParameterType.VALUE_GETTER)  }),
        MATHMULTIPLY("#008d8d" ,"#0ff" , 5 , new Parameter[]{
                new Parameter("Multiply" , ParameterType.NAME),
                new Parameter("Value" , ParameterType.VALUE_SETTER) ,
                new Parameter("" , ParameterType.MATH) ,
                new Parameter("Value" , ParameterType.VALUE_GETTER) ,
                new Parameter("Value" , ParameterType.VALUE_GETTER)  }),
        MATHDIVIDE("#008d8d" ,"#0ff" , 5 , new Parameter[]{
                new Parameter("Divide" , ParameterType.NAME),
                new Parameter("Value" , ParameterType.VALUE_SETTER) ,
                new Parameter("" , ParameterType.MATH) ,
                new Parameter("Value" , ParameterType.VALUE_GETTER) ,
                new Parameter("Value" , ParameterType.VALUE_GETTER)  }),
        MATHDISTANCE("#008d8d" ,"#0ff" , 5 , new Parameter[]{
                new Parameter("Distance" , ParameterType.NAME),
                new Parameter("Value" , ParameterType.VALUE_SETTER) ,
                new Parameter("" , ParameterType.MATH) ,
                new Parameter("Value" , ParameterType.VALUE_GETTER) ,
                new Parameter("Value" , ParameterType.VALUE_GETTER)  }),
        MATHMAX("#008d8d" ,"#0ff" , 5 , new Parameter[]{
                new Parameter("Max" , ParameterType.NAME),
                new Parameter("Value" , ParameterType.VALUE_SETTER) ,
                new Parameter("" , ParameterType.MATH) ,
                new Parameter("Value" , ParameterType.VALUE_GETTER) ,
                new Parameter("Value" , ParameterType.VALUE_GETTER)  }),
        MATHMIN("#008d8d" ,"#0ff" , 5 , new Parameter[]{
                new Parameter("Min" , ParameterType.NAME),
                new Parameter("Value" , ParameterType.VALUE_SETTER) ,
                new Parameter("" , ParameterType.MATH) ,
                new Parameter("Value" , ParameterType.VALUE_GETTER) ,
                new Parameter("Value" , ParameterType.VALUE_GETTER)  }),
        MATHEQUALS("#008d8d" ,"#0ff" , 5 , new Parameter[]{
                new Parameter("Equals" , ParameterType.NAME),
                new Parameter("Value" , ParameterType.VALUE_SETTER) ,
                new Parameter("" , ParameterType.MATH) ,
                new Parameter("Value" , ParameterType.VALUE_GETTER) ,
                new Parameter("Value" , ParameterType.VALUE_GETTER)  }),
        MATHGREATER("#008d8d" ,"#0ff" , 5 , new Parameter[]{
                new Parameter("Greater than" , ParameterType.NAME),
                new Parameter("Value" , ParameterType.VALUE_SETTER) ,
                new Parameter("" , ParameterType.MATH) ,
                new Parameter("Value" , ParameterType.VALUE_GETTER) ,
                new Parameter("Value" , ParameterType.VALUE_GETTER)  });


        private final String color;
        private final String highlightColor;
        private final int nOfParameters;
        private final Parameter[] parameters;
        MyNodeTypes(String  color , String highlightColor , int nOfParameters, Parameter[] parameters){
            this.nOfParameters = nOfParameters;
            this.parameters = parameters;
            this.color = color;
            this.highlightColor = highlightColor;
        }
    }

    public MyNode(MyNodeTypes mNT) {
        this.mNT = mNT;
        this.setLayoutX(50); this.setLayoutY(50);
        this.setStyle("-fx-background-color: silver ; -fx-background-radius: 20 20 20 20");
        int nOfParameters = mNT.nOfParameters;
        this.setPrefSize(200, (0.5 + nOfParameters) * 30 );
        parameters = new Parameter[nOfParameters];
        for (int i = 0 ; i < nOfParameters ; i++){
            parameters[i] = mNT.parameters[i].deepClone();
        }
        AnchorPane[] anchorPanes = new AnchorPane[nOfParameters];
        mainAnchorPane = new MainAnchorPane(mNT);

        mainAnchorPane.setPrefSize(200,30);
        mainAnchorPane.getChildren().add(parameters[0]);
        anchorPanes[0] = new MainAnchorPane(mNT);
        for (int i = 0 ; i < nOfParameters ; i++){
            if (i!=0) {
                anchorPanes[i] = new AnchorPane();
                anchorPanes[i].getChildren().add(parameters[i]);
            }
            anchorPanes[i].setPrefSize(200, 30);
            this.getChildren().add(anchorPanes[i]);

            if (parameters[i].getCircleType()==Parameter.CircleType.GET) inputPoints.add(parameters[i].getMyCircle());
            else if (parameters[i].getCircleType()==Parameter.CircleType.SET) outputPoints.add(parameters[i].getMyCircle());
            if (parameters[i].getActive()!=null)actives.add(parameters[i].getActive());
            if (parameters[i].getExtraActive()!=null)actives.add(parameters[i].getExtraActive());
        }
        AnchorPane lastAnchorPane = new AnchorPane();
        lastAnchorPane.setPrefSize(200 , 15);
        this.getChildren().add(lastAnchorPane);
        this.layoutsChanged();
        if (mNT== MyNodeTypes.COMBINE_RGBA) {
            Converter converter = new Converter(parameters[1].getMyCircle(),
                    parameters[2].getMyCircle(),parameters[3].getMyCircle(),parameters[4].getMyCircle(),parameters[5].getMyCircle());
            parameters[1].getMyCircle().setConverter(converter);
            }
        if (mNT== MyNodeTypes.SEPARATE_RGBA) {
            Converter converter = new Converter(parameters[5].getMyCircle(),
                        parameters[1].getMyCircle(),parameters[2].getMyCircle(),parameters[3].getMyCircle(),parameters[4].getMyCircle());
            parameters[1].getMyCircle().setConverter(converter);
            parameters[2].getMyCircle().setConverter(converter);
            parameters[3].getMyCircle().setConverter(converter);
            parameters[4].getMyCircle().setConverter(converter);
        }
        if (mNT.toString().matches("MATH(.*)")){
            Math math = new Math(parameters[3].getMyCircle() , parameters[4].getMyCircle() , mNT);
            parameters[1].getMyCircle().setMath(math);
        }
    }


    public void layoutsChanged(){
        mainAnchorPane.setLayoutX(MyNode.this.getLayoutX());
        mainAnchorPane.setLayoutY(MyNode.this.getLayoutY());
        for (int i = 0 ; i < mNT.nOfParameters ; i++){
            parameters[i].setParameterX(MyNode.this.getLayoutX() + 20);
            parameters[i].setParameterY(MyNode.this.getLayoutY() + i*30);
            if (parameters[i].getCircleType()!= Parameter.CircleType.NONE) {
                parameters[i].layoutsChanged();
                }
        }
    }

    public MainAnchorPane getMainAnchorPane() {
        return mainAnchorPane;
    }

    public ArrayList<MyCircle> getInputPoints()  {return inputPoints; }

    public ArrayList<MyCircle> getOutputPoints() {
        return outputPoints;
    }

    public ArrayList<Node> getActives() {return actives; }

    public class MainAnchorPane extends AnchorPane{
        MyNodeTypes mNT;
        MainAnchorPane(MyNodeTypes mNT) {
            this.mNT = mNT;
            this.setStyle("-fx-background-color: "+mNT.color+"; -fx-background-radius: 20 20 0 0");
        }

        public void hoverHighlight() {
            this.setStyle("-fx-background-color: "+mNT.highlightColor+"; -fx-background-radius: 20 20 0 0");
        }

        public void hoverUnhighlight() {
            this.setStyle("-fx-background-color: "+mNT.color+"; -fx-background-radius: 20 20 0 0");
        }
    }
}
