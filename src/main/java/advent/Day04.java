package advent;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Day04 {

    public static void main(String[] args) {
        List<String> input = Utils.readFile("input4");
        long i = parsePasswords(input);
        System.out.println(i);
    }

    public static long parsePasswords(List<String> strings) {
        List<String> requiredFields = List.of(
                "byr",
                "iyr",
                "eyr",
                "hgt",
                "hcl",
                "ecl",
                "pid"
        );
        List<List<String>> passwords = new ArrayList<>();
        List<String> s = new ArrayList<>();
        for (String line : strings) {
            if (line.isBlank()) {
                passwords.add(s);
                s = new ArrayList<>();
                continue;
            }
            String[] split = line.split(" ");
            s.addAll(Arrays.asList(split));
        }

        var collect = passwords.stream().map(s1 -> {

            HashMap<String, String> password = new HashMap<>();
            for (String arr : s1) {
                String[] split1 = arr.split(":");
                password.put(split1[0], split1[1]);
            }
            return password;

        }).filter(x -> x.keySet().containsAll(requiredFields)).filter(z -> {
            boolean valid = true;
            for (var key : z.keySet()) {
                String value = z.get(key);
                boolean valueValid = isValid(key, value);
                valid = valid && valueValid;
            }
            return valid;
        }).collect(Collectors.toList());


        return collect.size();
    }

    public static boolean isValid(String key, String value) {
        return switch (key) {
            case "byr" -> {
                int i = Integer.parseInt(value);
                yield i >= 1920 && i <= 2002;
            }
            case "iyr" -> {
                int i = Integer.parseInt(value);
                yield i >= 2010 && i <= 2020;
            }
            case "eyr" -> {
                int i = Integer.parseInt(value);
                yield i >= 2020 && i <= 2030;
            }
            case "hgt" -> {
                Matcher matcher = Pattern.compile("\\d+|\\D+").matcher(value);
                ArrayList<String> strings1 = new ArrayList<>();
                while (matcher.find()) {
                    strings1.add(matcher.group());
                }
                if (strings1.size() != 2) {
                    yield false;
                }
                String unit = strings1.get(1);
                int i = Integer.parseInt(strings1.get(0));

                if (unit.equals("cm")) {
                    yield i >= 150 && i < 193;
                } else {
                    yield i >= 59 && i < 76;
                }
            }
            case "hcl" -> {
                Matcher matcher = Pattern.compile("#[0-9a-f]{6}").matcher(value);
                yield matcher.find();
            }
            case "ecl" -> List.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(value);
            case "pid" -> {
                Matcher matcher = Pattern.compile("[0-9]{9}").matcher(value);
                yield matcher.find();
            }
            case "cid" -> true;
            default -> false;
        };
    }

}
