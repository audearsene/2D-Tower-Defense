package main.scenes;

import static main.GameStates.MENU;
import static main.GameStates.PLAYING;
import static main.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
//import java.util.ArrayList;

import helpz.LoadSave;
import main.Game;
//import objects.PathPoint;
import ui.MyButton;

public class GameOver extends GameScene implements SceneMethods{

    private MyButton bReplay, bMenu;
    private int[][] lvl;

    public GameOver(Game game) {
        super(game);
        loadDefaultLevel();
        initButtons();
    }

    private void initButtons() {
        int w =150;
        int h =w/2;
        int x = 640 / 2 - w/2;
        int y=200;
        int yOffset=150;

        bMenu = new MyButton("Menu", x, y, w, h);
        bReplay = new MyButton("Replay", x, y+yOffset, w, h);
    }

    private void loadDefaultLevel() {
        lvl= LoadSave.GetLevelData("new_level");
        //ArrayList<PathPoint> points = LoadSave.GetLevelPathPoints("new_level");
    }

    public void setLevel(int[][] lvl){
        this.lvl=lvl;
    }

    @Override
    public void render(Graphics g) {
        drawLevel(g);
        g.setFont(new Font("LucidaSans", Font.BOLD, 50));
        g.setColor(Color.RED);
        g.drawString("GAME OVER!", 160, 80);


        g.setFont(new Font("LucidaSans", Font.BOLD, 20));
        bMenu.draw(g);
        bReplay.draw(g);

    }

    private void replayGame(){
        resetAll();   

        SetGameState(PLAYING);
    }

    private void resetAll(){
         game.getPlaying().resetEverything();
    }

    private void drawLevel(Graphics g){
        for(int y=0; y < lvl.length; y++){
            for( int x=0; x<lvl[y].length; x++){
                int id = lvl[y][x];
                if(isAnimation(id)){
                    g.drawImage(getSprite(id), x*32, y*32, null);
                }else {
                    g.drawImage(getSprite(id), x*32, y*32, null);
                }
            }
        }
        for(int y=lvl.length; y<800; y++){
            for(int x=0; x<640; x++){
                g.drawImage(getSprite(0), x*32, y*32, null);
            }
        }
    }

    @Override
    public void mouseClicked(int x, int y) {
        if(bMenu.getBounds().contains(x, y)){
            SetGameState(MENU);
        }else if(bReplay.getBounds().contains(x, y)){
            replayGame();
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        bReplay.setMouseOver(false);

        if(bMenu.getBounds().contains(x, y)){
            bMenu.setMouseOver(true);
        }else if(bReplay.getBounds().contains(x, y)){
            bReplay.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if(bMenu.getBounds().contains(x, y)){
            bMenu.setMousePressed(true);
        }else if(bReplay.getBounds().contains(x, y)){
            bReplay.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
        bReplay.resetBooleans();
    }

    @Override
    public void mouseDragged(int x, int y) {
    }
    
}
