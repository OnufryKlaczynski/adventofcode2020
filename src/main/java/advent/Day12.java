package advent;

import java.util.List;

public class Day12 {

    public static void main(String[] args) {
        List<String> input12 = Utils.readFile("input12");
//        partOne(input12);
        long pionowoStatek = 0;
        long poziomoStatek = 0;

        long pionowoWaypoint = 1;
        long poziomoWaypoint = -10;

        Directions directions = Directions.valueOf("E");
        for (var s : input12) {
            char command = s.charAt(0);
            int value = Integer.parseInt(s.substring(1));


            switch (command) {
                case 'N':
                    pionowoWaypoint += value;
                    break;
                case 'S':
                    pionowoWaypoint -= value;
                    break;
                case 'E':
                    poziomoWaypoint -= value;
                    break;
                case 'W':
                    poziomoWaypoint += value;
                    break;
                case 'L':
                    int ordinal = directions.ordinal();
                    int howMuch = (value / 90);
                    howMuch =  (value / 90) % 4;
                    if ( howMuch == 3) {
                        long temp = poziomoWaypoint;
                        poziomoWaypoint = -pionowoWaypoint;
                        pionowoWaypoint = temp;
                    }
                    if ( howMuch == 2) {
                        poziomoWaypoint = -poziomoWaypoint;
                        pionowoWaypoint = -pionowoWaypoint;
                    }
                    if ( howMuch == 1) {
                        long temp = poziomoWaypoint;
                        poziomoWaypoint = pionowoWaypoint;
                        pionowoWaypoint = -temp;
                    }
                    break;
                case 'R':
                    ordinal = directions.ordinal();
                    howMuch =  (value / 90) % 4;
                    if ( howMuch == 1) {
                        long temp = poziomoWaypoint;
                        poziomoWaypoint = -pionowoWaypoint;
                        pionowoWaypoint = temp;
                    }
                    if ( howMuch == 2) {
                        poziomoWaypoint = -poziomoWaypoint;
                        pionowoWaypoint = -pionowoWaypoint;
                    }
                    if ( howMuch == 3) {
                        long temp = poziomoWaypoint;
                        poziomoWaypoint = pionowoWaypoint;
                        pionowoWaypoint = -temp;
                    }
                    break;
                case 'F':
                    pionowoStatek += value * pionowoWaypoint;
                    poziomoStatek += value * poziomoWaypoint;
                    break;

            }
        }
        System.out.println(Math.abs(pionowoStatek) + Math.abs(poziomoStatek));
    }

    private static void partOne(List<String> input12) {
        long pionowo = 0;
        long poziomo = 0;
        Directions directions = Directions.valueOf("E");
        for (var s : input12) {
            char command = s.charAt(0);
            int value = Integer.parseInt(s.substring(1));


            switch (command) {
                case 'N':
                    pionowo= pionowo + value;
                    break;
                case 'S':
                    pionowo= pionowo - value;
                    break;
                case 'E':
                    poziomo -= value;
                    break;
                case 'W':
                    poziomo += value;
                    break;
                case 'L':
                    int ordinal = directions.ordinal();
                    int howMuch = (value / 90);
                    int i = (ordinal - howMuch + 4) % 4;
                    directions = Directions.values()[i];

                    break;
                case 'R':
                    ordinal = directions.ordinal();
                    howMuch =  (value / 90) ;
                    i = (ordinal + howMuch + 4) % 4;
                    directions = Directions.values()[i];
                    break;
                case 'F':
                    pionowo += directions.pionowo * value;
                    poziomo += directions.poziomo * value;
                    break;

            }
        }
        System.out.println(Math.abs(pionowo) + Math.abs(poziomo));
    }

    public static enum Directions {

        N(1, 0),
        E(0, -1),
        S(-1, 0),
        W(0, 1);

        int pionowo;
        int poziomo;

        private Directions(int pionowo, int poziomo) {
            this.pionowo = pionowo;
            this.poziomo = poziomo;
        }
    }
}
