package game;

public class Rendering {
    private String air = "   ";
    private String wall = "  □";
    private String tankForward = "  ⍐";
    private String tankBack = "  ⍗";
    private String tankLeft = "  ⍇";
    private String tankRight = "  ⍈";

    private void printLine(int size, boolean newLine) {
        for (int i = 0; i < size; i++) {
            System.out.print("*");
        }
        if (newLine) {
            System.out.println();
        }
    }

    public void makeFrame(GameStorage storage) {
        System.out.println();
        printLine(10 * 3, true);

        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                boolean skip = false;
                for (int n = 0; n < storage.getWalls().size(); n++) {
                    if (j == storage.getWalls().get(n).getCoordinateX()
                            && i == storage.getWalls().get(n).getCoordinateY()) {
                        System.out.print(wall);
                        skip = true;
                    }
                }
                if (skip) {
                    continue;
                }
                for (int n = 0; n < storage.getTanks().size(); n++) {
                    if (j == storage.getTanks().get(n).getCoordinateX()
                            && i == storage.getTanks().get(n).getCoordinateY()) {
                        switch (storage.getTanks().get(n).getDirection()) {
                            case UP -> System.out.print(tankForward);
                            case DOWN -> System.out.print(tankBack);
                            case LEFT -> System.out.print(tankLeft);
                            case RIGHT -> System.out.print(tankRight);
                        }
                        skip = true;
                    }
                }
                if (skip) {
                    continue;
                }
                System.out.print(air);
            }
            System.out.println();
        }

        printLine(10 * 3, false);
    }
}