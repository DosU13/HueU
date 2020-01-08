package node;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Getter_Image extends Button {

    private Parameter.MyCircle superMyCircle;
    private Button imageOpener = new Button("Open");

    Getter_Image() {
        super("Save");
        //Set extension filter
        //Show open file dialog
        EventHandler<ActionEvent> saveEventHandler = event -> {
            BufferedImage bufferedImage = superMyCircle.getBufferedImage();

            FileChooser fileChooser = new FileChooser();

            //Set extension filter
            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilterPNG, extFilterJPG);

            //Show open file dialog
            File file = fileChooser.showSaveDialog(null);

            if (file != null) {
                try {
                    ImageIO.write(bufferedImage, "png", file);
                } catch (IOException ex) {
                    System.out.println("IOException" + ex.toString());
                }
            }
        };
        this.setOnAction(saveEventHandler);

        EventHandler<ActionEvent> openEventHandler = event -> {
            BufferedImage bufferedImage = superMyCircle.getBufferedImage();
            System.out.println(bufferedImage.getWidth());

            try {
                File defaultFile = new File(getClass().getResource("image.PNG").getFile());
                ImageIO.write(bufferedImage, "png", defaultFile);
                Desktop.getDesktop().open(defaultFile);
            } catch (IOException e) {
                System.out.println("IOException" + e.toString());
            }
        };
        imageOpener.setOnAction(openEventHandler);
    }

    Button getImageOpener() {
        return imageOpener;
    }

    void setSuperMyCircle(Parameter.MyCircle superMyCircle) {
        this.superMyCircle = superMyCircle;
    }

}
