import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Population {
    public static final String target = "Grant Alexander Toepfer";
    public Genome mostFit;
    public List<Genome> genes;
    public int numGenomes;

    /**
     * Initializes a Population with a number of default genomes.
     */
    public Population(Integer numGenomes, Double mutationRate) {
        this.numGenomes = numGenomes;
        genes = new ArrayList<>(numGenomes);
        for (int i = 0; i < numGenomes; i++) {
            genes.add(new Genome(mutationRate));
        }
        mostFit = genes.get(0);
    }

    /**
     * Called every breeding cycle and carries out the following steps:
     * <ul>
     * <li>update mostFit variable to the most fit Genome in the population. (Remember this is the genome with the lowest fitness!)</li>
     * <li>delete the least fit half of the population.</li>
     * <li>
     * create new genomes from the remaining population until the number of genomes is restored by doing either of the following with equal chance:
     * <ul>
     * <li>pick a remaining genome at random and clone it (with the copy constructor) and mutate the clone.</li>
     * <li>pick a remaining genome at random and clone it and then crossover the clone with another remaining genome selected at random and then mutate the result.</li>
     * </ul>
     * </li>
     * </ul>
     */
    public void day() {
        //kill weak half
        genes = genes.stream()
                .sorted((g1, g2) -> Integer.compare(g1.fitness(), g2.fitness()))
                .limit(numGenomes >> 1)
                .collect(Collectors.toList());

        //create new genes
        Random random = new Random();
        random.ints(numGenomes - genes.size(), 0, genes.size())
                .mapToObj(genes::get)
                .map(Genome::new)
                .peek(g -> {
                    if (random.nextBoolean()) g.crossover(genes.get(random.nextInt(genes.size())));
                })
                .peek(Genome::mutate)
                .forEach(genes::add);

        //get most fit
        //shuffle to add some variation, sorting the list is stable, so the top 50 will usually never change
        Collections.shuffle(genes);
        genes = genes.stream()
                .sorted((g1, g2) -> Integer.compare(g1.fitness(), g2.fitness()))
                .collect(Collectors.toList());

        mostFit = genes.stream()
                .reduce((g1, g2) -> g1.fitness() < g2.fitness() ? g1 : g2).get();
    }

    /**
     * Display the entire population.
     * Display the most fit individual in the population.
     */
    @Override
    public String toString() {
        return mostFit.toString();
    }
}
