package org.cook.maven;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args)
    {
        launch(args);
    }

    public void start(Stage stage)
    {
        stage.setTitle("Simulateur de marche en foret 2016");

        Game game = new Game(stage, 600, 600);

        stage.show();
    }
}
