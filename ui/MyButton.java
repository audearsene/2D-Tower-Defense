package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import main.Game;
import main.scenes.GameOver;
import main.scenes.GameScene;

public class MyButton extends JButton{

    public int x, y, width, height, id;
    private String text;
    private Rectangle bounds;
    private boolean mouseOver, mousePressed;
    private GameScene gameScene;
    private GameOver gameOver;

    //for normal Buttons
    public MyButton(String text, int x,int y, int width, int height){
        this.text=text;
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.id=-1;

        initBounds();
    }

    public MyButton(String text, int x,int y, int width, int height, GameScene gameScene){
        this.text=text;
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.gameScene=gameScene;

        initBounds();
    }

    //for tile Buttons
    public MyButton(String text, int x,int y, int width, int height, int id){
        this.text=text;
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.id=id;

        initBounds();
    }

    private void initBounds(){
        this.bounds=new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g){

        //Body
        drawBody(g);

        //Border
        drawBorder(g);

        //Text
        drawText(g);

    }

    private void drawBorder(Graphics g) {
        if(mousePressed){
            g.setColor(Color.black);
            g.drawRect(x, y, width, height);
            g.drawRect(x+1, y+1, width-2, height-2);
            g.drawRect(x+1, y+1, width-4, height-4);
        }else{
            g.setColor(Color.black);
            g.drawRect(x, y, width, height);
        }
    }

    private void drawBody(Graphics g) {
        if(mouseOver){
            if(gameScene == null){
                g.setColor(Color.gray);
                g.fillRect(x, y, width, height);
            }else{
                Image backButtons = gameScene.getSprite(2).getScaledInstance(width, height, Image.SCALE_SMOOTH);
                ImageIcon backButtonsIcon = new ImageIcon(backButtons);
                backButtonsIcon.paintIcon(this, g, x, y);
            }
        }else{

            if(gameScene == null){
                g.setColor(Color.WHITE);
                g.fillRect(x, y, width, height);
            }else{
                Image backButtonsD = gameScene.getSprite(1).getScaledInstance(width, height, Image.SCALE_SMOOTH);
                ImageIcon backButtonsIconD = new ImageIcon(backButtonsD);
                backButtonsIconD.paintIcon(this, g, x, y);
            }
            //g.setColor(Color.WHITE);

            // for(int y=this.y; y<this.height; y++){
            //     for(int x=this.x; x<this.width; x++){
            //         g.drawImage(gameScene.getSprite(1), x*32, y*32, null);
            //     }
            // }
        }
        //g.fillRect(x, y, width, height);
    }

    private void drawText(Graphics g) {
        int w = g.getFontMetrics().stringWidth(text);
        int h = g.getFontMetrics().getHeight();
        g.drawString(text, x- w/2 + width/2, y + h/2 + height/2);
    }

    public void resetBooleans(){
        this.mouseOver = false;
        this.mousePressed = false;
    }

    public void setText(String text){
        this.text=text;
    }

    public void setMouseOver(boolean mouseOver){
        this.mouseOver = mouseOver;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public boolean isMouseOver(){
        return mouseOver;
    }

    public boolean isMousePressed(){
        return mousePressed;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public int getId() {
        return id;
    }

    public GameScene getGameScene() {
        return gameScene;
    }

}