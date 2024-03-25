package main.scenes;

import java.awt.Graphics;
// import java.awt.image.BufferedImage;
// import java.io.File;
// import java.io.IOException;
// import java.util.ArrayList;
// //import java.util.Random;

import javax.imageio.ImageIO;

import main.Game;
import ui.MyButton;
import static main.GameStates.*;

public class Menu extends GameScene implements SceneMethods{

    //private Random random;
    //private BufferedImage img;
    //private ArrayList<BufferedImage> sprites = new ArrayList<>();

    private MyButton bPlaying, bEdit, bSettings, bQuit;

    public Menu(Game game) {
        super(game);
        //random = new Random();
		//importImg();
        //loadSprites();
        initButtons();
    }
    private void initButtons() {
        int w =150;
        int h =w/2;
        int x = 640 / 2 - w/2;
        int y=150;
        int yOffset=100;

        bPlaying = new MyButton("Play", x, y,w, h, this);
        bEdit= new MyButton("Edit", x, y + yOffset, w, h, this);
        //bSettings = new MyButton("Settings", x,y + yOffset*2,w, h, this);
        bQuit = new MyButton("Quit", x, y + yOffset * 2,w, h, this);
    }

    
    @Override
    public void render(Graphics g) {

        drawBackground(g);
        drawButtons(g);

    }
    
    private void drawBackground(Graphics g){
        for(int y=0; y<800; y++){
            for(int x=0; x<640; x++){
                g.drawImage(getSprite(0), x*32, y*32, null);
            }
        }
    }

    // private void drawBackgroundButtons(Graphics g){
    //     for(int y=0; y<150; y++){
    //         for(int x=0; x<(150/2); x++){
    //             g.drawImage(getSprite(1), y*32, x*32, null);
    //         }
    //     }
    // }

    private void drawButtons(Graphics g) {
        //drawBackgroundButtons(g);
        bPlaying.draw(g);
        bEdit.draw(g);
        //bSettings.draw(g);
        bQuit.draw(g);
    }

    // private void loadSprites(){
    //     for(int y=0; y<26; y++){
    //         for (int x=0; x<29;x++){
    //             sprites.add(img.getSubimage(x*32, y*32, 32, 32));
    //         }
    //     }
    // }

    // private int getRandInt(){
    //     return random.nextInt(754);
    // }

	// public void importImg(){
	// 	try{
	// 		img = ImageIO.read(new File("res/map-transformed.jpeg"));
	// 	}catch(IOException e){
	// 		e.printStackTrace();
	// 	}
	// }
    @Override
    public void mouseClicked(int x, int y) {

        if( bPlaying.getBounds().contains(x,y)){
            SetGameState(PLAYING);
        }

        if(bEdit.getBounds().contains(x,y))
            SetGameState(EDIT);

        // if(bSettings.getBounds().contains(x,y)){
        //     SetGameState(SETTINGS);
        // }

        if(bQuit.getBounds().contains(x,y)){
            System.exit(0);
        }
    }
    @Override
    public void mouseMoved(int x, int y) {
        bPlaying.setMouseOver(false);
        if( bPlaying.getBounds().contains(x,y)){
            bPlaying.setMouseOver(true);
        }
        bEdit.setMouseOver(false);
        if( bEdit.getBounds().contains(x,y)){
            bEdit.setMouseOver(true);
        }
        // bSettings.setMouseOver(false);
        // if( bSettings.getBounds().contains(x,y)){
        //     bSettings.setMouseOver(true);
        // }
        bQuit.setMouseOver(false);
        if( bQuit.getBounds().contains(x,y)){
            bQuit.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if( bPlaying.getBounds().contains(x,y)){
            bPlaying.setMousePressed(true);
        }
        if( bEdit.getBounds().contains(x,y)){
            bEdit.setMousePressed(true);
        }
        // if( bSettings.getBounds().contains(x,y)){
        //     bSettings.setMousePressed(true);
        // }
        if( bQuit.getBounds().contains(x,y)){
            bQuit.setMousePressed(true);
        }
    }
    @Override
    public void mouseReleased(int x, int y) {
        resetButton();
    }
    private void resetButton() {
        bPlaying.resetBooleans();
        bEdit.resetBooleans();
        //bSettings.resetBooleans();
        bQuit.resetBooleans();
    }
    @Override
    public void mouseDragged(int x, int y) {
    } 
}
