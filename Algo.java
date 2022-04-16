import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Algo {

    // initializations
    // creating hashMap with delta and alpha values
    static int deltaArray[][] = new int[][] { { 0, 110, 48, 94 }, { 110, 0, 118, 48 }, { 48, 118, 0, 110 },
            { 94, 48, 110, 0 } };
    static HashMap<Character, Integer> deltaTable = new HashMap<>();
    static String filePath = "input.txt";

    public static void main(String[] args) throws IOException {

        double beforeUsedMem = getMemoryInKB();
        double startTime = getTimeInMilliseconds();

        Input input = new Input().readInput(filePath);

        // creating hashMap to denote A,C,G,T with values
        deltaTable.put('A', 0);
        deltaTable.put('C', 1);
        deltaTable.put('G', 2);
        deltaTable.put('T', 3);

        // input string generator
        String inputString1 = Algo.generateInput(input.getSequence1(), input.getBase1());
        String inputString2 = Algo.generateInput(input.getSequence2(), input.getBase2());

        Alignment al = new Alignment(deltaArray, deltaTable, 30);
        al.setString1(inputString1);
        al.setString2(inputString2);

        System.out.println(al.getValue());
        String[] solution = al.getSolutionStrings();
        System.out.println(solution[0]);
        System.out.println(solution[1]);

        double afterUsedMem = getMemoryInKB();
        double endTime = getTimeInMilliseconds();
        double totalUsage = afterUsedMem - beforeUsedMem;
        double totalTime = endTime - startTime;

        System.out.println(totalTime);
        System.out.println(totalUsage);
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

    private static double getMemoryInKB() {
        double total = Runtime.getRuntime().totalMemory();
        return (total - Runtime.getRuntime().freeMemory()) / 10e3;
    }

    private static double getTimeInMilliseconds() {
        return System.nanoTime()/10e6;
        }
}