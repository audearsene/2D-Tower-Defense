package towers;

public abstract class ATower implements ITower{
    
    protected int x, y, id, towerType, cdTick, dmg;
    protected float range, cooldown;
    protected int tier;

    public ATower(int x, int y, int id, int towerType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.towerType = towerType;
        tier = 1;
        setDefaultDmg();
        setDefaultRange();
        setDefaultCooldown();
    }

    public abstract void update();

    public abstract void upgradeTower();
    

    public boolean isCooldownOver() {
        return cdTick >= cooldown;
    }

    public void resetCooldown() {
        cdTick = 0;
    }

    private void setDefaultCooldown() {
        cooldown = helpz.Constants.Towers.GetDefaultCooldown(towerType);
    }

    private void setDefaultRange() {
        range = helpz.Constants.Towers.GetDefaultRange(towerType);
    }

    private void setDefaultDmg() {
        dmg = helpz.Constants.Towers.GetStartDmg(towerType);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTowerType() {
        return towerType;
    }

    public void setTowerType(int towerType) {
        this.towerType = towerType;
    }

    public int getDmg() {
        return dmg;
    }

    public float getRange() {
        return range;
    }

    public float getCooldown() {
        return cooldown;
    }

    public int getTier(){
        return tier;
    }
    
}
