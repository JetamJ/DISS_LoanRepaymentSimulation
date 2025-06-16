package Generators;

import java.util.Random;

public class CUniformGenerator extends Generator {

    double lowerValue;
    double higherValue;
    Random gen;
    public CUniformGenerator(SeedGenerator seedGenerator, double lowerValue, double higherValue) {
        this.gen = new Random(seedGenerator.getSeed());
        this.lowerValue = lowerValue;
        this.higherValue = higherValue;
    }

    public double nextVal() {
        return this.gen.nextDouble() * (this.higherValue - this.lowerValue) + this.lowerValue;
    }
}
