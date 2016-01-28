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
        long startTime = System.nanoTime();
        Population population = new Population(100, .25d);
        int generation = 0;
        while (0 != population.mostFit.fitness()) {
            population.day();
            System.out.println(population.mostFit);
            generation++;
        }
        System.out.println("Generations: " + generation);
        long endTime = System.nanoTime();
        System.out.println("Running Time: " + (endTime - startTime) / 1000000 + " milliseconds");
    }

    /**
     * Tests the Genome class.
     */
    public static void testGenome() {
        Genome genome = new Genome(.05);
        for (int i = 0; i < 100; i++) {
            genome.mutate();
            System.out.println(genome);
        }
    }

    /**
     * Tests the Population class.
     */
    public static void testPopulation() {
        Population population = new Population(100, .25d);
        for (int i = 0; ; i++) {
            population.day();
            System.out.println(population);
        }
    }
}
