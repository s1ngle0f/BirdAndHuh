package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Hud {
    public Stage stage;
    private Viewport viewport;
    private SpriteBatch batch;

    private Label someText;

    public Hud(SpriteBatch batch) {
        this.batch = batch;

        this.viewport = new FitViewport(MyGdxGame.WIDTH, MyGdxGame.HEIGHT, new OrthographicCamera(MyGdxGame.WIDTH, MyGdxGame.HEIGHT));
        this.stage = new Stage(viewport, batch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        someText = new Label("Kawasaki", new Label.LabelStyle(new BitmapFont(), Color.CYAN));

        table.add(someText).expandX();
        stage.addActor(table);
    }
}
