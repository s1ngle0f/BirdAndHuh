package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector4;

public class Joystick {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Texture bgCircle, fgTexture;
    private float bgCircleSize, fgTextureSize;
    private boolean isStatic = false;
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
        editResult();
        System.out.println(result);
        if(isStatic || Gdx.input.isTouched()){
            batch.draw( bgCircle,
                    leftBottomPointOfCamera.x + centerPosition.x - bgCircleSize/2f,
                    leftBottomPointOfCamera.y + centerPosition.y - bgCircleSize/2f,
                    bgCircleSize,
                    bgCircleSize
            );
            batch.draw( fgTexture,
                    leftBottomPointOfCamera.x + activeCenterPosition.x - fgTextureSize/2f,
                    leftBottomPointOfCamera.y + activeCenterPosition.y - fgTextureSize/2f,
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
            centerPosition.set(
                    leftBottomPointOfCamera.x + bgCircleSize * 0.2f,
                    leftBottomPointOfCamera.y + bgCircleSize * 0.2f
            );
        else if (Gdx.input.justTouched()) {
            centerPosition.set(
                    (Gdx.input.getX()),
                    (camera.viewportHeight - Gdx.input.getY())
            );
        }
        if(Gdx.input.isTouched()){
            activeCenterPosition.set(
                    (Gdx.input.getX()),
                    (camera.viewportHeight - Gdx.input.getY())
            );
        }
        activeCenterPosition = limitVector(centerPosition, activeCenterPosition, bgCircleSize/2f);
    }

    public void editResult(){
        Vector2 tmp = new Vector2(activeCenterPosition.x, activeCenterPosition.y);
        tmp = tmp.sub(centerPosition);
        result.set(tmp.nor());
    }

    public Vector2 getResult(){
        return result;
    }

    public Vector2 limitVector(Vector2 origin, Vector2 target, float limit){
        Vector2 relativeVector = new Vector2(target.x, target.y);
        relativeVector.sub(origin);
        if(relativeVector.len() > limit){
            relativeVector.nor();
            relativeVector.set(
                    relativeVector.x * limit,
                    relativeVector.y * limit
            );
        }
        target = new Vector2(
                origin.x + relativeVector.x,
                origin.y + relativeVector.y
        );
        return target;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }
}
