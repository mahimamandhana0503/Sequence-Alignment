import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Input {

    private String base1;
    private String base2;
    private ArrayList<Integer> sequence1;
    private ArrayList<Integer> sequence2;

    public Input() {
    }

    public Input(String base1, String base2, ArrayList<Integer> sequence1, ArrayList<Integer> sequence2) {
        this.base1 = base1;
        this.base2 = base2;
        this.sequence1 = sequence1;
        this.sequence2 = sequence2;
    }

    public String getBase1() {
        return base1;
    }

    public void setBase1(String base1) {
        this.base1 = base1;
    }

    public String getBase2() {
        return this.base2;
    }

    public void setBase2(String base2) {
        this.base2 = base2;
    }

    public ArrayList<Integer> getSequence1() {
        return sequence1;
    }

    public void setSequence1(ArrayList<Integer> sequence1) {
        this.sequence1 = sequence1;
    }

    public ArrayList<Integer> getSequence2() {
        return sequence2;
    }

    public void setSequence2(ArrayList<Integer> sequence2) {
        this.sequence2 = sequence2;
    }

    //reads input from file and generates an inputString 
    Input readInput(String fileName) throws IOException {
        String s;
        sequence1 = new ArrayList<>();
        sequence2 = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));

        base1 = br.readLine();
        while ((s = br.readLine()) != null) {
            if (s.matches("[0-9]+")) {
                sequence1.add(Integer.parseInt(s));
            } else {
                base2 = s;
                break;
            }
        }

        while ((s = br.readLine()) != null) {
            if (s.matches("[0-9]+")) {
                sequence2.add(Integer.parseInt(s));
            } else {
                break;
            }
        }

        br.close();
        return new Input(base1, base2, sequence1, sequence2);
    }
}