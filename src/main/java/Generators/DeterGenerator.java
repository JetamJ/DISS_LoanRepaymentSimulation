package Generators;

import Entity.Interest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeterGenerator extends Generator {

    double interest;
    public DeterGenerator(double interest) {
        this.interest = interest;
    }

    public double nextVal() {
        return this.interest;
    }
}
