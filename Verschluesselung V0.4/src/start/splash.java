package start;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class splash extends JFrame {
    JPanel contentPane;
    JLabel imageLabel = new JLabel();

    public splash() {
        try {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            contentPane = (JPanel) getContentPane();
            this.setUndecorated(true);
            setSize(new Dimension(800, 600));
            // add the image label
            ImageIcon ii = new ImageIcon(this.getClass().getResource(
                    "/assets/vodafone.gif"));
            imageLabel.setIcon(ii);
            contentPane.add(imageLabel, java.awt.BorderLayout.CENTER);
            // show it
            this.setLocationRelativeTo(null);
            this.setVisible(true);
            Thread.sleep(5000);
            this.dispose();
            main haupt = new main();
            haupt.main1();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void main() {
        new splash();
    }

}