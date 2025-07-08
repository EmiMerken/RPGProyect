package Swing.Fight;

import javax.swing.border.LineBorder;
import java.awt.*;

public class FConsole extends FCommandComponent {

    public FConsole(){
        super.setEnabled(true);
        super.setBorder(lineBorder);
        setBackground(Color.red);
        setOpaque(true);
    }

    @Override
    public void setFCommandComponentSize(int width, int height) {
        size.setSize(width,height);
    }

    @Override
    public Dimension getFCommandComponentSize() {
        return this.size;
    }

    @Override
    public void setFCommandComponentColor(Color buttonColor) {
        super.setBackground(buttonColor);
    }

    @Override
    public Color getFCommandComponentColor() {
        return getBackground();
    }

    @Override
    public void setBorderTickness(int borderTickness) {
        this.borderTickness = borderTickness;
        updateBorder();
    }

    @Override
    public int getBorderTickness() {
        return borderTickness;
    }

    @Override
    public void setBorderColor(Color color) {
        this.borderColor = color;
        updateBorder();
    }

    @Override
    public Color getBorderColor() {
        return borderColor;
    }

    @Override
    public void setTextColor(Color color) {
        super.setForeground(color);
    }

    @Override
    public Color getTextColor() {
        return super.getForeground();
    }

    @Override
    protected LineBorder getLineBorder() {
        return this.lineBorder;
    }

    private void updateBorder(){
        this.lineBorder = new LineBorder(borderColor, borderTickness);
        super.setBorder(this.lineBorder);
    }
}
