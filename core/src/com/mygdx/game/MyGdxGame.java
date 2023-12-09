package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game {
	SpriteBatch batch;
	public OrthographicCamera camera;
	public static int CAM_HEIGHT = 9*60, CAM_WIDTH = 21*60;
	public static int HEIGHT, WIDTH;
	public HuhScreen huhScreen;
	public MenuScreen menuScreen;
	public GameScreen gameScreen;

	@Override
	public void create () {
		HEIGHT = Gdx.graphics.getHeight();
		WIDTH = Gdx.graphics.getWidth();
		batch = new SpriteBatch();

		camera = new OrthographicCamera();
//		camera.setToOrtho(false, CAM_WIDTH, CAM_HEIGHT);
		camera.setToOrtho(false, WIDTH, HEIGHT);

		huhScreen = new HuhScreen(this, batch, camera);
		gameScreen = new GameScreen(this, batch, camera);
		menuScreen = new MenuScreen(this, batch, camera);
		setScreen(gameScreen);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
