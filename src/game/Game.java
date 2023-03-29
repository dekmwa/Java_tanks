package game;

import utils.FileParsing;
import exception.InvalidUniqueCoordinatesExeption;
import exception.WrongCoordinatesExeption;
import objects.Direction;
import objects.Tank;
import objects.Wall;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private FileParsing fileParsing = new FileParsing();
    private boolean isGameAlive = true;
    private GameStorage storage = new GameStorage();

    private void isValidCoordinate(int[][] array) throws InvalidUniqueCoordinatesExeption, WrongCoordinatesExeption {
        for (int i = 0; i < array.length; i++) {
            if (array[i][0] > 10 || array[i][0] < 1 || array[i][1] > 10 || array[i][1] < 1) {
                throw new WrongCoordinatesExeption("координаты превышают допустимые значения");
            }
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (i != j && array[i][0] == array[j][0] && array[i][1] == array[j][1]) {
                    throw new InvalidUniqueCoordinatesExeption("координаты не уникальны");
                }
            }
        }
    }

    private boolean initializingMap(String filePath) throws InvalidUniqueCoordinatesExeption, WrongCoordinatesExeption {
        ArrayList<Wall> walls = new ArrayList<>();

        int[][] wallsCoordinateMassive = fileParsing.readFile(filePath);
        if (wallsCoordinateMassive == null) {
            return false;
        }
        isValidCoordinate(wallsCoordinateMassive);

        for (int i = 0; i < wallsCoordinateMassive.length; i++) {
            String name = "wall" + i;
            walls.add(new Wall(name, wallsCoordinateMassive[i][0], wallsCoordinateMassive[i][1]));
        }
        storage.setWalls(walls);
        return true;
    }

    private boolean initializingTanks(String filePath) throws InvalidUniqueCoordinatesExeption, WrongCoordinatesExeption {
        ArrayList<Tank> tanks = new ArrayList<>();

        int[][] tanksInfoMassive = fileParsing.readFile(filePath);
        if (tanksInfoMassive == null) {
            return false;
        }
        isValidCoordinate(tanksInfoMassive);

        for (int i = 0; i < tanksInfoMassive.length; i++) {
            String name = "tank" + i;
            tanks.add(new Tank(name, tanksInfoMassive[i][0], tanksInfoMassive[i][1],
                    Direction.values()[tanksInfoMassive[i][2]], tanksInfoMassive[i][3], tanksInfoMassive[i][4]));
        }
        storage.setTanks(tanks);
        return true;
    }

    private boolean checkMovement(Direction direction, Tank tank, String driveDirection) {
        int x;
        int y;

        switch (driveDirection) {
            case "forward" -> {
                switch (direction) {
                    case FORWARD -> {
                        x = 0;
                        y = -1;
                    }
                    case BACK -> {
                        x = 0;
                        y = 1;
                    }
                    case LEFT -> {
                        x = -1;
                        y = 0;
                    }
                    case RIGHT -> {
                        x = 1;
                        y = 0;
                    }
                    default -> {
                        x = 0;
                        y = 0;
                    }
                }
            }
            case "back" -> {
                switch (direction) {
                    case FORWARD -> {
                        x = 0;
                        y = 1;
                    }
                    case BACK -> {
                        x = 0;
                        y = -1;
                    }
                    case LEFT -> {
                        x = 1;
                        y = 0;
                    }
                    case RIGHT -> {
                        x = -1;
                        y = 0;
                    }
                    default -> {
                        x = 0;
                        y = 0;
                    }
                }
            }
            default -> {
                x = 0;
                y = 0;
            }
        }

        int tankDirectionX = tank.getCoordinateX() + x;
        int tankDirectionY = tank.getCoordinateY() + y;

        if (tankDirectionX < 1 || tankDirectionX > 10 || tankDirectionY < 1 || tankDirectionY > 10) {
            return false;
        }

        for (int i = 0; i < storage.getWalls().size(); i++) {
            if (tankDirectionX == storage.getWalls().get(i).getCoordinateX()
                    && tankDirectionY == storage.getWalls().get(i).getCoordinateY()) {
                return false;
            }
        }

        for (int i = 0; i < storage.getTanks().size(); i++) {
            if (tankDirectionX == storage.getTanks().get(i).getCoordinateX()
                    && tankDirectionY == storage.getTanks().get(i).getCoordinateY()) {
                return false;
            }
        }
        return true;
    }

    private void checkBullet(int coordinateX, int coordinateY, Direction direction) {

        int bulletX = coordinateX;
        int bulletY = coordinateY;

        int x = 0;
        int y = 0;

        switch (direction) {
            case FORWARD -> {
                x = 0;
                y = -1;
            }
            case BACK -> {
                x = 0;
                y = 1;
            }
            case LEFT -> {
                x = -1;
                y = 0;
            }
            case RIGHT -> {
                x = 1;
                y = 0;
            }
        }

        boolean bulletLive = true;
        while (bulletLive) {
            bulletX += x;
            bulletY += y;

            if (bulletX < 1 || bulletX > 10 || bulletY < 1 || bulletY > 10) {
                bulletLive = false;
            }

            for (int i = 0; i < storage.getWalls().size(); i++) {
                if (bulletX == storage.getWalls().get(i).getCoordinateX()
                        && bulletY == storage.getWalls().get(i).getCoordinateY()) {
                    storage.getWalls().remove(i);
                    bulletLive = false;
                    break;
                }
            }
            if (!bulletLive) {
                break;
            }
            for (int i = 0; i < storage.getTanks().size(); i++) {
                if (bulletX == storage.getTanks().get(i).getCoordinateX()
                        && bulletY == storage.getTanks().get(i).getCoordinateY()) {
                    storage.getTanks().remove(i);
                    bulletLive = false;
                    break;
                }
            }
        }
    }

    public void startGame() {
        Rendering rendering = new Rendering();
        Scanner scanner = new Scanner(System.in);
        String filePath;

        System.out.print("укажите, где находится файл с характеристиками танков: ");
        filePath = scanner.next();

        try {
            while (!initializingTanks("C:\\Users\\bagos\\Desktop\\tanks.txt")) {
                System.out.print("путь к файлу неверный, введите путь к файлу: ");
                filePath = scanner.next();
            }
        } catch (WrongCoordinatesExeption | InvalidUniqueCoordinatesExeption e) {
            isGameAlive = false;
            System.out.println(e.getMessage());
        }

        if (isGameAlive) {
            System.out.print("укажите, где находится файл с расположением объектов на карте: ");
            filePath = scanner.next();

        }
        try {
            while (isGameAlive && !initializingMap("C:\\Users\\bagos\\Desktop\\walls.txt")) {
                System.out.println("путь к файлу неверный, введите путь к файлу: ");
                filePath = scanner.next();
            }
        } catch (WrongCoordinatesExeption | InvalidUniqueCoordinatesExeption e) {
            isGameAlive = false;
            System.out.println(e.getMessage());
        }

        while (isGameAlive) {
            for (int i = 0; i < storage.getTanks().size(); i++) {
                rendering.makeFrame(storage.getTanks().get(i).getPosition(), storage);
                System.out.println();

                System.out.println("игрок танка: " + storage.getTanks().get(i).getName());

                boolean choiceAction = true;
                while (choiceAction) {
                    System.out.println("1. поехать вперед");
                    System.out.println("2. поехать назад");
                    System.out.println("3. повернуться направо");
                    System.out.println("4. повернуться налево");
                    System.out.println("5. выстрелить");
                    System.out.print("выберите ход: ");

                    int userChoice;
                    Scanner choice = new Scanner(System.in);
                    userChoice = choice.nextInt();
                    if (userChoice < 1 || userChoice > 5) {
                        System.out.println("такого хода нет! выберите ход");
                        continue;
                    } else {
                        choiceAction = false;
                    }

                    switch (userChoice) {
                        case 1 -> {
                            if (checkMovement(storage.getTanks().get(i).getPosition(), storage.getTanks().get(i),
                                    "forward")) {
                                storage.getTanks().get(i).forward();
                            } else {
                                System.out.println("танк не смог проехать");
                            }
                        }
                        case 2 -> {
                            if (checkMovement(storage.getTanks().get(i).getPosition(), storage.getTanks().get(i),
                                    "back")) {
                                storage.getTanks().get(i).back();
                            } else {
                                System.out.println("танк не смог проехать!");
                            }
                        }
                        case 3 -> storage.getTanks().get(i).turnRight();
                        case 4 -> storage.getTanks().get(i).turnLeft();
                        case 5 -> storage.getTanks().get(i).shot();
                    }

                    if (storage.getTanks().get(i).isShot()) {
                        storage.getTanks().get(i).setShot(false);
                        checkBullet(storage.getTanks().get(i).getCoordinateX(), storage.getTanks().get(i).getCoordinateY(),
                                storage.getTanks().get(i).getPosition());
                    }

                    if (storage.getTanks().size() == 1) {
                        System.out.println("побеждает игрок танка: " + storage.getTanks().get(0).getName());
                        isGameAlive = false;
                    }
                }
            }
        }
    }
}
