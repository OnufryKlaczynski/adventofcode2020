package advent;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Day10 {
    static HashMap<Integer, Long> memo = new HashMap<>();

    public static void main(String[] args) {

        long counter = 0;

        List<Integer> input = Utils.readFile("input10").stream().map(Integer::parseInt).sorted().collect(Collectors.toList());
//        partOne(input);
        input.add(0, 0);
        input.add(input.size(), input.get(input.size() - 1) + 3);

        counter = partTwo(input, 0);
        System.out.println(counter);


    }

    private static long partTwo(List<Integer> input, int index) {
        if (input.size() - 1 == index) {
            return 1;
        }

        long toReturn = 0;
        for (int i = 1; i < 4; i++) {
            if (index + i < input.size() && input.get(index + i) - input.get(index) < 4) {
                Long aLong = memo.get(index + i);
                if (aLong != null) {
                    toReturn += aLong;
                } else {
                    long counter = partTwo(input, index + i);
                    toReturn += counter;
                }
            }
        }
        memo.put(index, toReturn);
        return toReturn;
    }


    private static void partOne(List<Integer> input) {
        input = input.stream().sorted().collect(Collectors.toList());
        int oneDiffCount = 0;
        int threeDiffCount = 0;
        int twoDiffCount = 0;
        int startValue = 0;
        for (var value : input) {
            int diff = value - startValue;
            if (diff > 3) {
                break;
            }
            switch (diff) {
                case 1:
                    oneDiffCount += 1;
                    break;
                case 2:
                    twoDiffCount += 1;
                    break;
                case 3:
                    threeDiffCount += 1;
                    break;
            }
            startValue += diff;
        }
//        threeDiffCount++;
        System.out.println(input.size());
        System.out.println(oneDiffCount);
        System.out.println(twoDiffCount);
        System.out.println(threeDiffCount);
    }
}
