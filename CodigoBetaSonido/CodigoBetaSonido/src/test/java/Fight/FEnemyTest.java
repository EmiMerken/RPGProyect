package Fight;

import org.junit.jupiter.api.*;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class FEnemyTest {
    private FEnemy enemy;
    private static final String ENEMY_NAME = "Enemigo1";
    private static final int ATTACK_DAMAGE = 50;
    private static final int INITIAL_HP = 100;


    @BeforeEach
    void setUp() {
        enemy = new FEnemy(ENEMY_NAME,ATTACK_DAMAGE,INITIAL_HP);
    }
    @Test
    void testConstructorSinSprites() {
        assertEquals(ENEMY_NAME, enemy.getName());
        assertEquals(ATTACK_DAMAGE, enemy.getAttackDamage());
        assertEquals(INITIAL_HP, enemy.getHP());
        assertEquals(ENEMY_NAME + " HP: " + INITIAL_HP, enemy.getText());
    }

    @Test
    void testConstructorConSprites() {
        ImageIcon[] sprites = new ImageIcon[]{
                new ImageIcon(),
                new ImageIcon()
        };

        FEnemy enemyWithSprites = new FEnemy(ENEMY_NAME, ATTACK_DAMAGE, INITIAL_HP, sprites);

        assertEquals(ENEMY_NAME, enemyWithSprites.getName());
        assertEquals(ATTACK_DAMAGE, enemyWithSprites.getAttackDamage());
        assertEquals(INITIAL_HP, enemyWithSprites.getHP());
        assertNotNull(enemyWithSprites.getIcon());
    }

    @Test
    void testSetHP() {
        int nuevoHP = 75;
        enemy.setHP(nuevoHP);

        assertEquals(nuevoHP, enemy.getHP());
        assertEquals(ENEMY_NAME + " HP: " + nuevoHP, enemy.getText());
    }

    @Test
    void testSetStateExepcion() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            enemy.setState(0);
        });
    }

    @Test
    void testSetStateEstablecerSprite() {
        ImageIcon[] sprites = new ImageIcon[]{
                new ImageIcon(),
                new ImageIcon()
        };

        enemy.setSprites(sprites);
        enemy.setState(0);

        assertNotNull(enemy.getIcon());
        assertEquals(sprites[0], enemy.getIcon());
    }

    @Test
    void testSetSpritesActualizar() {
        ImageIcon[] sprites = new ImageIcon[]{
                new ImageIcon(),
                new ImageIcon()
        };

        enemy.setSprites(sprites);
        enemy.setState(0);

        assertNotNull(enemy.getIcon());
    }

}