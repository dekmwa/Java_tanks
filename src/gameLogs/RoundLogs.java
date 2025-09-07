package gameLogs;

import java.util.ArrayList;

public class RoundLogs {
    // запоминать все ходы в каждом раунде для восстановления
    private ArrayList<TankInfo> tanks = new ArrayList<>();

    public ArrayList<TankInfo> getTanks() {
        return tanks;
    }
    public void setTanks(ArrayList<TankInfo> tanks) {
        this.tanks = tanks;
    }
}
