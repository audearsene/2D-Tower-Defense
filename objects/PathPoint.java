package objects;

public class PathPoint {

    private int xCoord, yCoord;

    public PathPoint(int xCoord, int yCoord){
        this.xCoord=xCoord;
        this.yCoord=yCoord;
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

}
