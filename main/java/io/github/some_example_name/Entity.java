package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity extends Sprite {

    public Entity(Sprite sprite) {
        super(sprite);
    }

    public void draw(SpriteBatch spriteBatch){
        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);
    }
    public abstract void update(float delta);
}
