public class Main {

    /**
     * Instantiate a population and call day() until the target string is part of the population.
     * <ul>
     * <li>The target string has fitness zero so the loop should repeat until the most fit genome has fitness zero.</li>
     * <li>After each execution of day() output the most fit genome.</li>
     * <li>To measure performance output the number of generations (i.e times day() is called) and the execution time.</li>
     * </ul>
     */
    public static void main(String... args) {
        Population population = new Population(100, .05d);
        while (0 != population.mostFit.fitness()) {
            population.day();
            System.out.println(population);
        }
    }

    /**
     * Tests the Genome class.
     */
    public void testGenome() {

    }

    /**
     * Tests the Population class.
     */
    public void testPopulation() {

    }
}
