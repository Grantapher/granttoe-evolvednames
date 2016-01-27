public class Genome {
    public String name;
    public double rate;

    /**
     * Initializes a Genome with value ‘A’ and assigns the internal mutation rate a double between 0 and 1.
     */
    public Genome(double mutationRate) {
        name = "A";
        rate = mutationRate;
    }

    /**
     * A copy constructor that initializes a Genome with the same values as the input gene.
     */
    public Genome(Genome gene) {
        this.name = gene.name;
        this.rate = gene.rate;
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
     */
    public void crossover(Genome other) {

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
    public Integer fitness() {

    }

    /**
     * Display the Genome’s character string and fitness in an easy to read format.
     */
    @Override
    public String toString() {

    }
}

