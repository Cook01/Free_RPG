package org.cook.maven.game;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import org.cook.maven.input.KeyChangedEvent;


public class PlayerSoul extends Soul implements EventHandler<KeyChangedEvent> {

    private Body body;
    private boolean stand;

    public PlayerSoul(Body body) {
        stand = false;
        this.body = body;
    }

    public void handle(KeyChangedEvent event) {
        stand = true;

        for(KeyCode code : event.getKeyPressed()){
            if(code.toString().equals("LEFT")){
                body.walkLeft();
                stand = false;
            }
            if(code.toString().equals("RIGHT")){
                body.walkRight();
                stand = false;
            }
            if(code.toString().equals("UP")){
                body.walkUp();
                stand = false;
            }
            if(code.toString().equals("DOWN")){
                body.walkDown();
                stand = false;
            }
        }

        if(stand){
            body.stand(event.getLastKeyReleased().toString());
        }
    }

}
