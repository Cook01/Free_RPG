package org.cook.maven.graphics;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import org.cook.maven.Game;

public class GameLoop extends AnimationTimer {

    private Game game;
    private double startNanoTime;
    private GraphicsContext gc;

    public GameLoop(Game game){

        this.game = game;
    }

    public void handle(long now) {
        double t = (now - startNanoTime) / 1000000000.0;

        game.update();
        game.render(t);
    }
}
