import java.util.Random;

public class Genome implements Comparable<Genome> {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ -'";
    public StringBuilder name;
    public double rate;
    public Random random;
    private Integer fitness;

    /**
     * Initializes a Genome with value �A� and assigns the internal mutation rate a double between 0 and 1.
     */
    public Genome(double mutationRate) {
        name = new StringBuilder("A");
        rate = mutationRate;
        random = new Random();
        setFitness();
    }

    /**
     * A copy constructor that initializes a Genome with the same values as the input gene.
     */
    public Genome(Genome gene) {
        this.name = new StringBuilder(gene.name.toString());
        this.rate = gene.rate;
        random = new Random();
        this.fitness = gene.fitness;
    }

    /**
     * Mutates the string in this Genome using the following rules:
     * <ul>
     * <li>With mutationRate chance add a randomly selected character to a randomly selected position in the string.
     * </li>
     * <li>with mutationRate chance delete a single character from a randomly selected position of the string but do
     * this only if the string has length at least 2.</li>
     * <li>for each character in the string:
     * <ul><li>With mutationRate chance the character is replaced by a randomly selected character.</li></ul></li>
     * </ul>
     */
    public void mutate() {
        // Add character
        if (change()) {
            name.insert(random.nextInt(name.length() + 1), getNewChar());
        }

        // Delete character
        if (change() && name.length() > 0) {
            name.deleteCharAt(random.nextInt(name.length()));
        }

        // Change character
        if (change() && name.length() > 0) {
            int index = random.nextInt(name.length());
            name.replace(index, index + 1, String.valueOf(getNewChar()));
        }

        setFitness();
    }

    private boolean change() {
        return random.nextDouble() < rate;
    }

    private char getNewChar() {
        return ALPHABET.charAt(random.nextInt(ALPHABET.length()));
    }

    /**
     * Update the current Genome by crossing it over with other.
     * <ul>
     * <li>
     * Create the new list by following these steps for each index in the string starting at the first index:
     * <ul>
     * <li>
     * Randomly choose one of the two parent strings.
     * </li>
     * <li>
     * If the parent string has a character at this index (i.e. it is long enough) copy that character into the new list. Otherwise end the new list here.
     * </li>
     * </ul>
     * </li>
     * </ul>
     * <br>
     */
    public void crossover(Genome other) {
        StringBuilder nextName = new StringBuilder();
        for (int i = 0; ; i++) {
            boolean parentIsThis = random.nextBoolean();
            StringBuilder parent = parentIsThis ? name : other.name;

            if (i < parent.length()) {
                nextName.append(parent.charAt(i));
            } else {
                break;
            }
        }
        name = nextName;

        setFitness();
    }

    public Integer fitness() {
        return fitness;
    }

    /**
     * Returns the fitness of the Genome calculated using the following algorithm:
     * <ul>
     * <li>Let n be the length of the current string. Let m be the length of the target string.</li>
     * <li>Let l be the max( n, m ).</li>
     * <li>Let f be initialized to |m - n|.</li>
     * <li>For each character position add one to f if the character 1 ? i ? l in the current string is different from the character in the target string (or if one of the two characters does not exist). Otherwise add nothing to f .</li>
     * <li>Return f .</li>
     * </ul>
     */
    private void setFitness() {
        int n = name.length();
        int m = Population.target.length();

        //start special fitness
//        int[][] D = new int[n + 1][m + 1];
//
//        for (int i = 0; i <= n; i++) {
//            D[i][0] = i;
//        }
//
//        for (int i = 0; i <= m; i++) {
//            D[0][i] = i;
//        }
//
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= m; j++) {
//                int change = name.charAt(i - 1) == Population.target.charAt(j - 1) ? 0 : 1;
//                D[i][j] = Math.min(D[i - 1][j] + 1, Math.min(D[i][j - 1] + 1, D[i - 1][j - 1] + change));
//            }
//        }
//
//        fitness = D[n][m];
//
        //start normal fitness
        int l = Math.max(n, m);

        // Initialize f to the difference in length
        int f = Math.abs(m - n);

        // Add a point to f for every difference in characters
        for (int i = 0; i < l; i++) {
            if (i >= Population.target.length()
                    || i >= name.length()
                    || Population.target.charAt(i) != name.charAt(i)) {
                f++;
            }
        }

        fitness = f;
    }

    /**
     * Display the Genome's character string and fitness in an easy to read format.
     */
    @Override
    public String toString() {
        return String.format("(\"%s\", %d)", name.toString(), fitness());
    }

    @Override
    public int compareTo(Genome that) {
        return Integer.compare(this.fitness(), that.fitness());
    }
}

