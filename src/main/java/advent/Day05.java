package advent;

import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.lang.Math.*;

public class Day05 {

    public static void main(String[] args) {
        var input5 = Utils.readFile("input5").stream().map(Day05::getId).sorted().collect(Collectors.toList());
        for (int i = 1; i < input5.size() - 1; i++) {
            if (!(input5.get(i).equals(input5.get(i - 1) +1) && input5.get(i).equals(input5.get(i + 1) -1 ))) {
                System.out.println(input5.get(i));
            }
        }
    }

    public static int getId(String seat) {
        int row = (128 / 2) - 1;
        int column = (8 / 2) - 1;
        double rowi = 128 / 2.0f;
        double columni = 8 / 2.0f;

        for (char ch : seat.toCharArray()) {
            switch (ch) {
                case 'B':
                    row = row + (int) ceil(rowi / 2.0f);
                    rowi = rowi / 2.0f;
                    break;
                case 'F':
                    row = row - (int) floor(rowi / 2.0f);
                    rowi = rowi / 2.0f;
                    break;
                case 'R':
                    column = column + (int) ceil(columni / 2.0f);
                    columni = columni / 2.0f;
                    break;
                case 'L':
                    column = column - (int) floor(columni / 2.0f);
                    columni = columni / 2.0f;
                    break;
            }
        }

        return (row) * 8 + (column);
    }

}


