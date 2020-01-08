package node;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Setter_Image extends Button {

    private Label imageName;
    private BufferedImage bufferedImage = null;
    private Parameter.MyCircle superMyCircle;

    Setter_Image() {
        super("Choose");
        //Set extension filter
        //Show open file dialog
        EventHandler<ActionEvent> onAction = event -> {
            FileChooser fileChooser = new FileChooser();

            //Set extension filter
            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

            //Show open file dialog
            File file = fileChooser.showOpenDialog(null);

            try {
                bufferedImage = ImageIO.read(file);
                imageName.setText(file.getName());
                superMyCircle.setBufferedImage(bufferedImage);
            } catch (IOException ignored) {
            }
        };
        this.setOnAction(onAction);

        imageName = new Label();
    }

    Label getImageName() {
        return imageName;
    }

    void setSuperMyCircle(Parameter.MyCircle superMyCircle) {
        this.superMyCircle = superMyCircle;
    }
}
