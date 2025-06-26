package Fight;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa el mando del jugador con sus respectivos botones
 *
 * <div>Permite agregar <code>FButton</code>'s con <code>addButton()</code></div>
 * <div>Permite mover o elegir mediante <code>indicator</code> que boton se accionara</div>
 * <div>Permite añadir una consola visual mediante <code>createConsole()</code></div>
 */

public class FCommand extends JPanel implements KeyListener {
    /**
     * Desactiva todos los controles, es necesario asignarle este valor a <code>state</code>
     */
    public static final int DESACTIVATED = 0;
    /**
     * Activa todos los controles, es necesario asignarle este valor a <code>state</code>
     */
    public static final int ACTIVATED = 1;
    /**
     * Desactiva todos los controles a excepcion de <code>teclaConfirmadora</code>.
     * <div>
     * Es necesario asignarle este valor a <code>state</code>.
     * </div>
     */
    public static final int ONLYCONFIRM = 2;
    /**
     * tecla que activa la accion del boton elegido
     */
    public int confirmKey;
    /**
     * define en que estado esta el mando, ya sea:
     * <div>- <code>ACTIVED</code> (activado)</div>
     * <div>- <code>DESACTIVED</code> (desactivado)</div>
     * <div>- <code>ONLYCONFIRM</code> (solo confirmacion)</div>
     */
    public int state = ACTIVATED;
    public List<FButton> buttons;
    private int buttonsIndex = 0;
    /**
     * Es una pequeña "CAJA" la cual tiene texto dentro.
     * <div>(Especialmente util para representar el HP del jugador).</div>
     */
    private FButton console;
    /**
     * Indica en que boton se esta actualmente
     */
    private LineBorder indicator;
    public Color indicatorColor = Color.yellow;
    public int indicatorTickness = 3;

    public FCommand(){
        this.buttons = new ArrayList<>();
        this.indicator = new LineBorder(indicatorColor, indicatorTickness);
        super.addKeyListener(this);
        soliciteFocus();
    }

    public FCommand(List<FButton> buttons){
        this.buttons = buttons;
        this.indicator = new LineBorder(indicatorColor, indicatorTickness);
        addKeyListener(this);
        soliciteFocus();
    }

    public void addButton(FButton boton){
        buttons.add(boton);
        super.add(boton);
        if (buttons.size() == 1){
            boton.setBorder(indicator);
        }
    }

    /**
     * Crea una consola dentro del mando de manera automatica
     */
    public void createConsole(){
        //TODO : Crear excepction si es que ya se ha creado un HPConsole
        this.console = new FButton();
        console.setBackground(Color.white);
        console.setButtonSize(100,100);
        console.setText("introducir texto");
        super.add(console);
    }

    /**
     * Crea una consola dentro del mando predefinada por el jugador
     */
    public void createConsole(FButton console){
        //TODO : Crear excepction si es que ya se ha creado un HPConsole
        this.console = console;
        super.add(console);
    }

    public void setIndicadorColor(Color color) {
        this.indicator = new LineBorder(color, indicatorTickness);
    }

    public void setIndicadorTickness(int tickness) {
        this.indicator = new LineBorder(indicatorColor,tickness);
    }

    public void setConfirmKey(int keyEventConstant){
        this.confirmKey = keyEventConstant;
    }

    public void setConsoleText(String s){
        console.setText(s);
    }

    public FButton getConsole(){
        return this.console;
    }

    public void setConsole(FButton Console) {
        this.console = Console;
    }

    /**
     * Este metodo es el manejo de teclas presionadas
     * <div>Si se toca flecha izquierda, se activa <code>moveLeft</code></div>
     * <div>Si se toca flecha derecha, se activa <code>moveRight</code></div>
     * <div>Si se toca la tecla confirmadora, se activa <code>confirm</code></div>
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == confirmKey){
            confirm();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && 0< buttonsIndex && state == ACTIVATED){
            moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && buttonsIndex <buttons.size()-1 && state == ACTIVATED) {
            moveRight();
        }
    }

    /**
     * mueve el <code>indicador</code> al boton de la iqzuierda
     */
    private void moveLeft(){
        buttons.get(buttonsIndex).setBorder(buttons.get(buttonsIndex).getLineBorder());
        buttonsIndex--;
        buttons.get(buttonsIndex).setBorder(indicator);
    }

    /**
     * mueve el <code>indicador</code> al boton de la derecha
     */
    private void moveRight(){
        buttons.get(buttonsIndex).setBorder(buttons.get(buttonsIndex).getLineBorder());
        buttonsIndex++;
        buttons.get(buttonsIndex).setBorder(indicator);
    }

    /**
     * activa la accion del boton en el cual esta el <code>indicador</code>
     */
    private void confirm(){
        buttons.get(buttonsIndex).ejecuteAction();
    }

    private void soliciteFocus(){
        this.setFocusable(true);
        this.requestFocus();
    }

    public List<FButton> getButtons() {
        return buttons;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
