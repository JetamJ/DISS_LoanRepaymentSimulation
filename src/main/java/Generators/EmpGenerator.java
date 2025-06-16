package Generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Entity.Interest;
public class EmpGenerator extends Generator {
    SeedGenerator seedGenerator;
    ArrayList<Interest> interests;
    ArrayList<Random> generators = new ArrayList<>();
    Random gen;
    public EmpGenerator(SeedGenerator seedGenerator, ArrayList<Interest> interests) {
        this.seedGenerator = seedGenerator;
        this.gen = new Random(seedGenerator.getSeed());
        this.interests = interests;
        for (int i = 0 ; i < interests.size(); i++) {
            generators.add(new Random(this.seedGenerator.getSeed()));
        }
    }

    public double nextVal() {
        double limit = 0;
        double p = gen.nextDouble();
        for (int i = 0 ; i < interests.size(); i++) {
            limit += interests.get(i).getProbability();
            if (p < limit) {
                return generators.get(i).nextDouble()
                        * (interests.get(i).getHigherValue() - interests.get(i).getLowerValue())
                        + interests.get(i).getLowerValue();
            }
        }
        return -1;
    }
}
