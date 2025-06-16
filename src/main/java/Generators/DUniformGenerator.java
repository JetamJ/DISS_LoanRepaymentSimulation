package Generators;

import java.util.Random;

public class DUniformGenerator extends Generator {
    int min;
    int max;
    Random gen;
    public DUniformGenerator(SeedGenerator seedGenerator, int min, int max) {
        this.gen = new Random(seedGenerator.getSeed());
        this.min = min;
        this.max = max;
    }

    public double nextVal() {
        return this.gen.nextInt(max - min + 1) + min;
    }
}
