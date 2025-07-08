package io.github.some_example_name;

import com.badlogic.gdx.Game;
import screens.Play;

public class MapLoader extends Game {

    @Override
    public void create() {
        setScreen(new Play());
    }
    @Override
    public void render() {
        super.render();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width,height);
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }
    @Override
    public void dispose() {
        super.dispose();
    }
}
