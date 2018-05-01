import java.awt.*;

/**
 * Created by michael_hopps on 5/1/18.
 */
public class GrayScaleImage extends FilteredImage {

    public GrayScaleImage(String fileName, int x, int y) {
        super(fileName, x, y);
    }

    @Override
    public void draw(Graphics2D g2) {
            if(getImage() != null){

                Pixel[][] pixels = getArray();
                for (int i = 0; i < pixels.length; i++) {
                    for (int j = 0; j < pixels[0].length; j++) {
                        int ave = pixels[i][j].getBlue() + pixels[i][j].getRed() + pixels[i][j].getGreen();
                        ave = ave/3;
                        pixels[i][j].setBlue(255-ave);
                        pixels[i][j].setRed(255-ave);
                        pixels[i][j].setGreen(255-ave);
                    }
                }
                g2.drawImage(getImageFromArray(pixels), getLocation().x, getLocation().y, null);

            }else{
                g2.fillRect(getLocation().x, getLocation().y, 100, 100);
            }

    }
}
