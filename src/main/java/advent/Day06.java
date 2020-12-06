package advent;

import java.util.*;
import java.util.stream.Collectors;

public class Day06 {

    public static void main(String[] args) {
        List<String> lines = Utils.readFile("input6");
        ArrayList<ArrayList<Set<Integer>>> entries = new ArrayList<>();


        ArrayList<Set<Integer>> group = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).isBlank()) {
                group.sort(Comparator.comparingInt(Set::size));
                entries.add(group);
                group = new ArrayList<>();
                continue;
            }

            group.add(lines.get(i).chars().boxed().collect(Collectors.toSet()));
            if (lines.size() - 1 == i) {
                group.sort(Comparator.comparingInt(Set::size));
                entries.add(group);
                group = new ArrayList<>();
            }
        }

//        Optional<Long> reduce = entries.stream().map(arr -> arr.stream().distinct().count()).reduce(Long::sum);
//        System.out.println(reduce.get());

        int sum = 0;
        for (int i = 0; i < entries.size(); i++) {
            boolean b = true;
            Set<Integer> integersA = entries.get(i).get(0);
            for (int j = 1; j < entries.get(i).size(); j++) {
                Set<Integer> integersB = entries.get(i).get(j);

                integersA.retainAll(integersB);

                b = b && integersB.containsAll(integersA);
            }
            if (b) {
                sum += integersA.size();
            }
        }
        System.out.println(sum);
    }
}
