public class ArrayDequeTest {
    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }
    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }
    public static boolean checkRemove(int expected, int actual) {
        if (expected != actual) {
            System.out.println("removeLast() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }
    public static boolean checkGet(int expected, int actual) {
        if (expected != actual) {
            System.out.println("get() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }
    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");
        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> ald1 = new ArrayDeque<>();

        boolean passed = checkEmpty(true, ald1.isEmpty());

        ald1.addFirst(1);

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1, ald1.size()) && passed;
        passed = checkEmpty(false, ald1.isEmpty()) && passed;

        ald1.addLast(2);
        passed = checkSize(2, ald1.size()) && passed;

        ald1.addLast(3);
        passed = checkSize(3, ald1.size()) && passed;

        ald1.addLast(4);
        passed = checkSize(4, ald1.size()) && passed;

        ald1.addLast(5);
        passed = checkSize(5, ald1.size()) && passed;

        ald1.addLast(6);
        passed = checkSize(6, ald1.size()) && passed;

        ald1.addLast(7);
        passed = checkSize(7, ald1.size()) && passed;

        ald1.addLast(8);
        passed = checkSize(8, ald1.size()) && passed;

        System.out.println("Printing out deque: ");
        ald1.printDeque();

        printTestStatus(passed);

    }
    public static void addRemoveTest() {

        System.out.println("Running add/remove test.");

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> ald1 = new ArrayDeque<>();
        // should be empty
        boolean passed = checkEmpty(true, ald1.isEmpty());
        for (int i = 0; i < 9; i++) {
            ald1.addFirst(i);
        }
        // should not be empty
        passed = checkEmpty(false, ald1.isEmpty()) && passed;
        for (int i = 0; i < 9; i++) {
            passed = checkRemove(8 - i,ald1.removeFirst()) && passed;
        }
        // should be empty
        passed = checkEmpty(true, ald1.isEmpty()) && passed;

        printTestStatus(passed);

    }
    public static void addRemoveTest1000() {

        System.out.println("Running add/remove test 1000th.");

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> ald1 = new ArrayDeque<>();
        // should be empty
        boolean passed = checkEmpty(true, ald1.isEmpty());

        for (int i = 0; i < 1000; i++) {
            ald1.addFirst(i+1);
        }
        // should not be empty
        passed = checkEmpty(false, ald1.isEmpty()) && passed;
        for (int i = 0; i < 1000; i++) {
            ald1.removeFirst();
        }

        // should be empty
        passed = checkEmpty(true, ald1.isEmpty()) && passed;

        printTestStatus(passed);

    }

    public static void main(String[] args) {
        addIsEmptySizeTest();
        addRemoveTest();
        addRemoveTest1000();
    }
}
