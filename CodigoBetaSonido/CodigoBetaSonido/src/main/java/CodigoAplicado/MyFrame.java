package CodigoAplicado;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    public MyFrame (String name,int width, int height){
        this.setPreferredSize(new Dimension(width,height));
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(name);
    }
}
