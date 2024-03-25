package enemies;

import static helpz.Constants.Enemies.ROB;

import managers.EnemyManager;

public class Rob extends AEnemy{

    public Rob(float x, float y, int ID, EnemyManager em) {
        super(x, y, ID, ROB, em);
    }
    
}
