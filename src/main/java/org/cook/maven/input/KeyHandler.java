package org.cook.maven.input;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class KeyHandler implements EventHandler<KeyEvent> {
    private ArrayList<KeyCode> keyPressed;
    private KeyCode lastDirectionReleased;

    private ArrayList<EventHandler<KeyChangedEvent>> handlers;

    public KeyHandler(){
        keyPressed = new ArrayList<KeyCode>();
        handlers = new ArrayList<EventHandler<KeyChangedEvent>>();
    }

    public void handle(KeyEvent event) {
        if(event.getEventType().getName().equals("KEY_PRESSED") && !keyPressed.contains(event.getCode())){
            keyPressed.add(event.getCode());
        } else if(event.getEventType().getName().equals("KEY_RELEASED")){
            keyPressed.remove(event.getCode());

            if(event.getCode().toString().equals("LEFT") || event.getCode().toString().equals("RIGHT") || event.getCode().toString().equals("UP") || event.getCode().toString().equals("DOWN")){
                lastDirectionReleased = event.getCode();
            }
        }

        KeyChangedEvent keyChangedEvent = new KeyChangedEvent(keyPressed, lastDirectionReleased);

        fire(keyChangedEvent);

        System.err.println(keyPressed);
    }

    private void fire(KeyChangedEvent keyChangedEvent) {
        for(EventHandler<KeyChangedEvent> handler : handlers){
            handler.handle(keyChangedEvent);
        }
    }

    public void setOnKeyChanged(EventHandler<KeyChangedEvent> handler){
        handlers.add(handler);
    }
}
