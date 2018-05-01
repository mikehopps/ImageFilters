import java.awt.*;

/**
 * Created by michael_hopps on 5/1/18.
 */
public class GrayScaleImage extends FilteredImage {

    public GrayScaleImage(String fileName, int x, int y) {
        super(fileName, x, y);
        makeGrayScale();
    }
    public GrayScaleImage(Pixel[][] pixels, int x, int y) {
        super(pixels, x, y);
        makeGrayScale();
    }

    public void makeGrayScale(){
        Pixel[][] pixels = getPixels();
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                int ave = pixels[i][j].getBlue() + pixels[i][j].getRed() + pixels[i][j].getGreen();
                ave = ave/3;
                pixels[i][j].setBlue(ave);
                pixels[i][j].setRed(ave);
                pixels[i][j].setGreen(ave);
            }
        }
        setImage(pixels);
    }

}
