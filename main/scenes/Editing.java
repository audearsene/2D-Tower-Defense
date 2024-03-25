package main.scenes;

import static helpz.Constants.Tiles.ROAD_TILE;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import helpz.LoadSave;
import main.Game;
import objects.PathPoint;
//import managers.TileManager;
//import managers.TileManager;
import objects.Tile;
//import ui.ActionBar;
import ui.ToolBar;

public class Editing extends GameScene implements SceneMethods {

    private int[][] lvl;
    //private TileManager tileManager;
    private Tile selectedTile;
    private int mouseX, mouseY;
    private int lastTileX, lastTileY, lastTileId;
    private boolean drawSelect;
    private ToolBar toolBar;
    private PathPoint start, end;

    private int animationIndex;
    private int tick;

    public Editing(Game game) {
        super(game);
        loadDefaultLevel();
        toolBar=new ToolBar(0, 640, 640, 160, this);
    }

    private void loadDefaultLevel() {
        lvl= LoadSave.GetLevelData("new_level");
        ArrayList<PathPoint> points = LoadSave.GetLevelPathPoints("new_level");
        start = points.get(0);
        end = points.get(1);
    }

    public void update(){
        updateTick();
    }

    @Override
    public void render(Graphics g) {

        drawLevel(g);
        toolBar.draw(g);
        drawSelectedTile(g);
        drawPathPoint(g);

    }

    private void drawPathPoint(Graphics g) {
        if(start != null){
            g.drawImage(toolBar.getStartPathImg(), start.getxCoord()*32, start.getyCoord()*32, 32, 32, null);
        }

        if(end != null){
            g.drawImage(toolBar.getEndPathImg(), end.getxCoord()*32, end.getyCoord()*32, 32, 32, null);
        }
    }

    protected void updateTick(){
        tick++;
        if(tick >= 20){
            tick = 0;
            animationIndex++;
            if(animationIndex >=4){
                animationIndex = 0;
            }
        }
    }

    private void drawLevel(Graphics g){
        for(int y=0; y < lvl.length; y++){
            for( int x=0; x<lvl[y].length; x++){
                int id = lvl[y][x];
                if(isAnimation(id)){
                    g.drawImage(getSprite(id, animationIndex), x*32, y*32, null);
                } else
                    g.drawImage(getSprite(id), x*32, y*32, null);
            }
        }
    }

    protected boolean isAnimation(int spriteID){
        return game.getTileManager().isSpriteAnimation(spriteID);
    }

    public BufferedImage getSprite(int spriteID){
        return game.getTileManager().getSprite(spriteID);
    }

    private BufferedImage getSprite(int spriteID, int animationIndex){
        return game.getTileManager().getAniSprite(spriteID, animationIndex);
    }

    private void drawSelectedTile(Graphics g) {
        if(selectedTile != null && drawSelect){
            g.drawImage(selectedTile.getSprite(), mouseX, mouseY, 32, 32, null);
        }
    }

    public void saveLevel(){

        LoadSave.SaveLevel("new_level", lvl, start, end);
        game.getPlaying().setLevel(lvl);

    }

    public void setSelectedTile(Tile tile){
        this.selectedTile=tile;
        drawSelect=true;
    }

    private void changeTile(int x, int y) {

        if(selectedTile != null){
            int tileX=x/32;
            int tileY=y/32;

            if(selectedTile.getId() >=0){

                if(lastTileX == tileX && lastTileY == tileY && lastTileId == selectedTile.getId())
                    return;

                lastTileX=tileX;
                lastTileY=tileY;
                lastTileId=selectedTile.getId();

                lvl[tileY][tileX] = selectedTile.getId();
            }else {
                int id = lvl[tileY][tileX];
                if(game.getTileManager().getTile(id).getTileType() ==ROAD_TILE){
                    if(selectedTile.getId() == -1)
                        start = new PathPoint(tileX, tileY);
                    else
                        end = new PathPoint(tileX, tileY);
                }
            }
        }
    }

    @Override
    public void mouseClicked(int x, int y) {
        if(y>=640){
            toolBar.mouseClicked(x, y);
        }else{
            changeTile(mouseX, mouseY);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if(y>=640){
            toolBar.mouseMoved(x, y);
            drawSelect=false;
        }else{
            drawSelect=true;
            mouseX=(x/32)*32;
            mouseY=(y/32)*32;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
    }

    @Override
    public void mouseReleased(int x, int y) {
    }

    @Override
    public void mouseDragged(int x, int y) {
        if(y >= 640){

        }else{
            changeTile(x, y);
        } 
    }

    public void keyPressed(KeyEvent arg0) {
        
    }
    
}
