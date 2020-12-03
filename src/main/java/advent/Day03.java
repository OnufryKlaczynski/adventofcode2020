package advent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Day03 {

    public static void main(String[] args) {
        List<String> input = Utils.readFile("input3");

        ArrayList<Long> counts = new ArrayList<>();
        counts.add(countTrees(input, 1, 1));
        counts.add(countTrees(input, 3, 1));
        counts.add(countTrees(input, 5, 1));
        counts.add(countTrees(input, 7, 1));
        counts.add(countTrees(input, 1, 2));
        Long reduce = counts.stream().reduce(1L, (x, y) -> x * y);
        System.out.println(reduce);

    }

    public static long countTrees(List<String> map, int right, int down) {
        int repeatAfter = map.get(0).length();
        int count = 0;
        for (int i = 0, j = 0; i < map.size(); i+=down, j = (j + right) % repeatAfter) {
            char c = map.get(i).charAt(j);
            if ( c == '#') {
                count += 1;
            }

        }
        return count;
    }


}
