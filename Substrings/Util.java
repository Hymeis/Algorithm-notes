package Substrings;

import Substrings.Algorithms.Manacher;
import Substrings.Algorithms.ZAlgorithm;

/**
 * Author: Hymeis
 * Date Created: Feb. 5, 2025
 * Program description: A set of functions that applies substring algorithms
 */
public class Util {
    private ZAlgorithm zAlg = new ZAlgorithm();
    private Manacher manacher = new Manacher();


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

    /**
     * Get the number of palindromic substrings using Manacher algorithm O(n)
     * @param s The string
     * @return the number of substrings
     */
    public int numPalindromicSubstrings(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append('@');
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            sb.append('#');
        }
        sb.append('$');
        int[] manacherArray = manacher.manacherOddArray(new String(sb));
        int n = manacherArray.length;
        int ans = 0;
        // @ a # a # a # a !
        // 1 1 2 3 4 3 2 1 1
        for (int i = 1; i < n - 1; i++) {
            if (i % 2 == 0) {
                ans += manacherArray[i] / 2;
            }
            else {
                ans += (manacherArray[i] + 1) / 2;
            }
        }
        return ans;
    }
}
