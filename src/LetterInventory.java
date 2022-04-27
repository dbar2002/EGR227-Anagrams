public class LetterInventory {

    private static final int ALPHABET_LENGTH = 26;

    private int[] counters;
    int totalCount;

    /**
     * sets the fields in the LetterInventory to empty
     */
    LetterInventory() {
        /* Allocate space for the array */
        counters = new int[ALPHABET_LENGTH];
        /* Iterate over array with ForEach loop to assign 0's */
        for(int i:counters) {
            i = 0;
        }
        totalCount = 0;
    }

    /**
     * sets the fields in the LetterInventory from the data in the String
     */
    LetterInventory(String input) {
        /* Setup array */
        this();
        /* Iterate over the input String to allocate the appropriate counters */
        for(int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            /* Consider only letter characters */
            if (Character.isLetter(c)) {
                c = Character.toLowerCase(c);
                /* convenient way to get indices from character values */
                counters[getIndex(c)]++;
                totalCount++;
            }
        }
    }

    /**
     * accesses the count of a specified character
     */
    public int get(char c) {
        if(!Character.isLetter(c)) {
            throw new IllegalArgumentException("LetterInventory.get() can not accept characters outside of the alphabet.");
        }
        return counters[getIndex(c)];
    }

    /**
     * modifies the count of a specified character.
     */
    public void set(char c, int count) {
        if(!Character.isLetter(c)) {
            throw new IllegalArgumentException("LetterInventory.set() can not accept characters outside of the alphabet.");
        } else if (count < 0) {
            throw new IllegalArgumentException("Can not accept negative values.");
        }
        /* Modify the total count by delta of the counter of the letter */
        totalCount += count - counters[getIndex(c)];
        counters[getIndex(c)] = count;
    }

    /**
     * returns the total of all counts in the LetterInventory
     */
    public int size() {
        return totalCount;
    }

    /**
     * returns whether the LetterInventory is empty or not
     */
    public boolean isEmpty() {
        return totalCount == 0;
    }

    public String toString() {
        String buffer = "[";
        for(int i = 0; i < ALPHABET_LENGTH; i++) {
            for(int j = 0; j < counters[i]; j++) {
                buffer += (char)('a' + i);
            }
        }
        return buffer + ']';
    }

    /**
     * returns a new LetterInventory which contains the sum of the counts of this and another LetterInventory
     */
    public LetterInventory add(LetterInventory other) {
        LetterInventory toReturn = new LetterInventory();
        for(int i = 0; i < ALPHABET_LENGTH; i++) {
            toReturn.counters[i] = counters[i] + other.counters[i];
        }
        toReturn.totalCount = totalCount + other.totalCount;
        return toReturn;
    }

    /**
     * returns a new LetterInventory which contains the difference of the counts of this and another LetterInventory
     */
    public LetterInventory subtract(LetterInventory other) {
        LetterInventory toReturn = new LetterInventory();
        for(int i = 0; i < ALPHABET_LENGTH; i++) {
            if(counters[i] - other.counters[i] < 0) {
                return null;
            } else {
                toReturn.counters[i] = counters[i] - other.counters[i];
            }
        }
        toReturn.totalCount = totalCount - other.totalCount;
        return toReturn;
    }

    /**
     * returns the percentage of a LetterInventory that is a certain letter as a decimal
     */
    public double getLetterPercentage(char c) {
        return get(c) / (double)totalCount;
    }

    /**
     * gets the appropriate index in the array for the passed character
     */
    private static int getIndex(char c) {
        c = Character.toLowerCase(c);
        return (int)c - (int)'a';
    }
}

