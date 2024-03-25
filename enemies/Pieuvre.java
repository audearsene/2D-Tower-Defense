package enemies;

import static helpz.Constants.Enemies.PIEUVRE;

import managers.EnemyManager;

public class Pieuvre extends AEnemy{

    public Pieuvre(float x, float y, int ID, EnemyManager em) {
        super(x, y, ID, PIEUVRE, em);
    }
    
}
