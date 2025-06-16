package Entity;

public class Interest {
    double lowerValue;
    double higherValue;
    double probability;

    public Interest(double lowerValue, double higherValue, double probability) {
        this.lowerValue = lowerValue;
        this.higherValue = higherValue;
        this.probability = probability;
    }

    public double getLowerValue() {
        return lowerValue;
    }

    public void setLowerValue(double lowerValue) {
        this.lowerValue = lowerValue;
    }

    public double getHigherValue() {
        return higherValue;
    }

    public void setHigherValue(double higherValue) {
        this.higherValue = higherValue;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }
}
