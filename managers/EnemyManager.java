package managers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enemies.AEnemy;
import enemies.ExtraTer;
import enemies.Pieuvre;
import enemies.RedRob;
import enemies.Rob;
import helpz.LoadSave;
import main.scenes.Playing;
import objects.PathPoint;

import static helpz.Constants.Direction.*;
import static helpz.Constants.Enemies.*;
import static helpz.Constants.Tiles.*;


public class EnemyManager {

    private Playing playing;
    private BufferedImage[] enemyImgs;
    private ArrayList<AEnemy> enemies = new ArrayList<>();
    //private float speed = 0.5f;
    private PathPoint start, end;
    private int HPbarWidth = 20;
    private BufferedImage slowEffect;

    public EnemyManager(Playing playing, PathPoint start, PathPoint end) {
        this.playing = playing;
        enemyImgs = new BufferedImage[4];
        this.start = start;
        this.end = end;
        loadEnemyImgs();
        loadEffectImg();
    }
    private void loadEffectImg() {
        slowEffect = LoadSave.getSpriteTowers().getSubimage(32*9, 32*2, 32, 32);
    }

    private void loadEnemyImgs() {
        BufferedImage atlas = LoadSave.getSpriteEnemies();
        // enemyImgs[0] = atlas.getSubimage(0, 32, 32, 32);
        // enemyImgs[1] = atlas.getSubimage(32, 32, 32, 32);
        // enemyImgs[2] = atlas.getSubimage(2*32, 32, 32, 32);
        // enemyImgs[3] = atlas.getSubimage(3*32, 32, 32, 32);

        for( int i = 0; i<4; i++)
            enemyImgs[i] = atlas.getSubimage(i*32, 32, 32, 32);
    }
    public void update() {        
        // if(isTimeForNewEnemy()){
        //     spawnEnemy();
        // }
        for (AEnemy e : enemies){
            if(e.isAlive())
                //is next tile road(pos, dir)
                updateEnemyMove(e);
                //move enemy
        }
    }

    private void updateEnemyMove(AEnemy e) {
        if(e.getLastDir() == -1)
            setNewDirectionAndMove(e);
        //e pos
        //e dir
        //tile at new possible pos
        int newX = (int)(e.getX() + getSpeedAndWidth(e.getLastDir(), e.getEnemyType()));
        int newY = (int)(e.getY() + getSpeedAndHeight(e.getLastDir(), e.getEnemyType()));

        if(getTileType(newX,newY)== ROAD_TILE){
            //keep moving in same dir
            e.move(GetSpeed(e.getEnemyType()), e.getLastDir());

        }else if(isAtEnd(e)){
            e.kill();
            playing.removeOneLife();
        }else{
            //find new dir
            setNewDirectionAndMove(e);
        }

    }

    private void setNewDirectionAndMove(AEnemy e) {
        int dir = e.getLastDir();

        //move into the current till 100%
        int xCoord = (int)(e.getX()/32);
        int yCoord = (int)(e.getY()/32);

        fixEnemyOffsetTile(e, dir, xCoord, yCoord);

        if(isAtEnd(e))
            return;

        if(dir == LEFT || dir == RIGHT){
            int newY = (int) (e.getY() + getSpeedAndHeight(UP, e.getEnemyType()));
            if (getTileType((int) e.getX(), newY) == ROAD_TILE)
                e.move(GetSpeed(e.getEnemyType()), UP);
            else
                e.move(GetSpeed(e.getEnemyType()), DOWN);
        }else {
            int newX = (int)(e.getX() + getSpeedAndWidth(RIGHT, e.getEnemyType()));
            if(getTileType(newX, (int) e.getY()) == ROAD_TILE)
                e.move(GetSpeed(e.getEnemyType()), RIGHT);
            else
                e.move(GetSpeed(e.getEnemyType()), LEFT);
        }
    }

    private void fixEnemyOffsetTile(AEnemy e, int dir, int xCoord, int yCoord) {
        switch (dir) {
            // case LEFT:
            //     if(xCoord > 0)
            //         xCoord--;
            //     break;
            // case UP:
            //     if(yCoord > 0)
            //         yCoord--;
            //     break;
            case RIGHT:
                if(xCoord < 19)
                    xCoord++;
                break;
            case DOWN:
                if(yCoord< 19)
                    yCoord++;
                break;
        }

        e.setPos(xCoord*32, yCoord*32);
    }
    private boolean isAtEnd(AEnemy e) {
        if(e.getX() == end.getxCoord()*32)
            if(e.getY() == end.getyCoord()*32)
                return true;
        return false;
    }

    private int getTileType(int x, int y) {
        return playing.getTileType(x,y);
    }

    private float getSpeedAndHeight(int dir, int enemyType) {
        if(dir == UP)
            return -GetSpeed(enemyType);
        else if (dir == DOWN) 
            return GetSpeed(enemyType) + 32;
        return 0;
    }

    private float getSpeedAndWidth(int dir, int enemyType) {
        if(dir == LEFT)
            return -GetSpeed(enemyType);
        else if (dir == RIGHT) 
            return GetSpeed(enemyType) + 32;
        return 0;
    }

    public void spawnEnemy(int nextEnemy) {
        addEnemy(nextEnemy);
    }

    public void addEnemy(int enemyType) {

        int x = start.getxCoord()*32;
        int y = start.getyCoord()*32;

        switch (enemyType) {
            case EXTRATER:
                enemies.add(new ExtraTer(x, y, 0, this));
                break;
            case PIEUVRE:
                enemies.add(new Pieuvre(x, y, 0, this));
                break;
            case ROB:
                enemies.add(new Rob(x, y, 0, this));
                break;
            case REDROB:
                enemies.add(new RedRob(x, y, 0, this));
                break;
        
        }
        
    }
    public void draw(Graphics g) {
        for (AEnemy e:enemies){
            if(e.isAlive()){
                drawEnemy(e, g);
                drawHealthBar(e, g);
                drawEffects(e,g);
            }
        }
    }
    private void drawEffects(AEnemy e, Graphics g) {
        if(e.isSlowed()){
            g.drawImage(slowEffect, (int)e.getX(), (int)e.getY(), null);
        }
    }
    private void drawHealthBar(AEnemy e, Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)e.getX() + 16 - (getNewBarWidth(e)/ 2), (int)e.getY() -10, getNewBarWidth(e), 3);
    }

    private int getNewBarWidth(AEnemy e){
        return (int) (HPbarWidth * e.getHealthBarFloat());
    }

    private void drawEnemy(AEnemy e, Graphics g) {
        g.drawImage(enemyImgs[e.getEnemyType()], (int)e.getX(), (int)e.getY(), null);
    }

    public ArrayList<AEnemy> getEnemies(){
        return enemies;
    }
    public int getAmountOfAliveEnemies() {
        int size = 0;
        for(AEnemy e : enemies){
            if(e.isAlive()){
                size++;
            }
        }
        return size;
    }
    public void rewardPlayer(int enemyType) {
        playing.rewardPlayer(enemyType);
    }

    public void reset(){
        enemies.clear();
    }
}
