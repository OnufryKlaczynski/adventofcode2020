package advent;

import java.util.List;
import java.util.stream.Collectors;

public class Day09 {

    public static void main(String[] args) {
        int invalidNumber;
        int preqmbule = 25;
        List<Long> input9 = Utils.readFile("input9").stream().map(Long::parseLong).collect(Collectors.toList());
        Long number = findNumber(preqmbule, input9);
        for (int i = 0; i < input9.size(); i++) {
            long currentSum = 0;
            for (int j = i; j < input9.size(); j++) {
                if (input9.get(j) == number) {
                    break;
                }
                currentSum = currentSum + input9.get(j);
                if (currentSum == number) {
                    List<Long> longs = input9.subList(i, j);
                    List<Long> collect = longs.stream().sorted().collect(Collectors.toList());
                    System.out.println(collect.get(0) + collect.get(collect.size()-1));

                }
                if (currentSum > number) {
                    break;
                }
            }
        }
    }

    private static Long findNumber(int preqmbule, List<Long> input9) {
        for (int i = preqmbule; i < input9.size(); i++) {
            boolean sumOfTwo = isSumOfTwo(input9.subList(i- preqmbule, i), input9.get(i));
            if (!sumOfTwo) {
                return input9.get(i);
            }
        }
        return null;
    }

    public static boolean isSumOfTwo(List<Long> integers, long sumToFind){
        for (int i = 0; i <integers.size(); i++) {
            for (int j = 0; j <integers.size(); j++) {
                if (i == j){continue;}
                if ( (long)integers.get(i) + (long)integers.get(j) == sumToFind) {
                    return true;
                }
            }
        }
        return false;
    }
}
