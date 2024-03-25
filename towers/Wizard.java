package towers;

public class Wizard extends ATower{

    public Wizard(int x, int y, int id, int towerType) {
        super(x, y, id, 2);
    }

    @Override
    public void update() {
        cdTick++;
    }

    @Override
    public void upgradeTower() {
        this.tier++;
        range+=20;
        cooldown-=10;
    }
    
}
