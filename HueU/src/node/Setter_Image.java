package node;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

class Setter_Image extends Button {

    Label imageName;
    BufferedImage bufferedImage = null;
    Parameter.MyCircle superMyCircle;

    Setter_Image() {
        super("Choose");
        this.setOnAction(onAction);

        imageName = new Label();
    }

    public Label getImageName() {
        return imageName;
    }

    public void setSuperMyCircle(Parameter.MyCircle superMyCircle) {
        this.superMyCircle = superMyCircle;
    }

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
        } catch (IOException ex) {
            System.out.println(ex);
        }
    };
}
