package org.cook.maven.tilemap;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.json.simple.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TileSet {
    private int firstGid; // GID corresponding to the first tile in the set

    private Image image; // Image used for tiles in this set

    private String name; // Name given to this tileset

    private int tileWidth; // Maximum width of tiles in this set
    private int tileHeight; // Maximum height of tiles in this set

    private int imageWidth; // Width of source image in pixels
    private int imageHeight; // Height of source image in pixels

    private Map properties; // String key-value pairs
    //tileProperties -- Per-tile properties, indexed by gid as string

    private int margin; // Buffer between image edge and first tile (pixels)
    private int spacing; // Spacing between adjacent tiles in image (pixels)

    private int tileCount; // Total number of tiles in this set
    private int columns; // Total number of columns in this set

    private String transparentcolor;

    private ArrayList<Terrain> terrains; // Array of Terrains (optional)
    private ArrayList<Tile> tiles; // Gid-indexed Tiles (optional)


    public TileSet(JSONObject jsonTileSet) {
        this.firstGid = (int)(long)(Long) jsonTileSet.get("firstgid");


        String imgPath = (String)jsonTileSet.get("image");
        List<String> imgPathCut = Arrays.asList(imgPath.split("/"));

        InputStream fileStream = TileMapJSONLoader.class.getResourceAsStream("/img/tileset/" + imgPathCut.get(imgPathCut.size()-1));
        this.image = new Image(fileStream);

        this.name = (String) jsonTileSet.get("name");

        this.tileWidth = (int)(long)(Long) jsonTileSet.get("tilewidth");
        this.tileHeight = (int)(long)(Long) jsonTileSet.get("tileheight");

        this.imageWidth = (int)(long)(Long) jsonTileSet.get("imagewidth");
        this.imageHeight = (int)(long)(Long) jsonTileSet.get("imageheight");

        this.margin = (int)(long)(Long) jsonTileSet.get("margin");
        this.spacing = (int)(long)(Long) jsonTileSet.get("spacing");

        this.tileCount = (int)(long)(Long) jsonTileSet.get("tilecount");
        this.columns = (int)(long)(Long) jsonTileSet.get("columns");

        if(jsonTileSet.containsKey("transparentcolor")){
            this.transparentcolor = (String)jsonTileSet.get("transparentcolor");
        } else {
            this.transparentcolor = "Null";
        }

        /*this.terrains = new ArrayList<Terrain>();
        if(jsonTileSet.containsKey("terrains")){
            JSONArray jsonTerrainsArray = (JSONArray) jsonTileSet.get("terrains");
            for(java.lang.Object terrain : jsonTerrainsArray){
                JSONObject jsonTerrain = (JSONObject)terrain;
                terrains.add(new Terrain(jsonTerrain));
            }
        }

        this.tiles = new ArrayList<Tile>();
        if(jsonTileSet.containsKey("tiles")){
            JSONArray jsonTiles = (JSONArray) jsonTileSet.get("tiles");
            for(Object tile : jsonTiles){
                JSONObject jsonTile = (JSONObject)tile;
                tiles.add(new Tile(jsonTile));
            }
        }*/
        //TODO : Implementer les properties et les tileProperties
    }

    public TileSet() {

    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(image, 10, 10);
    }

    public int getFirstGid() {
        return firstGid;
    }

    public void setFirstGid(int firstGid) {
        this.firstGid = firstGid;
    }

    public int getTileCount() {
        return tileCount;
    }

    public void setTileCount(int tileCount) {
        this.tileCount = tileCount;
    }

    public void drawTile(GraphicsContext gc, int gid, int x, int y) {
        int index = gid - firstGid;

        int xMin = (index%columns)*tileWidth;
        int yMin = (index/columns)*tileHeight;

        gc.drawImage(image, xMin, yMin, tileWidth, tileHeight, x, y, tileWidth, tileHeight);
    }
}
