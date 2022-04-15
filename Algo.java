import java.util.HashMap;

public class Algo {

    public static void main(String[] args) {

        HashMap<String, Integer> deltaTable = new HashMap<>();
        // Delta a=new Delta('A','A');
        // deltaTable.put(a, 20);

        deltaTable.put("A",0);
        deltaTable.put("C",1);
        deltaTable.put("G",2);
        deltaTable.put("T",3);
        

        int deltaArray[][] = new int[][] { { 0, 110, 48, 94 }, { 110, 0, 118, 48 }, { 48, 118, 0, 110 },
                { 94, 48, 110, 0 } };

        System.out.println(deltaArray[deltaTable.get("A")][deltaTable.get("A")]);

    }

}




