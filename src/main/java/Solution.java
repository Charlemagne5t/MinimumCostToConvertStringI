import java.util.Arrays;

class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        int[][] dp = new int[26][26];

        for (int i = 0; i < 26; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2 - 2);
            dp[i][i] = 0;
        }
        for (int i = 0; i < original.length; i++) {
            int from = original[i] - 'a';
            int to = changed[i] - 'a';
            dp[from][to] = Math.min(dp[from][to], cost[i]);
        }
        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }
        long res = 0;

        for (int i = 0; i < source.length(); i++) {
            int from = source.charAt(i) - 'a';
            int to = target.charAt(i) - 'a';
            if (dp[from][to] != Integer.MAX_VALUE / 2 - 2) {
                res += dp[from][to];
            } else return -1;
        }

        return res;
    }
}