package Fight;

import javax.swing.*;
import java.awt.*;

/**
 * "FBackground"
 *
 *  Sirve mayormente para modificar de mejor manera las imagenes dentro de un <div>JPanel</div>
 */
public class FBackground extends JPanel {

    private Image image;

    public FBackground(LayoutManager layout){
        super(layout);
        setOpaque(true);
    }

    public void setImage(Image image){
        this.image = image;
    }

    protected Image getImage() {
        return image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
