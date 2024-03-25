package enemies;

import static helpz.Constants.Enemies.REDROB;

import managers.EnemyManager;

public class RedRob extends AEnemy{

    public RedRob(float x, float y, int ID, EnemyManager em) {
        super(x, y, ID, REDROB, em);
    }
    
}
