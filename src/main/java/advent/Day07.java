package advent;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day07 {

    private static final Pattern pattern = Pattern.compile("(\\d+.|^)(\\w+.\\w+)");
    private static int count = 0;
    public static final String SERACH_FOR = "shiny gold";

    public static void main(String[] args) {
        List<String> lines = Utils.readFile("input7");
        Map<String, Map<String, Integer>> stringArrayList = new HashMap<>();
        lines.stream()
                .forEach(p -> {
                    Matcher matcher = pattern.matcher(p);
                    Map<String, Integer> stringIntegerHashMap = new HashMap<>();
                    String key = "";
                    while (matcher.find()) {
                        if (matcher.group(1) != null && !matcher.group(1).isBlank()) {

                            stringIntegerHashMap.put(matcher.group(2), Integer.parseInt(matcher.group(1).trim()));
                        } else {
                            key = matcher.group(2);
                        }
                    }
                    stringArrayList.put(key, stringIntegerHashMap);
                });
        System.out.println();
//        for (ArrayList<Map<String, Integer>> c : collect) {
//            ArrayList<Map<String, Integer>> objects = new ArrayList<>(c.subList(1, c.size()));
//            for (Map<String, Integer> cs : c) {
//                stringListHashMap.put(cs.keySet().stream().findFirst().get(), objects);
//            }
//        }
//        findAll(stringArrayList);
        //extracted(stringArrayList, SERACH_FOR);
        count += countHowMany(stringArrayList.get(SERACH_FOR), stringArrayList, 0);
        System.out.println(count);

    }

    private static  int countHowMany(Map<String, Integer> stringIntegerMap, Map<String, Map<String, Integer>> stringArrayList, int xD) {
        int co = 0;
        for (var key : stringIntegerMap.keySet()) {
            Integer orDefault = stringIntegerMap.get(key);
            Map<String, Integer> stringIntegerMap1 = stringArrayList.get(key);
            co  += orDefault + orDefault * countHowMany(stringIntegerMap1, stringArrayList, orDefault);
        }
        return co;
    }

    private static void findAll(Map<String, Map<String, Integer>> stringListHashMap) {
        for (var key : stringListHashMap.keySet()) {
            boolean extracted = extracted(stringListHashMap, key);
            if (extracted) {
                count++;
            }

        }
    }

    private static boolean extracted(Map<String, Map<String, Integer>> stringListHashMap, String key) {
        var optional = Optional.ofNullable(stringListHashMap.get(key));
        if (optional.filter(x -> x.containsKey(SERACH_FOR)).isPresent()) {
            return true;
        }

        if (optional.isPresent()) {
            for (var k : optional.get().keySet()) {
                boolean extracted = extracted(stringListHashMap, k);
                if (extracted) {
                    stringListHashMap.get(k);
                    return true;
                }
            }

        }
        return false;
    }
}
