package main.scenes;

import java.awt.Color;
import java.awt.Graphics;

import main.Game;

import ui.MyButton;
import static main.GameStates.*;

public class Settings extends GameScene implements SceneMethods{

    private MyButton bMenu;

    public Settings(Game game) {
        super(game);

        initButtons();
    }

    private void initButtons() {
        int w =150;
        int h =w/2;
        int x = 0;
        int y=0;

        bMenu = new MyButton("Menu", x, y,w, h);
    }

    @Override
    public void render(Graphics g) {
        
        drawBackground(g);
        bMenu.draw(g);
    }

    private void drawBackground(Graphics g){
        for(int y=0; y<800; y++){
            for(int x=0; x<640; x++){
                g.drawImage(getSprite(1), x*32, y*32, null);
            }
        }
    }

    @Override
    public void mouseClicked(int x, int y) {
        if( bMenu.getBounds().contains(x,y)){
            System.out.println("button is clicked!!");
            SetGameState(MENU);

        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        if( bMenu.getBounds().contains(x,y)){
            bMenu.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if( bMenu.getBounds().contains(x,y)){
            bMenu.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        resetButton();
    } 
    private void resetButton() {
        bMenu.resetBooleans();
    }

    @Override
    public void mouseDragged(int x, int y) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseDragged'");
    } 
    
}
