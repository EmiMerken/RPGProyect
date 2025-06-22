package Fight;

import javax.swing.*;

/**
 * Clase abstracta que sirve para crear personajes de peleas
 *
 */
public abstract class FCharacter extends JLabel {

    protected String name;
    protected int attackDamage;
    protected int HP;

    public FCharacter(String name, int attackDamage, int HP) {
        this.name = name;
        this.attackDamage = attackDamage;
        this.HP = HP;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setHP(int hp) {
        this.HP = hp;
    }
    public int getHP() {
        return HP;
    }
    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }
    public int getAttackDamage() {
        return attackDamage;
    }
}
