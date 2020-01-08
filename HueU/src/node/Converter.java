package node;

import node.Parameter.MyCircle;

import java.awt.image.BufferedImage;

class Converter {
    private MyCircle imageMyCircle;
    private MyCircle redMyCircle;
    private MyCircle greenMyCircle;
    private MyCircle blueMyCircle;
    private MyCircle alphaMyCircle;
    private BufferedImage image;
    private int[][] redArray;
    private int[][] greenArray;
    private int[][] blueArray;
    private int[][] alphaArray;

    Converter(MyCircle imageMyCircle, MyCircle redMyCircle, MyCircle greenMyCircle, MyCircle blueMyCircle, MyCircle alphaMyCircle) {
        this.imageMyCircle = imageMyCircle;
        this.redMyCircle = redMyCircle;
        this.greenMyCircle = greenMyCircle;
        this.blueMyCircle = blueMyCircle;
        this.alphaMyCircle = alphaMyCircle;
        System.out.println("constructor");
    }

    BufferedImage getImage() {
        redArray = redMyCircle.getValue();
        greenArray = greenMyCircle.getValue();
        blueArray = blueMyCircle.getValue();
        alphaArray = alphaMyCircle.getValue();
        this.convertToImage();
        System.out.println("getImage");
        return image;
    }

    int[][] getRedArray() {
        image = imageMyCircle.getBufferedImage();
        this.convertToColors();
        System.out.println("getRedArray()");
        return redArray;
    }

    int[][] getGreenArray() {
        image = imageMyCircle.getBufferedImage();
        this.convertToColors();
        System.out.println("getGreenArray()");
        return greenArray;
    }

    int[][] getBlueArray() {
        image = imageMyCircle.getBufferedImage();
        this.convertToColors();
        System.out.println("getBlueArray()");
        return blueArray;
    }

    int[][] getAlphaArray() {
        image = imageMyCircle.getBufferedImage();
        this.convertToColors();
        return alphaArray;
    }

    private void convertToColors() {
        int width = image.getWidth();
        int height = image.getHeight();
        redArray = new int[width][height];
        greenArray = new int[width][height];
        blueArray = new int[width][height];
        alphaArray = new int[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Here (x,y)denotes the coordinate of image
                // for modifying the pixel value.
                int p = image.getRGB(x, y);

                alphaArray[x][y] = (p >> 24) & 0xff;
                redArray[x][y] = (p >> 16) & 0xff;
                greenArray[x][y] = (p >> 8) & 0xff;
                blueArray[x][y] = p & 0xff;
            }
        }

    }

    private void convertToImage() {
//        if (alphaArray.length!=redArray.length || alphaArray.length!=greenArray.length || alphaArray.length!=blueArray.length ||
//                alphaArray[0].length!=redArray[0].length || alphaArray[0].length!=greenArray[0].length || alphaArray[0].length!=blueArray[0].length){
//            System.out.println("WARNING!!! Resolution of colors are NOT equal");
//        }
        int width = 0;
        int height = 0;
        for (int[][] i : new int[][][]{alphaArray, redArray, greenArray, blueArray}) {
            if (i.length != 1) {
                width = i.length;
                height = i[0].length;
            }
        }
        // get image's width and height
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // convert to greyscale
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int a, r, g, b;

                a = getValueForPixel(alphaArray , x ,y);
                r = getValueForPixel(redArray , x , y);
                g = getValueForPixel(greenArray , x , y);
                b = getValueForPixel(blueArray , x , y);

                // replace RGB value with avg
                int p = (a << 24) | (r << 16) | (g << 8) | b;

                image.setRGB(x, y, p);
            }
        }

    }

    public int getValueForPixel(int[][] array, int x , int y) {
        if (array.length==1) return array[0][0];
        else return array[x][y];
    }
}
