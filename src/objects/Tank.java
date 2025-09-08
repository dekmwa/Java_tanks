package objects;

public class Tank {
    private String name;
    private int coordinateX;
    private int coordinateY;
    private Direction direction;
    private int health;
    private int ammo;
    private boolean shot = false;

    public Tank(String name, int coordinateX, int coordinateY, Direction direction, int health, int ammo) {
        this.name = name;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.direction = direction;
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
        switch (direction) {
            case UP -> coordinateY--;
            case DOWN -> coordinateY++;
            case LEFT -> coordinateX--;
            case RIGHT -> coordinateX++;
        }
    }

    public void back() {
        switch (direction) {
            case UP -> coordinateY++;
            case DOWN -> coordinateY--;
            case LEFT -> coordinateX++;
            case RIGHT -> coordinateX--;
        }
    }

    public void turnRight() {
        switch (direction) {
            case UP -> direction = Direction.RIGHT;
            case DOWN -> direction = Direction.LEFT;
            case LEFT -> direction = Direction.UP;
            case RIGHT -> direction = Direction.DOWN;
        }
    }

    public void turnLeft() {
        switch (direction) {
            case UP -> direction = Direction.LEFT;
            case DOWN -> direction = Direction.RIGHT;
            case LEFT -> direction = Direction.DOWN;
            case RIGHT -> direction = Direction.UP;
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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
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
                ", position=" + direction +
                ", health=" + health +
                ", ammo=" + ammo +
                '}';
    }
}
