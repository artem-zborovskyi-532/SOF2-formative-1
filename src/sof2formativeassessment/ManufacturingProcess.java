package sof2formativeassessment;

public class ManufacturingProcess{
    private static final int OPERATION_COST = 1;

    public static int minCost(String[] pA, String[] pB) {
        return helper(pA, pB, pA.length, pB.length);
    }

    private static int helper(String[] pA, String[] pB, int i, int j) {
        if (i == 0) return j;
        if (j == 0) return i;

        if (pA[i - 1].equals(pB[j - 1])) {
            return helper(pA, pB, i - 1, j - 1);
        }

        int insert = helper(pA, pB, i, j - 1);
        int delete = helper(pA, pB, i - 1, j);
        int substitute = helper(pA, pB, i - 1, j - 1);

        return OPERATION_COST + Math.min(insert, Math.min(delete, substitute));
    }

    public static int minCost(String[] pA, String[] pB, int sub, int del, int ins) {
        return helper(pA, pB, pA.length, pB.length, sub, del, ins);
    }

    private static int helper(String[] pA, String[] pB, int i, int j, int sub, int del, int ins) {
        if (i == 0) return j * ins;
        if (j == 0) return i * del;

        if (pA[i - 1].equals(pB[j - 1])) {
            return helper(pA, pB, i - 1, j - 1, sub, del, ins);
        }

        int insert = ins + helper(pA, pB, i, j - 1, sub, del, ins);
        int delete = del + helper(pA, pB, i - 1, j, sub, del, ins);
        int substitute = sub + helper(pA, pB, i - 1, j - 1, sub, del, ins);

        return Math.min(insert, Math.min(delete, substitute));
    }
}