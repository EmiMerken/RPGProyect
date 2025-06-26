package Fight;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * "FTextBox"
 *
 * Clase que representa una "caja de dialogo" dentro de una pelea de un juego RPG.
 *
 * <div>
 *     Permite modificar varias cosas de esta misma.
 * </div>
 * <div>
 *     Se debe agregar a <code>FBattle</code>.
 * </div>
 **/
public class FTextBox extends JPanel {

    /**
     * Con "dimension", unicamente impora la altura por como se ha construido <code>FBattle</code>
     */
    private final Dimension dimension = new Dimension(0,10);

    /**
     * Maneja la logica interna del FTextBox
     */
    private final JLabel label = new JLabel("INTRODUCIR TEXTO");

    private int borderTickness = 0;
    private Color borderColor = Color.black;

    public FTextBox(){
        super.setPreferredSize(dimension);
        super.add(label);
    }

    public void setHeight(int height){
        dimension.setSize(0,height);
    }

    public int getHeight() {
        return (int) dimension.getHeight();
    }

    public void setText(String text){
        label.setText(text);
    }

    public String getText(){
        return label.getText();
    }

    public void setTextColor(Color color){
        label.setForeground(color);
    }

    public Color getTextColor(){
        return label.getForeground();
    }

    public void setBackgroundColor(Color color){
        super.setBackground(color);
    }

    public Color getBackgroundColor() {
        return super.getBackground();
    }

    public void setBorderTickness(int borderTickness){
        this.borderTickness = borderTickness;
        this.setBorder(new LineBorder(borderColor,borderTickness));
    }

    public void setBorderColor(Color color){
        this.borderColor = color;
        this.setBorder(new LineBorder(color, borderTickness));
    }
}
