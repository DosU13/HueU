package node;

import node.Parameter.MyCircle;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Math {
    private MyCircle IValueMyCircle;
    private MyCircle IIValueMyCircle;
    private MyNode.MyNodeTypes mNT;

    public Math(MyCircle IValueMyCircle, MyCircle IIValueMyCircle, MyNode.MyNodeTypes mNT) {
        this.IValueMyCircle = IValueMyCircle;
        this.IIValueMyCircle = IIValueMyCircle;
        this.mNT = mNT;
    }

    int[][] getResultArray() {
        int[][] IArray = IValueMyCircle.getValue();
        int[][] IIArray = IIValueMyCircle.getValue();
        int width;
        int height;
        if (IArray.length != 1) {
            width = IArray.length;
            height = IArray[0].length;
        } else {
            width = IIArray.length;
            height = IIArray[0].length;
        }
        int[][] resultArray = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int f, s, r = 0;
                if (IArray.length == 1) f = IArray[0][0];
                else f = IArray[x][y];
                if (IIArray.length == 1) s = IIArray[0][0];
                else s = IIArray[x][y];
                switch (mNT) {
                    case MATHADD:
                        r = f + s;
                        break;
                    case MATHSUBTRACT:
                        r = f - s;
                        break;
                    case MATHMULTIPLY:
                        r = f * s;
                        break;
                    case MATHDIVIDE:
                        r = f / s;
                        break;
                    case MATHDISTANCE:
                        r = java.lang.Math.abs(f - s);
                        break;
                    case MATHMAX:
                        r = max(f, s);
                        break;
                    case MATHMIN:
                        r = min(f, s);
                        break;
                    case MATHEQUALS:
                        if (f == s) r = 1;
                        else r = 0;
                        break;
                    case MATHGREATER:
                        if (f >= s) r = 1;
                        else r = 0;
                        break;
                }
                resultArray[x][y] = r;
            }
        }
        return resultArray;
    }
}
