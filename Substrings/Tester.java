package Substrings;

/**
 * Author: Hymeis
 * Date Created: Feb. 5, 2025
 * Program description: The driver for testing the algorithms and
 * functions
 */
public class Tester {

    public static void main(String[] args) {
        Util utilTester = new Util();
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
            int expectedIndex = longer.indexOf(shorter);
            int actualIndex = utilTester.indexOf(longer, shorter);
            boolean testPassed = (expectedIndex == actualIndex);
            System.out.printf(
                "Longer: \"%s\" | Shorter: \"%s\" \n\tExpected: %d | Actual: %d \n\tTest Passed: %b%n",
                longer, shorter, expectedIndex, actualIndex, testPassed
            );
        }
        System.out.println("=================================");
    }
}
