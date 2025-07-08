package Swing.Fight;

import Swing.Fight.Exceptions.NullFButtonActionException;

import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Esta clase sirve para crear los botones que se agregan a la clase
 * <code><a href="Action.html">FBattle</a></code>
 * donde estos se acomodan de manera automatica
 */
public class FButton extends FCommandComponent {

    /**
     * Accion del boton:
     */
    public ActionListener action;

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
        if (actionListener != null){
            action.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"a"));
        } else {
            throw new NullFButtonActionException("El boton: " + this.getClass().getName() + " no tiene una accion determinada");
        }
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
    public Color getTextColor(){
        return super.getForeground();
    }

    protected LineBorder getLineBorder(){
        return this.lineBorder;
    }

    private void updateBorder(){
        this.lineBorder = new LineBorder(borderColor, borderTickness);
        super.setBorder(this.lineBorder);
    }
}
