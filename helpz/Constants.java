package helpz;

public class Constants {

    public static class Projectiles{
        public static final int ARROW = 0;
        public static final int CHAINS = 1;
        public static final int BOMB = 2;

        public static float GetSpeed(int type){
            switch(type){
                case ARROW:
                    return 8f;
                case CHAINS:
                    return 6f;
                case BOMB:
                    return 4f;
            }

            return 0f;
        }
    }

    public static class Towers {
        public static final int CANNON = 0;
        public static final int ARCHER = 1;
        public static final int WIZARD = 2;

        public static int GetTowerCost(int towerType){
            switch (towerType) {
                case CANNON:
                    return 65;
                case ARCHER:
                    return 30;
                case WIZARD:
                    return 45;          
            }
            return 0;
        }

        public static String GetName(int towerType){
            switch (towerType) {
                case CANNON:
                    return "Cannon";
                case ARCHER:
                    return "Archer";
                case WIZARD:
                    return "Wizard";          
            }
            return "";
        }
        
        public static int GetStartDmg(int towerType){
            switch (towerType) {
                case CANNON:
                    return 15;
                case ARCHER:
                    return 5;
                case WIZARD:
                    return 0;          
            }
            return 0;
        }

        public static float GetDefaultRange(int towerType){
            switch (towerType) {
                case CANNON:
                    return 100;
                case ARCHER:
                    return 100;
                case WIZARD:
                    return 100;          
            }
            return 0;
        }

        public static float GetDefaultCooldown(int towerType){
            switch (towerType) {
                case CANNON:
                    return 120;
                case ARCHER:
                    return 25;
                case WIZARD:
                    return 40;          
            }
            return 0;
        }
    }
    
    public static class Direction {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;

    }

    public static class Enemies {
        public static final int EXTRATER = 0;
        public static final int PIEUVRE = 1;
        public static final int ROB = 2;
        public static final int REDROB = 3;

        public static int GetReward(int enemyType) {
            switch (enemyType) {
                case EXTRATER:
                    return 15;
                case PIEUVRE:
                    return 5;
                case ROB:
                    return 25;
                case REDROB:
                    return 10;
            }
            return 0;
        }

        public static float GetSpeed(int enemyType){
            switch (enemyType) {
                case EXTRATER:
                    return 0.5f;
                case PIEUVRE:
                    return 0.65f;
                case ROB:
                    return 0.3f;
                case REDROB:
                    return 0.75f;
            }
            return 0;
        }

        public static int GetStartHealth(int enemyType){
            switch (enemyType) {
                case EXTRATER:
                    return 100;
                case PIEUVRE:
                    return 60;
                case ROB:
                    return 250;
                case REDROB:
                    return 85;
            }
            return 0;
        }
    }

    public static class Tiles {
        public static final int WATER_TILE=0;
        public static final int GRASS_TILE=1;
        public static final int ROAD_TILE=2;
    
    }

}
