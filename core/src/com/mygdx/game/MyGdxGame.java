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
	public TmpMapScreen tmpMapScreen;

	@Override
	public void create () {
		HEIGHT = Gdx.graphics.getHeight();
		WIDTH = Gdx.graphics.getWidth();
		batch = new SpriteBatch();

		camera = new OrthographicCamera();
//		camera.setToOrtho(false, CAM_WIDTH, CAM_HEIGHT);
		camera.setToOrtho(false, WIDTH, HEIGHT);

		huhScreen = new HuhScreen(this, batch, camera);
		try {
			gameScreen = new GameScreen(this, batch, camera);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		menuScreen = new MenuScreen(this, batch, camera);
		tmpMapScreen = new TmpMapScreen(this, batch, camera);
		setScreen(tmpMapScreen);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
