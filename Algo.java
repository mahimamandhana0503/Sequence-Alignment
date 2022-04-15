import java.util.HashMap;

public class Algo {

    public static void main(String[] args) {

        HashMap<String, Integer> deltaTable = new HashMap<>();
        
        deltaTable.put("A",0);
        deltaTable.put("C",1);
        deltaTable.put("G",2);
        deltaTable.put("T",3);
        
        int deltaArray[][] = new int[][] { { 0, 110, 48, 94 }, { 110, 0, 118, 48 }, { 48, 118, 0, 110 },
                { 94, 48, 110, 0 } };
    }
}
