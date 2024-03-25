package main.scenes;

import static helpz.Constants.Tiles.GRASS_TILE;

import java.awt.Color;
//import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
//import java.io.File;
//import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enemies.AEnemy;
//import helpz.LevelBuild;
import helpz.LoadSave;
import main.Game;
import managers.EnemyManager;
import managers.ProjectileManager;
import managers.TowerManager;
import managers.WaveManager;
import objects.PathPoint;
import towers.ATower;
//import managers.TileManager;
//import objects.Tile;
import ui.ActionBar;
//import ui.MyButton;
//import static main.GameStates.*;

public class Playing extends GameScene implements SceneMethods {

    private int[][] lvl;
    private ActionBar actionBar;
    private int mouseX, mouseY;
    private EnemyManager enemyManager;
    private TowerManager towerManager;
    private ProjectileManager projManager;
    private WaveManager waveManager;
    private PathPoint start, end;
    private ATower selectedTower;
    private int goldTick;
    private boolean gamePaused;

    public Playing(Game game) {
        super(game);

        loadDefaultLevel();
        //lvl = LevelBuild.getLevelData();
        actionBar = new ActionBar(0, 640, 640, 160, this);

        enemyManager = new EnemyManager(this, start, end);
        towerManager = new TowerManager(this);
        projManager = new ProjectileManager(this);
        waveManager = new WaveManager(this);

    }

    private void loadDefaultLevel() {
        lvl= LoadSave.GetLevelData("new_level");
        ArrayList<PathPoint> points = LoadSave.GetLevelPathPoints("new_level");
        start = points.get(0);
        end = points.get(1);
    }

    public void setLevel(int[][] lvl){
        this.lvl=lvl;
    }

    public void update(){

        if(!gamePaused){
            updateTick();
            waveManager.update();

            //Gold Tick
            goldTick++;
            if(goldTick % (60*3) == 0){
                actionBar.addGold(1);
            }

            if(isAllEnemiesDead()){
                if(isThereMoreWaves()){
                    waveManager.startWaveTimer();
                    //Check timer
                    if(isWaveTimerOver()){
                        //Increase wave index
                        waveManager.increaseWaveIndex();
                        enemyManager.getEnemies().clear();
                        waveManager.resetEnemyIndex();
                    }
                }
            }

            if(isTimeForNewEnemy()){
                spawnEnemy();
            }

            enemyManager.update();
            towerManager.update();
            projManager.update();
        }
        
    }

    private boolean isWaveTimerOver() {
        return waveManager.isWaveTimerOver();
    }

    private boolean isThereMoreWaves() {
        return waveManager.isThereMoreWaves();
    }

    private boolean isAllEnemiesDead() {

        if(waveManager.isThereMoreEnemiesInWave()){
            return false;
        }
        for(AEnemy e : enemyManager.getEnemies()){
            if(e.isAlive()){
                return false;
            }
        }
        return true;
    }

    private void spawnEnemy() {
        enemyManager.spawnEnemy(waveManager.getNextEnemy());
    }

    private boolean isTimeForNewEnemy() {
        if(waveManager.isTimeForNewEnemy()){
            if(waveManager.isThereMoreEnemiesInWave()){
                return true;
            }
        }
        return false;
    }

    public void setSelectedTower(ATower selectedTower) {

        this.selectedTower = selectedTower;
    }

    @Override
    public void render(Graphics g) {

        drawLevel(g);
        actionBar.draw(g);
        enemyManager.draw(g);
        towerManager.draw(g);
        projManager.draw(g);

        drawSelectedTower(g);
        drawHighlight(g);
        drawWaveInfos(g);
    }

    private void drawWaveInfos(Graphics g) {
        // if(waveManager.isWaveTimerStarted()){
        //     float timeLeft = waveManager.getTimeLeft();
        //     g.drawString("Time Left: " + timeLeft, 250, 700);
        // }
    }

    private void drawSelectedTower(Graphics g) {
        if(selectedTower != null)
            g.drawImage(towerManager.getTowerImgs()[selectedTower.getTowerType()], mouseX, mouseY, null);
    }

    private void drawHighlight(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect(mouseX, mouseY, 32, 32);
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
    }

    // private BufferedImage getSprite(int spriteID){
    //     return getGame().getTileManager().getSprite(spriteID);
    // }

    @Override
    public void mouseClicked(int x, int y) {
        if(y>=640)
            actionBar.mouseClicked(x, y);
        // else 
        //     enemyManager.addEnemy(x, y);
        else {
            //Above 640y
            if(selectedTower != null){
                //Trying to place a tower
                if(isTileGrass(mouseX, mouseY)){
                    if(getTowerAt(mouseX, mouseY) == null){
                        towerManager.addTower(selectedTower, mouseX, mouseY);
                        
                        removeGold(selectedTower.getTowerType());

                        selectedTower = null;
                    }
                }
            }else {
                //Not trying to place a tower
                // Checking if a tower exists at x,y
                ATower t = getTowerAt(mouseX, mouseY);
                actionBar.displayedTower(t);
            }
        }
    }

    private void removeGold(int towerType) {
        actionBar.payForTower(towerType);
    }

    public void removeTower(ATower displayedTower) {
        towerManager.removeTower(displayedTower);
    }

    public void upgradeTower(ATower displayedTower) {
        towerManager.upgradeTower(displayedTower);
    }

    private ATower getTowerAt(int x, int y){
        return towerManager.getTowerAt(x,y);
    }

    private boolean isTileGrass(int x, int y){
        int id = lvl[y / 32][x / 32];
        int tileType = game.getTileManager().getTile(id).getTileType();
        return tileType == GRASS_TILE;
    }


    public void shootEnemy(ATower t, AEnemy e) {
        projManager.newProjectile(t, e);
    }

    public void setGamePaused(boolean gamePaused){
        this.gamePaused=gamePaused;
    }

    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            selectedTower = null;
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if(y>=640 && !gamePaused){
            actionBar.mouseMoved(x, y);
        }else{
            mouseX=(x/32)*32;
            mouseY=(y/32)*32;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if(y>=640){
            actionBar.mousePressed(x, y);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        actionBar.mouseReleased(x, y);
    }

    @Override
    public void mouseDragged(int x, int y) {
    }

    public int getTileType(int x, int y) {
        int xCoord = x/32;
        int yCoord = y/32;

        if(xCoord < 0 || xCoord >19)
            return 0;
        if(yCoord < 0 || yCoord >19)
            return 0;

        int id = lvl[y/32][x/32];
        return game.getTileManager().getTile(id).getTileType();
    } 

    public void rewardPlayer(int enemyType){
        actionBar.addGold(helpz.Constants.Enemies.GetReward(enemyType));
    }

    public EnemyManager getEnemyManager(){
        return enemyManager;
    }

    public TowerManager getTowerManager(){
        return towerManager;
    }

    public WaveManager getWaveManager() {
        return waveManager;
    }

    public boolean isGamePaused(){
        return gamePaused;
    }

    public void removeOneLife() {
        actionBar.removeOneLife();
    }

    public void resetEverything() {
        actionBar.resetEverything();

        enemyManager.reset();
        towerManager.reset();
        projManager.reset();
        waveManager.reset();

        mouseX = 0;
        mouseY = 0;

        selectedTower = null;
        goldTick = 0;
        gamePaused = false;
    }
}
