package org.cook.maven.tilemap;

import org.json.simple.JSONObject;

import java.util.Map;

public class Object {
    private int id; // Incremental id - unique across all objects

    private int width; // Width in pixels. Ignored if using a gid.
    private int height; // Height in pixels. Ignored if using a gid.

    private String name; // String assigned to name field in editor
    private String type; // String assigned to type field in editor

    private Map properties; // string key-value pairs.

    private boolean visible; //Whether object is shown in editor.

    private int x; // x coordinate in pixels
    private int y; // y coordinate in pixels

    private float rotation; // Angle in degrees clockwise

    private int gid; // GID, only if object comes from a Tilemap


    public Object(JSONObject jsonObject) {
        this.id = (int)(long)((Long) jsonObject.get("id"));

        this.width = (int)(long)((Long) jsonObject.get("width"));
        this.height = (int)(long)((Long) jsonObject.get("height"));

        this.name = (String) jsonObject.get("name");
        this.type = (String) jsonObject.get("type");

        this.visible = (Boolean) jsonObject.get("visible");

        this.x = (int)(long)(Long) jsonObject.get("x");
        this.y = (int)(long)(Long) jsonObject.get("y");

        this.rotation = ((Long) jsonObject.get("rotation")).floatValue();

        this.gid = (int)(long)(Long) jsonObject.get("gid");

        // TODO : Implementer les Properties
    }
}
