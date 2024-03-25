package towers;

public class Archer extends ATower{

    public Archer(int x, int y, int id, int towerType) {
        super(x, y, id, 1);
    }

    @Override
    public void upgradeTower() {
        this.tier++;
        dmg+=2;
        range+=20;
        cooldown-=5;
    }

    @Override
    public void update() {
        cdTick++;
    }
    
}
