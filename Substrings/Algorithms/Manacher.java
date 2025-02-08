package Substrings.Algorithms;

/**
 * Author: Hymeis
 * Date: Feb. 7 2025
 * Program Description: Manacher algorithm is commonly used to compute the
 * number of palindrome substrings inside the given string
 */
public class Manacher {
    
    public Manacher() {

    }

    /**
     * For each index, increment on both sides as well as ans when chars are 
     * equal 
     * 
     * Example:
     * // a a a a
     * // 1 2 2 1
     * 
     * Time Complexity: O(n^2)
     * @param s the string
     * @return the int array containing the length of each index's palindrome
     */
    public int[] slowManacherOddArray(String s) {
        int n = s.length();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            while (true) {
                if (i - ans[i] < 0 && i + ans[i] >= n && 
                s.charAt(i-ans[i]) != s.charAt(i+ans[i])) break;
                ans[i]++;
            }
        }
        return ans;
    }

    /**
     * The idea is:
     * 1. if our index is outside the bound [left, right], we do brute force
     * 2. if outside the bound, we can actually infer our current value by
     * the symmetric property of palindrome (ans[center-index]=ans[center+index]
     * since the RHS of the palindrome is equal to the LHS of the palindrome)
     * 
     * Example:
     *  // @ a # a # a # a !
     *  // 1 1 2 3 4 3 2 1 1
     * 
     * Time Complexity: O(n)
     * @pre The first and last character are boundaries of original s
     * @param s the **bounded** string
     * @return the int array containing the length of each index's palindrome
     */
    public int[] manacherOddArray(String s) {
        int n = s.length();
        int left = 1;
        int right = 1;
        int[] ans = new int[n];
        for (int i = 1; i < n - 1; i++) {
            if (i < right) {
                int radius = left + (right - left) / 2;
                ans[i] = (i-2*radius >= 0 ? ans[i-2*radius] : 0);
            }
            while (s.charAt(i-ans[i]) == s.charAt(i+ans[i])) {
                ans[i]++;
            }
            left = i - ans[i];
            right = i + ans[i];
        }
        return ans;
    }
}
