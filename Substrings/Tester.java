package Substrings;

public class Tester {

    /**
     * Return the substring (shorter) index if it exists in longer. Return -1 is shorter is
     * not a substring of longer
     * @param longer The longer string
     * @param shorter The shorter string
     * @return The index of shorter within longer
     */

    public static void main(String[] args) {
        Util utilTester = new Util();

        // A set of test cases: each array has [longer, shorter].
        String[][] testCases = {
            {"Hymeis", "ei"},
            {"Hello World", "World"},
            {"abc", "d"},
            {"abcd", "bc"},
            {"aaaaa", "aaa"},
        };

        System.out.println("===== Substring Index Tests =====");
        for (String[] pair : testCases) {
            String longer = pair[0];
            String shorter = pair[1];

            // Expected result using the built-in indexOf
            int expectedIndex = longer.indexOf(shorter);

            // Actual result using your custom indexOf
            int actualIndex = utilTester.indexOf(longer, shorter);

            // Compare the two results
            boolean testPassed = (expectedIndex == actualIndex);

            // Print results: True if they match, False otherwise
            System.out.printf(
                "Longer: \"%s\" | Shorter: \"%s\" |\n\t Expected: %d | Actual: %d | Test Passed: %b%n",
                longer, shorter, expectedIndex, actualIndex, testPassed
            );
        }
        System.out.println("=================================");
    }
}
