package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class TmpMapScreen implements Screen {
    private MyGdxGame myGdxGame;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private TmxMapLoader tmxMapLoader;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer renderer;
    private Box2DDebugRenderer b2dr;
    private World world;
    private float unitScale = 1;
    private Joystick joystick;
    Player player;
    public TmpMapScreen(MyGdxGame myGdxGame, SpriteBatch batch, OrthographicCamera camera) {
        this.myGdxGame = myGdxGame;
        this.batch = batch;
        this.camera = camera;

        joystick = new Joystick(
                batch,
                camera,
                new Texture("bgJoystick.png"),
                new Texture("fgStick.png"),
                200,
                50
        );


        tmxMapLoader = new TmxMapLoader();
        tiledMap = tmxMapLoader.load("tilemaps/example.tmx");
        renderer = new OrthogonalTiledMapRenderer(tiledMap, unitScale);

        world = new World(new Vector2(0, -200f), true);

        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        b2dr = new Box2DDebugRenderer();

        for(MapObject object : tiledMap.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.x + rect.getWidth()/2) * unitScale, (rect.y + rect.getHeight()/2) * unitScale);

            body = world.createBody(bodyDef);

            shape.setAsBox(rect.getWidth()/2*unitScale, rect.getHeight()/2*unitScale);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }

        player = new Player(world);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1,1,1,1);
        world.step(1/60f, 6, 2);
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            camera.position.x += -10f;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            camera.position.x += 10f;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            player.body.applyForceToCenter(new Vector2(0, 30000), true);
        }
        camera.update();

        renderer.setView(camera);
        renderer.render();
        b2dr.render(world, camera.combined);
        batch.begin();
        joystick.render(delta);
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
