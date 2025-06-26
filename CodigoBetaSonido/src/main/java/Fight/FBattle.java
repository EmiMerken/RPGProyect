package Fight;

import javax.swing.*;
import java.awt.*;

/**
 * "FBattle"
 *
 * Clase que representa una batalla contra un enemigo dentro de un juego RPG
 *
 * <div>permite añadir automaticamente los siguientes elementos:</div>
 * <div>- <code>FTextBox</code> (Caja de texto)</div>
 * <div>- <code>FPlayer</code> (Los botones que este mismo tenga asignados)</div>
 * <div>- <code>FEnemy</code> (Enemigo en pantalla)</div>
 *
 * <div>Ademas de poder modificar el <code>FBackground</code> (fondo) a gusto</div>
 **/
public class FBattle extends JPanel {

    /**
     * JPanel ubicado en el centro
     */
    private final JPanel centerPanel = new JPanel(new BorderLayout());
    /**
     * JPanel ubicado por abajo
     */
    private final JPanel bottomPanel = new JPanel();
    /**
     * Clase que extiende <code>JPanel</code>, está misma se ubica por detras de todos los <code>JPanel</code>'s.
     */
    private final FBackground backgroundPanel = new FBackground(new BorderLayout());

    public FBattle(){
        super(new BorderLayout());
        internalPanelManagement(centerPanel,bottomPanel,backgroundPanel);
    }

    public void add(FTextBox textBox){
        bottomPanel.add(textBox);
    }

    public void add(FEnemy enemy){
        centerPanel.add(enemy);
    }

    public void add(FPlayer player){
        player.getCommand().setOpaque(false);
        bottomPanel.add(player.getCommand());
    }


    public void setBackgroundColor(Color color){
        backgroundPanel.setBackground(color);
    }

    public Color getBackgroundColor(){
        return backgroundPanel.getBackground();
    }

    public void setBackgroundImage(Image image){
        backgroundPanel.setImage(image);
    }

    public Image getBackgroundImage(){
        return backgroundPanel.getImage();
    }

    /**
     * Metodo que maneja los JPanel dentro de la clase
     *
     * @param centerPanel panel ubicado en el centro, aqui iria la clase <code>FEnemy</code>
     * @param bottomPanel panel ubicado por abajo, aqui iria las clases <code>FTextBox</code> y <code>FCommand</code>
     * @param backgroundPanel panel ubicado detras de todos, aqui iria la clase <code>FBackground</code>
     */
    private void internalPanelManagement(JPanel centerPanel, JPanel bottomPanel, FBackground backgroundPanel){
        backgroundPanel.setOpaque(true);
        centerPanel.setOpaque(false);
        bottomPanel.setOpaque(false);
        super.add(backgroundPanel);
        bottomPanel.setLayout(new BoxLayout(bottomPanel,BoxLayout.Y_AXIS)); //TODO : MOVER
        backgroundPanel.add(centerPanel, BorderLayout.CENTER);
        backgroundPanel.add(bottomPanel,BorderLayout.SOUTH);
    }
}
