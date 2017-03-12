package org.cook.maven;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import org.cook.maven.game.Body;
import org.cook.maven.graphics.Camera;
import org.cook.maven.graphics.GameLoop;
import org.cook.maven.input.KeyChangedEvent;
import org.cook.maven.input.KeyHandler;
import org.cook.maven.tilemap.TileMap;
import org.cook.maven.tilemap.TileMapJSONLoader;


public class Game {
    private Stage gameStage;
    private Group root;
    private Scene scene;
    private Canvas canvas;
    private GraphicsContext gc;

    private int windowWidth;
    private int windowHeight;

    private GameLoop mainLoop;

    private TileMap map;

    private Camera camera;

    private Body player;


    public Game(Stage stage, int windowWidth, int windowHeight){
        this.gameStage = stage;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        this.initGraphicalEnvironment();

        this.map = TileMapJSONLoader.loadFromJSONFile("/maps/Map_Test_04_-_100x100.json");
        this.camera = new Camera(0, 0, windowWidth, windowHeight);

        this.player = new Body("perso_test", true, 0, 0);
        map.spawn(player);

        camera.followBody(player);

        KeyHandler keyHandler = new KeyHandler();
        keyHandler.setOnKeyChanged((EventHandler<KeyChangedEvent>) player.getSoul());
        scene.setOnKeyPressed(keyHandler);
        scene.setOnKeyReleased(keyHandler);

        mainLoop = new GameLoop(this);
        mainLoop.start();
    }

    public void initGraphicalEnvironment() {
        this.root = new Group();
        this.scene = new Scene(root);

        gameStage.setScene(scene);

        this.canvas = new Canvas(windowWidth, windowHeight);
        root.getChildren().add(canvas);

        this.gc = canvas.getGraphicsContext2D();
    }

    public void update() {
        camera.update(0,0,map.getNbTileWidth()*map.getTileWidth(), map.getNbTileHeight()*map.getTileHeight());
        player.update();
    }

    public void render(double t) {
        map.draw(gc, camera, t);
    }
}
