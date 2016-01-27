import java.util.ArrayList;
import java.util.List;

public class Population {
    public static final String target = "Grant Alexander Toepfer";
    public Genome mostFit;
    public List<Genome> genes;

    /**
     * Initializes a Population with a number of default genomes.
     */
    public Population(Integer numGenomes, Double mutationRate) {
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

    }

    /**
     * Display the entire population.
     * Display the most fit individual in the population.
     */
    @Override
    public String toString() {

    }
}
