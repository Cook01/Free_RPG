package org.cook.maven.tilemap;

import javafx.scene.canvas.GraphicsContext;
import org.cook.maven.graphics.Camera;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class Layer {
    private int nbTileWidth; // Column count. Same as tilemap width in Tiled Qt.
    private int nbTileHeight; // Row count. Same as tilemap height in Tiled Qt.

    private String name; // Name assigned to this layer
    private String type; // "tilelayer", "objectgroup", or "imagelayer"
    private boolean visible; // Whether layer is shown or hidden in editor

    private float opacity; // Value between 0 and 1

    private String drawOrder; // "topdown" (default) or "index". type = "objectgroup" only.

    private int x; // Horizontal layer offset. Always 0 in Tiled Qt.
    private int y; // Vertical layer offset. Always 0 in Tiled Qt.

    private ArrayList<Integer> data; // Array of GIDs. type = "tilelayer" only.
    private ArrayList<Object> objects; // Array of Objects.  type = "objectgroup" only.

    private Map properties; // string key-value pairs.

    public Layer(JSONObject jsonLayer) {
        this.nbTileWidth = (int)(long)((Long) jsonLayer.get("width"));
        this.nbTileHeight = (int)(long)((Long) jsonLayer.get("height"));

        this.name = (String) jsonLayer.get("name");
        this.type = (String) jsonLayer.get("type");
        this.visible = (Boolean) jsonLayer.get("visible");

        this.opacity = ((Long) jsonLayer.get("opacity")).floatValue();

        this.x = (int)(long)((Long) jsonLayer.get("x"));
        this.y = (int)(long)((Long) jsonLayer.get("y"));

        this.data = new ArrayList<Integer>();
        this.objects = new ArrayList<Object>();

        if(this.type.equals("tilelayer")){
            JSONArray jsonData = (JSONArray) jsonLayer.get("data");

            for(java.lang.Object objGid : jsonData){
                Integer gid = (int)(long)((Long)objGid);
                this.data.add(gid);
            }

            this.drawOrder = "Null";
            this.objects.clear();
        } else if(this.type.equals("objectgroup")){
            JSONArray jsonObjects = (JSONArray) jsonLayer.get("objects");

            for(java.lang.Object obj : jsonObjects){
                JSONObject jsonObject = (JSONObject)obj;
                this.objects.add(new Object(jsonObject));
            }

            this.data.clear();
        } else {
            this.drawOrder = "Null";
            this.data.clear();
            this.objects.clear();
        }
        // TODO : Implementer les Properties
    }

    public void draw(GraphicsContext gc, Camera camera, ArrayList<Integer> indexTileToRender, ArrayList<TileSet> tileSets, int tileWidth, int tileHeight) {
        for(Integer index : indexTileToRender){
            int gid = data.get(index);

            if(gid != 0){
                TileSet tileSetToUse = new TileSet();

                for(TileSet tileSet : tileSets){
                    if(gid >= tileSet.getFirstGid() && gid <= tileSet.getFirstGid() + tileSet.getTileCount()){
                        tileSetToUse = tileSet;
                    }
                }

                int tileX = index%nbTileWidth;
                int x = tileX*tileWidth;
                x = x - camera.getMinX();

                int tileY = index/nbTileWidth;
                int y = tileY*tileHeight;
                y = y - camera.getMinY();

                tileSetToUse.drawTile(gc, gid, x, y);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
