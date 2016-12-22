package org.cook.maven.input;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public class KeyChangedEvent extends Event {
    private ArrayList<KeyCode> keyPressed;
    private KeyCode lastKeyReleased;

    public static final EventType<KeyChangedEvent> KEY_CHANGED =
            new EventType<KeyChangedEvent>(Event.ANY, "KEY_CHANGED");

    public KeyChangedEvent(ArrayList<KeyCode> keyPressed, KeyCode lastKeyReleased) {
        super(KEY_CHANGED);
        this.keyPressed = keyPressed;
        this.lastKeyReleased = lastKeyReleased;
    }

    public ArrayList<KeyCode> getKeyPressed() {
        return keyPressed;
    }

    public void setKeyPressed(ArrayList<KeyCode> keyPressed) {
        this.keyPressed = keyPressed;
    }

    public KeyCode getLastKeyReleased() {
        return lastKeyReleased;
    }

    public void setLastKeyReleased(KeyCode lastKeyReleased) {
        this.lastKeyReleased = lastKeyReleased;
    }
}
