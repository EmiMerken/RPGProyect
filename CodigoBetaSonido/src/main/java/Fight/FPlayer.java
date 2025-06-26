package Fight;

/**
 * Representa al jugador con su respectivo <code>FCommand</code> (mando) dentro del juego RPG.
 */
public class FPlayer extends FCharacter {
    private FCommand command;

    public FPlayer(String name, int attackDamage, int hp) {
        super(name, attackDamage, hp);
        command = new FCommand();
    }

    public FPlayer(String name, int attackDamage, int hp, FCommand command) {
        super(name, attackDamage, hp);
        this.command = command;
    }

    public void setCommand(FCommand command) {
        this.command = command;
    }

    public FCommand getCommand() {
        return command;
    }

    @Override
    public void setHP(int hp) {
        super.setHP(hp);
    }

    @Override
    public int getHP() {
        return super.getHP();
    }
}
