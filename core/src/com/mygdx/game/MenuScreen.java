package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector4;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class MenuScreen implements Screen {

    private MyGdxGame myGdxGame;
    private final SpriteBatch batch;
    private OrthographicCamera camera;
    Texture atlas;
    Animation<TextureRegion> animation;
    float time = 0;
    public MenuScreen(MyGdxGame myGdxGame, SpriteBatch batch, OrthographicCamera camera) {
        this.myGdxGame = myGdxGame;
        this.batch = batch;
        this.camera = camera;

        atlas = new Texture(Gdx.files.internal("bird_animation.png"));
        TextureRegion[][] textures = TextureRegion.split(atlas, 136, 226);
        Array<TextureRegion> animationFrames = new Array<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                animationFrames.add(textures[i][j]);
            }
        }
        animation = new Animation<TextureRegion>(0.1f, animationFrames, Animation.PlayMode.LOOP);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        time += delta;
        ScreenUtils.clear(1, 1, 1, 1);
//        if(Gdx.input.justTouched())
//            myGdxGame.setScreen(myGdxGame.gameScreen);
        camera.update();
        TextureRegion currentFrame = animation.getKeyFrame(time);
        batch.begin();
        batch.draw(currentFrame, 0, 0);
        batch.setProjectionMatrix(camera.combined);
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

    }
}
