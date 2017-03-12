package org.cook.maven.game;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import org.cook.maven.input.KeyChangedEvent;

import java.util.ArrayList;


public class PlayerSoul extends Soul implements EventHandler<KeyChangedEvent> {

    private Body body;
    private boolean stand;

    private KeyChangedEvent keys;

    public PlayerSoul(Body body) {
        stand = false;
        this.body = body;
        this.keys = new KeyChangedEvent(new ArrayList<KeyCode>(), KeyCode.valueOf("DOWN"));
    }

    public void handle(KeyChangedEvent event) {
        this.keys = event;
    }

    public void getWalkOrder(){
        stand = true;

        for(KeyCode code : keys.getKeyPressed()){
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
            body.stand(keys.getLastKeyReleased().toString());
        }
    }

}
