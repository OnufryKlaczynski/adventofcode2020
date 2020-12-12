package advent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day11 {


    public static void main(String[] args) {
        List<char[]> input = Utils.readFile("input11").stream().map(String::toCharArray).collect(Collectors.toList());

        findPointsToChangePartTwo(input);

        while (true) {
            List<Point> pointsToChange = findPointsToChangePartTwo(input);
            if (pointsToChange.isEmpty()) {
                break;
            }
            for (var point : pointsToChange) {
                char c = input.get(point.x)[point.y];
                if (c == '#') {
                    input.get(point.x)[point.y] = 'L';
                } else {
                    input.get(point.x)[point.y] = '#';
                }

            }
        }
        int count = 0;
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length; j++) {
                if (input.get(i)[j] == '#') {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private static List<Point> findPointsToChangePartTwo(List<char[]> input) {
        List<Point> pointsToChange = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length; j++) {
                int foundHash = 0;
                int foundL = 0;
                for (int k = -1; k < 2; k++) {


                    next:
                    for (int l = -1; l < 2; l++) {
                        if (k == 0 && l == 0) {
                            continue;
                        }
                        int copyi = i;
                        int copyj = j;
                        while (true) {
                            copyi += l;
                            copyj += k;
                            if (copyi < 0 || copyi >= input.size() || copyj < 0 || copyj >= input.get(i).length) {
                                break;
                            }
                            if (input.get(copyi)[copyj] == '#') {
                                foundHash += 1;
                                continue next;
                            }
                            if (input.get(copyi)[copyj] == 'L') {
                                foundL += 1;
                                continue next;
                            }
                        }
                    }

                }
                if (input.get(i)[j] == 'L' && foundHash == 0) {
                    pointsToChange.add(new Point(i, j));
                } else if (input.get(i)[j] == '#' && foundHash >= 5) {
                    pointsToChange.add(new Point(i, j));
                }

            }
        }
        return pointsToChange;
    }

    private static List<Point> findPointsToChange(List<char[]> input) {
        List<Point> pointsToChange = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length; j++) {
                if (input.get(i)[j] == 'L') {
                    boolean change = true;
                    find:
                    for (int k = -1; k <= 1; k++) {
                        int ik = i + k;
                        if (ik < 0 || ik >= input.size()) {
                            continue;
                        }
                        for (int l = -1; l <= 1; l++) {
                            int jl = j + l;
                            if (jl < 0 || jl >= input.get(i).length || (jl == j && ik == i)) {
                                continue;
                            }
                            if (input.get(ik)[jl] == '#') {
                                change = false;
                                break find;
                            }
                        }
                    }
                    if (change) {
                        pointsToChange.add(new Point(i, j));
                    }
                }
                if (input.get(i)[j] == '#') {
                    int count = 0;
                    find:
                    for (int k = -1; k <= 1; k++) {
                        int ik = i + k;
                        if (ik < 0 || ik >= input.size()) {
                            continue;
                        }
                        for (int l = -1; l <= 1; l++) {
                            int jl = j + l;
                            if (jl < 0 || jl >= input.get(i).length || (jl == j && ik == i)) {
                                continue;
                            }
                            if (input.get(ik)[jl] == '#') {
                                count += 1;
                                if (count >= 4) {
                                    break find;
                                }
                            }
                        }
                    }
                    if (count >= 4) {

                        pointsToChange.add(new Point(i, j));
                    }
                }


            }
        }
        return pointsToChange;
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }


}

