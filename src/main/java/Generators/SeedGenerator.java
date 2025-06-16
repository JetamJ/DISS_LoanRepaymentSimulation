package Generators;

import java.util.Random;

public class SeedGenerator {
    Random random;

    public SeedGenerator() {
        random = new Random();
    }

    public SeedGenerator(int seed) {
        random = new Random(seed);
    }

    public SeedGenerator(Random generator) {
        random = generator;
    }

    public int getSeed() {
        return random.nextInt();
    }
}
