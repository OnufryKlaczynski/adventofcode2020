package advent;

public class Advent2 {

    public static void main(String[] args) {
        long input2 = Utils.readFile("input2").stream().filter(Advent2::isValid).count();
        System.out.println(input2);
//        System.out.println(isValid("2-9 c: ccccccccc"));
    }

    public static boolean isValid(String text) {
        String[] s = text.split(" ");
        String[] frequency = s[0].split("-");
        char character = s[1].replaceFirst(":", "").charAt(0);
        String password = s[2];
        boolean b = password.charAt(Integer.parseInt(frequency[0])-1) == character;
        boolean b1 = password.charAt(Integer.parseInt(frequency[1])-1) == character;
        return b ^ b1;
    }
}
