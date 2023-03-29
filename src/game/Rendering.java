package game;

import objects.Direction;

public class Rendering {
    public void makeFrame(Direction position, GameStorage storage) {
        //линия
        for (int i = 0; i < 15; i++) {
            System.out.print("  *");
        }
        System.out.println();

        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                boolean skip = false;
                for (int n = 0; n < storage.getWalls().size(); n++) {
                    if (j == storage.getWalls().get(n).getCoordinateX()
                            && i == storage.getWalls().get(n).getCoordinateY()) {
                        System.out.print("  -");
                        skip = true;
                    }
                }
                if (skip) {
                    continue;
                }
                for (int n = 0; n < storage.getTanks().size(); n++) {
                    if (j == storage.getTanks().get(n).getCoordinateX()
                            && i == storage.getTanks().get(n).getCoordinateY()) {
                        switch (storage.getTanks().get(n).getPosition()) {
                            case FORWARD -> System.out.print("  ⍐");
                            case BACK -> System.out.print("  ⍗");
                            case LEFT -> System.out.print("  ⍇");
                            case RIGHT -> System.out.print("  ⍈");
                        }
                        skip = true;
                    }
                }
                if (skip) {
                    continue;
                }
                System.out.print("   ");
            }
            System.out.println();
        }
    }
}