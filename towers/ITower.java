package towers;

public interface ITower {

    public void resetCooldown();
    public int getX();

    public void setX(int x);

    public int getY();

    public void setY(int y);

    public int getId();

    public void setId(int id);

    public int getTowerType();

    public void setTowerType(int towerType);

    public int getDmg() ;

    public float getRange();

    public float getCooldown();

    public int getTier();
}
