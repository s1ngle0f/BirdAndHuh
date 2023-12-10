package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.TextArea;
import java.util.HashMap;

public class GameScreen implements Screen {
    private final MyGdxGame myGdxGame;
    private final SpriteBatch batch;
    private final OrthographicCamera camera;

    private final Texture sky, rock1, rock2, clouds1, clouds2, clouds3, clouds4;
    private final HashMap<String, BackgroundCircle> parallaxBg = new HashMap<>();
    private Bird bird;
    private Columns columns;
    private float time = 0;

    public GameScreen(MyGdxGame myGdxGame, SpriteBatch batch, OrthographicCamera camera) throws Exception {
        this.myGdxGame = myGdxGame;
        this.batch = batch;
        this.camera = camera;
        sky = new Texture("background/sky.png");
        rock1 = new Texture("background/rocks_1.png");
        rock2 = new Texture("background/rocks_2.png");
        clouds1 = new Texture("background/clouds_1.png");
        clouds2 = new Texture("background/clouds_2.png");
        clouds3 = new Texture("background/clouds_3.png");
        clouds4 = new Texture("background/clouds_4.png");
        parallaxBg.put("skyBg", new BackgroundCircle(sky, batch, camera, 0));
        parallaxBg.put("rock1Bg", new BackgroundCircle(rock1, batch, camera, 0));
        parallaxBg.put("rock2Bg", new BackgroundCircle(rock2, batch, camera, -0.03f));
        parallaxBg.put("clouds1Bg", new BackgroundCircle(clouds1, batch, camera, 0.1f));
        parallaxBg.put("clouds2Bg", new BackgroundCircle(clouds2, batch, camera, -0.13f));
        parallaxBg.put("clouds3Bg", new BackgroundCircle(clouds3, batch, camera, -0.15f));
        parallaxBg.put("clouds4Bg", new BackgroundCircle(clouds4, batch, camera, -0.17f));
        bird = new Bird(batch, camera, 136, 226);
        columns = new Columns(batch, camera, 300,
                6, 0.6f, 0.1f, 0.35f);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        time += delta;
        ScreenUtils.clear(1,1,1,1);
        move();
        camera.update();
        batch.begin();
        renderBackground(delta);
        bird.render(delta, time);
        generateColumn(delta);
        batch.setProjectionMatrix(camera.combined);
        batch.end();
    }

    public void move(){
        camera.position.add(3, 0, 0);
    }

    public void renderBackground(float delta){
        for (BackgroundCircle bgCircle : parallaxBg.values()) {
            bgCircle.render(delta);
        }
    }

    public void generateColumn(float delta){
        columns.render(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        for (BackgroundCircle bgCircle : parallaxBg.values()) {
            bgCircle.getBackground().dispose();
        }
    }
}
