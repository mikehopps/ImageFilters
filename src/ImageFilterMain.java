import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by michael_hopps on 4/23/18.
 */
public class ImageFilterMain extends JPanel {

    private FilteredImage myImage;

    public ImageFilterMain(int w, int h){
        setSize(w, h);

        myImage = new FilteredImage("testpic.jpg", 0, 0);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;


        Pixel[][] pixels = myImage.getArray();
        BufferedImage test = FilteredImage.getImageFromArray(pixels);
        g2.drawImage(test, 0, 0, null);

    }

    public static void main(String[] args) {
        //The JFrame class represents the window that holds the graphics
        JFrame window = new JFrame("Graphics!");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setBounds(0, 0, 600, 600 + 22); //(x, y, w, h) 22 due to title bar.

        //This puts an object of this JPanel into the JFrame.
        ImageFilterMain panel = new ImageFilterMain(600, 600);

        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);
        window.setVisible(true);
//        window.setResizable(false);
    }

}
