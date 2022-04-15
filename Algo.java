import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Algo {

    //initializations
    //creating hashMap with delta and alpha values
    static int deltaArray[][] = new int[][] { { 0, 110, 48, 94 }, { 110, 0, 118, 48 }, { 48, 118, 0, 110 },
                { 94, 48, 110, 0 } };
    static HashMap<Character, Integer> deltaTable = new HashMap<>();
    static String filePath = "input.txt";

    public static void main(String[] args) throws IOException {

        Input input = new Input().readInput(filePath);

        //creating hashMap to denote A,C,G,T with values
        deltaTable.put('A', 0);
        deltaTable.put('C', 1);
        deltaTable.put('G', 2);
        deltaTable.put('T', 3);

        //input string generator
        String inputString1 = Algo.generateInput(input.getSequence1(), input.getBase1());
        String inputString2 = Algo.generateInput(input.getSequence2(), input.getBase2());

    }

    static String generateInput(ArrayList<Integer> seq, String base) {
        for (int i = 0; i < seq.size(); i++) {
            int index = seq.get(i);
            String str1 = base.substring(0, index + 1);
            String str2 = base.substring(index + 1, base.length());
            base = str1 + base + str2;
        }
        return base;
    }
}