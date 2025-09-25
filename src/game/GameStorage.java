package game;

//import gameLogs.RoundLogs;
import objects.Tank;
import objects.Wall;

import java.util.ArrayList;

public class GameStorage {
    private ArrayList<Tank> tanks;
    private ArrayList<Wall> walls;

    public ArrayList<Tank> getTanks() { return tanks; }
    public void setTanks(ArrayList<Tank> tanks) {
        this.tanks = tanks;
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }
    public void setWalls(ArrayList<Wall> walls) {
        this.walls = walls;
    }
}
