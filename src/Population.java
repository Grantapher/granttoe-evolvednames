import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Population {
    public static final String target = "GRANT ALEXANDER TOEPFER";
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
                .sorted((g1, g2) -> Integer.compare(g1.fitness(), g2.fitness())) //sort by fitness ascending
                .limit(numGenomes >> 1)                                          //kill the later half
                .collect(Collectors.toList());                                   //put remaining genes back in the list

        //create new genes
        new Random().ints(numGenomes - genes.size(), 0, genes.size())   //create stream of indexes to existing genomes with the number of genomes we killed
                .mapToObj(genes::get)                                   //get the genomes from those indexes
                .map(Genome::new)                                       //change those genomes to new clones
                .peek(this::crossoverHalf)                              //crossoverHalf half of the genes
                .peek(Genome::mutate)                                   //mutate the genomes
                .forEach(genes::add);                                   //add the new genomes to existing ones

        //get most fit
        mostFit = genes.stream().reduce((g1, g2) -> g1.fitness() < g2.fitness() ? g1 : g2).get();
    }

    private void crossoverHalf(Genome g) {
        Random random = new Random();
        if (random.nextBoolean()) {
            g.crossover(genes.get(random.nextInt(genes.size())));
        }
    }

    /**
     * Display the entire population.
     * Display the most fit individual in the population.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Most fit: ");
        sb.append(mostFit);
        sb.append('\n');
        for (Genome g : genes) {
            sb.append(g).append('\n');
        }
        sb.append('\n');
        return sb.toString();
    }
}
