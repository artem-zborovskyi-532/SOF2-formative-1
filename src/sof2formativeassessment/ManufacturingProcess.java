package sof2formativeassessment;

/**
 * Utility class for computing the minimum cost of transforming one
 * manufacturing process into another using insertion, deletion, and
 * substitution operations.
 *
 * A manufacturing process is represented as an array of strings,
 * where each string is the name of a sub-process. The algorithm uses
 * a recursive approach with optimal substructure to find the minimum
 * cost transformation.
 */

public class ManufacturingProcess{
    private static final int OPERATION_COST = 1;

    /**
     * Returns the minimum cost of transforming manufacturing process PA into
     * manufacturing process PB, assuming all operations have a uniform cost of 1.
     *
     * @param pA the source manufacturing process to transform
     * @param pB the target manufacturing process to transform into
     * @return the minimum number of operations required to transform PA into PB
     */
    public static int minCost(String[] pA, String[] pB) {
        return helper(pA, pB, pA.length, pB.length);
    }

    /**
     * Recursive helper that computes the minimum cost of transforming
     * sub-process PA[1..i] into sub-process PB[1..j] with uniform operation cost.
     *
     * @param pA the source manufacturing process
     * @param pB the target manufacturing process
     * @param i the index defining the end of the current PA sub-process
     * @param j the index defining the end of the current PB sub-process
     * @return the minimum cost of transforming PA[1..i] into PB[1..j]
     */
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

    /**
     * Returns the minimum cost of transforming manufacturing process PA into
     * manufacturing process PB, where each operation type has a different cost.
     *
     * @param pA the source manufacturing process to transform
     * @param pB the target manufacturing process to transform into
     * @param sub the cost of substituting one sub-process for another
     * @param del the cost of deleting a sub-process from PA
     * @param ins the cost of inserting a sub-process into PA
     * @return the minimum cost of transforming PA into PB
     */
    public static int minCost(String[] pA, String[] pB, int sub, int del, int ins) {
        return helper(pA, pB, pA.length, pB.length, sub, del, ins);
    }

    /**
     * Recursive helper that computes the minimum cost of transforming
     * sub-process PA[1..i] into sub-process PB[1..j] with variable operation costs.
     *
     * @param pA the source manufacturing process
     * @param pB the target manufacturing process
     * @param i the index defining the end of the current PA sub-process
     * @param j the index defining the end of the current PB sub-process
     * @param sub the cost of substituting one sub-process for another
     * @param del the cost of deleting a sub-process from PA
     * @param ins the cost of inserting a sub-process into PA
     * @return the minimum cost of transforming PA[1..i] into PB[1..j]
     */
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