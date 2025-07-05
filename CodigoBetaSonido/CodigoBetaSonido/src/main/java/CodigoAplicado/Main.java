package CodigoAplicado;

import Fight.*;
import Sonido.Sonido;
import Sonido.ExcepcionSonido;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;


public class Main {

    public static FBattle battle;
    public static FPlayer player;
    public static FTextBox textBox;
    public static FCommand command;
    public static FEnemy enemy;
    public static Sonido sonido = new Sonido();
    public static Sonido musicaFondo = new Sonido();
    public static Sonido sonidoGameOver = new Sonido();

    public static void main(String[] args) {
        //EXTRAS
        MyFrame frame = new MyFrame("RPG",500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Creamos una batalla
        battle = new FBattle();

        //Ponemos un fondo
        Image backgroundImage = null;
        try {
            backgroundImage = ImageIO.read(new File("images/background.png"));
            reproducirMusica(2);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        //Creamos, modificamos y añadimos la caja de dialogo
        textBox = createTextBox();

        //Creamos al enemigo
        enemy = createEnemy();
        enemy.setState(0); //Sprite actual

        //Creamos al jugador
        player = new FPlayer("jugador",10,100);

        //Obtenemos el mando del jugador
        command = player.getCommand();
        //Y le añadimos botones
        FButton[] buttons = createButtons();

        //Creamos una consola
        command.createConsole();
        //La consola la usaremos para representar el HP de nuestro jugador
        command.setConsoleText("HP: " + player.getHP());
        //Con que tecla se confirmara algun boton
        command.setConfirmKey(KeyEvent.VK_ENTER);

        //Extras
        Timer timer = new Timer(100, null);
        final int[] miniIterador = {0};

        //Creamos una accion para el boton "atacar"
        buttons[0].setActionListener(e -> {
            reproducirSonido(6);
            if (command.state == FCommand.ACTIVATED){
                attack(miniIterador, timer);


            } else if (command.state == FCommand.ONLYCONFIRM){
                takeDamage(miniIterador,timer);
            }
        });
        //Creamos una accion para el boton "curar"
        buttons[1].setActionListener(e ->{
            reproducirSonido(6);
            if (command.state == FCommand.ACTIVATED){
                heal();
            } else if (command.state == FCommand.ONLYCONFIRM){
                takeDamage(miniIterador,timer);
            }
        });

        command.addButton(buttons[0]); //Añadir al mando el boton "atacar"
        command.addButton(buttons[1]); //Añadir al mando el boton "defenderse"

        //Finalmente añadimos a todos
        battle.setBackgroundImage(backgroundImage);
        battle.add(player); //Añadimos al jugador a la batalla
        battle.add(textBox);
        battle.add(enemy);

        frame.add(battle);

        //EXTRAS
        frame.pack();
        frame.setVisible(true);
    }

    public static FEnemy createEnemy(){
        FEnemy enemy = new FEnemy("Enemigo",10,200);

        //Asignamos sus sprites
        ImageIcon[] sprites = new ImageIcon[7];
        sprites[0] = new ImageIcon("images/normal.png");
        sprites[1] = new ImageIcon("images/shield.png");
        sprites[2] = new ImageIcon("images/attack1.png");
        sprites[3] = new ImageIcon("images/attack2.png");
        sprites[4] = new ImageIcon("images/takeDamage1.png");
        sprites[5] = new ImageIcon("images/takeDamage2.png");
        sprites[6] = new ImageIcon("images/takeDamage3.png");

        enemy.setSprites(sprites);

        return enemy;
    }

    public static FTextBox createTextBox(){
        FTextBox textBox = new FTextBox();
        //Asignamos texto
        textBox.setText("Enemigo encontrado");
        //Decoramos la caja de dialogo
        textBox.setHeight(100);
        textBox.setBackgroundColor(Color.gray);
        textBox.setBorderTickness(5);
        textBox.setBorderColor(Color.black);
        return textBox;
    }

    public static FButton[] createButtons(){
        FButton[] buttons = new FButton[2];
        buttons[0] = new FButton();
        buttons[0].setText("ATACAR");
        buttons[0].setButtonColor(Color.red);
        buttons[0].setTextColor(Color.black);

        buttons[1] = new FButton();
        buttons[1].setText("CURARSE");
        buttons[1].setButtonColor(Color.orange);
        buttons[1].setTextColor(Color.black);
        return buttons;
    }

    public static void attack(final int[] miniIterador, Timer timer){
        command.state = FCommand.DESACTIVATED;
        textBox.setText("HAZ GOLPEADO AL ENEMIGO!!!");
        reproducirSonido(5);
        enemy.setState(4);
        enemy.setHP(enemy.getHP() - player.getAttackDamage());
        checkGameState();
        timer.addActionListener(e->{
            miniIterador[0]++;
            switch (miniIterador[0]){
                case 4:
                    enemy.setState(5);
                    break;
                case 8:
                    enemy.setState(6);
                    break;
                case 16:
                    enemy.setState(1);
                    textBox.setText("El enemigo se ha cubierto! [TOCA ENTER]");
                    command.state = FCommand.ONLYCONFIRM;
                    miniIterador[0] = 0;
                    timer.removeActionListener(timer.getActionListeners()[0]);
                    timer.stop();
                    break;
            }
        });
        timer.start();
    }

    public static void takeDamage(final int[] miniIterador, Timer timer){
        textBox.setText("turno del enemigo!!!");
        enemy.setState(2);
        command.state = FCommand.DESACTIVATED;
        timer.addActionListener(e->{
            miniIterador[0]++;
            switch (miniIterador[0]){
                case 8:
                    enemy.setState(3);
                    player.setHP(player.getHP() - enemy.getAttackDamage());
                    command.setConsoleText("HP: " + player.getHP());
                    checkGameState();
                    textBox.setText("Haz recibido daño!!!");
                    reproducirSonido(1);
                    break;
                case 12:
                    enemy.setState(0);
                    command.state = FCommand.ACTIVATED;
                    miniIterador[0] = 0;
                    timer.removeActionListener(timer.getActionListeners()[0]);
                    timer.stop();
                    break;
            }
        });
        timer.start();
    }
    public static void heal(){
        command.state = FCommand.ONLYCONFIRM;
        textBox.setText("TE HAZ CURADO!! [TOCA ENTER]");
        reproducirSonido(3);
        enemy.setState(6);
        player.setHP(player.getHP() + 5);
        command.setConsoleText("HP: " + player.getHP());
    }

    public static void checkGameState(){
        if (player.getHP() <= 0 ){
            gameOver(false);
        }else if (enemy.getHP() <= 0){
            gameOver(true);
        }
    }

    public static void gameOver(boolean victoria){
        command.state = FCommand.DESACTIVATED;
        musicaFondo.stop();

        if (victoria){
            textBox.setText("Has ganado!!!");
            reproducirSonido(8);
        }else{
            textBox.setText("GAME OVER");
            sonidoGameOver.cargarSonido(4);
            sonidoGameOver.play();
            sonidoGameOver.loop();
        }
        for (FButton button : command.getButtons()){
            button.setEnabled(false);
        }
        FButton reinicioButton = new FButton();
        reinicioButton.setText("Reiniciar");
        reinicioButton.setButtonColor(Color.black);
        reinicioButton.setTextColor(Color.white);
        reinicioButton.setActionListener(e -> {
            reiniciarJuego();
            command.remove(reinicioButton);
        });
        command.addButton(reinicioButton);
    }
    public static void reiniciarJuego() {
        sonidoGameOver.stop();
        player.setHP(100);
        enemy.setHP(200);
        enemy.setState(0);
        command.state = FCommand.ACTIVATED;

        command.setConsoleText("HP: " + player.getHP());
        textBox.setText("Enemigo encontrado");

        for (FButton button : command.getButtons()) {
            button.setEnabled(true);
        }

        reproducirMusica(2);
    }

    public static void reproducirMusica (int i) throws ExcepcionSonido {
        musicaFondo.cargarSonido(i);
        musicaFondo.play();
        musicaFondo.loop();
    }

    public static void reproducirSonido (int i) throws ExcepcionSonido {
        sonido.cargarSonido(i);
        sonido.play();
    }
}