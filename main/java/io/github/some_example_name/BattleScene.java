package io.github.some_example_name;
import Swing.CodigoAplicado.FightExample;
import Swing.Fight.FButton;
import Swing.CodigoAplicado.FightExample.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

import javax.swing.*;

public class BattleScene {
    public BattleScene(){
        JFrame frame = new JFrame("batallaEjemplo");
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(FightExample.run());
        frame.setVisible(true);
    }
}
