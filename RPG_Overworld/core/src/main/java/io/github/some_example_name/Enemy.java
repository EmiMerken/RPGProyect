package io.github.some_example_name;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Enemy extends Entity{

    public Enemy(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void update(float delta) {
        //Check for collisions
    }
}
