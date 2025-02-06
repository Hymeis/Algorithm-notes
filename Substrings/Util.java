package Substrings;

public class Util {
    private ZAlgorithm zAlg = new ZAlgorithm();

    
    /**
     * Return the substring (shorter) index if it exists in longer. Return -1 is shorter is
     * not a substring of longer
     * @param longer The longer string
     * @param shorter The shorter string
     * @return The index of shorter within longer
     */
    public int indexOf(String longer, String shorter) {
        String temp = shorter + '#' + longer;
        int[] z = zAlg.zArray(temp);
        for (int i = shorter.length() + 1; i < z.length; i++) {
            if (z[i] == shorter.length()) return i - 1 - shorter.length();
        }
        return -1;
    }
}
