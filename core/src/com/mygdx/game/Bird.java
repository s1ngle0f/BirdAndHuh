package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Bird {
    private SpriteBatch batch;
    private OrthographicCamera camera;

    private Vector2 duration, position;
    private float speed = 100, width, height;
    Texture atlas;
    Animation<TextureRegion> animation;
    private Sound huh;
    public Bird(SpriteBatch batch, OrthographicCamera camera, float widthOneFrame, float heightOneFrame) {
        this.batch = batch;
        this.camera = camera;
        this.width = widthOneFrame;
        this.height = heightOneFrame;

        duration = new Vector2(0, -1);
        position = new Vector2(0, 0);

        atlas = new Texture(Gdx.files.internal("bird_animation.png"));
        TextureRegion[][] textures = TextureRegion.split(atlas, (int) width, (int) height);
        Array<TextureRegion> animationFrames = new Array<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                animationFrames.add(textures[i][j]);
            }
        }
        animation = new Animation<TextureRegion>(0.1f, animationFrames, Animation.PlayMode.LOOP);
    }

    public void render(float delta, float time){
        moving();
        calculatePosition(delta);
        TextureRegion currentFrame = animation.getKeyFrame(time);
        batch.draw(currentFrame,
                position.x,
                position.y
        );
    }

    private void moving() {
        if(Gdx.input.justTouched())
            duration.y = 10;
    }

    private void calculatePosition(float delta) {
        position.x = camera.position.x - width/2f;
        position.y += duration.y * speed * delta;
        if(duration.y >= -9.8f)
            duration.add(0, -0.5f);
    }

    public void setHuh(Sound huh) {
        this.huh = huh;
    }
}
