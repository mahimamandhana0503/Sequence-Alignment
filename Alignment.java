import java.util.HashMap;

public class Alignment {

    int[][] deltaValues;
    int gapPenalty;
    HashMap<Character, Integer> map;
    private int[][] dp;
    private String a;
    private String b;

    public Alignment(int[][] deltaValues, HashMap<Character, Integer> map, int gapPenalty) {
        this.deltaValues = deltaValues;
        this.gapPenalty = gapPenalty;
        this.map = map;
    }

    public void setString1(String a) {
        this.a = a;
    }

    public void setString2(String b) {
        this.b = b;
    }

    public int getValue() {
        int totalCost = 0;
        int m = a.length();
        int n = b.length();

        dp = new int[m + 1][n + 1];

        // Initial conditions
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i * gapPenalty;
        }

        for (int i = 0; i <= n; i++) {
            dp[0][i] = i * gapPenalty;
        }

        // Calculate min cost of matching
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int indexA = map.get(a.charAt(i - 1));
                int indexB = map.get(b.charAt(j - 1));

                dp[i][j] = min(deltaValues[indexA][indexB] + dp[i - 1][j - 1], gapPenalty + dp[i - 1][j],
                        gapPenalty + dp[i][j - 1]);
            }
        }

        // min cost at dp[m][n]
        totalCost = dp[m][n];

        return totalCost;
    }

    public String[] getSolutionStrings() {
        String alignments[] = new String[2];
        int i = a.length();
        int j = b.length();
        System.out.println(i);
        System.out.println(j);

        StringBuilder sba = new StringBuilder();
        StringBuilder sbb = new StringBuilder();

        while (!(i == 0 || j == 0)) {
            int indexA = map.get(a.charAt(i - 1));
            int indexB = map.get(b.charAt(j - 1));

            if (dp[i][j] == deltaValues[indexA][indexB] + dp[i - 1][j - 1]) {
                sba.append(a.charAt(i - 1));
                sbb.append(b.charAt(j - 1));
                i--;
                j--;
            }

            else if (dp[i][j] == gapPenalty + dp[i][j - 1]) {
                sba.append("_");
                sbb.append(b.charAt(j - 1));
                j--;
            }

            else if (dp[i][j] == gapPenalty + dp[i - 1][j]) {
                sba.append(a.charAt(i - 1));
                sbb.append("_");
                i--;
            }
        }

        int m = sba.length();
        int n = sbb.length();
        while (m <= a.length() + b.length() + 1) {
            if (i > 0)
                sba.append(a.charAt(i - 1));
            else
                sba.append("_");
            i--;
            m++;
        }

        while (n <= a.length() + b.length() + 1) {
            if (j > 0)
                sbb.append(b.charAt(j - 1));
            else
                sbb.append("_");
            j--;
            n++;
        }

        int index = 1;

        for (int v = 0; v < sba.length(); v++) {
            if (sba.charAt(v) == '_' && sbb.charAt(v) == '_') {
                index = v;
                break;
            }
        }

        alignments[0] = new StringBuilder(sba.substring(0, index)).reverse().toString();
        alignments[1] = new StringBuilder(sbb.substring(0, index)).reverse().toString();

        return alignments;

    }

    private int min(int a, int b, int c) {
        if (a < b) {
            if (a < c) {
                return a;
            } else
                return c;

        } else {
            if (b < c) {
                return b;
            } else
                return c;
        }
    }

}
