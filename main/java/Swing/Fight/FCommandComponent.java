package Swing.Fight;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;


public abstract class FCommandComponent extends JButton {


    /**
     * Tamaño del boton, tanto horizontal como vertical
     */
    public final Dimension size = new Dimension(30,50);
    /**
     * Grosor del borde del boton
     */
    public int borderTickness = 0;
    /**
     * Color del borde del boton
     */
    public Color borderColor = Color.black;
    protected LineBorder lineBorder = new LineBorder(Color.black,0);

    public abstract void setFCommandComponentSize(int width, int height);
    public abstract Dimension getFCommandComponentSize();
    public abstract void setFCommandComponentColor(Color buttonColor);
    public abstract Color getFCommandComponentColor();
    public abstract void setBorderTickness(int borderTickness);
    public abstract int getBorderTickness();
    public abstract void setBorderColor(Color color);
    public abstract Color getBorderColor();
    public abstract void setTextColor(Color color);
    public abstract Color getTextColor();

    @Override
    public void setText(String text) {
        super.setText(text);
    }

    @Override
    public String getText() {
        return super.getText();
    }

    protected abstract LineBorder getLineBorder();
}
