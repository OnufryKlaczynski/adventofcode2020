package advent;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Advent1 {

    public static void main(String[] args) {
        List<String> strings = Utils.readFile("input1");
        List<Integer> collect = strings.stream().map(Integer::parseInt).collect(Collectors.toList());
        for (int i = 0; i < collect.size(); i++) {
            for (int j = 0; j < collect.size(); j++) {
                if ( i == j ){
                    continue;
                }
                for (int k = 0; k < collect.size(); k++) {

                    if ( j == k || i == k) {
                        continue;
                    }
                    if (collect.get(i) + collect.get(j) + collect.get(k)== 2020) {
                        System.out.println(collect.get(i) * collect.get(j) * collect.get(k));
                        return;
                    }
                }
            }
        }
    }


}
