package towers;

public class Cannon extends ATower{

    public Cannon(int x, int y, int id, int towerType) {
        super(x, y, id, 0);
    }
    
    @Override
    public void update() {
        cdTick++;
    }

    @Override
    public void upgradeTower() {
        this.tier++;
        dmg+=5;
        range+=20;
        cooldown-=15;
    }

    
}
