package node;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import main.Draggable;
import node.Parameter.ParameterType;
import MouseGestures.MyNodeMakeDraggable;

public class MyNode extends VBox {
    private final myNodeTypes mNT;
    private AnchorPane[] anchorPanes;

    public enum myNodeTypes {
        INPUT("#8d0000" ,"#f00" ,2, new Parameter[]{
                new Parameter("Image" , ParameterType.NAME),
                new Parameter("Image" , ParameterType.OUT_IMAGE)}),
        OUTPUT( "#8d0000","#f00", 3, new Parameter[]{
                new Parameter("Output" , ParameterType.NAME),
                new Parameter("Image" , ParameterType.IN_IMAGE),
                new Parameter("Save" , ParameterType.BUTTON)}) ,
        SEPARATE_RGBA( "#8d8d00" ,"#ff0" , 6, new Parameter[]{
                new Parameter("Separate RGBA" , ParameterType.NAME),
                new Parameter("Red" , ParameterType.OUT_VALUE) ,
                new Parameter("Green" , ParameterType.OUT_VALUE) ,
                new Parameter("Blue" , ParameterType.OUT_VALUE) ,
                new Parameter("Alpha" , ParameterType.OUT_VALUE) ,
                new Parameter("Image" , ParameterType.IN_VALUE)}) ,
        COMBINE_RGBA("#8d8d00" ,"#ff0" ,6, new Parameter[]{
                new Parameter("Combine RGBA" , ParameterType.NAME),
                new Parameter("Image" , ParameterType.OUT_VALUE) ,
                new Parameter("Red" , ParameterType.IN_VALUE) ,
                new Parameter("Green" , ParameterType.IN_VALUE) ,
                new Parameter("Blue" , ParameterType.IN_VALUE) ,
                new Parameter("Alpha" , ParameterType.IN_VALUE)}) ,
        MATH("#008d8d" ,"#0ff" , 5 , new Parameter[]{
                new Parameter("Math" , ParameterType.NAME),
                new Parameter("Value" , ParameterType.OUT_VALUE) ,
                new Parameter("" , ParameterType.MATH) ,
                new Parameter("Value" , ParameterType.IN_VALUE) ,
                new Parameter("Value" , ParameterType.IN_VALUE)  });


        private final String color;
        private final String highlightColor;
        private final int nOfParameters;
        private final Parameter[] parameters;
        myNodeTypes(String  color ,String highlightColor ,  int nOfParameters, Parameter[] parameters){
            this.nOfParameters = nOfParameters;
            this.parameters = parameters;
            this.color = color;
            this.highlightColor = highlightColor;
        }
    }

    public MyNode(myNodeTypes mNT) {
        this.mNT = mNT;
        int nOfParameters = mNT.nOfParameters;
        Parameter[] parameters = mNT.parameters;
        anchorPanes = new AnchorPane[nOfParameters];

        this.setStyle("-fx-background-color: silver ; -fx-background-radius: 20 20 20 20");
        this.setPrefSize(200, (1 + nOfParameters) * 30 );

        MainAnchorPane mainAnchorPane = new MainAnchorPane(){
            @Override
            public MyNode getSuper(){
                return MyNode.this;
            }
        };
//        MyNodeMakeDraggable myNodeMakeDraggable = new MyNodeMakeDraggable();
//        myNodeMakeDraggable.makeDraggable(this , mainAnchorPane);

        Draggable.Nature nature = new Draggable.Nature(mainAnchorPane , this);

        anchorPanes[0] = mainAnchorPane;


        for (int i = 0 ; i < nOfParameters ; i++){
            if (i!=0) anchorPanes[i] = new AnchorPane();
            anchorPanes[i].setPrefSize(200, 300);
            this.getChildren().add(anchorPanes[i]);
            anchorPanes[i].getChildren().add(parameters[i]);
        }

    }

    public class MainAnchorPane extends AnchorPane{
        public MainAnchorPane() {
            this.setStyle("-fx-background-color: "+mNT.color+"; -fx-background-radius: 20 20 0 0");
        }

        public void hoverHighlight() {
            this.setStyle("-fx-background-color: "+mNT.highlightColor+"; -fx-background-radius: 20 20 0 0");
        }

        public void hoverUnhighlight() {
            this.setStyle("-fx-background-color: "+mNT.color+"; -fx-background-radius: 20 20 0 0");
        }

        public MyNode getSuper(){
            return null;
        }
    }

}
