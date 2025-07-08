package Swing.Fight;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa el mando del jugador con sus respectivos botones
 *
 * <div>Permite agregar <code>FButton</code>'s con <code>addButton()</code></div>
 * <div>Permite mover o elegir mediante <code>indicator</code> que boton se accionara</div>
 * <div>Permite añadir una consola visual mediante <code>createConsole()</code></div>
 */

public class FCommand extends JPanel {
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
     * define en que estado esta el mando, ya sea:
     * <div>- <code>ACTIVED</code> (activado)</div>
     * <div>- <code>DESACTIVED</code> (desactivado)</div>
     * <div>- <code>ONLYCONFIRM</code> (solo confirmacion)</div>
     */
    public int state = ACTIVATED;
    public List<FButton> buttons;
    public List<FConsole> consoles;


    public FCommand(){
        setOpaque(false);
        this.buttons = new ArrayList<>();
        this.consoles = new ArrayList<>();
        soliciteFocus();
    }

    public void addFButton(FButton boton){
        buttons.add(boton);
        super.add(boton);
    }

    public void addFConsole(FConsole console){
        consoles.add(console);
        super.add(console);
    }

    public List<FButton> getButtons() {
        return buttons;
    }

    public List<FConsole> getConsoles() {
        return consoles;
    }

    private void soliciteFocus(){
        this.setFocusable(true);
        this.requestFocus();
    }
}
