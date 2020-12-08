package advent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day08 {

    public static void main(String[] args) {
        List<String> input8 = Utils.readFile("input8");
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < input8.size(); i++) {
            String input = input8.get(i);
            String[] split = input.split(" ");
            String operation = split[0];
            int value = Integer.parseInt(split[1]);
            if (operation.equals("jmp") && value < 0) {
                indexes.add(i);
            }
            else if (operation.equals("nop") && value > 0) {
                indexes.add(i);
            }
        }
        for (int i = 0; i < indexes.size(); i++) {

             getAcc(input8, indexes.get(i));

        }

    }

    private static boolean getAcc(List<String> input8, int indexToSwap) {
        Set<Integer> visited = new HashSet<>();
        long acc = 0;
        for (int i = 0; i < input8.size(); ) {
            if (visited.contains(i)) {
                return false;
            }
            String s = input8.get(i);
            visited.add(i);
            String[] split = s.split(" ");
            String operation = split[0];

            int value = Integer.parseInt(split[1]);
            if ( i == indexToSwap) {
                operation = operation.equals("nop") ? "jmp" : "nop";
            }
            var skipIncrement = switch (operation) {
                case "nop":
                    yield false;
                case "acc":
                    acc += value;
                    yield false;
                case "jmp":
                    i += value;
                    yield true;
                default:
                    yield false;
            };
            if (skipIncrement) {
                continue;
            }
            i++;
        }
        System.out.println(acc);
        return true;
    }
}
