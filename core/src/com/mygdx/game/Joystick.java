package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Joystick {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Texture bgCircle, fgTexture;
    private float bgCircleSize, fgTextureSize;
    private boolean isStatic = false;
    private Vector2 position = new Vector2(), activePosition = new Vector2();
    private Vector2 centerPosition = new Vector2(), activeCenterPosition = new Vector2();
    private Vector2 leftBottomPointOfCamera = new Vector2(), result = new Vector2();

    public Joystick(SpriteBatch batch, OrthographicCamera camera, Texture bgCircle, Texture fgTexture, float bgCircleSize, float fgTextureSize) {
        this.batch = batch;
        this.camera = camera;
        this.bgCircle = bgCircle;
        this.fgTexture = fgTexture;
        this.bgCircleSize = bgCircleSize;
        this.fgTextureSize = fgTextureSize;
    }

    public void render(float delta){
        calculatePosition();
        System.out.println(activeCenterPosition);
        if(isStatic || Gdx.input.isTouched()){
            batch.draw( bgCircle,
                    position.x,
                    position.y,
                    bgCircleSize,
                    bgCircleSize
            );
            batch.draw( fgTexture,
                    activePosition.x,
                    activePosition.y,
                    fgTextureSize,
                    fgTextureSize
            );
        }
    }

    private void calculatePosition() {
        leftBottomPointOfCamera.set(
                camera.position.x - camera.viewportWidth/2f,
                camera.position.y - camera.viewportHeight/2f
        );
        if(isStatic)
            position.set(
                    leftBottomPointOfCamera.x + bgCircleSize * 0.2f,
                    leftBottomPointOfCamera.y + bgCircleSize * 0.2f
            );
        else if (Gdx.input.justTouched()) {
            position.set(
                    leftBottomPointOfCamera.x + (Gdx.input.getX()) - bgCircleSize/2f,
                    leftBottomPointOfCamera.y + (camera.viewportHeight - Gdx.input.getY()) - bgCircleSize/2f
            );
            centerPosition.set(
                    (Gdx.input.getX()),
                    (camera.viewportHeight - Gdx.input.getY())
            );
        }
        if(Gdx.input.isTouched()){
            activePosition.set(
                    leftBottomPointOfCamera.x + (Gdx.input.getX()) - fgTextureSize/2f,
                    leftBottomPointOfCamera.y + (camera.viewportHeight - Gdx.input.getY()) - fgTextureSize/2f
            );
            activeCenterPosition.set(
                    (Gdx.input.getX()),
                    (camera.viewportHeight - Gdx.input.getY())
            );
        }
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }
}
