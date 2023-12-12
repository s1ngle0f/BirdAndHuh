package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Button {
    private Texture texture;
    private SpriteBatch batch;
    private int width, height;
    private Vector2 position;

    public Button(Texture texture, SpriteBatch batch, int width, int height, Vector2 position) {
        this.texture = texture;
        this.batch = batch;
        this.width = width;
        this.height = height;
        this.position = position;
    }

    public void draw(){
        batch.draw(texture,
                MyGdxGame.leftBottomPointCamera.x + position.x,
                MyGdxGame.leftBottomPointCamera.y + position.y,
                width,
                height
        );
    }

    public boolean isHit(){
        if(Gdx.input.justTouched()){
            Vector2 touchPosition = new Vector2(
                    Gdx.input.getX(),
                    MyGdxGame.HEIGHT - Gdx.input.getY()
            );
            if(
                    touchPosition.x >= position.x && touchPosition.x <= position.x + width &&
                    touchPosition.y >= position.y && touchPosition.y <= position.y + height
            ){
                return true;
            }
        }
        return false;
    }
}
