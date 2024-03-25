package events;

import java.util.ArrayList;

public class Wave {
    private ArrayList<Integer> enemyList;

    public Wave(ArrayList<Integer> enemyList) {
        this.enemyList = enemyList;
    }
    
    //getters and setters
    public ArrayList<Integer> getEnemyList() {
        return enemyList;
    }
}
