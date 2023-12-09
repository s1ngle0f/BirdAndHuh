package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector4;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class HuhScreen implements Screen {

    private final SpriteBatch batch;
    private Texture img;
    private Vector4 color;
    private float timeout = 0.3f, counter = 0;
    private Vector2 direction = new Vector2(1, 1);
    private Vector2 position = new Vector2(0,0);
    private float speed = 100, size = 1;
    private Sound huh;
    private OrthographicCamera camera;
    private MyGdxGame myGdxGame;

    public HuhScreen(MyGdxGame myGdxGame, SpriteBatch batch, OrthographicCamera camera) {
        this.myGdxGame = myGdxGame;
        this.batch = batch;
        this.camera = camera;
        img = new Texture("badlogic.jpg");
        huh = Gdx.audio.newSound(
                Gdx.files.internal("huh.mp3")
        );
        color = new Vector4(1, 1, 1, 1);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(color.x, color.y, color.z, 1);

        if(position.x <= 0) {
            direction.x = 1;
            touchBorderEvent();
        }
        else if (position.x >= Gdx.graphics.getWidth() - img.getWidth()*size) {
            direction.x = -1;
            touchBorderEvent();
            this.myGdxGame.setScreen(myGdxGame.menuScreen);
        }

        if(position.y <= 0) {
            direction.y = 1;
            touchBorderEvent();
        }
        else if (position.y >= Gdx.graphics.getHeight() - img.getHeight()*size) {
            direction.y = -1;
            touchBorderEvent();
        }

        position.add(
                direction.x * speed * delta,
                direction.y * speed * delta
        );
        camera.update();
        batch.begin();
        batch.draw(img, position.x, position.y, img.getWidth()*size, img.getHeight()*size);
        batch.setProjectionMatrix(
                camera.combined
        );
        batch.end();
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
        img.dispose();
        huh.dispose();
    }

    public void touchBorderEvent(){
        updateColor();
        huh.play(0.01f);
        size -= 0.01f;
        speed += 20;
    }

    public void updateColor(){
        Random random = new Random();
        color = new Vector4(
                random.nextFloat(),
                random.nextFloat(),
                random.nextFloat(),
                1
        );
    }
}
