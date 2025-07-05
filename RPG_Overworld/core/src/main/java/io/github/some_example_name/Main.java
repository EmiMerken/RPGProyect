package io.github.some_example_name;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.sun.org.apache.xpath.internal.operations.Or;

public class Main implements ApplicationListener {

    SpriteBatch batch;
    FitViewport viewport;
    Stage stage;
    TiledMap tiledMap;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;
    Player player;
    @Override
    public void create() {
        player = new Player(new Sprite(new Texture("player.png")));
        viewport = new FitViewport(8,8);
        batch = new SpriteBatch();
        stage = new Stage(viewport,batch);
        Gdx.input.setInputProcessor(stage);
        tiledMap = new TmxMapLoader().load("grassPatch.tmx");
        renderer = new OrthogonalTiledMapRenderer(tiledMap);

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getWidth();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,512,512);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);
    }

    @Override
    public void render() {
        input();
        logic();
        draw();
    }
    private void input(){
        float delta = Gdx.graphics.getDeltaTime();

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            player.translateX(player.getSpeed() * delta);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            player.translateX(-player.getSpeed() * delta);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            player.translateY(player.getSpeed() * delta);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            player.translateY(-player.getSpeed() * delta);
        }
    }
    private void logic(){

    }
    private void draw(){
        float delta = Gdx.graphics.getDeltaTime();

        ScreenUtils.clear(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);

        stage.act(delta);
        stage.draw();

        renderer.setView(camera);
        renderer.render();

        batch.begin();
        player.draw(batch);
        batch.end();
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
