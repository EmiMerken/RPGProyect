package Swing.Fight;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * "FBattle"
 * Clase que representa una batalla contra un enemigo dentro de un juego RPG
 * <div>permite añadir automaticamente los siguientes elementos:</div>
 * <div>- <code>FTextBox</code> (Caja de texto)</div>
 * <div>- <code>FPlayer</code> (Los botones que este mismo tenga asignados)</div>
 * <div>- <code>FEnemy</code> (Enemigo en pantalla)</div>
 * <div>Ademas de poder modificar el <code>FBackground</code> (fondo) a gusto</div>
 **/
public class FBattle extends JPanel {

    /**
     * JPanel ubicado en el centro
     */
    private final JPanel centerPanel;
    /**
     * JPanel ubicado por abajo
     */
    private final JPanel bottomPanel;
    /**
     * Clase que extiende <code>JPanel</code>, está misma se ubica por detras de todos los <code>JPanel</code>'s.
     */
    private final FBackground backgroundPanel;
    private HashMap<FCommand, Integer> commands;

    public FBattle(){
        super(new BorderLayout());
        centerPanel = new JPanel(new BorderLayout()); //Enemigo al centro
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel,BoxLayout.Y_AXIS));
        backgroundPanel = new FBackground();
        opaqueManagement();
        internalPanelManagement();
    }

    public void addFTextBox(FTextBox textBox){
        bottomPanel.add(textBox);
    }

    public void addFEnemy(FEnemy enemy){
        centerPanel.add(enemy);
    }

    public void addFCommand(FCommand command){
        bottomPanel.add(command);
    }

    public void removeFCommand(FCommand command){
        bottomPanel.remove(command);
    }

    /**
     * @return Clase <code>FBackground</code> para manejarla de manera mas comoda
     */
    public FBackground getFBackground() {
        return backgroundPanel;
    }

    /**
     * Metodo que maneja los JPanel dentro de la clase
     *  <div><code>centerPanel</code> panel ubicado en el centro, aqui iria la clase <code>FEnemy</code></div>
     *  <code>bottomPanel</code> panel ubicado por abajo, aqui iria las clases <code>FTextBox</code> y <code>FCommand</code>
     *  <code>backgroundPanel</code> panel ubicado detras de todos, aqui iria la clase <code>FBackground</code>
     */
    private void internalPanelManagement(){
        super.add(backgroundPanel);
        backgroundPanel.add(centerPanel, BorderLayout.CENTER);
        backgroundPanel.add(bottomPanel,BorderLayout.SOUTH);
    }


    /**
     * configura las opacidades de cada panel
     */
    private void opaqueManagement(){
        backgroundPanel.setOpaque(true);
        centerPanel.setOpaque(false);
        bottomPanel.setOpaque(false);
    }
}
