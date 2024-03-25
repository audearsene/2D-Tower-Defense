package ui;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import helpz.LoadSave;
import main.scenes.Editing;
import objects.Tile;

public class ToolBar extends Bar {

    private Editing editing;
    private MyButton bMenu, bSave;
    private MyButton bPathStart, bPathEnd;
    private BufferedImage pathStart, pathEnd;
    private Tile selectedTile;

    private ArrayList<MyButton> tileButtons = new ArrayList<>();

    public ToolBar(int x, int y, int width, int height, Editing editing) {
        super(x, y, width, height);
        this.editing=editing;
        initPathImgs();
        initButtons();
    }

    private void initPathImgs() {
        pathStart = LoadSave.getSpriteAtlas().getSubimage(0, 15*32, 32, 32);
        pathEnd = LoadSave.getSpriteAtlas().getSubimage(0, 0, 32, 32);
    }

    private void initButtons() {
        bMenu = new MyButton("Menu", 2, 642, 100, 30);
        bSave = new MyButton("Save", 2, 674, 100, 30);

        int w=50;
        int h=50;
        int xStart=110;
        int yStart=650;
        int xOffset=(int)(w*1.1f);

        int i=0;
        for(Tile tile : editing.getGame().getTileManager().tiles){

            tileButtons.add(new MyButton("test", xStart + xOffset*i,yStart , w, h, i));
            i++;
        }

        bPathStart = new MyButton("PathStart", xStart, yStart + xOffset, w, h, i++);
        bPathEnd = new MyButton("PathEnd", xStart + xOffset, yStart + xOffset, w, h, i++);
    }

    private void saveLevel() {
        editing.saveLevel();
    }


    public void draw(Graphics g){

        //Background
        g.setColor(new Color(220,123,15));
        g.fillRect(x, y, width, height);
        
        //Buttons
        drawBottons(g);
    }

    private void drawBottons(Graphics g){
        bMenu.draw(g);
        bSave.draw(g);

        drawPathButton(g, bPathStart, pathStart);
        drawPathButton(g, bPathEnd, pathEnd);

        // bPathStart.draw(g);
        // bPathEnd.draw(g);

        drawTileButtons(g);
        drawSelectedTile(g);
    
    }

    private void drawPathButton(Graphics g, MyButton b, BufferedImage img) {
        g.drawImage(img, b.x, b.y, b.width, b.height, null);
        drawButtonFeedBack(g, b);
    }

    private void drawSelectedTile(Graphics g) {
        if(selectedTile != null){
            g.drawImage(selectedTile.getSprite(), 550, 670, 50, 50, null);
            g.setColor(Color.black);
            g.drawRect(550, 670, 50, 50);
        }
    }

    private void drawTileButtons(Graphics g) {
        for(MyButton b : tileButtons){
            
            //Sprite
            g.drawImage(getButtImg(b.getId()),b.x, b.y, b.width, b.height, null);

            //MouseOver
            if(b.isMouseOver()){
                g.setColor(Color.white);
            }else{
                g.setColor(Color.black);
            }

            //Border
            g.drawRect(b.x, b.y, b.width, b.height);

            //MousePressed
            if (b.isMousePressed()){
                g.drawRect(b.x+1, b.y+1, b.width-2, b.height-2);
                g.drawRect(b.x+2, b.y+2, b.width-4, b.height-4);
            }
           
        }
    }

    public BufferedImage getButtImg(int id) {
        return editing.getGame().getTileManager().getSprite(id);
    }

    public void mouseClicked(int x, int y) {
        if( bMenu.getBounds().contains(x,y))
            SetGameState(MENU);
        else if(bSave.getBounds().contains(x,y))
            saveLevel();
        else if(bPathStart.getBounds().contains(x,y)){
            selectedTile = new Tile(pathStart, -1, -1);
            editing.setSelectedTile(selectedTile);
        }else if(bPathEnd.getBounds().contains(x,y)){
            selectedTile = new Tile(pathEnd, -2, -2);
            editing.setSelectedTile(selectedTile);
        }
        else{
            for(MyButton b : tileButtons){
                if(b.getBounds().contains(x,y)){
                    selectedTile = editing.getGame().getTileManager().getTile(b.getId());
                    editing.setSelectedTile(selectedTile);
                    return;
                }
            }
        }
    }

    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        bSave.setMouseOver(false);
        bPathStart.setMouseOver(false);
        bPathEnd.setMouseOver(false);
        for(MyButton b : tileButtons)
            b.setMouseOver(false);

        if( bMenu.getBounds().contains(x,y))
            bMenu.setMouseOver(true);
        else if(bSave.getBounds().contains(x,y))
            bSave.setMouseOver(true);
        else if(bPathStart.getBounds().contains(x,y))
            bPathStart.setMouseOver(true);
        else if(bPathEnd.getBounds().contains(x,y))
            bPathEnd.setMouseOver(true);
        else{
            for(MyButton b : tileButtons){
                if(b.getBounds().contains(x,y)){
                    b.setMouseOver(true);
                    return;
                }
            }
        }
    }

    public void mousePressed(int x, int y) {
        if( bMenu.getBounds().contains(x,y))
            bMenu.setMousePressed(true);
        else if(bSave.getBounds().contains(x,y))
            bSave.setMousePressed(true);
        else{
            for(MyButton b : tileButtons){
                if(b.getBounds().contains(x,y)){
                    b.setMousePressed(true);
                    return;
                }
            }
        }
    }

    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
        bSave.resetBooleans();
        for(MyButton b : tileButtons)
            b.resetBooleans();
    }

    public BufferedImage getStartPathImg() {
        return pathStart;
    }

    public BufferedImage getEndPathImg() {
        return pathEnd;
    }

}
