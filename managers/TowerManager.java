package managers;

import static helpz.Constants.Towers.ARCHER;
import static helpz.Constants.Towers.CANNON;
import static helpz.Constants.Towers.WIZARD;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enemies.AEnemy;
import helpz.LoadSave;
import main.scenes.Playing;
import towers.ATower;
import towers.Archer;
import towers.Cannon;
import towers.Wizard;

public class TowerManager {

    private Playing playing;
    private BufferedImage[] towerImgs;
    private ArrayList<ATower> towers = new ArrayList<>();
    private int towerAmount = 0;
    //private ATower tower;

    public TowerManager(Playing playing){
        this.playing = playing;
        loadTowerTmgs();
        //initTowers();
    }

    // private void initTowers() {
    //     tower = new ATower(3*32, 6*32, 0, ARCHER); 
    // }

    private void loadTowerTmgs() {
        BufferedImage atlas = LoadSave.getSpriteTowers();
        towerImgs = new BufferedImage[3];
        for (int i = 0; i<3; i++)
            towerImgs[i] = atlas.getSubimage((4 +i)*32, 32, 32, 32);
    }

    public void addTower(ATower selectedTower, int xPos, int yPos){
        //towers.add(new ATower(xPos, yPos, towerAmount++, selectedTower.getTowerType()));

        switch (selectedTower.getTowerType()) {
            case ARCHER:
                towers.add(new Archer(xPos, yPos, towerAmount++, selectedTower.getTowerType()));
                break;
            case CANNON:
                towers.add(new Cannon(xPos, yPos, towerAmount++, selectedTower.getTowerType()));
                break;
            case WIZARD:
                towers.add(new Wizard(xPos, yPos, towerAmount++, selectedTower.getTowerType()));
                break;
        
            default:
                break;
        }
    }

    public void removeTower(ATower displayedTower) {
        for(int i = 0; i< towers.size(); i++){
            if(towers.get(i).getId() == displayedTower.getId()){
                towers.remove(i);
            }
        }
    }

    public void upgradeTower(ATower displayedTower) {
        for(ATower t : towers){
            if(t.getId() == displayedTower.getId()){
                t.upgradeTower();
            }
        }
    }

    public void update(){
        for(ATower t : towers){
            t.update();
            attackEnemyIfClose(t);
        }
    }

    private void attackEnemyIfClose(ATower t) {
        for(AEnemy e : playing.getEnemyManager().getEnemies()){
            if(e.isAlive())
                if(isEnemyInRange(t,e)){
                    if(t.isCooldownOver()){
                        //tower shoot enemy
                        playing.shootEnemy(t,e);
                        t.resetCooldown();
                    }
                }else{
                    //we do nothing
                }
        }
    }

    private boolean isEnemyInRange(ATower t, AEnemy e) {
        int range = helpz.Utilz.GetHypoDistance(t.getX(), t.getY(), e.getX(), e.getY());

        return range < t.getRange();
    }

    public void draw(Graphics g){
        for(ATower t : towers)
            g.drawImage(towerImgs[t.getTowerType()], t.getX(), t.getY(), null);
    }

    public BufferedImage[] getTowerImgs(){
        return towerImgs;
    }

    public ATower getTowerAt(int x, int y){
        for(ATower t: towers)
            if(t.getX() == x)
                if(t.getY() == y)
                    return t;
        return null;
    }

    public void reset(){
        towers.clear();
        towerAmount = 0;
    }
    
}
