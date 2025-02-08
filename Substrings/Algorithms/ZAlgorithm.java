package Substrings;
/**
 * Author: Hymeis
 * Date Created: Feb. 5, 2025
 * Program description: z-algorithm is the base idea of KMP and BM, where
 * you can utilize the array and find the substring index in linear or even
 * sublinear time
 */
public class ZAlgorithm {
    
    /**
     * The idea of z array is as follows:
     * z[0] = 0
     * z[i] = the length of the substring matching the same substring starting
     * from the beginning
     * 
     * Ex. 
     * s = A B A A B A A A B
     * z = 0 0 1 4 0 1 1 2 0
     * 
     * For example, why z[3] = 4 :
     * s[0~3] = ABAA
     * s[3~6] = ABAA
     * and
     * s[4] = 'B' != 'A' = s[7]
     * Hence z[3] is the longest matching substring w.r.t. the beginning
     */
    public ZAlgorithm() {

    }

    /**
     * Brute force of building the z array
     * Time Complexity: O(n^2)
     * @param s
     * @return z-array
     */
    public int[] slowZArray(String s) {
        int n = s.length();
        int[] z = new int[n];        
        for (int i = 0; i < n; i++) {
            while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i+z[i])) {
                z[i]++;
            }
        }
        return z;
    }

    /**
     * Time Complexity: O(n)
     * Source: https://cp-algorithms.com/string/z-function.html
     * @param s
     * @return z-array
     */
    public static int[] zArray(String s) {
        int n = s.length();
        int[] z = new int[n];
        int left = 1;
        int right = 1;
        for (int i = 1; i < n; i++) {
            // i is within the sliding window: pre-computing can be done
            if (i < right) {
                /** Technically z[i]=z[i-left], unless our i is overly on the right side 
                 * (i.e. we cannot infer what's outside the boundary)
                 * This is why we take the minimum of z[i-left] and right-i: access values
                 * inside the sliding window
                 * */ 
                z[i] = Math.min(right-i, z[i-left]);
            }
            // Brute force, the same thing as the function above
            while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])) {
                z[i]++;
            }
            /** Either the value we accessed in the brute force is outide the boundary, or 
             * we've reached outside of the boundary by simply incrementing i (e.g. z[i] = 0)
             * So we want to update the sliding window.
             * Update both left and right
             */ 
            if (i + z[i] > right) {
                left = i;
                right = i + z[i];
            }
        }
        return z;
    } 
}