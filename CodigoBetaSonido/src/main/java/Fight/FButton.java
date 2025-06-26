package Fight;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Esta clase sirve para crear los botones que se agregan a la clase
 * <code><a href="Action.html">FBattle</a></code>
 * donde estos se acomodan de manera automatica
 */
public class FButton extends JButton {

    /**
     * Accion del boton:
     */
    public ActionListener action;
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

    public FButton(){
        super.setEnabled(true);
        super.setBorder(lineBorder);
        setBackground(Color.red);
        setOpaque(true);
    }

    /**
     * @param actionListener la nueva accion
     * <div></div>
     * <div>
     *     EJEMPLO:
     * </div>
     * <div>
     *
     *                       <code>
     *
     *     boton.setActionListener(a -> {
     *     System.out.println("¡¡Se ha presionado el boton!!");
     *     });
     *                       </code>
     * </div>
     */
    public void setActionListener(ActionListener actionListener) {
        if (actionListener != null){
            super.removeActionListener(this.action); //Se elimina el actionListener ya creado
        }
        this.action = actionListener;
        super.addActionListener(actionListener);
    }
    /**
     * ejecuta la accion del <code>actionListener</code>
     */
    protected void ejecuteAction(){
        //TODO : Crear un exception personalizado si es que no hay un listener ya creado
        action.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"a"));
    }

        public void setButtonSize(int width, int height) {
        size.setSize(width,height);
    }

    public Dimension getButtonSize() {
        return this.size;
    }

    public void setButtonColor(Color buttonColor) {
        super.setBackground(buttonColor);
    }

    public Color getButtonColor()
    {
        return getBackground();
    }

    public void setBorderTickness(int borderTickness){
        super.setBorder(new LineBorder(this.borderColor,borderTickness));
    }

    public int getBorderTickness() {
        return borderTickness;
    }

    public void setBorderColor(Color color){
        super.setBorder(new LineBorder(color, this.borderTickness));
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setTextColor(Color color){
        super.setForeground(color);
    }

    public Color getTextColor(){
        return super.getForeground();
    }

    @Override
    public void setText(String text) {
        super.setText(text);
    }

    @Override
    public String getText() {
        return super.getText();
    }

    protected LineBorder getLineBorder(){
        return this.lineBorder;
    }
}
