package org.cook.maven.tilemap;

import javafx.scene.canvas.GraphicsContext;
import org.cook.maven.game.Body;
import org.cook.maven.graphics.Camera;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class TileMap {
    private int nbTileWidth; // Number of tile columns
    private int nbTileHeight; // Number of tile rows

    private int tileWidth; //Map grid width.
    private int tileHeight; //Map grid height.

    private String orientation; // Orthogonal, isometric, or staggered

    private ArrayList<Layer> layers;  // Array of Layers
    private ArrayList<TileSet> tileSets; // Array of Tilesets

    private String backgroundColor; // Hex-formatted color (#RRGGBB) (Optional)
    private String renderOrder; // Rendering direction (orthogonal maps only)

    private Map properties; // string key-value pairs.

    private int nextObjectId; // Auto-increments for each placed object

    private ArrayList<Body> bodys;

    public TileMap(JSONObject jsonMap){
        this.nbTileWidth = (int)(long)((Long) jsonMap.get("width"));
        this.nbTileHeight = (int)(long)((Long) jsonMap.get("height"));

        this.tileWidth = (int)(long)((Long) jsonMap.get("tilewidth"));
        this.tileHeight = (int)(long)((Long) jsonMap.get("tileheight"));

        this.orientation = (String) jsonMap.get("orientation");

        this.layers = new ArrayList<Layer>();
        JSONArray jsonLayers = (JSONArray) jsonMap.get("layers");
        for(java.lang.Object layer : jsonLayers){

            JSONObject jsonLayer = (JSONObject)layer;
            this.layers.add(new Layer(jsonLayer));
        }

        this.tileSets = new ArrayList<TileSet>();
        JSONArray jsonTileSets = (JSONArray) jsonMap.get("tilesets");
        for(java.lang.Object tileSet : jsonTileSets){

            JSONObject jsonTileSet = (JSONObject)tileSet;
            this.tileSets.add(new TileSet(jsonTileSet));
        }

        if(jsonMap.containsKey("backgroundcolor")){
            this.backgroundColor = (String) jsonMap.get("backgroundcolor");
        } else {
            this.backgroundColor = "Null";
        }

        if(this.orientation.equals("orthogonal")){
            this.renderOrder = (String) jsonMap.get("renderorder");
        } else {
            this.renderOrder = "Null";
        }

        this.nextObjectId = (int)(long)((Long) jsonMap.get("nextobjectid"));

        /*this.properties = new HashMap();
        JSONArray jsonProperties = (JSONArray) jsonMap.get("properties");
        for(Object propertie : jsonProperties){
            JSONObject jsonPropertie = (JSONObject)propertie;

            ArrayList<String> key = new ArrayList<String>();
            key = (ArrayList<String>)jsonPropertie.keySet();
            String value = (String) jsonPropertie.get(key.get(0));

            this.properties.put(key.get(0), value);
        }
        * TODO : Implementer les Properties
        */

        bodys = new ArrayList<Body>();
    }

    public TileMap() {

    }

    public int getNbTileWidth() {
        return nbTileWidth;
    }

    public void setNbTileWidth(int nbTileWidth) {
        this.nbTileWidth = nbTileWidth;
    }

    public int getNbTileHeight() {
        return nbTileHeight;
    }

    public void setNbTileHeight(int nbTileHeight) {
        this.nbTileHeight = nbTileHeight;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }

    public void draw(GraphicsContext gc, Camera camera, double time) {
        int tileMinX =  (((int)camera.getMinX())/tileWidth);
        if(tileMinX > 0){
            tileMinX --;
        }
        int tileMinY = (((int)camera.getMinY())/tileHeight);
        if(tileMinY > 0){
            tileMinY --;
        }

        int tileMaxX =  (((int)camera.getMaxX())/tileWidth);
        if(tileMaxX < nbTileWidth-1){
            tileMaxX ++;
        } else if(tileMaxX == nbTileWidth){
            tileMaxX --;
        }
        int tileMaxY = (((int)camera.getMaxY())/tileHeight);
        if(tileMaxY < nbTileHeight-1){
            tileMaxY ++;
        } else if(tileMaxY == nbTileHeight){
            tileMaxY --;
        }

        int tileMin = (nbTileWidth*tileMinY) + tileMinX;
        int tileMax = (nbTileWidth*tileMaxY) + tileMaxX;

        ArrayList<Integer> indexTileToRender = new ArrayList<Integer>();

        for(int i = tileMin; i <= tileMax; i++){
            int xi = i%nbTileWidth;
            int yi = i/nbTileWidth;

            if(xi >= tileMinX && xi <= tileMaxX && yi >= tileMinY && yi <= tileMaxY){
                indexTileToRender.add(i);
            }
        }

        for(Layer layer : layers){

            if(layer.getName().startsWith("0")){
                for(Body body : bodys){
                    body.getGBody().drawGraphicBody(gc, time, camera);
                }
            } else {
                layer.draw(gc, camera, indexTileToRender, tileSets, tileWidth, tileHeight);
            }
        }
    }

    public void spawn(Body bodyToSpawn){
        bodys.add(bodyToSpawn);
    }
}
