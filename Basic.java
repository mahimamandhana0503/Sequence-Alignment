import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Basic {

    // initializations
    // creating hashMap with delta and alpha values
    static int deltaArray[][] = new int[][] { { 0, 110, 48, 94 }, { 110, 0, 118, 48 }, { 48, 118, 0, 110 },
            { 94, 48, 110, 0 } };
    static HashMap<Character, Integer> deltaTable = new HashMap<>();

    public static void main(String[] args) throws IOException {

        // clearing or creating the output file
        clearOrCreateOutputFile(args[1]);

        double beforeUsedMem = getMemoryInKB();
        double startTime = getTimeInMilliseconds();

        Input input = new Input().readInput(args[0]);

        // creating hashMap to denote A,C,G,T with values
        deltaTable.put('A', 0);
        deltaTable.put('C', 1);
        deltaTable.put('G', 2);
        deltaTable.put('T', 3);

        // input string generator
        String inputString1 = Basic.generateInput(input.getSequence1(), input.getBase1());
        String inputString2 = Basic.generateInput(input.getSequence2(), input.getBase2());

        Alignment al = new Alignment(deltaArray, deltaTable, 30);
        al.setString1(inputString1);
        al.setString2(inputString2);

        int value = al.getValue();
        String[] solution = al.getSolutionStrings();

        double afterUsedMem = getMemoryInKB();
        double endTime = getTimeInMilliseconds();
        double totalUsage = afterUsedMem - beforeUsedMem;
        double totalTime = endTime - startTime;

        String output = Basic.toString(solution, totalUsage, totalTime, value);

        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(args[1])));
        writer.write(output);
        writer.close();
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
        return System.nanoTime() / 10e6;
    }

    private static String toString(String[] solution, Double totalUsage, Double totalTime, int value) {
        return value + "\n" + solution[0] + "\n" + solution[1] + "\n" + totalTime + "\n" + totalUsage;
    }

    private static void clearOrCreateOutputFile(String outputFile) throws IOException {
        if (outputFile == null) {
            File output = new File(outputFile);
            output.createNewFile();
        } else {
            BufferedWriter br = new BufferedWriter(new FileWriter(new File(outputFile)));
            br.write("");
            br.close();
        }

    }

}