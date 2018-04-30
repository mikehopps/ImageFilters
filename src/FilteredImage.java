import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;

/**
 * Created by michael_hopps on 4/30/18.
 */
public class FilteredImage {

    private BufferedImage image;
    private Point location;

    public FilteredImage(String fileName, int x, int y) {
        try{
            image = ImageIO.read(new File("res/" + fileName));
        }catch(Exception e){
            e.printStackTrace();
        }
        location = new Point(x, y);

    }


    public void draw(Graphics2D g2){
        if(image != null){
            g2.drawImage(image, location.x, location.y, null);
        }else{
            g2.fillRect(location.x, location.y, 100, 100);
        }
    }

    public void drawGrayScale(Graphics2D g2){
        if(image != null){



            g2.drawImage(image, location.x, location.y, null);
        }else{
            g2.fillRect(location.x, location.y, 100, 100);
        }
    }


    /*
    Returns an integer array, where each integer represents the color of one pixel.
    result[0][0] is the color of the top left pixel.
     */
    public Pixel[][] getArray(){

        if(image != null) {
            final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
            final int width = image.getWidth();
            final int height = image.getHeight();
            final boolean hasAlphaChannel = image.getAlphaRaster() != null;

            int[][] result = new int[height][width];
            int row = 0;
            int col = 0;
            if (hasAlphaChannel) { //2 cases, image has ARGB or just RGB.
                final int pixelLength = 4;
                for (int pixel = 0; pixel < pixels.length; pixel += pixelLength) {
                    int argb = 0;
                    argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
                    argb += ((int) pixels[pixel + 1] & 0xff); // blue
                    argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
                    argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
                    result[row][col] = argb;
                    col++;
                    if (col == width) {
                        col = 0;
                        row++;
                    }
                }
            } else {
                final int pixelLength = 3;
                for (int pixel = 0; pixel < pixels.length; pixel += pixelLength) {
                    int argb = 0;
                    argb += -16777216; // 255 alpha  2^24 power, 4th byte to 255
                    argb += ((int) pixels[pixel] & 0xff); // blue
                    argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
                    argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
                    result[row][col] = argb;
                    col++;
                    if (col == width) {
                        col = 0;
                        row++;
                    }
                }
            }
            Pixel[][] answer = new Pixel[height][width];
            for (int r = 0; r < height; r++) {
                for (int c = 0; c < width; c++) {
                    answer[r][c] = new Pixel(result[r][c]);
                }
            }
            return answer;
        }
        return null;
    }

    public static BufferedImage getImageFromArray(Pixel[][] array){
        BufferedImage newImage = new BufferedImage(array[0].length, array.length, BufferedImage.TYPE_4BYTE_ABGR);
        for (int r = 0; r < array.length; r++) {
            for (int c = 0; c < array[0].length; c++) {
                newImage.setRGB(c, r, array[r][c].getArgb());
            }
        }
        return newImage;

    }

}
