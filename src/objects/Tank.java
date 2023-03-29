package objects;

public class Tank {
    private String name;
    private int coordinateX;
    private int coordinateY;
    private Direction position;
    private int health;
    private int ammo;
    private boolean shot = false;

    public Tank(String name, int coordinateX, int coordinateY, Direction position, int health, int ammo) {
        this.name = name;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.position = position;
        this.health = health;
        this.ammo = ammo;
    }

    public void shot() {
        if (ammo > 0) {
            shot = true;
            ammo--;
        }
    }

    public void forward() {
        switch (position) {
            case FORWARD -> coordinateY--;
            case BACK -> coordinateY++;
            case LEFT -> coordinateX--;
            case RIGHT -> coordinateX++;
        }
    }

    public void back() {
        switch (position) {
            case FORWARD -> coordinateY++;
            case BACK -> coordinateY--;
            case LEFT -> coordinateX++;
            case RIGHT -> coordinateX--;
        }
    }

    public void turnRight() {
        switch (position) {
            case FORWARD -> position = Direction.RIGHT;
            case BACK -> position = Direction.LEFT;
            case LEFT -> position = Direction.FORWARD;
            case RIGHT -> position = Direction.BACK;
        }
    }

    public void turnLeft() {
        switch (position) {
            case FORWARD -> position = Direction.LEFT;
            case BACK -> position = Direction.RIGHT;
            case LEFT -> position = Direction.BACK;
            case RIGHT -> position = Direction.FORWARD;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public Direction getPosition() {
        return position;
    }

    public void setPosition(Direction position) {
        this.position = position;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public boolean isShot() {
        return shot;
    }

    public void setShot(boolean shot) {
        this.shot = shot;
    }

    @Override
    public String toString() {
        return "objects.Tank{" +
                "name='" + name + '\'' +
                ", coordinateX=" + coordinateX +
                ", coordinateY=" + coordinateY +
                ", position=" + position +
                ", health=" + health +
                ", ammo=" + ammo +
                '}';
    }
}
