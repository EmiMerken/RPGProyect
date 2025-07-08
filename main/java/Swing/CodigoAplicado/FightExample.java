package Swing.CodigoAplicado;

import Swing.Fight.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class FightExample {

    public static FBattle battle;
    public static FTextBox textBox;
    public static FCommand command;
    public static FEnemy enemy;

    public static int hp = 100;
    public static int attackDamage = 10;

    public static Container run(){
        //EXTRAS
        MyFrame frame = new MyFrame("RPG",500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Creamos una batalla
        battle = new FBattle();

        //Creamos, modificamos y añadimos la caja de dialogo
        textBox = createTextBox();

        //Creamos al enemigo
        enemy = new FEnemy("Enemigo",10,200);
        enemy.setSprites("C:\\Users\\emiji\\Desktop\\POO_codigos_2025-1-main\\POO_codigos_2025-1-main\\RPGProyect-main\\RPG_Overworld\\core\\src\\main\\resources\\RPGBatalla\\FightTest\\Sprites");
        //enemy.setBrightness(100);
        enemy.setActualSprite(0); //Sprite actual


        //Obtenemos el mando del jugador
        command = new FCommand();
        //Y le añadimos botones
        FButton[] buttons = createButtons();

        FConsole console = new FConsole();
        console.setText("asd");
        console.setTextColor(Color.BLACK);
        console.setFCommandComponentColor(Color.white);

        command.addFConsole(console);

        //Extras
        Timer timer = new Timer(100, null);
        final int[] miniIterador = {0};

        //Creamos una accion para el boton "atacar"
        buttons[0].setActionListener(e -> {
            if (command.state == FCommand.ACTIVATED){
                attack(miniIterador, timer);
            } else if (command.state == FCommand.ONLYCONFIRM){
                takeDamage(miniIterador,timer);
            }
        });
        //Creamos una accion para el boton "curar"
        buttons[1].setActionListener(e ->{
            if (command.state == FCommand.ACTIVATED){
                heal();
            } else if (command.state == FCommand.ONLYCONFIRM){
                takeDamage(miniIterador,timer);
            }
        });

        command.addFButton(buttons[0]); //Añadir al mando el boton "atacar"
        command.addFButton(buttons[1]); //Añadir al mando el boton "defenderse"

        //Finalmente añadimos a todos

        FBackground background = battle.getFBackground();
        try {
            background.setImage("C:\\Users\\emiji\\Desktop\\POO_codigos_2025-1-main\\POO_codigos_2025-1-main\\RPGProyect-main\\RPG_Overworld\\core\\src\\main\\resources\\RPGBatalla\\FightTest\\Walls\\FWallTestImage.png");
            background.setMosaic(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //background.setBrightness(3.0f, 100);
        //background.changeColor(Color.red);
        //background.setAlpha(50);
        background.monoColor(Color.black, 80);
        background.scalate(2.0f,2.0f);
        //background.setBrightness(50.0f,0);

        //background.pos(1,1);

        //enemy.pos(500,0);
        //enemy.rotate(45);
        //enemy.scalate(1.2f,1.2f);



        battle.addFCommand(command); //TODO: Añadir mando en vez de jugador
        battle.addFTextBox(textBox);
        battle.addFEnemy(enemy);

        frame.add(battle);

        //EXTRAS
        frame.pack();
        return frame.getContentPane();
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
        buttons[0].setFCommandComponentColor(Color.red);
        buttons[0].setTextColor(Color.black);

        buttons[1] = new FButton();
        buttons[1].setText("CURARSE");
        buttons[1].setFCommandComponentColor(Color.orange);
        buttons[1].setTextColor(Color.black);
        return buttons;
    }

    public static void attack(final int[] miniIterador, Timer timer){
        command.state = FCommand.DESACTIVATED;
        textBox.setText("HAZ GOLPEADO AL ENEMIGO!!!");
        enemy.setActualSprite(4);
        enemy.setHP(enemy.getHP() - attackDamage);
        timer.addActionListener(e->{
            miniIterador[0]++;
            switch (miniIterador[0]){
                case 4:
                    enemy.setActualSprite(5);
                    break;
                case 8:
                    enemy.setActualSprite(6);
                    break;
                case 16:
                    enemy.setActualSprite(1);
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
        enemy.setActualSprite(2);
        command.state = FCommand.DESACTIVATED;
        timer.addActionListener(e->{
            miniIterador[0]++;
            switch (miniIterador[0]){
                case 8:
                    enemy.setActualSprite(3);
                    hp = hp - enemy.getAttackDamage();
                    textBox.setText("Haz recibido daño!!!");
                    break;
                case 12:
                    enemy.setActualSprite(0);
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
        enemy.setActualSprite(6);
        hp = hp + 5;
    }
}

class MainExample{
    public static void main(String[] args) {
        JFrame frame = new JFrame("batallaEjemplo");
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(FightExample.run());
        frame.setVisible(true);
    }
}
