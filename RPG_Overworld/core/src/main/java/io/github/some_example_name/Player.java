package io.github.some_example_name;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity{
    private Vector2 velocity = new Vector2();
    private float speed = 1.25f;

    public Player(Sprite sprite){
        super(sprite);
    }

    @Override
    public void update(float delta) {
        /*
        if(velocity.y > speed){
        velocity.y = speed;
    } else if (velocity.y < speed){
        velocity.y = -speed;
    }
        setX(getX() + velocity.x * delta);
        setY(getY() + velocity.y * delta);
        }
         */
    }

    public float getSpeed() {
        return speed;
    }
}
