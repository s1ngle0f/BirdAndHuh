package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.util.ListUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Columns {
    class Column{
        public Vector2 position;
        public float spaceBetween;

        public Column(Vector2 position, float spaceBetween) {
            this.position = position;
            this.spaceBetween = spaceBetween;
        }

        @Override
        public String toString() {
            return "Column{" +
                    "position=" + position +
                    ", spaceBetween=" + spaceBetween +
                    '}';
        }
    }
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private float horizontalSpaceBetween;
    private int leftBottomPointCamera, count;
    private float procentOfVerticalDelta, procentMinHole, procentMaxHole;
    private List<Column> columns = new ArrayList<>();
    private Random random;
    private TextureRegion textureRegion;
    public Columns(SpriteBatch batch, OrthographicCamera camera, float horizontalSpaceBetween, int count,
                   float procentOfVerticalDelta, float procentMinHole, float procentMaxHole) throws Exception {
        if (1-procentOfVerticalDelta < procentMaxHole)
            throw new Exception("Неверный размер");

        this.batch = batch;
        this.camera = camera;
        this.horizontalSpaceBetween = horizontalSpaceBetween;
        this.count = count;
        this.procentOfVerticalDelta = procentOfVerticalDelta;
        this.procentMinHole = procentMinHole;
        this.procentMaxHole = procentMaxHole;

        random = new Random();
        textureRegion = new TextureRegion(new Texture("column.png"));
        firstGeneration();
    }

    private void firstGeneration() {
        float heightOfBounds = (1-procentOfVerticalDelta); //В процентах (0.3, к примеру)
        for(int i = 0; i < count; i++){
            columns.add(
                    new Column(
                            new Vector2(
                                    horizontalSpaceBetween * i + MyGdxGame.WIDTH * 3 / 4,
                                    procentOfVerticalDelta/2f*MyGdxGame.HEIGHT + random.nextInt(
                                            (int) (heightOfBounds * MyGdxGame.HEIGHT) + 1
                                    )
                            ),
                            (float) (procentMinHole + Math.random() * (procentMaxHole - procentMinHole))
                    )
            );
        }
    }

    public void render(float delta) {
        leftBottomPointCamera = (int)(camera.position.x) - MyGdxGame.WIDTH/2;
        clearUsefulColumns();

        for(Column column : columns){
            batch.draw(textureRegion,
                    column.position.x,
                    column.position.y - textureRegion.getTexture().getHeight() - column.spaceBetween/2f
            );
        }
    }

    private void clearUsefulColumns() {
        for (Column column : columns) {
            if (column.position.x + textureRegion.getTexture().getWidth() <= leftBottomPointCamera){
                generateNewPositionForColumn(column);
            }
        }
        ListUtil.<Column>shiftListLeft(columns);
        for(Column column : columns){
            System.out.print(column + ", ");
        }
        System.out.println("");
    }

    private void generateNewPositionForColumn(Column column) {
        Vector2 positionOfLastColumn = columns.get(columns.size() - 1).position;
        column.position.x = positionOfLastColumn.x + horizontalSpaceBetween;
        float heightOfBounds = (1-procentOfVerticalDelta); //В процентах (0.15, к примеру)
        column.position.y = procentOfVerticalDelta/2f*MyGdxGame.HEIGHT + random.nextInt(
                (int) (heightOfBounds * MyGdxGame.HEIGHT) + 1
        );
        column.spaceBetween = (float) (procentMinHole + Math.random() * (procentMaxHole - procentMinHole));
    }
}
