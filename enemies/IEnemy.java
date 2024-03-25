package enemies;

import java.awt.Rectangle;

public interface IEnemy {

    public float getX();

    public float getY();

    public Rectangle getBounds();

    public int getHealth();

    public int getID();

    public int getEnemyType();

    public int getLastDir();

    public boolean isAlive();

    public boolean isSlowed();
}
