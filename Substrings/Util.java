package Substrings;

/**
 * Author: Hymeis
 * Date Created: Feb. 5, 2025
 * Program description: A set of functions that applies substring algorithms
 */
public class Util {
    private ZAlgorithm zAlg = new ZAlgorithm();


    /**
     * Return the substring (shorter) index if it exists in longer. Return -1 is shorter is
     * not a substring of longer
     * @pre Assume longer and shorter only contains alphabetical letters (must not contain '#')
     * @param longer The longer string
     * @param shorter The shorter string
     * @return The index of shorter within longer
     */
    public int indexOf(String longer, String shorter) {
        // Separate longer and shorter
        String temp = shorter + '#' + longer;
        int[] z = zAlg.zArray(temp);
        for (int i = shorter.length() + 1; i < z.length; i++) {
            if (z[i] == shorter.length()) return i - 1 - shorter.length();
        }
        return -1;
    }
}
