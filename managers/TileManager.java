package managers;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import helpz.LoadSave;
import objects.Tile;
import static helpz.Constants.Tiles.*;

public class TileManager {
    public Tile GRASS,WATER,ROAD,ROADLEFTDOWN,ROADDOWN,ROADLEFTUP,ROADRIGHTDOWN,ROADRIGHTUP;
    public BufferedImage atlas;
    public ArrayList<Tile> tiles = new ArrayList<>();
    
    public TileManager(){

        loadAtlas();
        createTiles();

    }

    private void createTiles(){

        int id=0; 
        tiles.add(GRASS = new Tile(getSprite(2, 3), id++, GRASS_TILE));//0
        tiles.add(WATER = new Tile(getSprite(3, 21), id++, WATER_TILE));//1
        tiles.add(ROAD = new Tile(getSprite(7, 5),id++, ROAD_TILE));//2
        tiles.add(ROADRIGHTDOWN = new Tile(getSprite(8, 5), id++, ROAD_TILE));//3
        tiles.add(ROADDOWN = new Tile(getSprite(8, 6), id++, ROAD_TILE));//4
        tiles.add(ROADRIGHTUP= new Tile(getSprite(8, 7), id++, ROAD_TILE));//5
        tiles.add(ROADLEFTDOWN = new Tile(getSprite(6, 5), id++, ROAD_TILE));//6
        tiles.add(ROADLEFTUP = new Tile(getSprite(6, 7), id++, ROAD_TILE));//7
    }

    private void loadAtlas(){
        atlas=LoadSave.getSpriteAtlas();
    }

    public Tile getTile(int id){
        return tiles.get(id);
    }

    public BufferedImage getSprite(int id){
        return tiles.get(id).getSprite();
    }

    public BufferedImage getAniSprite(int id, int animationIndex){
        return tiles.get(id).getSprite(animationIndex);
    }

    public BufferedImage[] getAniSprites(int xCoord, int yCoord){
        BufferedImage[] arr = new BufferedImage[4];
        for(int i = 0; i<4; i++){
            arr[i] = getSprite(xCoord+i, yCoord+i);
        }
        return arr;
    }

    private BufferedImage getSprite(int xCoord, int yCoord){
        return atlas.getSubimage(xCoord*32, yCoord*32, 32, 32);
    }

    public boolean isSpriteAnimation(int spriteID) {
        return tiles.get(spriteID).isAnimation();
    }
}
