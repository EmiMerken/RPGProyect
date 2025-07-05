package Fight;

import javax.swing.*;

/**
 * "FEnemy"
 *
 * Representa a un enemigo con sus respectivos <code>sprites</code> dentro de un juego RPG
 */
public class FEnemy extends FCharacter {


    private ImageIcon[] sprites = new ImageIcon[0];

    public FEnemy(String name, int attackDamage, int hp){
        super(name,attackDamage,hp);
        setHorizontalAlignment(JLabel.CENTER);
        textCenter();
    }


    public FEnemy(String name, int attackDamage, int hp, ImageIcon[] sprites) {
        super(name,attackDamage,hp);
        this.sprites = sprites;
        setState(0);
        setHorizontalAlignment(JLabel.CENTER);
        textCenter();
    }

    public void setSprites(ImageIcon[] sprites) {
        this.sprites = sprites;
    }

    /**
     * se asigna un sprite al enemigo
     * @param state indice del arreglo de sprites
     */
    public void setState(int state){
        //TODO : EXCEPTION EN CASO DE QUE NO HAY ARREGLO
        setIcon(sprites[state]);
    }

    /**
     * Permite centrar el texto y que quede por encima del sprite del enemigo
     */
    private void textCenter(){
        setText(name + " HP: " + HP);
        setHorizontalTextPosition(JLabel.CENTER);
        setVerticalTextPosition(JLabel.TOP);
    }

    @Override
    public void setHP(int hp) {
        super.setHP(hp);
        super.setText(name + " HP: " + hp);
    }


}
